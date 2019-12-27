package jobs;

import models.Login;
import models.Nivel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
/**
 * 
 * Funcionalidade: Essa classe tem como função criar valores
 * genéricos no banco de dados a cada vez que a aplicação é iniciada
 * pela primeira vez.
 * 
 * @author Rafael Ribeiro, Jackson Wellington
 * 
 *
 * 
 * 
 **/
@OnApplicationStart
public class Inicializador extends Job {
	@Override
	public void doJob() throws Exception {
		if (Login.count() == 0) {
			Login l = new Login();
			l.email = "incubadorajc@gmail.com";
			l.nome = "Admin";
			l.senha = "12345";
			l.cidade = "João Câmara";
			l.numero = "(84)4005-4105";
			l.nivel = Nivel.ADMIN;
			l.save();
		}

	}
}
