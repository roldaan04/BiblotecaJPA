package Biblioteca;

public class Main {
    public static void main(String[] args) {
        DAOUsuario dao = new DAOUsuario();
        Usuario usuario = new Usuario("12345678D", "juanito.hola@gmail.com", "Juanito", "hola12345", null, "normal");
        //dao.add(usuario);
        //Usuario usuarioEliminar= dao.getbyId(4);
        //dao.delete(usuarioEliminar);
    }
}
