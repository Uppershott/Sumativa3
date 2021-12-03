package com.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.models.ProductoUsuario;

@Repository
public interface ProductoUsuarioRepository extends JpaRepository<ProductoUsuario,Long> {

}
