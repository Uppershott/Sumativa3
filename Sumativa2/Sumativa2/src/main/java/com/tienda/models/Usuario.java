package com.tienda.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;
	
	@Transient
	private String contrasenaConfirmation;
	
	// relación muchos a muchos
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_usuarios", // tabla intermedia
			joinColumns = @JoinColumn(name = "usuario_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="productos_usuarios",
			joinColumns=@JoinColumn(name="producto_id"),
			inverseJoinColumns=@JoinColumn(name="usuario_id"))
	private List<Producto> productos;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nombre, String apellido, String correo, String contrasena, List<Producto> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", contraseña=" + contrasena + ", productos=" + productos + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public String getContrasenaConfirmation() {
		return contrasenaConfirmation;
	}

	public void setContrasenaConfirmation(String contrasenaConfirmation) {
		this.contrasenaConfirmation = contrasenaConfirmation;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
}
