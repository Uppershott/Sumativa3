package com.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
	List<Categoria> findAll();
	
	//@Query(value="SELECT * FROM categorias_productos WHERE categoria_id=?1", nativeQuery=true)
	//List<Producto> find
	
}
