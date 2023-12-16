package dao;
import jakarta.persistence.EntityManager;
import entity.*;
import jakarta.persistence.TypedQuery;
public class CurrencyDao {



    public double find(String abbreviation) {
        if (abbreviation == null || abbreviation.isEmpty()) {
            throw new IllegalArgumentException("Abbreviation cannot be null or empty");
        }

        EntityManager em = datasource.MariaDBJPAConnection.getInstance();
        if (em == null) {
            throw new IllegalStateException("EntityManager is null");
        }
        TypedQuery<currencies> query = em.createQuery("SELECT c FROM currencies c WHERE c.abbreviation = :abbreviation", currencies.class);
        query.setParameter("abbreviation", abbreviation);
        currencies result = query.getSingleResult();

        if (result != null) {
            return result.getRate();
        } else {
            throw new IllegalArgumentException("No currency found with the provided abbreviation");
        }
    }


}
