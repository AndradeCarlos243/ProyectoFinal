package Modelo;
/*
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vendedor", schema = "farmaciafei", catalog = "")
public class VendedorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idvendedor")
    private int idvendedor;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "puesto")
    private String puesto;
    @Basic
    @Column(name = "correo")
    private String correo;

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendedorEntity that = (VendedorEntity) o;
        return idvendedor == that.idvendedor && Objects.equals(nombre, that.nombre) && Objects.equals(puesto, that.puesto) && Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvendedor, nombre, puesto, correo);
    }
}
*/