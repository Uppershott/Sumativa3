package com.tienda.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="productos_usuarios")
public class ProductoUsuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String cantidad;
	private String costoTotal;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;

	public ProductoUsuario() {
		super();
	}

	public ProductoUsuario(Long id, String cantidad, String costoTotal, Usuario usuario, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.costoTotal = costoTotal;
		this.usuario = usuario;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "ProductoUsuario [id=" + id + ", cantidad=" + cantidad + ", costoTotal=" + costoTotal + ", usuario="
				+ usuario + ", producto=" + producto + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
