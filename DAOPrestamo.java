package Biblioteca;

import java.time.LocalDate;
import java.util.List;

public class DAOPrestamo extends DAOGenerico<Prestamo>{
    public DAOPrestamo(){
        super(Prestamo.class);
    }

    public LocalDate calcularFechaLimite(Prestamo p){
        return p.getFechaInicio().plusDays(15);
    }

    public LocalDate registrarDevolucion(Prestamo p){
        Ejemplar devolucion= p.getEjemplar();
        LocalDate fechaDevuelto= LocalDate.now();
        devolucion.setEstado("Disponible");
        return fechaDevuelto;
    }

    public Prestamo realizarPrestamo(Ejemplar e, Usuario u, LocalDate fechaInicio){
        boolean validacion= true;
        String mensaje= null;
        if(u.getPrestamos().size() > 3){
            validacion= false;
            mensaje= "El usuario tiene mas de 3 prestamos activos";
        }
        if(!(e.getEstado().equals("Disponible"))){
            validacion= false;
            mensaje= "El ejemplar no esta disponible";
        }
        if(u.getPenalizacionHasta() != null){
            validacion= false;
            mensaje= "La penalizacion esta activa";
        }
        if(validacion){
            Prestamo p= new Prestamo(e,fechaInicio,u);
            return p;
        }else{
            System.out.println(mensaje);
        }
        return null;
    }

    public void establecerPenalizacion(Usuario u){
        int dias=0;
        for(Prestamo prestamo : u.getPrestamos()){
            if(registrarDevolucion(prestamo).isAfter(prestamo.getFechaDevolucion())){
                dias+= 15;
            }
        }
        if(dias>0){
            u.setPenalizacionHasta(LocalDate.now().plusDays(dias));
        }
    }



}
