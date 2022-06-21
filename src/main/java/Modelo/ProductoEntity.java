 package Modelo;
/*
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "producto", schema = "farmaciafei", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Producto.Todos", query = "select a from ProductoEntity a order by a.nombre"),
        @NamedQuery(name = "Producto.BuscarPorId", query = "select a from ProductoEntity a where a.idproducto = :idproducto"),
        @NamedQuery(name = "Producto.BuscarPorNombre", query = "select a from ProductoEntity a where a.nombre like :nombre")
})
public class ProductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idproducto")
    private int idproducto;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "marca")
    private String marca;
    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic
    @Column(name = "precio")
    private Double precio;

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEntity that = (ProductoEntity) o;
        return idproducto == that.idproducto && Objects.equals(nombre, that.nombre) && Objects.equals(marca, that.marca) && Objects.equals(cantidad, that.cantidad) && Objects.equals(precio, that.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproducto, nombre, marca, cantidad, precio);
    }
}*/