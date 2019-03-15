package myapp.demo.model;

import javax.persistence.Entity;

import java.util.Date;
@Entity
public class Admin extends Personne {

    private String login;
    private String motpasse;



    public Admin() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }



    public Admin(String nom, String prenom, double phonne, String file, Date createdAt, Date updatedAt, String login, String motpasse) {
        super(nom, prenom, phonne, file, createdAt, updatedAt);
        this.login = login;
        this.motpasse = motpasse;
    }
}
