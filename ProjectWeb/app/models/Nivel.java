package models;

/**
 * 
 * classe com função de enumerador para definir quais
 * opções de nível existem no sistema.
 * 
 * @author Rafael Ribeiro, Jackson Wellington
 * 
 *
 * 
 * 
 **/
public enum Nivel {
	ADMIN("admin"),COMUM("comum");
	
private String descricao;
	
	
	Nivel(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
