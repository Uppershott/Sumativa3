package com.tienda.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tienda.models.Role;
import com.tienda.models.Usuario;
import com.tienda.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	UsuarioRepository UsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("UserDetails");
		Usuario Usuario = UsuarioRepository.findByCorreo(email);
		System.out.println(email);
		if (Usuario == null) {
			System.out.println("usuario null");
			throw new UsernameNotFoundException("Usuario not found");
		}

		return new org.springframework.security.core.userdetails.User(Usuario.getCorreo(), Usuario.getContrasena(),
				getAuthorities(Usuario));
	}

	private List<GrantedAuthority> getAuthorities(Usuario Usuario) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : Usuario.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getNombre());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}
}
