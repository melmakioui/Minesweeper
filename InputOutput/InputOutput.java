package ProjecteMinesweeper.InputOutput;

import java.util.Scanner;

public class InputOutput {

    private static final Scanner input = new Scanner(System.in);
    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;
    private static final int EASY = 10;
    private static final int NORMAL = 16;
    private static final int DIFFICULT = 25;

    private static final int MIN_ROWS_COLUMNS = 1;
    private static int MAX_ROWS_COLUMNS;


    public static int selectLevel() {
        int level;
        displayLevels();
        level = input.nextInt();

        switch (level) {
            case 1 -> {
                MAX_ROWS_COLUMNS = EASY;
                return EASY;
            }
            case 2 -> {
                MAX_ROWS_COLUMNS = NORMAL;
                return NORMAL;
            }
            case 3 -> {
                MAX_ROWS_COLUMNS = DIFFICULT;
                return DIFFICULT;
            }
        }
        MAX_ROWS_COLUMNS = EASY;
        return EASY;
    }

    public static int generateNumMines(int level) {

        return switch (level) {
            case EASY -> 10;
            case NORMAL -> 40;
            case DIFFICULT -> 70;
            default -> level;
        };
    }

    public static int selectOption() {

        int option;
        displayOptions();
        option = input.nextInt();

        while (option < MIN_OPTION || option > MAX_OPTION) {
            System.out.println("*¡¡¡PLEASE CHOOSE A CORRECT OPTION!!!*");
            System.out.print("> ");
            option = input.nextInt();
        }

        return option;
    }


    public static int selectPositionXY() {
        int position;
        System.out.print("> ");
        position = input.nextInt();

        while (position < MIN_ROWS_COLUMNS || position > MAX_ROWS_COLUMNS) {
            System.out.println("¡¡PLEASE PUT A CORRECT POSITION!!");
            System.out.print("> ");
            position = input.nextInt();
        }

        return position - 1;
    }


    public static void displayAlreadyShowedCell() {
        System.out.println("¡¡¡¡THIS CELL IS ALREADY SHOWED!!!!");

    }

    public static void displayCellHasFlag() {
        System.out.println("¡¡¡¡THIS CELL HAS A FLAG, REMOVE FLAG !!!!");

    }


    private static void displayOptions() {
        System.out.println("CHOOSE AN OPTION" +
                "\n 1. Show" +
                "\n 2. Put Flag/Remove Flag");
        System.out.print("> ");
    }

    private static void displayLevels() {
        System.out.println("**SELECT LEVEL**" +
                "\n 1. Easy" +
                "\n 2. Normal" +
                "\n 3. Hard");
        System.out.print(">");
    }


}

