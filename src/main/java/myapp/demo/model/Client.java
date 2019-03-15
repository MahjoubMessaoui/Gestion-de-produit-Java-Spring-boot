package myapp.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;


@Entity

public class Client extends Personne {
    private String adresse;

    @OneToMany(mappedBy ="client")
    private List<Commande> cmd;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
@JsonIgnore
    public List<Commande> getCmd() {
        return cmd;
    }


    public void setCmd(List<Commande> cmd) {
        this.cmd = cmd;
    }

    public Client() {

    }


    public Client(String nom, String prenom, double phonne, String file, Date createdAt, Date updatedAt, String adresse, List<Commande> cmd) {
        super(nom, prenom, phonne, file, createdAt, updatedAt);
        this.adresse = adresse;
        this.cmd = cmd;
    }
}
