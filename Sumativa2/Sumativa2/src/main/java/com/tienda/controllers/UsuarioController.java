package com.tienda.controllers;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.models.Producto;
import com.tienda.models.Usuario;
import com.tienda.services.CategoriaService;
import com.tienda.services.ProductoService;
import com.tienda.services.UsuarioService;

@Controller
@Scope("session")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping("/login")
	public String login(Principal principal, Model model, HttpSession session) {
		String nombre = principal.getName();
		System.out.println(nombre);
		System.out.println("login");
		usuarioService.findByEmail(nombre);
		session.setAttribute("carritoProductos", new ArrayList<Producto>());
		
		model.addAttribute("listaProductos", productoService.encontrarTodosProductos());
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
		
		return "inicio.jsp";
	}
	
	@RequestMapping("/loginU")
	public String loginUsuario(Principal principal, Model model, HttpSession session) {
		String nombre = principal.getName();
		
		usuarioService.findByEmail(nombre);
		session.setAttribute("carritoProductos", new ArrayList<Producto>());
		
		model.addAttribute("listaProductos", productoService.encontrarTodosProductos());
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
		
		return "inicio.jsp";			
	}
	
	@RequestMapping("/crear")
	public String crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
		//usuarioService.registroUsuario(usuario);
		usuarioService.persistirUsuarioRol(usuario);
		return "login.jsp";
	}
	
	@RequestMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registroUsuario.jsp";
	}
	
	@RequestMapping("/registrarUsuario")
	public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		Usuario usuarioAux = usuarioService.encontrarUsuarioPorCorreo(usuario.getCorreo());
		if(usuarioAux!=null) {
			System.out.println("Ya existe un usuario con ese correo!");
			return "redirect:/registrar";
		}else {
			usuarioService.insertarUsuario(usuario);
			return "login.jsp";
		}
	}
	
	
}
