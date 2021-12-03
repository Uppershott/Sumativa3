package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.Role;
import com.tienda.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> buscaRolPorNombre(String nombre){
		return roleRepository.findByNombre(nombre);
	}
}
