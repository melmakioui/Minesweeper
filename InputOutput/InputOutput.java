package ProjecteMinesweeper.InputOutput;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class InputOutput {

    private static final Scanner input = new Scanner(System.in);
    private static final int MIN_ROWS_COLUMNS = 1;
    private static final int MAX_ROWS_COLUMNS = 10;
    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;

    public static int selectOption() {

        int option = 0;
        displayOptions();
        option = input.nextInt();

        return validateOption(option);
    }


    private static int validateOption(int option) {

        while (option < MIN_OPTION || option > MAX_OPTION) {
            System.out.println("PLEASE CHOOSE A CORRECT OPTION");
            option = input.nextInt();
        }
        return option;
    }


    public static int selectPositionXY() {
        int position = 0;
        System.out.println("CELL:");
        System.out.print("> ");
        position = input.nextInt();

        return validateXYCoordinate(position) - 1;
    }


    private static int validateXYCoordinate(int position) {

        while (position < MIN_ROWS_COLUMNS || position > MAX_ROWS_COLUMNS) {
            System.out.println("PLEASE PUT A CORRECT POSITION");
            position = selectPositionXY();
        }

        return position;
    }


    private static void displayOptions() {
        System.out.println("CHOOSE AN OPTION" +
                "\n 1. Uncheck" +
                "\n 2. Put Flag" +
                "\n 3. Remove Flag");
        System.out.print("> ");
    }


    public static int setCorrectCoordinateXY() {
        System.out.println("THIS POSITION IS ALREADY UNCOVERED" +
                "\nSELECT A COVERED CELL");
        int position;
        position = selectPositionXY();
        return position;
    }

    public static void displayInvalidCellFlag() {
        System.out.println("¡THIS CELL ISN'T MARKED!");
    }

    public static String A = "a";

    public static void displayInvalidCellRemove() {
        System.out.println("THIS CELL IS ALREADY UNCOVERED, YOU CANNOT PUT/REMOVE A FLAG IN AN UNCOVERED CELL");
        System.out.println(A);
    }

    public static void displayWinner(){
        System.out.println("\033[0;32m" + "**YOU WIN**");
    }

}

