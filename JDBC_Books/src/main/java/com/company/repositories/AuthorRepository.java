package com.company.repositories;

import com.company.utils.EntityManagerUtil;
import com.company.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorRepository extends AbstractRepo<Author> {

    public AuthorRepository() {
        super(Author.class);
    }

    public List<Author> findByName(String name) {
        EntityManager em = EntityManagerUtil.getEmf().createEntityManager();
        TypedQuery<Author> query = em.createNamedQuery("Author.findByName", Author.class);
        query.setParameter("name", "%" + name + "%");
        List<Author> authors = query.getResultList();
        em.close();
        return authors;
    }
}
