package controllers;

import java.util.List;

import models.Noticia;
import play.mvc.Controller;

public class Start extends Controller{

	public static void index(){
		List<Noticia> not = Noticia.find("order by id desc").fetch(3);
		render(not);
	}
	public static void elements(){
		render();
	}
	public static void info(Long id){
		List<Noticia> not = Noticia.find("id=?1", id).fetch();
		render(not);
	}
	
	
	
	
}
