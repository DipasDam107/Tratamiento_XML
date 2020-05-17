/*
 Diseña una clase llamada Libro, que sea capaz de mantener para un libro su ISBN, nombre,
precio y autores. Crea un programa que cargue en un ArrayList de ‘Libro’ la información
correspondiente que viene en el archivo ‘libreria.xml’. A continuación, ordenará el ArrayList por
título de libro y mostrará por pantalla el contenido completo de ese ArrayList. Pista:
Implementar en la clase Libro el método toString() para facilitar el programa.

 */
package dam107t11e8;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {
    static ArrayList <Libro> libros;
    public static void main(String[] args) {
        libros=new ArrayList<>();
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
                String isbn = elemento.getElementsByTagName("isbn").item(0).getTextContent();
                Double precio = Double.valueOf(elemento.getElementsByTagName("precio").item(0).getTextContent());
                NodeList autores = elemento.getElementsByTagName("autor");
                String[] authors = new String[autores.getLength()];
                for (int y = 0; y < autores.getLength(); y++) 
                    authors[y] = autores.item(y).getTextContent();  
                
                libros.add(new Libro(isbn, titulo, precio, authors));
                
            }
            
            Collections.sort(libros);
            
            for(Libro lib : libros){
                System.out.println(lib.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
