package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private int x;
    private int y;
    private boolean win = false;
    private boolean lost = false;
    private int option = 0;
    private Grid grid;


    public Game(Grid grid) {
        this.grid = new Grid();

        initGame();
    }


    private void initGame() {

        do {
            option = 0;
            option = InputOutput.selectOption();
            selectedOption();


        } while (!win && !lost);


    }

    private void selectedOption() {

        x = InputOutput.selectPositionXY();
        y = InputOutput.selectPositionXY();

        switch (option) {
            case 1:

                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
        }
    }


}
