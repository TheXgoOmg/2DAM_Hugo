package org.dam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.dam.DAO.DAO;
import org.dam.util.JpaUtil;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();


        System.out.println("\n\nHaz 'help' para ver los posibles comandos");

        String accion="";

        while (!accion.equals("exit")) {
            System.out.print("> ");
            accion = sc.nextLine();

            switch (accion) {
                case "help":
                    System.out.println("--- Comandos ---");
                    System.out.println("muestra Chasis - Muestra todos los datos de Chasis");
                    System.out.println("muestra -r Chasis - Muestra todos los datos de Chasis de manera recursiva");
                    System.out.println("muestra Mecanico - Muestra los datos de Mecanico según su ID");
                    System.out.println("insertar - Inserta un nuevo Mecanico en la BD");
                    System.out.println("actualizar - Actualizas los datos de un Mecanico según su ID");
                    System.out.println("eliminar - Eliminas un Mecanico de la BD degún su ID");
                    System.out.println("exit - Salir del programa");
                    break;
                case "muestra Chasis":
                    DAO.mostrarTodo(em);
                    break;
                case "muestra -r Chasis":
                    DAO.mostrarTodoRecursivo(em);
                    break;
                case "muestra Mecanico":
                    DAO.mostrar_Mecanico();
                    break;
                case "insertar":
                    DAO.insertar(em, tx);
                    break;
                case "actualizar":
                    DAO.actualizar(em, tx);
                    break;
                case "eliminar":
                    DAO.eliminar(em, tx);
                    break;
            }
        }
    }
}