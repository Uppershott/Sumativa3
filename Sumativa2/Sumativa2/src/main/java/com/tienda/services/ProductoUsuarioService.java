package com.tienda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.ProductoUsuario;
import com.tienda.repositories.ProductoUsuarioRepository;

@Service
public class ProductoUsuarioService {
	
	@Autowired
	ProductoUsuarioRepository productoUsuarioRepository;

	public void insertarCompra(ProductoUsuario productoUsuario) {
		productoUsuarioRepository.save(productoUsuario);
	}
	
	
}
