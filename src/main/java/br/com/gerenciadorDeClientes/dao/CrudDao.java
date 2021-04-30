package br.com.gerenciadorDeClientes.dao;

import br.com.gerenciadorDeClientes.entity.PessoaEntity;
import br.com.gerenciadorDeClientes.exception.DaoException;
import br.com.gerenciadorDeClientes.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDao<E extends PessoaEntity<ID>, ID extends Serializable> {

   private EntityManager em = new JPAUtil().getEntityManager();

   protected Class<E> classE;

    public E salvarOuAlterar(final E entity) throws DaoException {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            tentaExecutarRollbackNaTransacao();
            e.printStackTrace();
            throw new DaoException("Não foi possivel salvar.", e);

        }
        return entity;
    }

    public E alterar(final E entity) throws DaoException {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            tentaExecutarRollbackNaTransacao();
            e.printStackTrace();
            throw new DaoException("Não foi possivel alterar", e);
        }
        return entity;
    }

    private void tentaExecutarRollbackNaTransacao() {
        try {
            em.getTransaction().rollback();
        } catch (Exception ex) {
            System.out.println("Não foi necessário o rollback!");
        }
    }

    public void excluir(final ID id) throws DaoException {
        try {
            E e = buscarPorId(id);
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } catch (Exception e) {
            tentaExecutarRollbackNaTransacao();
            e.printStackTrace();
            throw new DaoException("Não foi possivel excluir", e);
        }
    }

    public E buscarPorId(final ID id) {
        if (id == null) {
            return null;
        }

        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT e FROM ").append(getClassE().getName()).append(" e WHERE e.id = :id ");
            TypedQuery<E> query = em.createQuery(jpql.toString(), getClassE());
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<E> listarTodos() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select e From " + getClassE().getName() + " e ");
        jpql.append(adicionaWhereBuscarTodos());
        return em.createQuery(jpql.toString()).getResultList();
    }

    public String adicionaWhereBuscarTodos() {
        return "";
    }

    @SuppressWarnings("unchecked")
    public Class<E> getClassE() {
        if (classE == null) {
            classE = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return classE;
    }
}