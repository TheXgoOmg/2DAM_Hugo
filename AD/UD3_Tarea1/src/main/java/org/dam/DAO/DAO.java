package org.dam.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.dam.Modelo.Chasis;
import org.dam.Modelo.Mecanico;
import org.dam.Modelo.Motor;
import org.dam.util.JpaUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAO {
    private static final Scanner sc = new Scanner(System.in);
    private static final EntityManager em =  JpaUtil.getEntityManager();

    public static void mostrarTodo() {
        List<Mecanico> lista = JpaUtil.getEntityManager().createQuery("FROM Mecanico").getResultList();
        if (lista != null) {
            for (Mecanico m : lista) {
                System.out.println(m);
            }
        } else {
            System.out.println("No se ha encontrado ningún mecanico");
        }
    }

    public static void mostrarTodoRecursivo() {
        List<Mecanico> lista = JpaUtil.getEntityManager().createQuery("FROM Mecanico").getResultList();
        if (lista != null) {
            for (Mecanico m : lista) {
                ArrayList<Motor> motors = new ArrayList<>();
                m.getMecanicoMotores().forEach(mecanicoMotor -> {motors.add(mecanicoMotor.getMotor());});

                System.out.println(m+", Motores: "+motors);
            }
        } else {
            System.out.println("No se ha encontrado ningún mecanico");
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
    }

    static void insertar() {
        System.out.println("\n--- Insertar datos en tabla 'Mecanico' ---");
        System.out.print("Introduce el Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce los Años de experiencia: ");
        int aniosExperiencia = sc.nextInt();
        System.out.print("Introduce el taller: ");
        String taller = sc.nextLine();

        Mecanico mecanico = new  Mecanico();
        mecanico.setNombre(nombre);
        mecanico.setExperiencia_anios(aniosExperiencia);
        mecanico.setTaller(taller);

        em.persist(mecanico);
    }

    static void actualizar() {
        System.out.println("\n--- Actualizar datos en tabla 'Mecanico' ---");
        System.out.print("Introduce el ID a actualizar: ");
        Long ID = sc.nextLong();
        System.out.print("Introduce los Años de experiencia: ");
        int aniosExperiencia = sc.nextInt();
        System.out.print("Introduce el taller: ");
        String taller = sc.nextLine();
    }

    static void eliminar() {

    }
}
