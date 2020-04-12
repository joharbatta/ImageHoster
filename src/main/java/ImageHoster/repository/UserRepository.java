package ImageHoster.repository;

import ImageHoster.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

public class UserRepository {

    @PersistenceUnit(unitName = "imagehoster")
    private EntityManagerFactory emf;


    public void registerUser(User newUser) {
        EntityManager em = emf.createEntityManager();
    }
}