package controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import inventarioo.model.entities.Producto;
import inventarioo.model.managers.ManagerProducto;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public BeanProducto() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	private ManagerProducto managerProducto;
	private List<Producto> listaProducto;

	private Producto producto;
	private Producto productoseleccionado;
    @PostConstruct
	public void inicializar() {
		listaProducto = managerProducto.findAllProducto();
		producto = new Producto();
	}
    
public String actionListenerInsertarProducto() {
		
		try {
			
			
			managerProducto.insertarProducto(producto);
			
			listaProducto = managerProducto.findAllProducto();

			producto = new Producto();			
			
			JSFUtil.createMensajeInfo("Datos Ingresados");
			
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
			JSFUtil.createMensajeInfo("Usuario incorrecto");
		}
		return "index.xhtml";
		
	}
	public void actionListenerEliminarProducto(int Id) {
		managerProducto.eliminarProducto(Id);
		listaProducto = managerProducto.findAllProducto();
		JSFUtil.createMensajeInfo("Producto Eliminado");
	}

	public void actionListenerSelecionarProducto(Producto producto) {
		productoseleccionado = producto;
	}
	public void actionListenerActualizarProducto() {
		try {
			
			managerProducto.actualizarProducto(productoseleccionado);
			listaProducto = managerProducto.findAllProducto();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
    
    
	public List<Producto> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProductoseleccionado() {
		return productoseleccionado;
	}

	public void setProductoseleccionado(Producto productoseleccionado) {
		this.productoseleccionado = productoseleccionado;
	}
	
}
