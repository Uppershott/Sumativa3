package com.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	List<Role> findByNombre(String nombre);
}
