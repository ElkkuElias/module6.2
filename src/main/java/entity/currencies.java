package entity;


public class currencies {

    private int id;

    public double rate;

    public String name;

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
