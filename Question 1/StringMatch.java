import java.util.*;
import java.util.Scanner;

class StringMatch {
    public static void main(String[] args) {
        // String text = "cogwrgaccag";
        // String pattern = "c_g";
        Scanner input = new Scanner(System.in);

       
        System.out.print("Enter a string    :");
        String text = input.nextLine();

        System.out.print("Enter a pattern    :");
        String pattern = input.nextLine();
        
        
        input.close();
        matchPattern(text, pattern);

    }

    static void matchPattern(String text, String pattern) {
        // System.out.println(pattern);
        int count = 0;
        boolean flag;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            flag = true;
            for (int j = 0; j < pattern.length() && flag == true; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    if (pattern.charAt(j) != '_') {
                        flag = false;
                        // System.out.println(i + j + "element in text and " + j + "elemnt in pattern");
                    }
                }

            }
            if (flag) {
                count++;
                System.out.println("\nMatched pattern starts at "+i+" index of text string");
            }

        }
        // System.out.println(count);
        if(count==0) System.out.println("\nNo match found");

    }
}