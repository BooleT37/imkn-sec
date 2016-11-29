package ru.naumen.entities;

/**
 * @author aarkaev
 * @since 19.11.2016
 */
public class Scholarship {

    private Student student;
    private double amount;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Scholarship{" + "student=" + student + ", amount=" + amount + '}';
    }
}
