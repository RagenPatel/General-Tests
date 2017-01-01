import javafx.scene.layout.Pane;

import java.util.*;

/**
 * Created by Ragen on 12/31/16.
 */
public class FizzBuzz {

    public static void main(String[] args) {

        System.out.print("Enter an int value: ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        List<String> output = fizzBuzz(input);

        for(int i = 0; i < output.size(); i++) {
            if(i == output.size()-1) {
                System.out.println(output.get(i));
                break;
            }
            System.out.println(output.get(i) + ",");
        }


    }


    public static List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<>();
        int threeCount = 0;
        int fiveCount = 0;

        for(int i = 1; i <= n; i++) {

            if(divisibleByFive(i) && divisibleByThree(i)) {
                result.add("FizzBuzz");
            } else if (divisibleByFive(i) && !divisibleByThree(i)) {
                result.add("Buzz");
            } else if (divisibleByThree(i) && !divisibleByFive(i)) {
                result.add("Fizz");
            } else {
                result.add(i + "");
            }

        }


        return result;
    }

    public static boolean divisibleByFive (int input) {
        boolean result = false;
        for(int i = 0; i < input/5; i++) {
            input = input%5;

            if(input == 0) {
                return true;
            }
        }



        return result;
    }

    public static boolean divisibleByThree (int input) {
        boolean result = false;

        for(int i = 0; i < input/3; i++) {
            input = input%3;

            if(input == 0) {
                return true;
            }
        }


        return result;
    }

}
