package Biblioteca;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DAOUsuario dao = new DAOUsuario();
        Usuario usuario = new Usuario("12345678D", "juanito.hola@gmail.com", "Juanito", "hola12345", "normal");
        //dao.add(usuario);
        //Usuario usuarioEliminar= dao.getbyId(4);
        //dao.delete(usuarioEliminar);
        DAOLibro daoLibro= new DAOLibro();
        Libro libro1= daoLibro.buscarTitulo("9781111111111");
        List<Ejemplar> ejemplares=  libro1.getEjemplares();
        DAOEjemplar daoEjemplar = new DAOEjemplar();
        System.out.println(daoEjemplar.calcularStock(ejemplares));
    }
}
