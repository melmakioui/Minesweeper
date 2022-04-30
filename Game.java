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
            case 1 -> {

                selectCoordinates();
                if (grid.cellContainsMine(x, y)) {
                    lost = true;
                    grid.displayBombs();
                    return;
                }

                if (!grid.checkCoordinates(x, y)) {
                    grid.uncoverCells(x, y);
                    return;
                }
                selectCorrectCoordinates();
                grid.uncoverCells(x, y);
            }
            case 2 -> {
                selectCoordinates();
                grid.markCell(x, y);
            }
            case 3 -> {
                selectCoordinates();
                grid.unmarkCell(x, y);
            }
        }
    }

    private void selectCoordinates() {
        System.out.print("ROW ");
        this.x = InputOutput.selectPositionXY();
        System.out.print("COLUMN ");
        this.y = InputOutput.selectPositionXY();
    }


    private void selectCorrectCoordinates() {

        while (grid.checkCoordinates(x, y)) {
            x = InputOutput.setCorrectCoordinateXY();
            y = InputOutput.setCorrectCoordinateXY();
        }
    }

}
