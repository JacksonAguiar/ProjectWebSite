package controllers;

import play.mvc.Before;
import play.mvc.Controller;
/**
 * 
 * Classe interceptadora para conferir se o usuário possui sessão.
 * Anotada em todas as classes do controlador com o "@With".
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
public class Seguranca extends Controller {
	@Before(unless = { "Noticias.generic"})
	static void verificar() {
		if (!session.contains("usuario.email")) {
			Logins.form();
		}
	}
}
