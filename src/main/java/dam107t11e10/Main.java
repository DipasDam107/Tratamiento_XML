/*
 Modifica el archivo XML ‘librería.xml’ pasando el precio a dólares (1 dólar= 0,91eur). Guárdalo
 con el nombre ‘libreriaDolar.xml’ 
 */
package dam107t11e10;

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

            NodeList listaLibros = doc.getElementsByTagName("precio");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element e = (Element) listaLibros.item(i);
                double dolares = Double.valueOf(e.getTextContent()) * 1.08;
                e.setTextContent(String.valueOf(dolares));
            }

            File ficheroSalida = new File("ficheros" + File.separator + "libreriaDolar.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(ficheroSalida));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
