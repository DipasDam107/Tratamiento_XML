/*
Realiza un programa que muestre el nombre de todos los libros con su alto, ancho y número
de páginas. (Algunos puede que no tengan toda o parte de esa información, mostrar una
interrogación en sus valores, por ejemplo: “El perfume  Dimensiones ? cm x ? cm. ? páginas). 
 */
package dam107t11e7;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
                String ancho = "?";
                String alto = "?";
                Element elemento = (Element)listaLibros.item(i);
                String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
                Element dimensiones = (Element)elemento.getElementsByTagName("dimensiones").item(0);
                String paginas = dimensiones.getAttribute("paginas");
                if(dimensiones.hasAttribute("ancho"))
                    ancho=dimensiones.getAttribute("ancho");
                if(dimensiones.hasAttribute("alto"))
                    alto=dimensiones.getAttribute("alto");
                
                System.out.printf("Titulo: %s -> Dimesiones %s cm x %s cm. %s paginas\n", titulo, ancho, alto, paginas);
                    
                
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
