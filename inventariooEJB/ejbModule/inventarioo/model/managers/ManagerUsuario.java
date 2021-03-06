package inventarioo.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import inventarioo.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext
	private EntityManager em;	
	
    public ManagerUsuario() {
        // TODO Auto-generated constructor stub
    }
    public List<Usuario> findAllUsuario() {
		String consulta = "SELECT u FROM Usuario u order by id_usuario";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}


	public Usuario findUsuarioById(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}
	public Usuario findUsuarioBycedula(String cedula) {
		return em.find(Usuario.class, cedula);
	}

	public void eliminarUsuario(int IdUsuario) {
		Usuario usuario = findUsuarioById(IdUsuario);
		if (usuario != null)
			em.remove(usuario);
	}

	public void insertarUsuario(Usuario usuario) throws Exception {
		
		em.persist(usuario);
		
		System.out.println("Usuario insertado.........");
	}
	public String crearUsuario(Usuario usuario,String cedula) throws Exception{
		
    	Usuario p=new Usuario();
    	p.setNombres(usuario.getNombres());
    	p.setCedula(cedula);
    	p.setRuc(usuario.getRuc());
    	p.setApellidos(usuario.getApellidos());
    	p.setDireccion(usuario.getDireccion());
    	p.setEmail(usuario.getEmail());
    	//p.setPrueba(usuario.getPrueba());
    	p.setTelefono(usuario.getTelefono());
    	em.persist(p);
    	return "usuario creado ok.";
	
    }
	
	 public String eliminarUsuarioR(int idusu) {
	    	Usuario p=em.find(Usuario.class, idusu);
	    	em.remove(p);
	    	return "se ha eliminado el usuario ok.";
	    }
	

	 public void actualizarUsuario(Usuario usuario) throws Exception {
			Usuario user = findUsuarioById(usuario.getIdUsuario());
			if (user == null)
				throw new Exception("No existe el Usuario con el Id especificada");
			
			//user.setCedula(cedula);
			user.setRuc(usuario.getRuc());
			user.setNombres(usuario.getNombres());
			user.setApellidos(usuario.getApellidos());
			user.setDireccion(usuario.getDireccion());
			user.setEmail(usuario.getEmail());
			user.setTelefono(usuario.getTelefono());
			em.merge(user);
		}



}
