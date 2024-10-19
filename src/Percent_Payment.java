public class Percent_Payment extends Payment{
    private double percent;

    public Percent_Payment(User user, double amount, double percent){
        super(user,amount);
        this.percent=percent;
    }

    public double getPercent(){
        return this.percent;
    }
    public void setPercent(double percent){
        this.percent = percent;
    }
    public User getUser(){
        return super.getUser();
    }
    public void setUser(User user){
        super.setUser(user);
    }
    public double getAmount(){
        return super.getAmount();
    }
    public void setAmount(double amount){
        super.setAmount(amount);
    }

}
