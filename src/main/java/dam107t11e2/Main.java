/*
 Realiza un programa que muestre 
 el título de todos los libros presentes ‘librería.xml’. 
 */
package dam107t11e2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {

    public static void main(String[] args) {
        File file = new File("ficheros" + File.separator + "libreria.xml");
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            Document doc = dB.parse(new InputSource(isr));

            NodeList listaLibros = doc.getElementsByTagName("titulo");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodo = listaLibros.item(i);
                System.out.println("Titulo: " + nodo.getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
