/*package Negocio;

import javax.persistence.*;
import java.util.List;

public class ProductosHelper {

    private EntityManager em;
    private ProductoEntity producto;

    public ProductosHelper(EntityManager em){
        this.em = em;
    }

    public ProductosHelper(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("farmaciafei");
        em = emf.createEntityManager();
    }

    public boolean AgregarProducto(ProductoEntity producto){
        EntityTransaction tx = null;
        System.out.println("Hola");
        try{
            producto = new ProductoEntity();
            this.producto.setNombre(producto.getNombre());
            this.producto.setMarca(producto.getMarca());
            this.producto.setCantidad(producto.getCantidad());
            this.producto.setPrecio(producto.getPrecio());

            tx = em.getTransaction();
            tx.begin();
            em.persist(producto);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean ModificarProducto(ProductoEntity productos){
        EntityTransaction tx = null;
        try{
            producto = new ProductoEntity();
            producto.setNombre(productos.getNombre());
            producto.setMarca(productos.getMarca());
            producto.setCantidad(productos.getCantidad());
            producto.setPrecio(productos.getPrecio());

            tx = em.getTransaction();
            tx.begin();
            em.persist(producto);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean EliminarProducto(ProductoEntity producto, String nombre){
        EntityTransaction tx = null;
        try{
            producto = (ProductoEntity) em.createNamedQuery("Producto.BuscarPorNombre")
                    .setParameter("nombre", "%"+nombre+"%").getSingleResult();

            tx = em.getTransaction();
            tx.begin();
            em.remove(producto);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public List<ProductoEntity> BuscarTodos(){
        try {
            List<ProductoEntity> productos = em.createNamedQuery("Producto.Todos").getResultList();
            return productos;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ProductoEntity BuscarPorId(Integer id){
        try {
            ProductoEntity producto = (ProductoEntity) em.createNamedQuery("Producto.BuscarPorId").setParameter(
                    "idproducto", id).getSingleResult();
            em.refresh(producto);
            return producto;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}*/
