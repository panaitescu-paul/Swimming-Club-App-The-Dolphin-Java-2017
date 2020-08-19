package application.swimmingClub;

public class Member {

    // personal info
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    // swimming info
    private String activityForm;
    private String swimmerProgramme;
    private String swimmerType;

    // payment info
    private double paymentAmount;
    private boolean subscriptionPaid;
    private String discipline;
    private double bestLap;

    public Member (String firstName,
                    String lastName,
                    int age,
                    String email,
                    String activityForm,
                    String swimmerProgramme,
                    String discipline,
                    double bestLap
                   ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.activityForm = activityForm;
        this.swimmerProgramme = swimmerProgramme;

        // set swimmer type based on age
        if (this.age >= 18) {
            this.swimmerType = "senior";
        }else {
            this.swimmerType = "junior";
        }

        //calculate the payment based on the activity type and age
        if (this.activityForm.equals("active")) {
            if (this.age > 60) {
                this.paymentAmount = 1600 - (1600 * 0.25); // senior discount -25%
            } else if (this.age < 18) {
                this.paymentAmount = 1000;
            } else {
                this.paymentAmount = 1600;
            }
        }else { //passive
            this.paymentAmount = 500;
        }

        this.subscriptionPaid=false;
        this.discipline = discipline;
        this.bestLap = bestLap;
    }

    public Member(String firstName,
                  String lastName,
                  int age,
                  String email,
                  String activityForm,
                  String swimmerProgramme,
                  boolean subscriptionPaid,
                  String discipline,
                  double bestLap){
        this(firstName, lastName, age, email, activityForm, swimmerProgramme, discipline, bestLap);
        if (subscriptionPaid){
            this.paySubscription();
        }
    }


    //marks the member as paid
    public void paySubscription() {
        this.subscriptionPaid = true;
    }


    //Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    public String getActivityForm() {
        return activityForm;
    }
    public String getSwimmerProgramme() {
        return swimmerProgramme;
    }
    public String getSwimmerType() {
        return swimmerType;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }
    public boolean isSubscriptionPaid() {
        return subscriptionPaid;
    }
    public String getDiscipline() {
        return discipline;
    }
    public double getBestLap() {
        return bestLap;
    }
    public String getName()
    {
        return firstName + " " + lastName;
    }
}
