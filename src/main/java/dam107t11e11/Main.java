/*
 Modifica el archivo XML ‘librería.xml’ pasando el alto y el ancho a pulgadas. Guárdalo con el
nombre ‘libreriaPulgadas.xml’ 
 */
package dam107t11e11;

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

            NodeList listaLibros = doc.getElementsByTagName("dimensiones");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element e = (Element) listaLibros.item(i);
                if (e.hasAttribute("alto") && e.hasAttribute("ancho")) {
                    e.setAttribute("alto", String.valueOf(Double.valueOf(e.getAttribute("alto")) * 0.39));
                    e.setAttribute("ancho", String.valueOf(Double.valueOf(e.getAttribute("ancho")) * 0.39));
                }
            }

            File ficheroSalida = new File("ficheros" + File.separator + "libreriaPulgadas.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(ficheroSalida));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
