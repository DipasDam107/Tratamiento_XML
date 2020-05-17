/*
 Realiza un programa que muestre el nombre de todos los libros y su autor o autores. 
 */
package dam107t11e6;

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
            
            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element elemento = (Element)listaLibros.item(i);
                String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("------------------------");
                System.out.println(titulo.toUpperCase());
                System.out.println("------------------------");
                NodeList autores = elemento.getElementsByTagName("autor");
                if(autores.getLength()>1)
                        System.out.println("Autores: ");
                    else
                        System.out.println("Autor: ");
                for (int y = 0; y < autores.getLength(); y++) {
                     String p = autores.item(y).getTextContent();            
                     System.out.println(p);
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
