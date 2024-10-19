public class Payment {
    private User user;
    private double amount;

    public Payment(User user, double amount){
        this.user = user;
        this.amount=amount;
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public double getAmount(){
        return this.amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
}
