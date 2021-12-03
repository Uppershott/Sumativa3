package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.Producto;
import com.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;

	public List<Producto> encontrarTodosProductos() {
		return productoRepository.findAll();
	}

	public void insertarProducto(Producto producto) {
		productoRepository.save(producto);
	}

	public Producto encontrarProducto(Long id) {
		return productoRepository.findById(id).get();
	}

	public void eliminarProducto(Producto producto) {
		productoRepository.delete(producto);
	}
}
