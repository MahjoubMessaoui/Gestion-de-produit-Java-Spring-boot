package myapp.demo.dao;

import myapp.demo.model.Commande;
import myapp.demo.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {

    @Query("SELECT c from Commande c where c.livreur= :idliv  ")
    List<Commande> getbyliv(@Param("idliv") Livreur idliv);
}
