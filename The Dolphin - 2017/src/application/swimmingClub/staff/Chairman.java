package application.swimmingClub.staff;

import application.swimmingClub.Member;
import application.swimmingClub.SwimmingClub;
import application.swimmingClub.database.FileHandler;

import java.util.ArrayList;
import java.util.List;

//the Coach staff member, responsible for checking the disciplines and the top five swimmers
public class Chairman extends Staff{

    //create the object with the default user credentials
    public Chairman(List<Member> memberList) {
        super(memberList, "ch", "ch");
    }

    public void menu() {

        while (true) {

            System.out.println("\n------------ " + YELLOW + "Chairman Menu" + RESET + " ------------\n");

            switch (chooseMenuOptions(BLUE + "1. Create Membership\n" + PURPLE + "2. Show Memberships\n" + RED + "3. [Log out]\n" + RESET,1, 4)) {
                case 1://user chose to create a new membership
                    createMembership();
                    break;
                case 2://user chose to see all the members of the club
                    showAllMembers();
                    break;
                case 3://user chose to log out
                    return;
            }
        }
    }

    private void createMembership() {

        // customer's personal information and preferences
        String firstName;
        String lastName;
        int age;
        String email;
        String activityForm;
        String swimmerProgramme;
        String discipline;

        System.out.println("\n------------ " + GREEN + "Create Membership" + RESET + " ------------\n");

        firstName = validNameInput("First name: ");
        lastName  = validNameInput("Last Name: ");
        age = chooseMenuOptions(CYAN + "Age[0-100]: " + RESET, 0, 100);

        while(true) {

            System.out.print(CYAN + "Email: " + RESET);
            email = getInputString();
            if (!email.contains("@")){
                System.out.println(RED + "Email does not contain an at sign (\"@\")" + RESET);
            }
            else if (!email.contains(".com") && !email.contains(".dk")){
                System.out.println(RED + "Email does not contain a valid domain ending(eg: \".com\"; \".dk\")" + RESET);
            }
            else{
                List<String> memberEmails = new ArrayList<>();
                for (int i = 0; i < memberList.size(); i++) {
                    memberEmails.add(memberList.get(i).getEmail());
                }
                if (memberEmails.contains(email)){
                    System.out.println(RED + "Email found in database, try a different one!" + RESET);
                }
                else{
                    break;
                }
            }
        }
        // loop for checking the input for activityForm
        while (true) { // run until valid activityForm is provided
            System.out.println(CYAN + "Choose activity form: " + RESET);
            System.out.println(BLUE + "1. Active");
            System.out.println("2. Passive" + RESET);

            int choice = getInputInt();

            if (choice == 1) {
                activityForm = "active";
                break;
            }else if (choice == 2) {
                activityForm = "passive";
                break;
            }else {
                System.out.println(RED + "Incorrect input! Try again." + RESET);
            }
        }

        // loop for checking the input for swimmerProgramme
        while (true) { // run until valid swimmerProgramme is provided

            System.out.println(CYAN + "Choose programme: " + RESET);
            System.out.println(BLUE + "1. Elite");
            System.out.println("2. Exercise" + RESET);

            int choice = getInputInt();

            if (choice == 1) {
                swimmerProgramme = "elite";
                break;
            }else if (choice == 2) {
                swimmerProgramme = "exercise";
                break;
            }else {
                System.out.println(RED + "Incorrect input! Try again." + RESET);
            }
        }

        if (swimmerProgramme.equals("elite")) {
            discipline = chooseDiscipline();
        }
        else {
            discipline = "None";
        }

        Member createdMember = new Member(firstName, lastName, age, email, activityForm, swimmerProgramme, discipline, SwimmingClub.getRandLapTime());

        //member overview
        showMemberOverview(createdMember);

        //confirm dialog
        System.out.println();
        System.out.println(BLUE + "1. Confirm" + RESET);
        System.out.println(RED + "2. Discard" + RESET);

        if (chooseMenuOptions(1, 3) == 2){
            System.out.println(RED + "Member request cancelled!" + RESET);
            return;
        }
        else{

            //add a new member with a random best lap from 0.00 to 400.00
            memberList.add(createdMember);

            System.out.println();

            // write members in file
            FileHandler.writeToFile(memberList);

            System.out.println(BLUE + "Member created!" + RESET);
        }

    }

    //print the overview of the member being created
    private static void showMemberOverview(Member member){

        System.out.printf (YELLOW + "Name:              %s %s%n", PURPLE + member.getFirstName(), member.getLastName());
        System.out.println(YELLOW + "Age:               " + PURPLE + member.getAge());
        System.out.println(YELLOW + "Email:             " + PURPLE + member.getEmail());
        System.out.println(YELLOW + "Activity Form:     " + PURPLE + member.getActivityForm());
        System.out.println(YELLOW + "Programme:         " + PURPLE + member.getSwimmerProgramme());
        System.out.println(YELLOW + "Type:              " + PURPLE + member.getSwimmerType());
        System.out.println(YELLOW + "Discipline:        " + PURPLE + member.getDiscipline());
        System.out.println(YELLOW + "Subscription fee:  " + PURPLE + member.getPaymentAmount() + RESET);
    }

    //gets user input, checks it for invalid characters, formats the name and returns it
    private static String validNameInput(String prompt){

        //create the name String with a default value
        String name = "";

        //boolean instantiated to false
        boolean validInput = false;
        //loop keeps going until the validInput boolean remains true at the end of the loop
        while(!validInput) {
            System.out.print(CYAN + prompt + RESET);
            //get the string input from the user
            name = getInputString();
            //checks if the name is less than 3 characters
            if (name.length() < 3) {
                System.out.println(RED + "Name is too short!" + RESET);
                continue;
            }
            //sets the boolean to true, and if everything goes right, it will remain true
            validInput = true;
            //remove all the empty spaces in the input
            name = name.replace(" ", "");
            //set the name to lower case, to simplify the checking for alphabetic characters
            name = name.toLowerCase();
            for (int i = 0; i < name.length(); i++) {
                //the current char of the name is alphabetic, so keep the boolean true
                if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
                    validInput = true;
                }
                //the current char is not alphabetic, so print an error message, set the boolean to true(so the loop runs the next iteration)
                //and break this for loop
                else {
                    System.out.println(RED + "Name contains invalid characters! Try again!" + RESET);
                    validInput = false;
                    break;
                }
            }
            //if the input entered was considered to be valid,
            //set the first letter to uppercase and break the while loop, ending the method
            if (validInput) {
                name = (char) (name.charAt(0) - 32) + name.substring(1);
                break;
            }
        }

        return name;
    }
}
