/*
 Realiza un programa que muestre el título de todos los libros presentes en ‘librería.xml’ con su
 precio de aquellos libros que cuesten menos de 10 euros
 */
package dam107t11e5;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("ficheros" + File.separator + "libreria.xml");
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            Document doc = dB.parse(new InputSource(isr));
            System.out.println("------------------------");
            System.out.println("GANGAS DE MENOS DE 10€");
            System.out.println("------------------------");
            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element elemento = (Element)listaLibros.item(i);
                String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
                String precio = elemento.getElementsByTagName("precio").item(0).getTextContent();
                if(Double.valueOf(precio)<10)
                    System.out.println("Titulo: " + titulo + ". Precio: "+ precio + "€");
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
