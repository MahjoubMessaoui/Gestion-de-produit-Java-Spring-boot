package myapp.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity


public class Livreur extends Personne {

    private String position;
    @OneToMany(mappedBy = "livreur")
    private List<Commande> cmd;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @JsonIgnore
    public List<Commande> getCmd() {
        return cmd;
    }

    public void setCmd(List<Commande> cmd) {
        this.cmd = cmd;
    }

    public Livreur() {

    }


    public Livreur(String nom, String prenom, double phonne, String file, Date createdAt, Date updatedAt, String position, List<Commande> cmd) {
        super(nom, prenom, phonne, file, createdAt, updatedAt);
        this.position = position;
        this.cmd = cmd;
    }
}
