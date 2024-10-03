package com.company.repositories;

import com.company.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AbstractRepo<T> {

    private Class<T> entityClass;

    public AbstractRepo(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager em = EntityManagerUtil.getEmf().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public T findById(int id) {
        EntityManager em = EntityManagerUtil.getEmf().createEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    public List<T> findAll() {
        EntityManager em = EntityManagerUtil.getEmf().createEntityManager();
        List<T> entities = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        em.close();
        return entities;
    }
}
