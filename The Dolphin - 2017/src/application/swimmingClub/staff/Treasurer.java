package application.swimmingClub.staff;

import application.swimmingClub.Member;
import application.swimmingClub.database.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class Treasurer extends Staff{

    //list to store the members who have not paid their subscription for this year
    private List<Member> unpaidMembers = new ArrayList<>();;

    //create the object with the default user credentials
    public Treasurer(List<Member> memberList) {
        super(memberList, "tr", "tr");
    }

    public void menu() {

        while (true) {

            System.out.println("\n------------ " + PURPLE + "Treasurer Menu" + RESET + " ------------\n");

            int choice = chooseMenuOptions(GREEN + "1. Make Payment\n" + YELLOW + "2. Show Unpaid Subscriptions\n" + RED + "3. [Log out]\n" + RESET,1, 4);

            switch (choice) {
                case 1://user chose to make payment for a member, show the list of unpaid members so the user can easily choose a member
                    showUnpaidSubscriptions();
                    makePayment();
                    break;
                case 2://user chose to just see the members who have not paid
                    showUnpaidSubscriptions();
                    advance();
                    break;
                case 3:
                    return;
            }
        }
    }

    //makes payment for a member
    private void makePayment() {

        System.out.println("Select member index: 0 - " + (unpaidMembers.size() - 1) );

        //get the index chosen by the user
        int choice = chooseMenuOptions(0, unpaidMembers.size());

        System.out.println("Payment amount: ");
        int amount = getInputInt();

        if (amount >= unpaidMembers.get(choice).getPaymentAmount()) { // check if inserted amount covers the subscription fees

            System.out.println(CYAN + "Payment finalized! Membership activated!" + RESET);
            unpaidMembers.get(choice).paySubscription(); // activate membership

        } else {
            System.out.println(RED + "Not enough money paid!" + RESET);

        }

        // write members in file
        FileHandler.writeToFile(memberList);
    }

    //creates the list of unpaid members, and shows them in a table
    private void showUnpaidSubscriptions() {

        unpaidMembers = new ArrayList<>();

        for (int i = 0; i < memberList.size(); i++) {

            if (!memberList.get(i).isSubscriptionPaid()) {

                unpaidMembers.add(memberList.get(i));
            }
        }

        showMembers(unpaidMembers);
    }
}
