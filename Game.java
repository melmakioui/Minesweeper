package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private int x;
    private int y;
    private boolean lost = false;
    private int playerOption = 0;
    private Grid grid;


    public Game() {
        this.grid = new Grid();

        initGame();
    }


    private void initGame() {


        do {
            grid.displayGrid();
            playerOption = InputOutput.selectOption();
            selectedOption(); // --> buscar nombre adecuado

        } while (!lost);

    }


    private void selectedOption() {


        switch (playerOption) {
            case 1:

                selectCoordinates();

                if (grid.cellContainsMine(x, y)) {
                    lost = true;
                    grid.displayBombs();
                    return;
                }

                if (!grid.checkCoordinates(x, y)) {
                    grid.uncoverCells(x, y);
                    return;
                } else {

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

    private void selectCoordinates() {
        System.out.println("ROW");
        this.x = InputOutput.selectPositionXY();
        System.out.println("COLUMN");
        this.y = InputOutput.selectPositionXY();
    }


    private void selectCorrectCoordinates() {


        while (grid.checkCoordinates(x,y)){
            System.out.println("CELL ALREADY UNCHECKED");
            selectCoordinates();
        }
    }

}
