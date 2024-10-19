import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splitwise {
    public static void main(String[] args) {
        Balance balance = new Balance();

        balance.addUser("priya", "Priyanka Choudhary", "+70604");
        balance.addUser("tina","Tina Saran", "+99087");
        balance.addUser("muku", "Mukesh Repaswal", "+88077");
        balance.addUser("sumit", "Sumit C", "+94133");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Please choose [1] New Expense [2] Show balance");
            String input = scanner.nextLine();
            switch (input){
                case "1" :
                    createNewExpenditure(balance,scanner);
                    break;
                case "2":
                    showTheBalance(balance,scanner);
                    break;
                default:
                    System.out.println("Invalid choice");
                    return;
            }
        }
    }
    public static void createNewExpenditure(Balance balance, Scanner scanner){
        System.out.println("Please select expenditure type [1] EQUAL [2] PERCENT ");
        String type = scanner.nextLine();
        switch (type){
            case "1":
                createEqualExpenditure(balance, scanner);
                break;
            case "2":
                createPercentExpenditure(balance,scanner);
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
    }
    public static void createEqualExpenditure(Balance balance, Scanner scanner){
        System.out.println("Enter number of users");
        int noOfUsers = Integer.parseInt(scanner.nextLine());
        System.out.println("Amount was paid by?");
        String payeeUserId = scanner.nextLine();
        System.out.println("Total amount paid was?");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("The amount was spent on?");
        String label = scanner.nextLine();
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(balance.userMap.get(payeeUserId), amount));
        for (int i=1; i<noOfUsers; i++){
            System.out.println("Enter ID of Participant"+ (i+1));
            String participantId = scanner.nextLine();
            payments.add(new Payment(balance.userMap.get(participantId),amount));
        }
        balance.addExpenditure(Expenditure_type.EQUAL, payeeUserId, amount,label,payments);
        balance.showBalance(payeeUserId);
    }

    public static void createPercentExpenditure(Balance balance, Scanner scanner){
        System.out.println("Enter number of users");
        int noOfUsers = Integer.parseInt(scanner.nextLine());
        System.out.println("Amount was paid by?");
        String payeeUserId = scanner.nextLine();
        System.out.println("Total amount paid was?");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter percentage shared by "+ payeeUserId);
        double percentLeft = 100.0;
        double percentPayee = Double.parseDouble(scanner.nextLine());
        percentLeft -= percentPayee;
        System.out.println("The amount was spent on?");
        String label = scanner.nextLine();
        List<Payment> payments = new ArrayList<>();
        payments.add(new Percent_Payment(balance.userMap.get(payeeUserId), amount, percentPayee));
        for (int i=1; i<noOfUsers; i++){
            System.out.println("Enter ID of Participant"+ (i+1));
            String participantId = scanner.nextLine();
            System.out.println("Enter percentage shared by "+ participantId);
            double percentParticipant = Double.parseDouble(scanner.nextLine());
            percentLeft -= percentParticipant;
            if (percentLeft<0){
                System.out.println("Incorrect percentage share of users");
                return;
            }
            if (balance.userMap.get(participantId)!=null){
                payments.add(new Percent_Payment(balance.userMap.get(participantId),amount, percentParticipant));
            }
            else {
                System.out.println("Invalid User ID");
                return;
            }
        }
        balance.addExpenditure(Expenditure_type.PERCENT, payeeUserId, amount,label,payments);
        balance.showBalance(payeeUserId);
    }

    public static void showTheBalance(Balance balance, Scanner scanner){
        System.out.println("Enter the user whose balance you want to see");
        String userId = scanner.nextLine();
        balance.showBalance(userId);
    }
}
