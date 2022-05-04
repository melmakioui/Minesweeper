package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private final int EMPTY_CELLS = 90;
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
            System.out.println( "FLAGS: " + grid.getFlagCounter());
            grid.displayGrid();
            playerOption = InputOutput.selectOption();
            selectedOption();

            if (isWinner()){
                InputOutput.displayWinner();
                grid.displayGrid();
                break;
            }
        } while (!lost);

    }


    public boolean isWinner(){
        return (grid.getSavedCellsCounter() == 10 && grid.getFlagCounter() >= 0) || grid.getUncoveredCells() == EMPTY_CELLS;
    }



    private void selectedOption() {

        switch (playerOption) {
            case 1 -> {

                selectCoordinates();
                if (grid.containsMine(x, y)) {
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
        System.out.print("X COORDINATE (ROW)");
        this.x = InputOutput.selectPositionXY();
        System.out.print("Y COORDINATE (COLUMN)");
        this.y = InputOutput.selectPositionXY();
    }


    private void selectCorrectCoordinates() {
        while (grid.checkCoordinates(x, y)) {
            x = InputOutput.setCorrectCoordinateXY();
            y = InputOutput.setCorrectCoordinateXY();
        }
    }

}
