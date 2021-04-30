package br.com.gerenciadorDeClientes.dao;

import br.com.gerenciadorDeClientes.entity.PessoaEntity;
import br.com.gerenciadorDeClientes.entity.PessoaFisicaEntity;
import br.com.gerenciadorDeClientes.util.JPAUtil;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Model
public class PessoaFisicaDao extends CrudDao<PessoaFisicaEntity, Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em = new JPAUtil().getEntityManager();

    public List<PessoaFisicaEntity> execute(String namedQuery) {
        TypedQuery<PessoaFisicaEntity> query = em.createNamedQuery(namedQuery, PessoaFisicaEntity.class);
        return query.getResultList();
    }

    @Override
    public String adicionaWhereBuscarTodos(){
        return "WHERE e.tipo = '" + PessoaEntity.TIPO_PESSOA_FISICA + "'";
    }

    public List<PessoaFisicaEntity> buscar(PessoaFisicaEntity pessoaFisica) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM PessoaFisicaEntity p ");
        jpql.append("WHERE p.tipo = '" + PessoaEntity.TIPO_PESSOA_FISICA + "' ");

        if(!pessoaFisica.getNome().isEmpty()){
            jpql.append("AND UPPER(p.nome) LIKE UPPER('%" + pessoaFisica.getNome() + "%')");
        }
        if(!pessoaFisica.getCpf().isEmpty()){
            jpql.append("AND p.cpf LIKE '%" + pessoaFisica.getCpf() + "%'");
        }
        if(pessoaFisica.getDtNascimento() != null){
            jpql.append("AND p.dtNascimento = '%" + pessoaFisica.getDtNascimento() + "%'");
        }
        if(!pessoaFisica.getEmail().isEmpty()){
            jpql.append("AND p.email LIKE '%" + pessoaFisica.getEmail() + "%'");
        }
        if(!pessoaFisica.getTelefone().isEmpty()){
            jpql.append("AND p.telefone LIKE '%" + pessoaFisica.getTelefone() + "%'");
        }

        return em.createQuery(jpql.toString()).getResultList();
    }
}
