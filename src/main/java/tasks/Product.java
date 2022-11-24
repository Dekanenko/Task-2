package tasks;

public class Product {

     public enum Measurements {
         KILO("Kilo"),
         UNIT("Unit");

         private String textM;

         Measurements(String text){
             this.textM = text;
         }

         public String getText(){
             return textM;
         }
     };

    private String name;
    private double amount = 0;
    private Measurements measure;
    private double price = 0;

    public Product() {
    }

    public Product(String name, double amount, Measurements measure, double price) {
        this.name = name;
        this.amount = amount;
        this.measure = measure;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getMeasure() {
        return measure.getText();
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMeasure(Measurements measure) {
        this.measure = measure;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name:"+name+"\tamount:"+amount+"\tmeasure:"+measure.getText()+"\tprice:"+price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (Double.compare(product.getAmount(), getAmount()) != 0) return false;
        if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
        if (!getName().equals(product.getName())) return false;
        return getMeasure().equals(product.getMeasure());
    }
}
