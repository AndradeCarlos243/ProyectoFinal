package Modelo;

import javax.persistence.*;
import java.util.Objects;
/*
@Entity
@Table(name = "venta", schema = "farmaciafei", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Venta.Todas", query = "select a from VentaEntity a order by a.idVenta"),
        @NamedQuery(name = "Venta.BuscarPorId", query = "select a from VentaEntity a where a.idVenta = :idVenta")
})
public class VentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idVenta")
    private int idVenta;
    @Basic
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic
    @Column(name = "idPrecioProductoVendido")
    private Integer idPrecioProductoVendido;
    @Basic
    @Column(name = "idVendedor")
    private Integer idVendedor;
    @Basic
    @Column(name = "idSucursal")
    private Integer idSucursal;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "descuento")
    private Double descuento;
    @Basic
    @Column(name = "totalConDescuento")
    private Double totalConDescuento;
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idproducto")
    private Modelo.ProductoEntity productoByIdProducto;
    @ManyToOne
    @JoinColumn(name = "idPrecioProductoVendido", referencedColumnName = "idPrecioProductoVendido")
    private Modelo.PrecioproductovendidoEntity precioproductovendidoByIdPrecioProductoVendido;
    @ManyToOne
    @JoinColumn(name = "idVendedor", referencedColumnName = "idvendedor")
    private Modelo.VendedorEntity vendedorByIdVendedor;
    @ManyToOne
    @JoinColumn(name = "idSucursal", referencedColumnName = "idSucursal")
    private Modelo.SucursalEntity sucursalByIdSucursal;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdPrecioProductoVendido() {
        return idPrecioProductoVendido;
    }

    public void setIdPrecioProductoVendido(Integer idPrecioProductoVendido) {
        this.idPrecioProductoVendido = idPrecioProductoVendido;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotalConDescuento() {
        return totalConDescuento;
    }

    public void setTotalConDescuento(Double totalConDescuento) {
        this.totalConDescuento = totalConDescuento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaEntity that = (VentaEntity) o;
        return idVenta == that.idVenta && Objects.equals(idProducto, that.idProducto) && Objects.equals(idPrecioProductoVendido, that.idPrecioProductoVendido) && Objects.equals(idVendedor, that.idVendedor) && Objects.equals(idSucursal, that.idSucursal) && Objects.equals(total, that.total) && Objects.equals(descuento, that.descuento) && Objects.equals(totalConDescuento, that.totalConDescuento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, idProducto, idPrecioProductoVendido, idVendedor, idSucursal, total, descuento, totalConDescuento);
    }

    public Modelo.ProductoEntity getProductoByIdProducto() {
        return productoByIdProducto;
    }

    public void setProductoByIdProducto(Modelo.ProductoEntity productoByIdProducto) {
        this.productoByIdProducto = productoByIdProducto;
    }

    public Modelo.PrecioproductovendidoEntity getPrecioproductovendidoByIdPrecioProductoVendido() {
        return precioproductovendidoByIdPrecioProductoVendido;
    }

    public void setPrecioproductovendidoByIdPrecioProductoVendido(Modelo.PrecioproductovendidoEntity precioproductovendidoByIdPrecioProductoVendido) {
        this.precioproductovendidoByIdPrecioProductoVendido = precioproductovendidoByIdPrecioProductoVendido;
    }

    public Modelo.VendedorEntity getVendedorByIdVendedor() {
        return vendedorByIdVendedor;
    }

    public void setVendedorByIdVendedor(Modelo.VendedorEntity vendedorByIdVendedor) {
        this.vendedorByIdVendedor = vendedorByIdVendedor;
    }

    public Modelo.SucursalEntity getSucursalByIdSucursal() {
        return sucursalByIdSucursal;
    }

    public void setSucursalByIdSucursal(Modelo.SucursalEntity sucursalByIdSucursal) {
        this.sucursalByIdSucursal = sucursalByIdSucursal;
    }
}*/
