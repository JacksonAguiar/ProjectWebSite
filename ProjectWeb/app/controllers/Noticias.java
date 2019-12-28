package controllers;

import java.io.File;
import java.util.List;

import models.Noticia;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Funcionalidade: Essa classe tem como função detalhar as noticias criar,
 * editar ou remover noticias, adicionar membros ou eventos na agenda.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 */
@With(Seguranca.class)
public class Noticias extends Controller {
	/**
	 * página inicial, detalhamento de membros, noticias e da agenda.
	 */
	public static void generic() {
		List<Noticia> not = Noticia.find("order by id desc").fetch();
		render(not);
	}

	public static void salvar(Noticia not) {
		if (not.texto.equals("") || not.texto.equals(null)) {
			flash.error("Adicione uma Mensagem");
		} else {
			not.save();
			flash.success("Notícia adicionada com sucesso");
		}
		generic();
	}

	public static void editar(Long id) {
		Noticia noticias = Noticia.findById(id);
		renderTemplate("Noticias/generic.html", noticias);
	}

	public static void deletar(Long id) {
		Noticia not = Noticia.findById(id);
		not.delete();
		flash.success("Removido com sucesso");
		generic();
	}
}
