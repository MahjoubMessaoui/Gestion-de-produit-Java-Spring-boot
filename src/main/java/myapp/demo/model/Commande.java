package myapp.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"},
        allowGetters = true)
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String price;
    private String qnt;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


    @ManyToOne()
    @JoinColumn(name = "commande_id")
    private Client client;

    @ManyToMany
    @JoinTable(name = "commande_produit",
       joinColumns =@JoinColumn(name = "command_id",referencedColumnName = "ID"),
       inverseJoinColumns = @JoinColumn(name = "produit_ID",referencedColumnName = "ref"))

       private List<Produit> produit;

    @ManyToOne
    @JoinColumn(name ="commande_liv" )
    private Livreur livreur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Produit> getProduit() {
        return produit;
    }

    public void setProduit(List<Produit> produit) {
        this.produit = produit;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Commande(String price, String qnt, Date createdAt, Date updatedAt, Client client, List<Produit> produit, Livreur livreur) {
        this.price = price;
        this.qnt = qnt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
        this.produit = produit;
        this.livreur = livreur;
    }

    public Commande() {

    }
}
