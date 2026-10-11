
package com.example;

import com.example.model.*;
import javax.persistence.*;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("gestion-reservations-pu");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // 1. Créer un utilisateur
            Utilisateur user = new Utilisateur();
            user.setNom("Alice Dupont");
            user.setEmail("alice@example.com");

            // 2. Créer une salle
            Salle salle = new Salle();
            salle.setNom("Salle A");
            salle.setCapacite(20);

            em.persist(salle);

            // 3. Créer une réservation
            Reservation res = new Reservation();
            res.setDateReservation(LocalDate.now());
            res.setSalle(salle);

            // Associer la réservation à l'utilisateur
            user.addReservation(res);

            Reservation r = new Reservation();

            r.setDateDebut(LocalDate.of(2026, 10, 12));
            r.setDateFin(LocalDate.of(2026, 10, 15));

            System.out.println(r.datesValides());
            // 4. Enregistrer l'utilisateur et sa réservation
            em.persist(user);

            em.getTransaction().commit();

            System.out.println("Utilisateur et réservation enregistrés !");

            // 5. Supprimer l'utilisateur
            em.getTransaction().begin();

            Utilisateur utilisateurASupprimer =
                    em.find(Utilisateur.class, user.getId());

            em.remove(utilisateurASupprimer);

            em.getTransaction().commit();

            System.out.println("Utilisateur supprimé !");
            System.out.println("Ses réservations sont supprimées en cascade !");

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}