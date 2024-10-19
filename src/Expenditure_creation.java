import java.util.List;

public class Expenditure_creation {
    public static Expenditure create_Expenditure(Expenditure_type type, String payeeId, double amount_paid, String description, List<Payment> payments){
        switch (type){
            case EQUAL :
                int noOfPeople = payments.size();
                double individual_amount = ((double) Math.round(amount_paid * 100 / noOfPeople)) / 100;
                double totalRoundedAmount = 0.0;
                for (Payment payment : payments){
                    payment.setAmount(individual_amount);
                    totalRoundedAmount += individual_amount;
                }
                payments.get(payments.size()-1).setAmount(individual_amount + amount_paid - totalRoundedAmount);
                return new Expenditure(payeeId, amount_paid,payments,description);

            case PERCENT :
                for (Payment payment: payments){
                    Percent_Payment percent_payment = (Percent_Payment) payment;
                    double percent = percent_payment.getPercent();
                    double individual_amount_percent = (amount_paid*percent)/100;
                    payment.setAmount(individual_amount_percent);
                }
                return new Expenditure(payeeId,amount_paid,payments,description);

            default:
                System.out.println("Invalid expense type");
                return null;
        }
    }
}
