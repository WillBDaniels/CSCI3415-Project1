CSCI3415-Project1
=================

This is the Java calculator project for CSCI 3415

This project creates a simple java calculator with a GUI that uses a number of well-known algorithms to parse up an input expression and then gives the output. 
The Following mathematical operations are currently supported: +, -, /, *, ^, ( ), (unary)-, (unary)+
If you came across this project and you are NOT in CSCI 3415 for the fall and you would like to either request new functionality, or improve upon it yourself, simply raise an issue for it please. 

This calculator will also support the ability to read in a batch file of mathematical expressions, line-ordered in a file, as such: 

expression 1

expression 2

expression 3

expression 4

...

...

expression n

This file can be any regular file format extension, so long as it's a plain-text file. The results are placed in a user-defined location in a .txt file.  

To get started working on this project, you simply need to follow these 4 easy steps (assuming a fresh computer): 

1. clone the repository to your local filesystem from either the ssh location : git@github.com:WillBDaniels/CSCI3415-Project1.git or the https location: https://github.com/WillBDaniels/CSCI3415-Project1.git
2. Download the latest version of the Java 8 JDK(8 is needed since lambda expressions are employed) from [Here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
3. set you JAVA_HOME environment variable. Instructions for how to do this can be done with a simple Google search. 
4. at the top-most directory of the repository, type the command 'gradlew run' (for windows) or './gradlew run' (for mac/linux). 

That's it! Then you're off and running and developing. I hope you enjoy using and developing it!

