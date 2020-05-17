/*
 Crea un archivo XML desde cero que solo contenga un elemento raíz llamado <agenda> y
elementos hijo de tipo texto como los que muestra la siguiente figura. No hace falta que el
usuario introduzca los valores, puedes meterlos en el código “a mano”.
 */
package dam107t11e14;

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

/**
 *
 * @author User
 */
public class Main {
    static Element contacto, raiz;
    static Document doc;
    public static void main(String[] args) {
        File file = new File("ficheros" + File.separator + "agenda.xml");
        try{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            doc = dB.newDocument();
            raiz = doc.createElement("agenda");
            doc.appendChild(raiz);
            
            introContacto("Maria Alvarez");
            introContacto("Juan Valdes");
            introContacto("Daniel Dipas");
            introContacto("Manuel Geronimo");

            File ficheroSalida = new File("ficheros" + File.separator + "agenda.xml");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(ficheroSalida));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void introContacto(String con){
            contacto=doc.createElement("contacto");
            contacto.appendChild(doc.createTextNode(con));
            raiz.appendChild(contacto);
    }
}
