package application.swimmingClub;

import application.swimmingClub.staff.Staff;

import static application.swimmingClub.staff.Staff.*;

/*
    Handles login credentials
 */
public class Login {

    private Staff chairman;
    private Staff coach;
    private Staff treasurer;

    // constructor
    Login(Staff chairman, Staff coach, Staff treasurer) {
        this.chairman = chairman;
        this.coach = coach;
        this.treasurer = treasurer;
    }

    //main method
    void login() {
        while(true) {

            //print the logo to the console
            Logo.draw();

            //print the menu and runs a case based on user input (1-4)
            switch (Staff.chooseMenuOptions(RESET +
                  "\n┌─────────────── " + CYAN + "Welcome to The Dolphin Swimming Club!" + RESET + " ───────────────┐\n" +
                    "│\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │\n" +
                    "│ " + YELLOW + "1. Chairman" + RESET + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │\n" +
                    "│ " + PURPLE + "2. Treasurer" + RESET + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │\n" +
                    "│ " + BLUE + "3. Coach" + RESET + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │\n" +
                    "│ " + RED + "4. [Exit]" + RESET + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │" + "\n│\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  │\n" +
                    "└────────────────────── " + CYAN + "Choose an option: " + DARK_GREEN + "[1-4]" + RESET + " ──────────────────────┘\n\n",1, 5)) {
                case 1://user chose the Chairman option
                    //ask for the username and the password of the Chairman
                    if (loginCheck(chairman.getUsername(), chairman.getPassword())) {
                        System.out.println(YELLOW + "\nLogged in as Chairman!\n" + RESET);
                        //enter the main menu of the chairman
                        chairman.menu();
                    } else {
                        System.out.println(RED + "Invalid input!" + RESET);
                        //"press enter to continue" prompt
                        Staff.advance();
                    }
                    break;
                case 2://user chose the Treasurer option
                    if (loginCheck(treasurer.getUsername(), treasurer.getPassword())) {
                        System.out.println(PURPLE + "\nLogged in as Treasurer!\n" + RESET);
                        treasurer.menu();
                    } else {
                        System.out.println(RED + "Invalid input!" + RESET);
                        Staff.advance();
                    }
                    break;
                case 3://user chose the Coach option
                    if (loginCheck(coach.getUsername(), coach.getPassword())) {
                        System.out.println(BLUE + "\nLogged in as Coach!\n" + RESET);
                        coach.menu();
                    } else {
                        System.out.println(RED + "Invalid input!" + RESET);
                        Staff.advance();
                    }
                    break;
                case 4://User chose to exit the program
                    System.out.println(RED + "The program is closing..." + RESET);
                    System.exit(0);
            }
        }
    }

    //checks if the username and password entered by the user matches the ones provided by parameters
    private static boolean loginCheck(String defaultUsername, String defaultPassword)
    {
        System.out.println("\n------------ " + CYAN + "Log in!" + RESET + " ------------\n");
        System.out.print(GREEN + "Username " + BLUE + "[Default:" + GREEN + "\'" + defaultUsername + "\'" + BLUE + "]" + GREEN + ": " + RESET);
        String username = Staff.getInputString();
        System.out.print(GREEN + "Password " + BLUE + "[Default:" + GREEN + "\'" + defaultPassword + "\'" + BLUE + "]" + GREEN + ": " + RESET);
        String password = Staff.getInputString();

        return username.equals(defaultUsername) &&
                password.equals(defaultPassword);
    }
}
