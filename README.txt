VirtualPetsAssignment och26 spe80

To run the JAR file, open a terminal and navigate to the current directory. Then enter the command below:

java -jar VirtualPets_och26_spe80.jar 

First you must compile the source code in the "src/virtualpets" directory. All
of the resulting source code should be placed into "/bin/virtualpets". The
jarring command can then be run, or GUIMain.class can be called from the output
directory. The command below should suffice to compile the code and place it
into the appropriate directory:

javac -d "../../bin/virtualpets" /*.class

(bash script only)
Another way is to just import it into eclipse and run it internally and then
jar it. But hey what you do in your own time isn't up to us.
To build the JAR file, run the following command:

jar cvfm newBuildName.jar buildmanifest.txt -C bin . img config

"buildmanifest.txt" simply seals the jar and sets GUIMain.class as the main entry point.