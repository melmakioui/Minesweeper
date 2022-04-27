package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private int x;
    private int y;
    private boolean lost = false;
    private int option = 0;
    private Grid grid;


    public Game() {
        this.grid = new Grid();

        initGame();
    }


    private void initGame() {

        do {
            //grid.displayGrid();
            grid.displayNumbers();
            grid.displayBombs();
            option = InputOutput.selectOption();
            selectedOption();

        } while (!lost);

    }


    private void selectedOption() {

        x = InputOutput.selectPositionXY();
        y = InputOutput.selectPositionXY();

        switch (option) {
            case 1:
                if (grid.checkCoordinates(x,y)){
                    lost = true;
                    grid.displayBombs();
                }
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
