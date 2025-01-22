package welcome;

import jakarta.persistence.*;

public class Welcome {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("welcome");
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();

    }

}