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
        tx.begin();

        System.out.print("> ");
        String accion = sc.nextLine();

        switch (accion) {
            case "muestra Mecanico":
                DAO.mostrarTodo();
                break;
            case "muestra -r Mecanico":
                DAO.mostrarTodoRecursivo();
                break;
        }
    }
}