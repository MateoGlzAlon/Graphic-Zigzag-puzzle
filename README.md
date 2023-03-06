# Graphic-Zigzag-puzzle
Final project of the subject "Programacion II" coursed in Universidad de Leon

In this practice the student must develop an application in Swing to create and solve zigzag puzzles. The application should cover the following requirements:

1.- It will allow to create a zigzag puzzle, entering the size of the matrix in rows and columns and filling all the cells with numbers from 1 to 9. The application will check that the matrix is well defined and will allow to store it in a text file that will have the format of the input of the intermediate practice, with a single blank separating the numbers, no blanks at the end or at the beginning of lines and each line ending in an end-of-line character. It may be assumed that a matrix has no dimension greater than 10.
2.- It is also possible to retrieve the definition of a zigzag puzzle from a file and to modify and resave it.
3.- The user can also solve zigzag puzzles in graphic mode. The user will be able to load a puzzle and join boxes graphically, with the mouse, to form a solution to the puzzle. The program will warn if the user tries to join two boxes that cannot be joined or that result in a crossing with the previously drawn lines. The interface to create a line between two cells of the matrix must be user-friendly, for example, by dragging and dropping, or by selecting the initial and final cells.
4.- If the user is unable to solve the puzzle, he/she can ask the program for help to see if there is a solution, and if there is, the program will draw it for the user to see. This action must not block the Swing event thread, so it must be performed using the SwingWorker class.
5.- All the actions allowed by the program must be able to be done from the main menu of the application.
6.- There shall be an edit menu that allows undoing and redoing actions an arbitrary number of times. It shall be possible to undo and redo in both puzzle creation mode and puzzle solving mode.
