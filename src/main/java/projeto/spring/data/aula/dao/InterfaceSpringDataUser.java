package projeto.spring.data.aula.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
	
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome (String nome);
	
	@Lock(LockModeType.READ) // bloqueia quando um usuário já esta consultando um mesmo objeto
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscarPorNomeParam(@Param("paramnome") String paramnome);
	
	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioSpringData u where u.nome = ?1")
	public void deletarPorNome(String nome);
	
	@Modifying
	@Transactional
	@Query(value = "update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
}
