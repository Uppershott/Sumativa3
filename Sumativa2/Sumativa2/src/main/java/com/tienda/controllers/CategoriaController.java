package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.models.Categoria;
import com.tienda.services.CategoriaService;

@Controller
@RequestMapping("/categoria")
@Scope("session")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping("")
	public String inicioCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		
		model.addAttribute("listaCategorias", categoriaService.encontrarTodasCategorias());
		return "categoria.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregarCategoria(@ModelAttribute("categoria") Categoria categoria) {
		categoriaService.insertarCategoria(categoria);
		
		return "redirect:/categoria";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		model.addAttribute("categoria", categoriaService.buscarCategoria(id));
		
		return "categoriaEdit.jsp";
	}
	
	@RequestMapping(value="/editarCategoria", method=RequestMethod.PUT)
	public String editarCategoria(@ModelAttribute("categoria") Categoria categoria) {
		categoriaService.insertarCategoria(categoria);
		System.out.println("Categoria "+categoria.getNombre()+" actualizada");
		return "redirect:/categoria";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarCategoria(@RequestParam("id") Long id, Model model) {
		Categoria categoria = categoriaService.buscarCategoria(id);
		if(categoria==null) {
			System.out.println("La categoría que intenta eliminar no existe");
			return "redirect:/categoria";
		}else {
			categoriaService.eliminarCategoria(categoria);
			System.out.println("Categoria "+categoria.getNombre()+" eliminada");
			return "redirect:/categoria";
		}
	}
}
