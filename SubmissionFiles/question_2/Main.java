import java.io.*;

public class Main {
    private static final String dbName = "SampleDatabase.txt";
    private static final String queryName = "QueryBase.txt";

    public static void main(String[] args) {
        FileReader dnaDB = null, query = null;
        try {
            dnaDB = new FileReader(args[0]);
            query = new FileReader(args[1]);
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
            /**
             * Default files if not defined arguments
             */
            try {
                dnaDB = new FileReader(dbName);
                query = new FileReader(queryName);
            } catch (FileNotFoundException e1) {
//                e1.printStackTrace();
                System.out.println("No Files Found");
                System.exit(1);
            }
        }

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

        DNAMatcher matcher = new DNAMatcher(dnaDB, query, out);

        out.flush();
        out.close();
        try {
            dnaDB.close();
            query.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
