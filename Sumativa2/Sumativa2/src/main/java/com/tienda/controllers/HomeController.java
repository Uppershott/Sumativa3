package com.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.models.Usuario;
import com.tienda.services.CategoriaService;
import com.tienda.services.ProductoService;
import com.tienda.services.UsuarioService;

@Controller
@Scope("session")
public class HomeController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("")
	public String inicio(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login.jsp";
	}
	
	@RequestMapping("/login")
	public String login() {

		return "usuario/login.jsp";
	}
	
	
	
	@RequestMapping("/inicio")
	public String inicio(Model model, HttpSession session) {
		//session.setAttribute("carritoProductos", new ArrayList<Producto>());
		
		model.addAttribute("listaProductos", productoService.encontrarTodosProductos());
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
		return "inicio.jsp";
	}
	
	@RequestMapping("/registro")
	public String registro(@ModelAttribute("usuario") Usuario usuario) {
		
		return "registroUsuario.jsp";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("usuario", new Usuario());
		return "login.jsp";
	}
}
