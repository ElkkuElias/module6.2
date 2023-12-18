package entity;
import jakarta.persistence.*;

@Entity
@Table(name="currencies")

public class currencies {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="rate")
    public double rate;

    @Column(name="name")
    public String name;
    @Column(name="abbreviation")
    public String abbreviation;

    public currencies(String name, String abbreviation, double rate){
        super();
        this.name = name;
        this.abbreviation = abbreviation;
        this.rate = rate;
    }

    public currencies() {

    }

    public double getRate(){
        return this.rate;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return id;
    }

    public String calculate(String amount){
        double amountDouble = Double.parseDouble(amount);
        double result = amountDouble * this.rate;
        return String.valueOf(result);
    }
    public String getAbbreviation(){
        return this.abbreviation;
    }



}
