package com.tienda.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	Usuario findByCorreo(String correo);
	
	Usuario findByNombre(String nombre);
}
