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

        System.out.print("> ");
        String accion = sc.nextLine();

        switch (accion) {
            case "muestra Chasis":
                DAO.mostrarTodo(em);
                break;
            case "muestra -r Chasis":
                DAO.mostrarTodoRecursivo(em);
                break;
        }

//        DAO.mostrar_Mecanico();

//        DAO.insertar(em, tx);

//        DAO.actualizar(em, tx);

//        DAO.eliminar(em, tx);
    }
}