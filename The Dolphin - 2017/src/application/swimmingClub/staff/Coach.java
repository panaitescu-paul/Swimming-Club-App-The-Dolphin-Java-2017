package application.swimmingClub.staff;

import application.swimmingClub.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//the Coach staff member, responsible for checking the disciplines and the top five swimmers
public class Coach extends Staff{

    //create the object with the default user credentials
    public Coach(List<Member> memberList) {
        super(memberList, "co", "co");
    }

    //main method
    public void menu()
    {
        while(true){

            System.out.println("\n------------ " + BLUE + "Coach Menu" + RESET + " ------------\n");

            int option = chooseMenuOptions(YELLOW + "1. Show disciplines\n" + GREEN + "2. Show top 5 swimmers from discipline\n" + RED + "3. [Log out]\n" + RESET,1, 4);

            switch(option)
            {
                case 1://user chose to see the disciplines
                    showDisciplines();
                    break;
                case 2:// user chose to see the top five from a discipline
                    showTopFiveFromDiscipline();
                    break;
                case 3:
                    return;
            }
        }
    }

    private void showTopFiveFromDiscipline(){
        // get the user to choose a discipline
        String chosenDiscipline = chooseDiscipline();

        //list used to store the members of the chosen discipline
        List<Member> disciplineMembers = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getDiscipline().equals(chosenDiscipline)) {
                disciplineMembers.add(memberList.get(i));
            }
        }

        System.out.println(CYAN + "Top 5 Swimmers from " + chosenDiscipline + ":" + RESET);

        //an array of doubles used to store the bestlaps of the members from the disciplineMembers list
        double[] sortedLaps = new double[disciplineMembers.size()];
        for (int i=0; i < disciplineMembers.size(); i++) {
            sortedLaps[i] = disciplineMembers.get(i).getBestLap();
        }

        //sort the array of doubles in ascending order
        Arrays.sort(sortedLaps);

        //horizontal divider for the table
        String divider = "*───────────────────────────────────────────────*";

        //format for the table output
        String tableFormat = "│ %8s │ %25s │ %6s │%n";

        //print the table header, using the table format
        System.out.println(divider);
        System.out.printf(tableFormat, "Position", "Name", "Time");
        System.out.println(divider);

        //iterate through each double in the sortedLaps
        for (int i = 0; i < 5; i++) { // show just top 5 from sortedLaps
            //iterate through each member of the discipline, and print the one whose bestLap matches the one in the sortedLaps array
            for (int j = 0; j < disciplineMembers.size(); j++) {
                if (sortedLaps[i] == disciplineMembers.get(j).getBestLap())
                {
                    System.out.printf(tableFormat, i+1, disciplineMembers.get(j).getName(), String.format("%.2f", disciplineMembers.get(j).getBestLap()));
                    System.out.println(divider);

                    //remove the member from that discipline, to avoid the double printing of a member in the table
                    disciplineMembers.remove(disciplineMembers.get(j));
                    break;
                }
            }
        }
    }
}
