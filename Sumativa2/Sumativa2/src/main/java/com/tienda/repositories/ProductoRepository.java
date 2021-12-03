package com.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
	List<Producto> findAll();
	
}
