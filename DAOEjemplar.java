package Biblioteca;

import java.util.List;

public class DAOEjemplar extends DAOGenerico<Ejemplar>{

    public DAOEjemplar() {
        super(Ejemplar.class);
    }

    public int calcularStock(List<Ejemplar> listaEjemplares){
        int contador = 0;
        for(Ejemplar e: listaEjemplares){
            if(e.getEstado().equals("Disponible")){
                contador++;
            }
        }
        return contador;
    }

    public void updateEjemplar(Ejemplar e){
        e.setEstado("Prestado");
        update(e);
    }







}
