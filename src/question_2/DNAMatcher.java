package question_2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DNAMatcher {
    /**
     * Defines the maximum number of characters in a line of DNA Sequence String
     */
    public static final int LINE_LENGTH = 70;
    private static final String dbName = "SampleDatabase.txt";
    private static final String queryName = "QueryBase.txt";

    public DNAMatcher() {
        FileReader dnaDB = null, query = null;

        try {
            dnaDB = new FileReader(this.getClass().getResource("/" + dbName).getFile());
            query = new FileReader(this.getClass().getResource("/" + queryName).getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Map<String, Pattern> queries = readQueries(query);
        for (Pattern value : queries.values()) {
            System.out.println(value.getDesc());
//            System.out.println(value.getQueryString());
            for (char c : value.getPattern()) {
                System.out.print(c + " ");
            }
            System.out.println();
            for (int i : value.getPi()) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    private Map<String, Pattern> readQueries(FileReader queryFileReader) {
        BufferedReader queryReader = new BufferedReader(queryFileReader);
        String s = null;
        Map<String, Pattern> queries = new LinkedHashMap<>();

        try {
            String desc = null;
            while (!(s = queryReader.readLine()).equals(">EOF")) {
                if (s.charAt(0) == '>') {
                    desc = s.substring(1);
                    queries.put(desc, new Pattern(desc));
                } else {
                    Pattern pattern = queries.get(desc);
                    pattern.setQueryString(pattern.getQueryString() + s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return queries;
    }

    private char[] readDNASequence() {
        return null;
    }

    private char[] readPattern() {
        return null;
    }

//    private char[] detectPrefix(char[] pattern){
//        return null;
//    }


    private int[] matchStrings(char[] sequence, char[] pattern, int[] pi) {
        return null;
    }

    private class Pattern {
        private String desc;
        private String queryString;
        private char[] pattern;
        private int[] pi;
        private boolean piBuilt = false;

        public Pattern(String desc) {
            this.desc = desc;
            this.queryString = "";
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
            this.pattern = queryString.toCharArray();
        }

        public char[] getPattern() {
            return pattern;
        }

        public void setPattern(char[] pattern) {
            this.pattern = pattern;
            this.piBuilt = false;
        }

        public int[] getPi() {
            if (!piBuilt) {
                buildPiTable();
                piBuilt = true;
            }
            return pi;
        }

        public void setPi(int[] pi) {
            this.pi = pi;
        }

        /**
         * Builds the Pi Table for the current pattern in the object.
         * And stores it in the object itself. This should be executed before
         * getPi() method is executed.
         */
        public void buildPiTable() {
            pi = new int[pattern.length];

            /*
             * i is the counter running through the pattern array
             * j is the pi value
             */
            for (int i = 1, j = 0; i < pattern.length; i++) {


                /* if prefix matches */
                if (pattern[j] == pattern[i]) {
                    j++;
                } else {
                    while (j > 0 && pattern[j] != pattern[i]) {
                        j = pi[j - 1];
                    }
                }
                pi[i] = j;
            }
        }

    }
}
