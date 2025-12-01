package org.dam;



import jakarta.persistence.*;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sanderson_universos");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx1 = em.getTransaction();

            create(em,tx1,33,"Elantris 2", 104);

            update(em,tx1,"Elantris 2", "Elantris", "Elantris Continuación");

            delete(em,tx1,33);

            select(em, 32);
    }

    // Operaciones CRUD
    public static void create(EntityManager em, EntityTransaction tx, Integer codigo, String titulo, Integer idSaga) {
        // persist
        tx.begin();

        System.out.println("\n--- CREATE ---");
        Libro nuevoLibro = new Libro();
        nuevoLibro.setCodigo(codigo);
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setIdSaga(idSaga);

        em.persist(nuevoLibro);

        tx.commit();

        System.out.printf("Nuevo libro: %s, %s%n%n", nuevoLibro.getCodigo(), nuevoLibro.getTitulo());

        System.out.println("--- LIBROS ---");
        TypedQuery<Libro> q = em.createQuery("SELECT l FROM Libro l", Libro.class);
        List<Libro> libros = q.getResultList();
        libros.forEach(System.out::println);
    }

    public static void update(EntityManager em, EntityTransaction tx, String titulo, String saga, String nuevoTitulo) {
        // merge
        tx.begin();

        System.out.println("\n--- UPDATE ---");
        Libro libro = em.createQuery("SELECT l FROM Libro l JOIN l.saga s WHERE l.titulo = :titulo AND s.titulo = :saga", Libro.class)
                .setParameter("titulo",titulo)
                .setParameter("saga",saga)
                .getSingleResult();

        em.detach(libro);
        Libro libroNuevo = new Libro();
        libroNuevo.setCodigo(libro.getCodigo());
        libroNuevo.setTitulo(nuevoTitulo);
        libroNuevo.setIdSaga(libro.getIdSaga());

        em.merge(libroNuevo);

        tx.commit();

        System.out.printf("Nuevo update: %s -> %s%n%n", libro.getTitulo(), nuevoTitulo);

        System.out.println("--- LIBROS ---");
        TypedQuery<Libro> q = em.createQuery("SELECT l FROM Libro l", Libro.class);
        List<Libro> libros = q.getResultList();
        libros.forEach(System.out::println);
    }

    public static void delete(EntityManager em, EntityTransaction tx, Integer idLibro) {
        // remove
        tx.begin();

        System.out.println("\n--- DELETE ---");

        Libro libro = em.find(Libro.class,idLibro);
        em.remove(libro);

        tx.commit();

        System.out.printf("Libro eliminado: %s, %s%n%n", libro.getCodigo(),libro.getTitulo());

        System.out.println("--- LIBROS ---");
        TypedQuery<Libro> q = em.createQuery("SELECT l FROM Libro l",Libro.class);
        List<Libro> libros = q.getResultList();
        libros.forEach(System.out::println);
    }

    public static void select(EntityManager em, Integer idLibro) {
        // find

        System.out.println("\n--- CONSULTA ---");

        Libro libro = em.find(Libro.class,idLibro);
        System.out.println(libro);
    }
}
