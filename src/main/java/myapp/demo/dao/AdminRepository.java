package myapp.demo.dao;

import myapp.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("select a  from Admin a where a.login= :login and a.motpasse= :motpasse")
    Admin login(@Param("login") String login ,@Param("motpasse") String motpasse);


}
