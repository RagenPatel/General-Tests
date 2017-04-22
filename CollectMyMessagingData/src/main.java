import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Ragen on 4/15/17.
 */

//Simple program that takes in data in the form of text file and separates messages sent only by target user.

public class main {

    //Names you want to ignore. Keep the < > signs.
    public static String name1 = "<Insert name 1>", name2 = "<insert name 2>", name3 = "<insert name 3>";

    //Name you want to extract. Keep < > signs.
    public static String targetName = "<target name>";

    public static void main(String[] args) {

        for(int i = 1; i < 5; i++) {
            File input = new File("hangouts"+i+".txt");
            System.out.println("Retrieving data for hangouts" + i + ".txt");
            //extractMyData(input);
            extractHangoutsData(input);
        }

    }

    /**
     * Takes hangouts text file as input and extracts messages only sent by me to myHangoutsData.txt
     * @param input
     * @output outputs to "My" + nameOfFile + "Data.txt"
     */
    public static void extractHangoutsData(File input) {

        try{
            PrintWriter writer = new PrintWriter("My"+input.toString().substring(0,9) + "Data.txt", "UTF-8");

            String line;

            try (
                    InputStream fis = new FileInputStream(input);
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {

                    String tempLine = line.toLowerCase();

                    //Checks if the messages are from people other than mine. If they are, continue in the loop and skip this line
                    if(tempLine.indexOf(name1) > 0 || tempLine.indexOf(name2) == 0 ||
                            tempLine.indexOf(name3) > 0 || tempLine.indexOf("http") > 0) {
                        continue;
                    } else if (tempLine.indexOf(targetName) > 0) {
                        writer.println(line.substring(0, 11) + line.substring(34));
                    }

                }
            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Takes in File and extracts data only related to me and outputs it to a new file called
     * @param input
     */
    public static void extractMyData(File input) {

        try{
            PrintWriter writer = new PrintWriter("myData.txt", "UTF-8");

            String line;
            boolean checkPerson = false;
            try (
                    InputStream fis = new FileInputStream(input);
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {

                    String tempLine = line.toLowerCase();

                    if(checkPerson) {
                        if(tempLine.indexOf("deep •") == 0 || tempLine.indexOf("dhruv •") == 0 ||
                                tempLine.indexOf("priyam •") == 0) {
                            checkPerson = false;
                            continue;
                        } else {
                            continue;
                        }
                    } else {

                        if(tempLine.indexOf("deep patel ") == 0 || tempLine.indexOf("dhruv patel ") == 0 ||
                                tempLine.indexOf("priyam patel ") == 0) {
                            checkPerson = true;
                            continue;
                        }
                    }

                    if(tempLine.length() > 10) {
                        if(tempLine.charAt(2) != '/') {
                            writer.println(line);
                        }
                    } else {
                        writer.println(line);
                    }


                }
            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
