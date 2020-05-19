***CONSOLE CANVAS***

This application allows you to draw line and rectangle on a canvas and also fill the canvas with single color.

**Prerequisites**

1. JAVA 8 
2. MAVEN 3 or above

**Build and Run**

cd ConsoleCanvas

mvn clean install

cd target
 
java -jar ConsoleCanvas-1.0-SNAPSHOT.jar

**NOTE:** If unit test MenuTest.java/ApplicationTest.java is failing during build, please try running after removing \r from TestUtil.getMenu().
This generally happens in Linux machine.

**Project Structure & Design**

This project is based on Command pattern where there are commands, executor, commandRegistry and receiver. 
It is broken into following packages based upon their functionality:

1. Model :  Information/Models related to command are kept here.
2. Commands : All the operations that can be done on Canvas are kept under commands folder.
3. Receiver : In this case, receiver is our canvas.
4. Exception : Type of exceptions are kept under this folder.
5. Validation : All the validation part in under this folder. Every command fired by user is validated through classes present in this folder.
6. Execution : Defines steps to create a shape after user inputs the command.
7. Util : Any utility class can go here.

**Assumptions**

1. There is only single user.
2. Until and unless user creates new canvas, old canvas will be used for painting.
3. The upper left corner is identified with (0,0) coordinates.
4. The pixels that are not painted carries Character.MIN_VALUE value.
5. Lines supported are only vertical or horizontal.
6. If shape goes out of canvas area then error will be displayed and no image will be painted.
7. By default, canvas is painted after every command.
8. -(ve) coordinates means imaginary space and hence can't be plotted.
9. If coordinates of rectangle form a line then rectangle wouldn't be printed and error will be thrown.
10. Every command given by user is considered a new command and hence commands are not singleton.
11. Purpose of validator remain the same for every command object. Hence validators are made singleton.
12. All the utility classes contain static method as they just extend some kind of help to the objects.
13. Only one kind of exception is considered - InvalidInputException. This can be broken down further but keeping into account the scope of application I have added all in one.
14. I have used System.out.println() at certain places to print but in production this will be replaced with Loggers or any other utility libraries.
15. Max canvas size supported is 100x100;

**Future scope**

1. Can be extended to support multiple users at a time but with different canvas.
2. More type of commands can be added.
3. Instead of passing params in a list to the commands, proper command request object could be created and handled in a cleaner way.
4. It can be extended to multiple users working simultaneously on single canvas in a group.