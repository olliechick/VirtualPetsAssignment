VirtualPetsAssignment och26 spe80

To run the JAR file, open a terminal and navigate to the current directory. Then enter the command below:

java -jar VirtualPets_och26_spe80.jar 

First you must compile the 
To build the JAR file. Run the following command:

jar cvfm newBuildName.jar buildmanifest.txt -C bin . img config

"buildmanifest.mf" simply seals the jar and sets GUIMain.class as the main entry point.