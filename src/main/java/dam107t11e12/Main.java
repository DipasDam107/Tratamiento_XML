/*
Modifica el archivo XML ‘librería.xml’ añadiendo a cada libro una etiqueta <editorial>. Al
usuario se le dirá el título del libro y él introducirá el nombre de la editorial. Guarda el archivo
con el nombre ‘libreriaConEditorial.xml’
 */
package dam107t11e12;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {
    static Scanner teclado;
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        File file = new File("ficheros" + File.separator + "libreria.xml");
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            Document doc = dB.parse(new InputSource(isr));

            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element e = (Element) listaLibros.item(i);
                System.out.println("Dime editorial para el libro " + e.getElementsByTagName("titulo").item(0).getTextContent() + ":");
                String editorial = teclado.nextLine();
                Element hijo = doc.createElement("editorial");
                hijo.appendChild(doc.createTextNode(editorial));
                e.appendChild(hijo);
                
            }

            File ficheroSalida = new File("ficheros" + File.separator + "libreriaConEditorial.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(ficheroSalida));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
