package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.models.Categoria;
import com.tienda.models.Producto;
import com.tienda.models.ProductoUsuario;
import com.tienda.models.Usuario;
import com.tienda.services.CategoriaService;
import com.tienda.services.ProductoService;
import com.tienda.services.ProductoUsuarioService;

@Controller
@RequestMapping("/producto")
@Scope("session")
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProductoUsuarioService productoUsuarioService;

	@RequestMapping("")
	public String inicioProducto(Model model) {
		model.addAttribute("producto", new Producto());

		model.addAttribute("listaProductos", productoService.encontrarTodosProductos());
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());

		return "producto.jsp";
	}

	@RequestMapping("/crear")
	public String crearProducto(@ModelAttribute("producto") Producto producto) {
		productoService.insertarProducto(producto);

		return "redirect:/producto";
	}

	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		System.out.println("Producto: "+id);
		Producto producto = productoService.encontrarProducto(id);

		model.addAttribute("producto", producto);
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
		return "productoEdit.jsp";
	}
	
	@RequestMapping(value = "/editarProducto", method = RequestMethod.PUT)
	public String editarProducto(@ModelAttribute("producto") Producto producto) {
		productoService.insertarProducto(producto);

		return "redirect:/producto";
	}

	@RequestMapping("/eliminar")
	public String eliminarProducto(@RequestParam("id") Long id, Model model){
		Producto producto = productoService.encontrarProducto(id);
		if(producto==null) {
			System.out.println("No existe el producto que intenta eliminar");
			return "redirect:/producto";
		}else {
			productoService.eliminarProducto(producto);
			return "redirect:/producto";
		}
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/agregarCarrito")
	public String agregarProductoCarrito(@RequestParam("id") Long id, Model model, HttpSession session) {
		model.addAttribute("listaProductos", productoService.encontrarTodosProductos());
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());

		Producto producto = productoService.encontrarProducto(id);

		List<Producto> carritoProductos = (List<Producto>) session.getAttribute("carritoProductos");
		carritoProductos.add(producto);

		session.setAttribute("carritoProductos", carritoProductos);
		System.out.println("Producto " + producto.getNombre() + " agregado al carrito!");
		return "inicio.jsp";
	}

	@RequestMapping("/productoUsuario")
	public String verCarrito(Model model, HttpSession session) {
		List<Producto> productosCarrito = (List<Producto>) session.getAttribute("carritoProductos");
		session.setAttribute("costoTotal", calculaTotal(productosCarrito));
		
		model.addAttribute("costoTotal", calculaTotal(productosCarrito));
		model.addAttribute("carritoProductos", (List<Producto>) session.getAttribute("carritoProductos"));
		return "carritoCompra.jsp";
	}

	@RequestMapping("/quitarProducto")
	public String quitarProductoCarrito(@RequestParam("id") Long id, Model model, HttpSession session) {
		List<Producto> productosCarrito = (List<Producto>) session.getAttribute("carritoProductos");

		for (int i = 0; i < productosCarrito.size(); i++) {
			if (productosCarrito.get(i).getId() == id) {
				System.out.println("Removido: " + productosCarrito.get(i).getNombre());
				productosCarrito.remove(i);

			}
		}
		session.setAttribute("carritoProductos", productosCarrito);
		// model.addAttribute("carritosProducto", productosCarrito);
		// model.addAttribute("costoTotal", calculaTotal(productosCarrito));
		return "redirect:/producto/productoUsuario";
	}

	@RequestMapping("/anularCompra")
	public String anularCompra(HttpSession session) {
		session.setAttribute("carritoProductos", new ArrayList<Producto>());
		return "redirect:/inicio";
	}

	@RequestMapping("/procederCompra")
	public String comprarProductos(HttpSession session) {
		List<Producto> productosComprar = (List<Producto>) session.getAttribute("carritoProductos");
		String costoTotal=session.getAttribute("costoTotal").toString();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario==null) {
			System.out.println("No se puede procesar la compra si no inicia sesión!");
			return "login.jsp";
		}else if(productosComprar.size()==0) { 
			System.err.println("No se puede procesar la compra porque el carrito está vacío!");
			return "redirect:/inicio";
		}else{
			
			for(int i=0; i<productosComprar.size();i++) {
				ProductoUsuario productoUsuario = new ProductoUsuario();
				productoUsuario.setUsuario(usuario);
				productoUsuario.setCostoTotal(costoTotal);
				productoUsuario.setCantidad("1");
				productoUsuario.setProducto(productosComprar.get(i));
				productoUsuarioService.insertarCompra(productoUsuario);
				
			}
			System.out.println("Compra realizada con éxito");
			return "redirect:/producto";
		}
	}
	
	
	//------------------------------------------------------------------------------------------
	
	public Integer calculaTotal(List<Producto> productosCarrito) {
		int costoTotal = 0;
		for (int i = 0; i < productosCarrito.size(); i++) {
			costoTotal += Integer.parseInt(productosCarrito.get(i).getPrecio());
		}
		return costoTotal;
	}

	@RequestMapping("/buscarCategoria")
	public String buscarCategoria(@RequestParam("id") Long id, Model model) {
		System.out.println("categoria: " + id);
		
		if (id == 0) {
			return "redirect:/inicio";
		} else {
			Categoria categoria = categoriaService.buscarCategoria(id);
			List<Producto> productos = productoService.encontrarTodosProductos();
			List<Producto> productoConCategoria=new ArrayList<Producto>();
			for(int i=0; i<productos.size(); i++) {
				List<Categoria> categoriasProductos = productos.get(i).getCategorias();
				for(int j=0; j<categoriasProductos.size();j++) {
					if(categoriasProductos.get(j).getId()==categoria.getId()) {
						System.out.println("Agregado "+productos.get(i).getNombre()+" categoria "+categoriasProductos.get(j).getNombre());
						productoConCategoria.add(productos.get(i));
					}
				}
			}
			model.addAttribute("listaProductos", productoConCategoria);
			model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
			return "inicio.jsp";
		}
	}

}
