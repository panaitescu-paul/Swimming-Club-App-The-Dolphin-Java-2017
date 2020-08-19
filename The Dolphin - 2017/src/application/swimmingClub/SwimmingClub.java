package application.swimmingClub;

import application.swimmingClub.staff.Chairman;
import application.swimmingClub.staff.Coach;
import application.swimmingClub.staff.Staff;
import application.swimmingClub.staff.Treasurer;
import application.swimmingClub.database.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// main class of the application responsible with logging in the user of the system. Adding default members
public class SwimmingClub {

    public static List<Member> memberList = new ArrayList<Member>();

    // disciplines in the form of an array of strings
    public static final String[] DISCIPLINES = {"None", "Backstroke", "Freestyle", "Butterfly"};

    //Staff memebrs
    private Staff chairman  = new Chairman(memberList);
    private Staff coach     = new Coach(memberList);
    private Staff treasurer = new Treasurer(memberList);

    private Login login = new Login(chairman, coach, treasurer);


    public SwimmingClub()
    {
        //add default members; used for debugging
        memberList.add(new Member("Jacob"   , "Larman"   , 9 , "jaco9@gmail.com"      , "active" , "elite"   , SwimmingClub.DISCIPLINES[3], getRandLapTime()));
        memberList.add(new Member("Gary"    , "Morrison" , 98, "gary876@yahoo.com"    , "active" , "elite"   , SwimmingClub.DISCIPLINES[2], getRandLapTime()));
        memberList.add(new Member("Richard" , "Robertson", 37, "rick22@hotmail.com"   , "passive", "exercise", SwimmingClub.DISCIPLINES[0], getRandLapTime()));
        memberList.add(new Member("Anthony" , "Lawrence" , 11, "anthy77@gmail.com"    , "active" , "elite"   , SwimmingClub.DISCIPLINES[1], getRandLapTime()));
        memberList.add(new Member("Emma"    , "Jones"    , 67, "emma76@gmail.com"     , "active" , "elite"   , SwimmingClub.DISCIPLINES[2], getRandLapTime()));
        memberList.add(new Member("David"   , "Grimes"   , 37, "david123@yahoo.com"   , "passive", "exercise", SwimmingClub.DISCIPLINES[0], getRandLapTime()));
        memberList.add(new Member("Isabella", "Johnson"  , 13, "bella05@outlook.com"  , "active" , "elite"   , SwimmingClub.DISCIPLINES[1], getRandLapTime()));
        memberList.add(new Member("Matthew" , "Shaw"     , 54, "bigMatt@gmx.com"      , "active" , "elite"   , SwimmingClub.DISCIPLINES[1], getRandLapTime()));
        memberList.add(new Member("Ava"     , "Watson"   , 26, "ava69@zoho.com"       , "passive", "exercise", SwimmingClub.DISCIPLINES[0], getRandLapTime()));
        memberList.add(new Member("George"  , "Freeman"  , 35, "george777@hotmail.com", "passive", "exercise", SwimmingClub.DISCIPLINES[0], getRandLapTime()));
        memberList.add(new Member("Lucas"   , "Larman"   , 30, "lucky30@gmail.com"    , "passive", "exercise", SwimmingClub.DISCIPLINES[0], getRandLapTime()));

        //save the members to the file
        FileHandler.writeToFile(memberList);
    }

    //open the login menu
    public void run () {

        login.login();
    }

    // return a random value for the member's best lap
    public static double getRandLapTime()
    {
        //random double between 100 and 500
        return 100.0 + new Random().nextDouble() * 400.0;
    }
}
