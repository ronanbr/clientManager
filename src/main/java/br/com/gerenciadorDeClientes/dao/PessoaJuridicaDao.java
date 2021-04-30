package br.com.gerenciadorDeClientes.dao;

import br.com.gerenciadorDeClientes.entity.PessoaEntity;
import br.com.gerenciadorDeClientes.entity.PessoaJuridicaEntity;
import br.com.gerenciadorDeClientes.util.JPAUtil;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Model
public class PessoaJuridicaDao extends CrudDao<PessoaJuridicaEntity, Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em = new JPAUtil().getEntityManager();

    public List<PessoaJuridicaEntity> execute(String namedQuery) {
        TypedQuery<PessoaJuridicaEntity> query = em.createNamedQuery(namedQuery, PessoaJuridicaEntity.class);
        return query.getResultList();
    }

    @Override
    public String adicionaWhereBuscarTodos(){
        return "WHERE e.tipo = '" + PessoaEntity.TIPO_PESSOA_JURIDICA + "'";
    }

    public List<PessoaJuridicaEntity> buscar(PessoaJuridicaEntity pessoaJuridica) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM PessoaJuridicaEntity p ");
        jpql.append("WHERE p.tipo = '" + PessoaEntity.TIPO_PESSOA_JURIDICA + "' ");

        if(!pessoaJuridica.getNomeFantasia().isEmpty()){
            jpql.append("AND UPPER(p.nomeFantasia) LIKE UPPER('%" + pessoaJuridica.getNomeFantasia() + "%')");
        }
        if(!pessoaJuridica.getRazaoSocial().isEmpty()){
            jpql.append("AND UPPER(p.razaoSocial) LIKE UPPER('%" + pessoaJuridica.getRazaoSocial() + "%')");
        }
        if(!pessoaJuridica.getCnpj().isEmpty()){
            jpql.append("AND p.cnpj LIKE '%" + pessoaJuridica.getCnpj() + "%'");
        }
        if(!pessoaJuridica.getSite().isEmpty()){
            jpql.append("AND p.site LIKE '%" + pessoaJuridica.getSite() + "%'");
        }

        return em.createQuery(jpql.toString()).getResultList();
    }

}
