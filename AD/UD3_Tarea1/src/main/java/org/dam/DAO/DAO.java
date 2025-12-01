package org.dam.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.dam.Modelo.Chasis;
import org.dam.Modelo.Mecanico;
import org.dam.Modelo.Motor;
import org.dam.util.JpaUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DAO {
    private static final Scanner sc = new Scanner(System.in);
    private static final EntityManager em =  JpaUtil.getEntityManager();

    public static void mostrarTodo(EntityManager em) {
        TypedQuery<Chasis> lista = em.createQuery("FROM Chasis",  Chasis.class);
        List<Chasis> chasisList = lista.getResultList();
        if (lista != null) {
            for (Chasis c : chasisList) {
                System.out.println(c);
            }
        } else {
            System.out.println("No se ha encontrado ningún chasis");
        }
    }

    public static void mostrarTodoRecursivo(EntityManager em) {
        TypedQuery<Chasis> lista = em.createQuery("FROM Chasis",  Chasis.class);
        List<Chasis> chasisList = lista.getResultList();
        for (Chasis chasis : chasisList) {
            System.out.println("-".repeat(200));
            System.out.println(chasis);
            System.out.printf("\tMecanicos: \n");
            Set<Mecanico> mecanicos = chasis.getMecanicos();
            for (Mecanico m : mecanicos) {
                System.out.println("\t\t" + m.toString());
            }
        }
    }

    public static void mostrar_Mecanico() {
        System.out.println("\n--- Buescar en tabla 'Mecanico' ---");
        System.out.print("Inserta el ID a buscar: ");
        Long id =  sc.nextLong();
        TypedQuery<Mecanico> q = JpaUtil.getEntityManager().createQuery("SELECT m FROM Mecanico m WHERE m.id_mecanico = :id", Mecanico.class);
        q.setParameter("id", id);
        Mecanico mecanico = q.getSingleResult();
        if (mecanico != null) {
            System.out.printf(mecanico.toString());
        } else {
            System.out.println("No se ha podido encontrar el mecánico");
        }
        System.out.println("\n"+"-".repeat(200));
    }

    public static void insertar(EntityManager em, EntityTransaction tx) {
        try {
            tx.begin();

            System.out.println("\n--- Insertar datos en tabla 'Mecanico' ---");
            System.out.print("Introduce el Nombre: ");
            sc.skip("\n");
            String nombre = sc.nextLine();
            System.out.print("Introduce los Años de experiencia: ");
            int aniosExperiencia = sc.nextInt();
            System.out.print("Introduce el taller: ");
            sc.skip("\n");
            String taller = sc.nextLine();
            System.out.print("Introduce el ID del Chasis: ");
            Long id = sc.nextLong();

            Mecanico mecanico = new  Mecanico();
            mecanico.setNombre(nombre);
            mecanico.setExperiencia_anios(aniosExperiencia);
            mecanico.setTaller(taller);
            mecanico.setChasis(new Chasis(id));

            em.merge(mecanico);

            tx.commit();

            System.out.println("--- Mecánico creado");
        } catch (Exception e) {
            System.out.println("Error al crear el mecánico");
            tx.rollback();
        }
    }

    public static void actualizar(EntityManager em, EntityTransaction tx) {
        try {
            tx.begin();

            System.out.println("\n--- Actualizar datos en tabla 'Mecanico' ---");
            System.out.print("Introduce el ID a actualizar: ");
            Long id = sc.nextLong();
            Mecanico mecanico = em.find(Mecanico.class, id);

            System.out.print("Introduce el nuevo Nombre: ");
            sc.skip("\n");
            String nombre = sc.nextLine();
            if (!nombre.isBlank()) {
                mecanico.setNombre(nombre);
            }

            System.out.print("Introduce los nuevos Años de experiencia: ");
            int aniosExperiencia = sc.nextInt();
            if (!String.valueOf(aniosExperiencia).isBlank()) {
                mecanico.setExperiencia_anios(aniosExperiencia);
            }

            System.out.print("Introduce el nuevo taller: ");
            sc.skip("\n");
            String taller = sc.nextLine();
            if (!taller.isBlank()) {
                mecanico.setTaller(taller);
            }

            em.persist(mecanico);

            tx.commit();

            System.out.println("--- Mecánico actualizado");
        } catch (Exception e) {
            System.out.println("Error al actualizar al mecánico");
            tx.rollback();
        }
    }

    public static void eliminar(EntityManager em, EntityTransaction tx) {
        try {
            tx.begin();

            System.out.print("Introduce el ID del Mecanico a elimminar: ");
            Long id = sc.nextLong();

            Mecanico mecanico = em.find(Mecanico.class, id);
            System.out.println(mecanico);
            em.remove(mecanico);

            tx.commit();

            System.out.println("--- Mecánico eliminado");
        } catch (Exception e) {
            System.out.printf("Error al borrar al mecánico");
            tx.rollback();
        }
    }
}
