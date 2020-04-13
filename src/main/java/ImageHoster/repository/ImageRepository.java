package ImageHoster.repository;

import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class ImageRepository {

    @PersistenceUnit(unitName = "imagehoster")
    private EntityManagerFactory emf;


    public Image uploadImage(Image newImage) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newImage);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newImage;
    }


    public List<Image> getAllImages() {
        EntityManager em=emf.createEntityManager();
        TypedQuery<Image> query = em.createQuery("SELECT p from Image p", Image.class);
        List<Image> resultList = query.getResultList();
        return resultList;
    }


    public Image getImageByTitle(String title) {
        EntityManager em = emf.createEntityManager();
//        System.out.println(title);
        try {
            TypedQuery<Image> typedQuery = em.createQuery("SELECT i from Image i where i.title =:title", Image.class).setParameter("title", title);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
