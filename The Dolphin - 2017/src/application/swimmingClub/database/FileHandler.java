package application.swimmingClub.database;

import application.swimmingClub.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// handles writing data from a member list to a file as well as reading data from a file to a member list
public class FileHandler {

    private static final String fileName = "src//data.txt";

    // function receives an array list of members and writes it to a file
    public static void writeToFile(List<Member> memberList) {
        // The name of the file to open.
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter =  new BufferedWriter(fileWriter);

            // for every member from the list, copy its variables in a single line in the file, separated by commas
            for (int i = 0; i < memberList.size(); i++) {
                Member currentMember = memberList.get(i);
                bufferedWriter.write(currentMember.getFirstName() + ", ");
                bufferedWriter.write(currentMember.getLastName() + ", ");
                bufferedWriter.write(currentMember.getAge() + ", ");
                bufferedWriter.write(currentMember.getEmail() + ", ");
                bufferedWriter.write(currentMember.getActivityForm() + ", ");
                bufferedWriter.write(currentMember.getSwimmerProgramme() + ", ");
                bufferedWriter.write(currentMember.isSubscriptionPaid() + ", ");
                bufferedWriter.write(currentMember.getDiscipline() + ", ");
                bufferedWriter.write(currentMember.getBestLap() + "");
                bufferedWriter.newLine();
            }

            // close the file
            bufferedWriter.close();
        }
        catch(IOException ex) {
            // print an error message
            System.out.println( "Error writing to file '" + fileName + "'");
            ex.printStackTrace();
        }
    }

    // function reads the file and returns a new array list of members
    public static List<Member> readFromFile() {
        // This will reference one line at a time
        String line;
        int index = 0;

        List<Member> memberList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                // Divide the line into array elements using comma and space as separators.
                List<String> temporaryList = Arrays.asList(line.split(", "));

                //reads the fields from the line
                String  firstName = temporaryList.get(0);
                String  lastName = temporaryList.get(1);
                int     age = Integer.parseInt(temporaryList.get(2)); // String -> int
                String  email = temporaryList.get(3);
                String  activityForm = temporaryList.get(4);
                String  swimmerProgramme = temporaryList.get(5);
                boolean subscriptionPaid = Boolean.parseBoolean(temporaryList.get(6)); // String -> boolean
                String  discipline = temporaryList.get(7);
                double  bestLap    = Double.parseDouble(temporaryList.get(8));

                //create a new member with the new fields
                Member currentMember = new Member(firstName, lastName, age, email, activityForm, swimmerProgramme, subscriptionPaid, discipline, bestLap);

                //add the newly created member to the list
                memberList.add(currentMember);
                
                index ++; // next line, next member
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memberList;
    }



}
