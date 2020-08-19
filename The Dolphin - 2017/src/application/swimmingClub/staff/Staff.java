package application.swimmingClub.staff;

import application.swimmingClub.Member;
import application.swimmingClub.SwimmingClub;
import application.swimmingClub.database.FileHandler;

import java.util.List;
import java.util.Scanner;

public class Staff {

    //static scanner for handling input
    private static Scanner input = new Scanner(System.in);

    //credentials
    private String username;
    private String password;

    //console colors
    public static final String RESET      = "\u001B[0m";
    public static final String RED        = "\u001B[31m";
    public static final String GREEN      = "\u001B[32m";
    public static final String CYAN       = "\u001B[36m";
    public static final String YELLOW     = "\u001B[33m";
    public static final String PURPLE     = "\u001B[35m";
    public static final String BLUE       = "\u001B[34m";
    public static final String DARK_GREEN = "\u001B[1;32m";
    public static final String BLACK      = "\u001B[30m";
    public static final String BOLD       = "\u001B[1m";

    //the list of members accessible throughout the program
    static List<Member> memberList;

    //create the Staff object with the credentials chosen by the subclasses (Chairman, Treasurer & Coach)
    Staff(List<Member> memberList, String username, String password) {

        this.username = username;
        this.password = password;
        Staff.memberList = memberList;
    }

    //default method, overloaded by the subclasses
    public void menu(){}

    //menu to choose a discipline from a list, used for showing the top 5 swimmers
    //and when choosing a discipline during member creation
    String chooseDiscipline(){

        //print the disciplines
        System.out.println(CYAN + "Select discipline: ");
        System.out.println(BLUE + "1. Backstroke");
        System.out.println("2. Freestyle");
        System.out.println("3. Butterfly" + RESET);

        while (true) { // run until valid discipline is provided

            //if the user entered a valid number from the list, return the element of that index and break the loop
            //if not, return null and continue the loop
            String chosenDiscipline;

            int choice = chooseMenuOptions(0, 4);

            if (choice == 1) {
                chosenDiscipline = "Backstroke";
            }
            else if (choice == 2) {
                chosenDiscipline = "Freestyle";
            }
            else if (choice == 3) {
                chosenDiscipline = "Butterfly";
            }
            else {
                System.out.println(RED + "Incorrect input! Try again." + RESET);
                chosenDiscipline = null;
            }
            if (chosenDiscipline != null) {
                return chosenDiscipline;
            }
        }
    }

    //prints the disciplines, along with all the members belonging to each one
    void showDisciplines(){

        String[] disciplines = SwimmingClub.DISCIPLINES;

        System.out.println(BLUE + "Disciplines:" + RESET);

        //iterate through each discipline and print its name, followed by a list of member belonging to that discipline
        //also skip printing the members from the "None" discipline(index 0) by starting from the index of 1
        for(int i = 1; i < disciplines.length; i++) {

            System.out.print(CYAN + disciplines[i] + ": " + RESET);

            //boolean used to print the first member of a discipline
            //used in order to avoid having a comma after the colons
            //because the first member should be printed by the loop without a leading comma, unlike the rest
            boolean firstMember = true;

            //print each member of that discipline in a single line
            for (int j = 0; j < memberList.size(); j++) {

                if (memberList.get(j).getDiscipline().equals(disciplines[i])) {
                    if (firstMember) {
                        System.out.print(memberList.get(j).getName());
                        firstMember = false;
                    } else {
                        System.out.print(", " + memberList.get(j).getName());
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        advance();
    }

    // read the member from the file and print them inside a table
    static void showAllMembers() {

        memberList = FileHandler.readFromFile();
        showMembers(memberList);
        advance();
    }

    //print the table containing a specified list of members
    static void showMembers(List<Member> members){

        //long horizontal line by the length of the table
        String divider = "*────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────*";

        //String containing the format for the members
        String tableFormat = "│ %5s │ %10s │ %10s │ %3s │ %25s │ %12s │ %9s │ %6s │ %6s │ %12s │ %10s │%n";

        //print the table header
        System.out.println(divider);
        System.out.print(BOLD); // color - active
        System.out.format(tableFormat, "Index", "First Name", "Last Name", "Age", "Email", "ActivityForm", "Programme", "Type", "Fee", "Subscription", "Discipline");
        System.out.print(RESET); // color - RESET
        System.out.println(divider);

        //print each member in the format of the table
        for (int index = 0; index < members.size(); index++) {

            //store the current member in a temporary variable
            Member currentMember = members.get(index);

            String subscriptionText;
            //add extra white spaces to balance the length, as it is affected by the color codes
            if (members.get(index).isSubscriptionPaid()) {
                subscriptionText = GREEN + "        Paid" + RESET;
            }
            else{
                subscriptionText =   RED + "      Unpaid" + RESET;
            }

            //print the member fields
            System.out.printf(tableFormat, index, currentMember.getFirstName(), currentMember.getLastName(), currentMember.getAge(), currentMember.getEmail(),
                              currentMember.getActivityForm(), currentMember.getSwimmerProgramme(), currentMember.getSwimmerType(), currentMember.getPaymentAmount(),
                              subscriptionText, currentMember.getDiscipline());
            //print the table divider
            System.out.println(divider);
        }
    }

    // get a string input from the user
    public static String getInputString() {
        return input.nextLine();
    }

    // function returns valid options to be used in Menu. Exp: 1,2,3
    public static int chooseMenuOptions(int min, int max) {
        return chooseMenuOptions(null, min, max);
    }

    //return an integer choice bound by the min and max values
    public static int chooseMenuOptions(String prompt, int min, int max)
    {
        int number = getInputInt(prompt);

        //check if the input is bound by the min and max values
        if (number >= min && number < max) {
            return number;
        } else {
            System.out.println(RED + "Invalid option" + RESET);
            return chooseMenuOptions(prompt, min, max);
        }
    }

    // get the first valid number input from the user
    static int getInputInt(String prompt){
        //if the prompt parameter is not null, print it
        if (prompt != null)
            System.out.print(prompt);
        //try getting the integer input from the user
        try{
            int choice = Integer.parseInt(input.next());
            //catch the newline character
            input.nextLine();
            return choice;
        }
        //catch the exception thrown when the user entered something other than an integer
        //and rerun the method recursively
        catch(NumberFormatException e)
        {
            System.out.println(RED + "Invalid Option!" + RESET);
            return getInputInt(prompt);
        }
    }

    //call the method without a prompt
    static int getInputInt() {
        return getInputInt(null);
    }

    //a prompt showing "press enter to continue"
    public static void advance()
    {
        System.out.println(BLACK + "Press enter to continue..." + RESET);
        input.nextLine();
    }

    //getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
