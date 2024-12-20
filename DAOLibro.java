package Biblioteca;

public class DAOLibro extends DAOGenerico<Libro> {

    public DAOLibro() {
        super(Libro.class);
    }

    public Libro buscarTitulo(String isbn) {
        return em.find(Libro.class, isbn);
    }
}
