package dao;
import jakarta.persistence.EntityManager;
import entity.*;

public class TransactionDao {
    public transaction persist(transaction transaction) {
        EntityManager em = datasource.MariaDBJPAConnection.getInstance();
        if (em == null) {
            throw new IllegalStateException("EntityManager is null");
        }
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        return transaction;
    }
}
