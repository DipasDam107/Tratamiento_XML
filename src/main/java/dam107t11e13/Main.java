/*
 Modifica el archivo XML ‘librería.xml’ eliminando las etiquetas ‘isbn’, ‘dimensiones’, ‘tematica’
y ‘precio’. Guárdalo con el nombre ‘libreriaResumen.xml 
 */
package dam107t11e13;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
    public static void main(String[] args) {
        File file = new File("ficheros" + File.separator + "libreria.xml");
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            Document doc = dB.parse(new InputSource(isr));

            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element e = (Element) listaLibros.item(i);
                borrar(e,"isbn");
                borrar(e,"dimensiones");
                borrar(e,"tematica");
                borrar(e,"precio");
            }

            File ficheroSalida = new File("ficheros" + File.separator + "libreriaResumen.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(ficheroSalida));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void borrar(Element e, String elemento){
        if(e.getElementsByTagName(elemento).item(0) != null){
            Element hijo = (Element) e.getElementsByTagName(elemento).item(0);
            e.removeChild(hijo);
            
        }
    }
}
