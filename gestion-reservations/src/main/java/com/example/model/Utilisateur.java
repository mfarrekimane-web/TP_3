package com.example.model;

import com.example.model.Reservation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nom;

    @OneToMany(
            mappedBy = "utilisateur",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Reservation> reservations = new HashSet<>();


    public Utilisateur() {
    }

    public Utilisateur(String email, String nom) {
        this.email = email;
        this.nom = nom;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setUtilisateur(this);
    }


    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.setUtilisateur(null);
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}

