package sistema.testes;

import javax.persistence.EntityManager;

import sistema.dao.MaquinaDao;
import sistema.modelo.Maquina;
import sistema.util.JPAUtil;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;

public class CadastroDeMaquinas {
	
	public static void main(String[] args) {

		//instancia o Looca
		Looca looca = new Looca();

		//instancia qual informação do looca nos é necessária, neste caso, informações do sistema
		Sistema sistema = looca.getSistema();
		Memoria memoria = looca.getMemoria();

		//cria um objeto do tipo Maquina, em seu construtor é passada
		//o sistema operacional e a fabricante do computador;

		Maquina maquina = new Maquina(
				sistema.getSistemaOperacional(),
				sistema.getFabricante(),
				memoria.getDisponivel()
		);

		//vai até JPAUtil criar uma EntityManager
		//e instancia uma data-access-object das máquinas passando
		//o entityManager em seu construtor

		EntityManager em = JPAUtil.getEntityManager();
		MaquinaDao maquinaDao = new MaquinaDao(em);


		//começa a transação
		em.getTransaction().begin();

		//chama o método de persistência no banco
		maquinaDao.cadastrar(maquina);

		//termina a transação e fecha a conexão
		em.getTransaction().commit();
		em.close();
	}

}
