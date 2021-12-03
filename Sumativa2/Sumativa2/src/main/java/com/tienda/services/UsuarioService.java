package com.tienda.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tienda.models.Role;
import com.tienda.models.Usuario;
import com.tienda.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleService roleService;

	@Autowired
	BCryptPasswordEncoder bcpe;
	
	public void insertarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario encontrarUsuarioPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByCorreo(email);
	}
	
	public void persistirUsuarioRol(Usuario usuario) {
		List<Role> roles=roleService.buscaRolPorNombre("ROLE_USER");
		String encode=bcpe.encode(usuario.getContrasena());
		String hashpw=BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
		System.out.println("Password: "+usuario.getContrasena());
		System.out.println("Encode: "+encode);
		System.out.println("Hashpw: "+hashpw);
		
		if(!encode.equals(hashpw)) {
			System.out.println("Códigos distintos");
		}
		
		if(BCrypt.checkpw(usuario.getContrasena(), encode)) {
			System.out.println("checkpw devuelve true en encode");
		}
		
		if(BCrypt.checkpw(usuario.getContrasena(), hashpw)) {
			System.out.println("checkpw devuelve true en hashpw");
		}
		
		usuario.setContrasena(bcpe.encode(usuario.getContrasena()));
		usuario.setRoles(roleService.buscaRolPorNombre("ROLE_USER"));
		
		System.out.println("rol:"+roles.get(0).getId());
		System.out.println("nombre: "+roles.get(0).getNombre());
		
		usuarioRepository.save(usuario);
	}
	
}
