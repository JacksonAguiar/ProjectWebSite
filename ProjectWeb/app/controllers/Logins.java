package controllers;

import java.util.List;
import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.Login;
import play.data.validation.Valid;
import play.db.jpa.JPABase;
import play.libs.Crypto;
import play.libs.Mail;
import play.mvc.Controller;

/**
 * 
 * Funcionalidade: Cadastrar usuarios no sistema e estabelecer sessões
 * 
 * @author Rafael Ribeiro, Jackson Wellington
 * 
 *
 * 
 * 
 **/
public class Logins extends Controller {
	/**
	 * Formulário de entrada para estabelecer sessões
	 */
	public static void form() {
		render();
	}

	/**
	 * Buscar usuários no banco e estabelecer sessões
	 * 
	 * @param email new String
	 * @param senha new String
	 */
	public static void entrar(String email, String senha) {

		Login l = Login.find("email = ?1 and senha = ?2", email, Crypto.passwordHash(senha)).first();

		if (l == null) {
			flash.error("Email ou senha inválidos");
			form();
		} else {
			session.put("usuario.email", l.email);
			session.put("usuario.nivel", l.nivel);
			session.put("usuario.nome", l.nome);
			session.put("usuario.id", l.id);
			Noticias.generic();
		}
	}

	/**
	 * quebrar sessões, ou no caso, limpar.
	 */
	public static void logout() {
		session.clear();
		Start.index();
	}

	public static void esqueciMinhaSenha() {
		render();
	}

	public static void enviarEmail(String email) {

		Login l = Login.find("email = ?1", email).first();

		if (l != null) {
			Random rand = new Random();
			String hash = Crypto.passwordHash(rand.nextInt(1000) + "");
			l.hash = hash;
			l.save();
			try {
				HtmlEmail mail = new HtmlEmail();
				mail.addTo(email);
				mail.setFrom("rafaelribeirofranco4@gmail.com", "Rafael");
				mail.setSubject("Recuperação de senha");
				String msg = "<h2>Você esqueceu sua senha?</h2>";
				msg += "<p>Segue abaixo o link para recuperação de sua senha:</p><br/>";
				msg += "<a href = 'http://localhost:9000/Logins/novaSenha?hash=" + hash + "'>Nova senha</a>";
				// set the html message
				mail.setHtmlMsg("<html>" + msg + "</html>");
				Mail.send(mail);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			flash.error("Email não cadastrado");
			esqueciMinhaSenha();
		}

		form();
	}

	public static void novaSenha(String hash) {
		render(hash);
	}

	public static void salvarNovaSenha(String hash, String senha, String confirmaSenha) {
		Login l = Login.find("hash = ?1", hash).first();
		if (l != null) {
			if (senha.equals(confirmaSenha)) {
				l.senha = senha;
				l.hash = null;
				l.save();
				flash.success("Nova senha cadastrada com sucesso.");
				form();
			} else {
				flash.error("senhas diferentes");
				novaSenha(hash);
			}
		} else {
			flash.error("Hash inválido");
			esqueciMinhaSenha();
		}
	}

}
