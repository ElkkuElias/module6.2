package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.persistence.*;

public class MariaDBJPAConnection {


    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getInstance() {
        if (em == null) {

            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("currencyMariaDbUnit");
                if (emf == null){
                    System.out.println("emf is null");
                    return null;
                }
                System.out.println("Creating EntityManagerFactory");
            }
        em = emf.createEntityManager();
        System.out.println("Creating EntityManager");
    }
    return em;
    }
}
