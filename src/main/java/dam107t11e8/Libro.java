
package dam107t11e8;

public class Libro implements Comparable{
    String isbn;
    String titulo;
    Double precio;
    String[] autores;

    public Libro(String isbn, String nombre, Double precio, String[] autores) {
        this.isbn = isbn;
        this.titulo = nombre;
        this.precio = precio;
        this.autores=autores;
    }

    @Override
    public String toString() {
        String aut = "";
        for (int i = 0; i < autores.length; i++) {
            aut += autores[i] + ", ";
        }
        aut=aut.substring(0, aut.length()-2);
        return "isbn=" + isbn + ", titulo=" + titulo + ", precio=" + precio + ", autores=" + aut;
    }

    @Override
    public int compareTo(Object o) {
        Libro l = (Libro)o;
        return this.getTitulo().compareTo(l.getTitulo());
    }

    public String getTitulo() {
        return titulo;
    }

    
    
    
}
