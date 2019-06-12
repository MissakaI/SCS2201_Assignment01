import java.io.*;
import java.util.*;
import java.util.Scanner;

class StringMatch {
    public static void main(String[] args) {
        FileReader patternFile=null,textFile=null;
        try {
            patternFile=new FileReader(args[0]);
            textFile=new FileReader(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(1);
        }

        BufferedReader patternReader = new BufferedReader(patternFile);
        BufferedReader textReader = new BufferedReader(textFile);

        try {
            String pattern = patternReader.readLine();
            String text=textReader.readLine();

            List<String> match=matchPattern(text, pattern); // calling pattern check function
            patternReader.close();
            textReader.close();

            /*Output File*/
            File outputFile = new File("Output.txt");
            if (outputFile.exists()) {
                System.out.println("File with Output name found. Deleting...");
                if (outputFile.delete()) {
                    System.out.println("Deleted Successfully");
                } else {
                    System.out.println("Deletion Failed");
                }
            }

            try {
                System.out.println("Creating new output file.");
                if (outputFile.createNewFile()) {
                    System.out.println("Created new output file.");
                }
            } catch (IOException e) {
                System.out.println("Output File creation failed");
            }

            PrintStream out = System.out;
            try {
                out = new PrintStream(outputFile);
            } catch (FileNotFoundException e) {
                System.exit(1);
            }

            if (match.size()>0) {
                for (String s : match) {
                    out.println(s);
                }
            }else{
                out.println("No Match Found");
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static List<String> matchPattern(String text, String pattern) {
        // System.out.println(pattern);
        List<String> al = new ArrayList<String>();

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
                // System.out.println("\nMatched pattern starts at "+i+" index of text string");
                al.add("Matched pattern starts at " + i + " index of text string");
            }

        }
        return al;
        // System.out.println(count);
//        if (count == 0)
//            System.out.println("\nNo match found");
//
//        for(String s:al){
//            System.out.println(s);
//        }

    }
}