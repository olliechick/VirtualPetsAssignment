# VirtualPetsAssignment
## GUI Version
To run the program, use the command ``java -jar VirtualPets_och26_spe80.jar``. You can download the jar [by clicking here](https://github.com/olliechick/VirtualPetsAssignment/raw/GUI/VirtualPets_och26_spe80.jar).

### Compiling it yourself
First you must compile the source code in the ``src/virtualpets`` directory. All
of the resulting source code should be placed into ``/bin/virtualpets``. The
jarring command can then be run or GUIMain.class can be called from the output
directory. The command below should suffice to compile the code and place it
into the appropriate directory:

``javac -d "../../bin/virtualpets" /*.class``

Another way is to just import it into eclipse and run it internally and then
jar it. But hey what you do in your own time isn't up to us.

To build the JAR file, run the following command:

``jar cvfm newBuildName.jar buildmanifest.txt -C bin . img config``

``buildmanifest.txt`` simply seals the jar and sets GUIMain.class as the main entry point

## Command-line version
To run the command-line version of the program, use the command ``java -jar VirtualPetsCLI.jar``. You can download the jar [by clicking here](https://github.com/olliechick/VirtualPetsAssignment/raw/master/VirtualPetsCLI.jar). The source for this is available in the master branch.
