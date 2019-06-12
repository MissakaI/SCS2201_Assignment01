# SCS2201_Assignment01
String Matching Algorithms
****
## Question 1

The algorithm for the "Question one"  we used is the naive algorithm. 
The given solution is taking keyboard inputs of both the pattern and the text.

The program will take both the lengths of the pattern and the text. 
For outer loop will run (text length-pattern length) times. In that for loop, we are checking characters of both text and the pattern. Outer for loop will loop until text length - pattern length times.

<!-- The reason behind that is; assume we have text ABCDABF and pattern AB -->
The inner loop will loop through the characters of the pattern.
    Loop will break only if found any mismatching character with text or if the loop is already gone through the entire pattern

    In each iteration, it will look whether the character is matching if not loop breaks, and if yes, same pattern character will be compared to the next charact of text.

    In the iteration, if '_' found it will just igonre it. It won't consider as a mismatch and will continue comparing characters(will just increment the i,j values. Loop won't break)

The outer loop will keep a count on how many matching patterns are there. 
If count is zero it will display "No match found" 
If count there is at least matching pattern program will display the index of the text which matches the pattern

## Question 2

The DNAMatcher class is contains all the algorithm for the second question.
To execute it excute the Main class.

Here in DNAMatcher the methods `readQueries` reads the query file and make instances of `DNAMatcher.Pattern` class
Complexity of this method is in order of O(mk) where `k` is the number of queries and `m` is 
the length of a query resulted in generating the pi table.

And the method `readDNASequences` reads the DNA DB file and and make instances of `DNAMatcher.Sequence` class.

`matchStrings` method implements the Knuth-Morris-Pratt algorithm and in this specific method it is
slightly modified to check matches with several patterns in single iteration of the sequence.
Thus modifies the complexity of the original algorithm as well. Which is originally O(n) is now in
order of O(nk) where `k` is the number of queries.

#### Execution of Question 2
Built using JDK 8 release 181

1. To complile `javac Main.java`
2. Execute with default files `java Main`

To pass optional input files use
`java Main <sequenceFile> <queryFile>`

eg: `java Main "DNA Database.txt" QueryBase.txt`