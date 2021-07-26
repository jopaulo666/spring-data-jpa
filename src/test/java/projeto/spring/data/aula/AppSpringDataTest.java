package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Ignore
	@Test
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setNome("Carla Moraes");
		usuarioSpringData.setLogin("carla");
		usuarioSpringData.setSenha("22554");
		usuarioSpringData.setEmail("carla@gmail.com");
		usuarioSpringData.setIdade(25);

		interfaceSpringDataUser.save(usuarioSpringData);

		System.out.println("Usuarios cadastrados -> " + interfaceSpringDataUser.count());

	}

//	@Ignore
	@Test
	public void testeConsulta() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);

		System.out.println("---------------------------------------------");
		System.out.println("Código = " + usuarioSpringData.get().getId());
		System.out.println("Login = " + usuarioSpringData.get().getLogin());
		System.out.println("Senha = " + usuarioSpringData.get().getSenha());
		System.out.println("Nome = " + usuarioSpringData.get().getNome());
		System.out.println("E-mail = " + usuarioSpringData.get().getEmail());
		System.out.println("Idade = " + usuarioSpringData.get().getIdade());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println("Tipo telefone = " + telefone.getTipo());
			System.out.println("Número telefone = " + telefone.getNumero());
		}
		
		System.out.println("---------------------------------------------");
	}

	@Ignore
	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> list = interfaceSpringDataUser.findAll();

		System.out.println("Lista de Usuários: ");
		for (UsuarioSpringData usuarioSpringData : list) {

			System.out.println("---------------------------------------------");
			System.out.println("Código = " + usuarioSpringData.getId());
			System.out.println("Login = " + usuarioSpringData.getLogin());
			System.out.println("Senha = " + usuarioSpringData.getSenha());
			System.out.println("Nome = " + usuarioSpringData.getNome());
			System.out.println("E-mail = " + usuarioSpringData.getEmail());
			System.out.println("Idade = " + usuarioSpringData.getIdade());
		}
	}

	@Ignore
	@Test
	public void testeUpdate() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);

		UsuarioSpringData data = usuarioSpringData.get();

		data.setNome("João Paulo da Mata Mendes");

		interfaceSpringDataUser.save(data);

		System.out.println("Usuário(a) '" + data.getNome() + "' atualizado(a) com sucesso!");
	}

	@Ignore
	@Test
	public void testeDelete() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(6L);

		System.out.println("Usuário(a) '" + usuarioSpringData.get() + "' deletado(a) com sucesso!");

	}

	@Ignore
	@Test
	public void testeConsultaNome() {

		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("");

		for (UsuarioSpringData usuarioSpringData : list) {

			System.out.println("Busca = " + usuarioSpringData.getNome());
		}
	}

	@Ignore
	@Test
	public void testeConsultaNomeExato() {

		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscarPorNomeParam("Adnari Mendes");

		System.out.println("---------------------------------------------");
		System.out.println("Código = " + usuarioSpringData.getId());
		System.out.println("Login = " + usuarioSpringData.getLogin());
		System.out.println("Senha = " + usuarioSpringData.getSenha());
		System.out.println("Nome = " + usuarioSpringData.getNome());
		System.out.println("E-mail = " + usuarioSpringData.getEmail());
		System.out.println("Idade = " + usuarioSpringData.getIdade());
	}

	@Ignore
	@Test
	public void testeDeletePorNome() {

		interfaceSpringDataUser.deletarPorNome("Adnari Mendes");
	}
	
	@Ignore
	@Test
	public void testeUpdateEmailPorNome() {

		interfaceSpringDataUser.updateEmailPorNome("adnari@gmail.com", "Adnari Mendes");
	}
	
	@Ignore
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Celular");
		telefone.setNumero("91 98478-8574");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}

}
