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