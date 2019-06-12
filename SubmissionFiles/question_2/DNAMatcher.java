import java.io.*;
import java.util.*;

public class DNAMatcher {

    public DNAMatcher(FileReader dnaDB, FileReader query, PrintStream out) {

        Map<String, Pattern> queries = readQueries(query);
        Map<String, Sequence> sequences = readDNASequences(dnaDB);

        for (Sequence value : sequences.values()) {
            matchStrings(value, new ArrayList<>(queries.values()));
        }

        /**
         * Uncomment to see the Patterns read form the file
         * */
        /*
        out.println("Patterns");

        for (Pattern value : queries.values()) {
            out.print(value.getDesc() + ": ");
            for (char c : value.getPattern()) {
                out.print(c + " ");
            }
            out.println();
        }
        */


        /**
         * Uncomment to see the sequences read from the file
         * */
        /*
        out.println("\nSequences");

        for (Sequence value : sequences.values()) {
            out.println(value.getDesc());
            out.println(value.getSequenceString());
            out.println();
        }
        */

        for (Pattern pattern : queries.values()) {
            out.println(pattern.getDesc());
            if (pattern.getMatches().size() > 0) {
                for (Pattern.MatchInstance match : pattern.getMatches()) {
                    out.println(match.getSequence().getDesc() + " at offset " + match.getOffset());
                }
            } else {
                out.println("NONE");
            }
            out.println();
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

    private Map<String, Sequence> readDNASequences(FileReader dbFileReader) {
        BufferedReader dbReader = new BufferedReader(dbFileReader);
        String s = null;
        Map<String, Sequence> sequences = new LinkedHashMap<>();

        try {
            String desc = null;
            while (!(s = dbReader.readLine()).equals(">EOF")) {
                if (s.charAt(0) == '>') {
                    desc = s.substring(1);
                    sequences.put(desc, new Sequence(desc));
                } else {
                    Sequence sequence = sequences.get(desc);
                    sequence.setSequenceString(sequence.getSequenceString() + s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sequences;
    }

    private char[] readPattern() {
        return null;
    }


    private void matchStrings(Sequence sequence, ArrayList<Pattern> patterns) {

        int j[] = new int[patterns.size()];
        Arrays.fill(j, -1);
        for (int i = 0; i < sequence.getSequence().length; i++) {

            for (int k = 0; k < patterns.size(); k++) {
                Pattern pattern = patterns.get(k);

                while (j[k] >= 0 && ((j[k] + 1) == pattern.getPattern().length || sequence.getSequence()[i] != pattern.getPattern()[j[k] + 1])) {
                    if ((j[k] + 1) == pattern.getPattern().length) {
                        pattern.getMatches().add(pattern.new MatchInstance(sequence, (i - pattern.getPattern().length)));
                    }
                    j[k] = (pattern.getPi()[j[k]]) - 1;
                }
                if (sequence.getSequence()[i] == pattern.getPattern()[j[k] + 1]) {
                    j[k]++;
                }
            }
        }
    }

    class Pattern {
        private String desc;
        private String queryString;
        private char[] pattern;
        private int[] pi;
        private boolean piBuilt = false;
        private ArrayList<MatchInstance> matches;

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

        public ArrayList<MatchInstance> getMatches() {
            if (matches == null) {
                matches = new ArrayList<>();
            }
            return matches;
        }

        public void setMatches(ArrayList<MatchInstance> matches) {
            this.matches = matches;
        }

        public class MatchInstance {
            private Sequence sequence;
            private int offset;

            public MatchInstance(Sequence sequence, int offset) {
                this.sequence = sequence;
                this.offset = offset;
            }

            public Sequence getSequence() {
                return sequence;
            }

            public void setSequence(Sequence sequence) {
                this.sequence = sequence;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

    }

    private class Sequence {
        private String desc;
        private String sequenceString;
        private char[] sequence;

        public Sequence(String desc) {
            this.desc = desc;
            this.sequenceString = "";
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSequenceString() {
            return sequenceString;
        }

        public void setSequenceString(String sequenceString) {
            this.sequenceString = sequenceString;
            this.sequence = sequenceString.toCharArray();
        }

        public char[] getSequence() {
            return sequence;
        }

        public void setSequence(char[] sequence) {
            this.sequence = sequence;
        }
    }
}
