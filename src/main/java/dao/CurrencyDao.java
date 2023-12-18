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
    public currencies findCurrency(String abbreviation) {
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
            return result;
        } else {
            throw new IllegalArgumentException("No currency found with the provided abbreviation");
        }
    }
public currencies getCurrencybyID(int id){
        EntityManager em = datasource.MariaDBJPAConnection.getInstance();
        if (em == null) {
            throw new IllegalStateException("EntityManager is null");
        }
        TypedQuery<currencies> query = em.createQuery("SELECT c FROM currencies c WHERE c.id = :id", currencies.class);
        query.setParameter("id", id);
        currencies result = query.getSingleResult();
        return result;
}
public int getNumberOfCurrencies(){
EntityManager em = datasource.MariaDBJPAConnection.getInstance();
    if (em == null) {
        throw new IllegalStateException("EntityManager is null");
    }
    TypedQuery<currencies> query = em.createQuery("SELECT c FROM currencies c", currencies.class);
    int result = query.getResultList().size();
    return result;
}



    public currencies persist(currencies currency) {
        EntityManager em = datasource.MariaDBJPAConnection.getInstance();
        if (em == null) {
            throw new IllegalStateException("EntityManager is null");
        }
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
        return currency;
    }


}
