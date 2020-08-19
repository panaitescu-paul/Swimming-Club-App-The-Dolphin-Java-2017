package application.swimmingClub;
/*
   Main class containing the logo for the company
*/
public class Logo
{
    //static reference to the logo String
    private static final String logo = makeDolphin();
    //method that prints the logo to the output
    public static void draw()
    {
        System.out.println(logo);
    }

    //return a string representation of the dolphin
    private static String makeDolphin()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t\t                @@@@").append('\n');
        sb.append("\t\t\t\t              @@,,@@").append('\n');
        sb.append("\t\t\t\t        @@@@@@,,,,@@@@").append('\n');
        sb.append("\t\t\t\t      @@,,,,,,::::::,,@@").append('\n');
        sb.append("\t\t\t\t    @@,,````::::::::::,,@@").append('\n');
        sb.append("\t\t\t\t  @@@@,,@@``::::::::::::,,@@").append('\n');
        sb.append("\t\t\t\t@@,,,,::````::,,````::::::@@").append('\n');
        sb.append("\t\t\t\t  @@@@@@@@@@@@,,,,@@``::::,,@@").append('\n');
        sb.append("\t\t\t\t        @@::@@@@,,@@@@``::::@@").append('\n');
        sb.append("\t\t\t\t          @@    @@@@  @@::::@@").append('\n');
        sb.append("\t\t\t\t                      @@::@@").append('\n');
        sb.append("\t\t\t\t                    @@@@::@@").append('\n');
        sb.append("\t\t\t\t                  @@::::@@").append('\n');
        sb.append("\t\t\t\t                    @@::@@").append('\n');
        sb.append("\t\t\t\t                      @@").append('\n');
        makeColors(sb);
        return sb.toString();
    }

    //replace the Ascii characters from the given String with colored background blocks
    private static void makeColors(StringBuilder text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            switch(text.charAt(i))
            {
                case '@':
                    //BLACK blocks
                    text.replace(i, i+1, "\u001B[40m \u001b[0m");
                    break;
                case ':':
                    //BLUE blocks
                    text.replace(i, i + 1, "\u001B[44m \u001b[0m");
                    break;
                case ',':
                    //CYAN blocks
                    text.replace(i, i + 1, "\u001B[46m \u001b[0m");
                    break;
                case '`':
                    //white blocks
                    text.replace(i, i + 1, "\u001B[47m \u001b[0m");
                    break;
            }
        }
    }
}
