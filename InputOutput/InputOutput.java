package ProjecteMinesweeper.InputOutput;

import ProjecteMinesweeper.Game;

import java.util.Scanner;

public class InputOutput {

    private static final Scanner input = new Scanner(System.in);
    private static final int MIN_ROWS_COLUMNS = 1;
    private static final int MAX_ROWS_COLUMNS = 10;
    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;

    public static int selectOption() {

        int option = 0;
        chooseOptionText();
        option = input.nextInt();

        return validateOption(option);
    }


    private static int validateOption(int option) {

        while (option < MIN_OPTION || option > MAX_OPTION) {
            System.out.println("PLEASE CHOOSE AN OPTION");
            option = input.nextInt();
        }

        return option;
    }


    public static int selectPositionXY() {
        int position = 0;
        System.out.println("POSITION");
        position = input.nextInt();

        return validatePosition(position) -1;
    }


    private static int validatePosition(int position) {

        while (position < MIN_ROWS_COLUMNS || position > MAX_ROWS_COLUMNS) {
            System.out.println("PLEASE PUT A CORRECT POSITION");
            position = input.nextInt();
        }

        return position;
    }


    private static void chooseOptionText() {
        System.out.println("CHOOSE AN OPTION" +
                "\n 1. Uncheck" +
                "\n 2. Put Flag" +
                "\n 3. Remove Flag");
        System.out.print("> ");
    }

}
