import jakarta.persistence.*;
import modelo.poblaciones;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("comunidadPA");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        poblaciones nuevaPoblacion = new poblaciones();
        try {
            tx.begin();

            // CREATE - Insertar nueva población

            System.out.println("\n--- CREATE - Insertar nuevo alumno ---");

            nuevaPoblacion.setParent_code(46);
            nuevaPoblacion.setLabel("Marines Vell");
            em.persist(nuevaPoblacion);
            System.out.println("Nueva población " + nuevaPoblacion.getTown_code() + " " + nuevaPoblacion.getLabel());


            // Consultar todas las poblaciones
            System.out.println("\n--- Todas las poblaciones de ----");
            TypedQuery<poblaciones> q1 = em.createQuery("SELECT a FROM poblaciones a", poblaciones.class);
            List<poblaciones> todaslaspoblaciones = q1.getResultList();
            for (poblaciones alumno : todaslaspoblaciones) {
                System.out.println(alumno);
            }


            tx.commit();

            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        tx = em.getTransaction();


        try {
            tx.begin();
            // 'nuevaPoblacion' quedó DETACHED tras el commit anterior.
            nuevaPoblacion.setLabel("Marines Vell (actualizada)");
            // Si también quieres cambiar la provincia (FK), ajusta el parent_code:
            // nuevaPoblacion.setParent_code(46);

            // merge reatacha la entidad y sincroniza sus cambios con la BBDD
            poblaciones gestionada = em.merge(nuevaPoblacion);
            System.out.println("Población actualizada (merge): " + gestionada);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }


        // Consultar todas las poblaciones (Para ver la actualización)

        System.out.println("\n--- Todas las poblaciones de ----");
        TypedQuery<poblaciones> q1 = em.createQuery("SELECT a FROM poblaciones a", poblaciones.class);
        List<poblaciones> todaslaspoblaciones = q1.getResultList();
        for (poblaciones alumno : todaslaspoblaciones) {
            System.out.println(alumno);
        }



        // Borrado

        tx = em.getTransaction();

        try {
            tx.begin();
            Integer idEliminar = nuevaPoblacion.getTown_code();

            poblaciones existente = em.find(poblaciones.class, idEliminar);
            if (existente != null) {
                em.remove(existente);
                System.out.println("Población eliminada: town_code=" + idEliminar);
            } else {
                System.out.println("No existe población con town_code=" + idEliminar);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }


        // --- UPDATE (merge): Gátova -> GÁTOVA en la provincia de València ---
        tx = em.getTransaction();
        tx.begin();
        try {
            String provinciaValencia = "Valencia/València";
            String nombreOriginal = "Gátova";

            poblaciones gatova = em.createQuery(
                            "SELECT p FROM poblaciones p JOIN p.provincia pr WHERE pr.label = :prov AND p.label = :nombre",
                            poblaciones.class
                    ).setParameter("prov", provinciaValencia)
                    .setParameter("nombre", nombreOriginal)
                    .getSingleResult();

            // Opción demostrativa con merge: creamos una instancia DETACHED con la PK y nuevos valores
            em.detach(gatova);
            poblaciones gatovaDet = new poblaciones();
            gatovaDet.setTown_code(gatova.getTown_code());
            gatovaDet.setParent_code(gatova.getParent_code());
            gatovaDet.setLabel(gatova.getLabel().toUpperCase());

            poblaciones actualizada = em.merge(gatovaDet);
            System.out.println("Actualizada: " + actualizada);

            tx.commit();
        } catch (jakarta.persistence.NoResultException nre) {
            if (tx.isActive()) tx.rollback();
            System.out.println("No se encontró 'Gátova' en la provincia de València.");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
// --- DELETE: eliminar 'Casinos' en la provincia de València ---
        tx = em.getTransaction();
        tx.begin();
        try {
            String provinciaValencia = "Valencia/València";
            String nombreEliminar = "Casinos";

            poblaciones casinos = em.createQuery(
                            "SELECT p FROM poblaciones p JOIN p.provincia pr WHERE pr.label = :prov AND p.label = :nombre",
                            poblaciones.class
                    ).setParameter("prov", provinciaValencia)
                    .setParameter("nombre", nombreEliminar)
                    .getSingleResult();

            em.remove(casinos);
            System.out.println("Eliminada población: " + casinos.getLabel() + " (ID " + casinos.getTown_code() + ")");

            tx.commit();
        } catch (jakarta.persistence.NoResultException nre) {
            if (tx.isActive()) tx.rollback();
            System.out.println("No se encontró 'Casinos' en la provincia de València.");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }



// Cierre de recursos
        em.close();
        emf.close();


    }
}