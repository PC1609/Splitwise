import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Balance {
    List<Expenditure> expenditures;
    Map<String, User> userMap;
    Map<User, Map<User, Double>> balanceSheet;

    public Balance(){
        this.expenditures = new ArrayList<Expenditure>();
        this.userMap= new HashMap<String,User>();
        this.balanceSheet= new HashMap<User, Map<User, Double>>();
    }

    public void addUser(String payeeId,String name, String phoneNumber){
        userMap.put(payeeId, new User(payeeId,name,phoneNumber));
        balanceSheet.put(userMap.get(payeeId), new HashMap<User,Double>());
    }
    public void addExpenditure(Expenditure_type type,String payeeId, double amount_paid, String description, List<Payment> payments){
        User payee = userMap.get(payeeId);
        Expenditure newExpenditure = Expenditure_creation.create_Expenditure(type,payeeId,amount_paid,description,payments);
        expenditures.add(newExpenditure);


        for (Payment payment : newExpenditure.getPaymentsList()){
            User notPay = payment.getUser();
            Map<User, Double> balance_map = balanceSheet.get(payee);
            if (!balance_map.containsKey(notPay)){
                balance_map.put(notPay,0.0);
            }
            balance_map.put(notPay, balance_map.get(notPay)+payment.getAmount());

            balance_map = balanceSheet.get(notPay);
            if (!balance_map.containsKey(payee)){
                balance_map.put(payee,0.0);
            }
            balance_map.put(payee,balance_map.get(payee)-payment.getAmount());
        }
    }
    public void showBalance(String userId){
        User thisUser = userMap.get(userId);
        if (thisUser==null){
            System.out.println("Invalid userId");
            return;
        }
        for (Map.Entry<User, Double> entry : balanceSheet.get(thisUser).entrySet()){
            if (entry.getKey()!= thisUser){
                if (entry.getValue()!=0){
                    if (entry.getValue()<0){
                        System.out.println(userMap.get(userId).getName() + " owes Rs. " + Math.abs(entry.getValue()) + " to " + entry.getKey().getName());
                    }else{
                        System.out.println(entry.getKey().getName() + " owes Rs. " + entry.getValue() + " to " + userMap.get(userId).getName());
                    }
                }
            }
        }
    }
}
