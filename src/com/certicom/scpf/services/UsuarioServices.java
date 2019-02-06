package com.certicom.scpf.services;

import java.util.List;


import com.certicom.scpf.domain.Usuario;
import com.certicom.scpf.mapper.UsuarioMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class UsuarioServices implements UsuarioMapper{

	UsuarioMapper usuarioMapper=(UsuarioMapper)ServiceFinder.findBean("usuarioMapper");

	@Override
	public List<Usuario> buscarPorLoginClave(Usuario usuario) {
		return usuarioMapper.buscarPorLoginClave(usuario);
	}

	@Override
	public void insertUsuario(Usuario usuario) {
		usuarioMapper.insertUsuario(usuario);
	}

	@Override
	public List<Usuario> getlistaUsuario() {
		return usuarioMapper.getlistaUsuario();
	}
	
	@Override
	public List<Usuario> getlistaEjecutivo() {		
		return usuarioMapper.getlistaEjecutivo();
	}
	
	@Override
	public List<Usuario> getlistaEjecutivoByNegocio(Integer idNegocio) {		
		return usuarioMapper.getlistaEjecutivoByNegocio(idNegocio);
	}	

	@Override
	public List<Usuario> buscarusuario(Usuario usuario) {
		return usuarioMapper.buscarusuario(usuario);
	}

	@Override
	public Usuario buscarPorId(int idusuario) {
		return usuarioMapper.buscarPorId(idusuario);
	}


	@Override
	public void actualizarEstado(Usuario usuario) throws Exception {
		usuarioMapper.actualizarEstado(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		usuarioMapper.actualizar(usuario);
	}
	
	@Override
	public void actualizarLogin(Usuario usuario) {
		usuarioMapper.actualizarLogin(usuario);
	}

	@Override
	public void actualizarPassword(Usuario usuario) {
		usuarioMapper.actualizarPassword(usuario);
	}
	
	
	@Override
	public void actualizarFechaAcceso(Usuario usuario) {
		usuarioMapper.actualizarFechaAcceso(usuario);
	}

	@Override
    public List<Usuario> buscarPorDNI(String dni) {
        return usuarioMapper.buscarPorDNI(dni);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioMapper.buscarPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        return usuarioMapper.buscarPorApellido(apellido); 
    }

	@Override
	public Usuario buscarPorLogin(String string) throws Exception {
		return usuarioMapper.buscarPorLogin(string);
	}
	

	
	@Override
	public List<Usuario> getlistaUsuario_Perfil(Usuario usuario) {
		return usuarioMapper.getlistaUsuario_Perfil(usuario); 
	}
	
	@Override
	public List<Usuario> getlistaUsuario_byCentroAtencion(Integer id_centro_atencion) {
		return usuarioMapper.getlistaUsuario_byCentroAtencion(id_centro_atencion); 
	}

	@Override
	public void deleteUsuario(Integer idusuario) throws Exception {
		usuarioMapper.deleteUsuario(idusuario);
	}

	@Override
	public List<Usuario> listarUsuariosXPerfilProceso(String proceso)
			throws Exception {
		return usuarioMapper.listarUsuariosXPerfilProceso(proceso);
	}

	@Override
	public List<Usuario> obtenerUsuariosxOficina(Integer idoficina)
			throws Exception {
		return usuarioMapper.obtenerUsuariosxOficina(idoficina);
	}

	@Override
	public void resetearPassword(Usuario usuario) throws Exception {
		usuarioMapper.resetearPassword(usuario);
	}

	@Override
	public void actualizarImagen(Usuario usuario) throws Exception {
		usuarioMapper.actualizarImagen(usuario);
	}

	@Override
	public List<Usuario> listUsuario() throws Exception {		
		return usuarioMapper.listUsuario();
	}

	@Override
	public List<Usuario> listUsuarioActivos() throws Exception {
		return usuarioMapper.listUsuarioActivos();
	}


	@Override
	public void insertaDatosEjecutivoPlanilla(Usuario usuario) throws Exception {
		usuarioMapper.insertaDatosEjecutivoPlanilla(usuario);
		
	}

	@Override
	public List<Usuario> listaSupervisor(Integer id_cargo)
			throws Exception {		
		return usuarioMapper.listaSupervisor(id_cargo);
	}

	@Override
	public List<Usuario> listaCoordinador(Integer id_cargo)
			throws Exception {		
		return usuarioMapper.listaCoordinador(id_cargo);
	}

	@Override
	public void actualizarCargo(Integer idusuario, Integer id_cargo)
			throws Exception {
		usuarioMapper.actualizarCargo(idusuario, id_cargo);
		
	}


	@Override
	public List<Usuario> listaPromotorxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		return usuarioMapper.listaPromotorxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public Usuario buscarPorDni(String string) throws Exception {
		return usuarioMapper.buscarPorDni(string);
	}

	@Override
	public List<Usuario> listaSupervisorxIdNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.listaSupervisorxIdNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listaCoordinadorxIdNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.listaCoordinadorxIdNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listarEjecutivosxNegocio(Integer id_negocio) throws Exception {
		return usuarioMapper.listarEjecutivosxNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listaPromotorCoordinadorxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorCoordinadorxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public Integer getEjecutivoxNegocio(Integer id_negocio) throws Exception {
		return usuarioMapper.getEjecutivoxNegocio(id_negocio);
	}

	@Override
	public Usuario getUsuario_byIdUsuario(Integer idusuario) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuario_byIdUsuario(idusuario);
	}

	@Override
	public int verificarDniCarnetRepetidos(String dniUsuario) throws Exception {
		return usuarioMapper.verificarDniCarnetRepetidos(dniUsuario);
	}

	@Override
	public int verificarloginRepetido(String login) throws Exception {
		return usuarioMapper.verificarloginRepetido(login);
	}

	@Override
	public List<Usuario> listarUsuarioReasignar(Integer idusuario)
			throws Exception {
		return usuarioMapper.listarUsuarioReasignar(idusuario);
	}

	@Override
	public List<Usuario> listUsuarioActivosNoCesados() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioActivosNoCesados();
	}

	@Override
	public List<Usuario> listaPromotorBackxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorBackxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public List<Usuario> listaPromotorCoordinadorxNegocioReasignar(
			Integer id_negocio, Integer id_supervisor, Integer idusuario)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorCoordinadorxNegocioReasignar(id_negocio, id_supervisor, idusuario);
	}

	@Override
	public Integer getLastUser() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getLastUser();
	}

}
