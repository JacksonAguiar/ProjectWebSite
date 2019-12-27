package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.br.CPF;

import net.sf.ehcache.search.expression.Criteria;
import play.data.validation.Email;
import play.data.validation.Match;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity

/**
 * @author Rafael Ribeiro, Jackson Wellington
 * 
 * 
 *         Funcionalidade: Essa classe tem como função criar uma tabela no banco
 *         de dados h2 com o nome Login e persistir dados.
 * 
 */
public class Login extends Model {
	// Colunas da tabela de banco de dados
	@Required
	public String nome;
	@Required
	@Email
	public String email;

	@Required
	@MinSize(5)
	public String senha;
	@Required
	public String cidade;
	@Required
	//@Match(value = "([0-9]{2})[0-9]{5}-[0-9]{4}", message = "Valor deve ser de acordo com o formato: (00)00000-0000")
	public String numero;
	
	public String hash;

	// Enum que define o nível de cada usuário
	@Enumerated(EnumType.STRING)
	public Nivel nivel;

	// Construtor que define a cada novo usuário o nível comum
	public Login() {
		nivel = Nivel.COMUM;
	}

	// Criptografando a senha
	public void setSenha(String s) {
		if (!s.equals("") && s.length() >= 5) {
			senha = Crypto.passwordHash(s);
		} else {
			senha = s;
		}
	}
}
