package org.dam.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

import java.util.Map;
import java.util.HashMap;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT_NAME = "BMW_Clasicos";
    @Getter
    private static EntityManagerFactory entityManagerFactory;

    static {
        initialize();
    }

    private static void initialize() {
        try {
            System.out.println("Inicializando Hibernate 7.1.2...");

            // Configuración programática adicional si es necesaria
            Map<String, Object> configOverrides = new HashMap<>();
            configOverrides.put("hibernate.connection.autocommit", "false");

            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, configOverrides);

            System.out.println("EntityManagerFactory creado exitosamente con Hibernate 7");
            System.out.println("Metadata: " + entityManagerFactory.getMetamodel().getEntities().size() + " entidades cargadas");

        } catch (Exception e) {
            System.err.println("Error inicializando Hibernate 7: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError("Fallo en inicialización de Hibernate 7: " + e.getMessage());
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            initialize();
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory cerrado");
            entityManagerFactory = null;
        }
    }

    public static boolean isInitialized() {
        return entityManagerFactory != null && entityManagerFactory.isOpen();
    }
}