/*package Negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class VentasHelper {
    private EntityManager em;

    public VentasHelper(EntityManager em){
        this.em = em;
    }

    public VentasHelper(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ventas");
        em = emf.createEntityManager();
    }

    public boolean AgregarVenta(VentaEntity venta, List<ProductoEntity> productos, VendedorEntity vendedor, SucursalEntity sucursal, Double total, Double descuento, Double totalConDescuento){
        EntityTransaction tx = null;
        ProductoEntity p = new ProductoEntity();
        PrecioproductovendidoEntity prec = new PrecioproductovendidoEntity();
        try{
            venta = new VentaEntity();
            for (ProductoEntity producto:productos) {
                p = producto;
                venta.setProductoByIdProducto(producto);
                prec.setNombre(p.getNombre());
                prec.setPrecio(p.getPrecio());
                venta.setPrecioproductovendidoByIdPrecioProductoVendido(prec);

            }
            venta.setVendedorByIdVendedor(vendedor);
            venta.setSucursalByIdSucursal(sucursal);
            venta.setTotal(total);
            venta.setDescuento(descuento);
            venta.setTotalConDescuento(totalConDescuento);

            tx = em.getTransaction();
            tx.begin();
            em.persist(venta);
            tx.commit();
            return true;

        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }


    public boolean EliminarVenta(VentaEntity producto, Integer id){
        EntityTransaction tx = null;
        try{
            VentaEntity venta = (VentaEntity) em.createNamedQuery("Venta.BuscarPorId").setParameter(
                    "idVenta", id).getSingleResult();

            tx = em.getTransaction();
            tx.begin();
            em.remove(venta);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public List<VentaEntity> BuscarTodas(){
        try {
            List<VentaEntity> ventas = em.createNamedQuery("Venta.Todas").getResultList();
            return ventas;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public VentaEntity BuscarPorId(Integer id){
        try {
            VentaEntity venta = (VentaEntity) em.createNamedQuery("Venta.BuscarPorId").setParameter(
                    "idVenta", id).getSingleResult();
            em.refresh(venta);
            return venta;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}*/
