package com.company.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("bookAPP");
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void closeEmf() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
