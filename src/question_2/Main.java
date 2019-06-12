package question_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static final String dbName = "SampleDatabase.txt";
    private static final String queryName = "QueryBase.txt";

    public static void main(String[] args) {
        FileReader dnaDB = null, query = null;
//        File f=new File(".");
//        System.out.println(f.getAbsolutePath());
        try{
            dnaDB=new FileReader(args[0]);
            query=new FileReader(args[1]);
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
            /**
             * Default files if not defined arguments
             */
            try {
                dnaDB = new FileReader(new Main().getClass().getResource("/" + dbName).getFile());
                query = new FileReader(new Main().getClass().getResource("/" + queryName).getFile());
            } catch (FileNotFoundException e1) {
//                e1.printStackTrace();
                System.out.println("No Files Found");
                System.exit(1);
            }

        }

        DNAMatcher matcher=new DNAMatcher(dnaDB,query);
    }
}
