package entities;

public class Cashier {
    private String name;
    private int id;
    private double monthlySalary;

    public Cashier(String name, int id, double monthlySalary) {
        this.name = name;
        this.id = id;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    @Override
    public String toString() {
        return "Cashier{" +
               "name='" + name + '\'' +
               ", id=" + id +
               ", monthlySalary=" + monthlySalary +
               '}';
    }
}
