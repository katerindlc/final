package inventarioo.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import inventarioo.model.entities.Producto;

/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerProducto() {
        // TODO Auto-generated constructor stub
    }
    public List<Producto> findAllProducto() {
		String consulta = "SELECT p FROM Producto p";
		Query q = em.createQuery(consulta, Producto.class);
		return q.getResultList();
	}
    public Producto findUsuarioByProducto(int id) {
		return em.find(Producto.class, id);
	}
    public void insertarProducto(Producto producto) throws Exception {
		em.persist(producto);
		
		System.out.println("Usuario insertado.........");
	}
    public String eliminarProducto(int id) {
    	Producto p=em.find(Producto.class, id);
    	em.remove(p);
    	return "se ha eliminado el usuario ok.";
    }
    public void actualizarProducto(Producto producto) throws Exception {
		Producto prod = findUsuarioByProducto(producto.getIdProducto());
		if (prod == null)
			throw new Exception("No existe el Usuario con el Id especificada");
		prod.setCodigo(producto.getCodigo());
		prod.setNombre(producto.getNombre());
		prod.setStock(producto.getStock());
		prod.setPreciocompra(producto.getPreciocompra());
		prod.setPrecioventa(producto.getPrecioventa());
		prod.setDescuento(producto.getDescuento());
		prod.setObservaciones(producto.getObservaciones());
		em.merge(prod);
	}

}
