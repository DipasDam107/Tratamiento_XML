/*
 Realiza un programa que muestre el nombre del documento raíz del archivo ‘librería.xml’. 
 */
package dam107t11e1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
            
            Element raiz = doc.getDocumentElement();
            System.out.println ("Raíz: " + doc.getDocumentElement().getNodeName());

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
