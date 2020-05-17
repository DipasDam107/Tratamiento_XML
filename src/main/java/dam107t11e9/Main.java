/*
  Haz una función llamada buscarAtributosEnHijos () a la que se le pase como
parámetro un Element y una cadena. La función buscará si ese Element tiene algún hijo con
un atributo con nombre igual a la cadena pasada. En caso afirmativo devolverá el valor de ese
atributo y en caso negativo devolverá la cadena vacía “ ”. Por ejemplo, si le pasásemos el primer
libro del archivo ‘librería.xml’ (es el que tiene por título ‘Follas novas’), y el atributo ‘paginas’
devolvería “496”. La explicación es que ese Element, entre todos sus hijos, tiene uno con un
atributo llamado ‘paginas’ con valor 496

Luego haz un programa que muestre todos los libros de ‘librería.xml’ con el número de páginas,
que obtenga ese número de páginas con la función realizada. 
 */
package dam107t11e9;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {
    public static void main(String[] args) {
        boolean encontrado=false;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime titulo de libro: ");
        String titulo = teclado.nextLine();
        
        File file = new File("ficheros" + File.separator + "libreria.xml");
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = factory.newDocumentBuilder();
            Document doc = dB.parse(new InputSource(isr));

            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element libro = (Element)listaLibros.item(i);
                if(libro.getElementsByTagName("titulo").item(0).getTextContent().equals(titulo)){
                    encontrado=true;
                    System.out.println("Que atributo estas buscando: ");
                    String att = teclado.nextLine();
                    String resultado=buscarAttsEnHijos(libro,att);
                    if(resultado!=null) System.out.println("El valor del atributo es: " + resultado);
                    else System.out.println("No se ha encontrado el atributo");
                }
            } 
            
            if(encontrado==false)
                System.out.println("No se ha encontrado el libro");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static String buscarAttsEnHijos(Element e, String nomAtt){
        boolean encontrado=false;
        NodeList nodos = e.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            if(nodos.item(i) instanceof Element){
                Element elemento = (Element)nodos.item(i);
                if(elemento.hasAttributes() && elemento.hasAttribute(nomAtt)){
                    return elemento.getAttribute(nomAtt);
                }   
            }
        }
        return null;
    }
}
