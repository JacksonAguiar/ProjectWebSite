package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Noticia extends Model{
	
	@Column(columnDefinition = "text")
	public String texto;
	

	public String titulo;
	
}
