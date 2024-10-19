import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Expenditure {
    private String payeeId;
    private double amount_paid;
    private List<Payment> payments;
    private String description;

    public Expenditure( String payeeId,double amount_paid, List<Payment> payments, String description){
        this.payeeId = payeeId;
        this.amount_paid =amount_paid;
        this.payments = payments;
        this.description=description;
    }
    public String getPayee(){
        return this.payeeId;
    }
    public void setPayee(String payeeId){
        this.payeeId=payeeId;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public double getAmount_paid(){
        return this.amount_paid;
    }
    public void setAmount_paid(double amount_paid){
        this.amount_paid=amount_paid;
    }
    public List<Payment> getPaymentsList(){
        return this.payments;
    }
    public void setPayments(List<Payment> payments){
        this.payments=payments;
    }
}
