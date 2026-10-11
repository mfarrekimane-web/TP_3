
package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    public boolean datesValides() {
        return dateDebut != null
                && dateFin != null
                && dateDebut.isBefore(dateFin);
    }
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = false)
    private Salle salle;

    public Reservation() {
    }

    public Reservation(LocalDate dateReservation, Utilisateur utilisateur, Salle salle) {
        this.dateReservation = dateReservation;
        this.utilisateur = utilisateur;
        this.salle = salle;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }


    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
}