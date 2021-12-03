package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.Categoria;
import com.tienda.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> encontrarTodasCategorias() {
		return categoriaRepository.findAll();
	}

	public void insertarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
	}

	public Categoria buscarCategoria(Long id) {
		return categoriaRepository.findById(id).get();
	}

	public void eliminarCategoria(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}

	
	/*
	 * public List<Producto> encontrarProductosPorCategoria(Long id) { return
	 * categoriaRepository.findByCategoria(id); }
	 */
}
