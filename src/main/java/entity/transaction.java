package entity;
import jakarta.persistence.*;

@Entity
@Table(name="transaction")
public class transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="amount")
    public double amount;

    @OneToOne
    private currencies fromCurrency;

    @OneToOne
    private currencies toCurrency;
    public transaction(double amount, currencies fromCurrency, currencies toCurrency){
        super();
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;

    }
    public transaction() {

    }

    public currencies getFromCurrency(){
        return this.fromCurrency;
    }
    public currencies getToCurrency(){
        return this.toCurrency;
    }
    public double getAmount(){
        return this.amount;
    }


    public int getId(){
        return id;
    }
}
