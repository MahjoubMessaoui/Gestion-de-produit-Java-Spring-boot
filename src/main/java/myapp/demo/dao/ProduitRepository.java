package myapp.demo.dao;

import myapp.demo.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {

    @Query("select p from Produit p where p.ref= :ref")
    Produit getByRef(@Param("ref")Long ref);

    @Query("select d from Produit d where d.price= :price")
   List<Produit>getByPrice(@Param("price")String price);
}
