package Biblioteca;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    private Libro isbn;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prestamo> prestamos = new ArrayList<>();

        public Ejemplar() {
        }

        public Ejemplar(Biblioteca.Libro isbn, String estado) {
            this.isbn = isbn;
            this.estado = estado;
        }




    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Biblioteca.Libro getIsbn() {
            return isbn;
        }

        public void setIsbn(Biblioteca.Libro isbn) {
            this.isbn = isbn;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", prestamos=" + prestamos +
                '}';
    }
}