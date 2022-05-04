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
        grid.displayBombs();
        initGame();
    }


    private void initGame() {


        do {
            System.out.println(grid.getSavedCellsCounter());
            System.out.println(grid.getUncoveredCells());
            System.out.println("FLAGS: " + grid.getFlagCounter());
            grid.displayGrid();
            playerOption = InputOutput.selectOption();
            choosedOption();

        } while (!isLoss() && !isWinner());

    }


    private void choosedOption() {

        selectCoordinates();

        switch (playerOption) {
            case 1 -> verifyUncoverCoordinates();
            case 2 -> verifyMarkCellCoordinates();
            case 3 -> verifyUnmarkCellCoordinates();
        }
    }


    private void verifyUncoverCoordinates() {
        while (grid.isCellUncovered(x, y)) {
            InputOutput.displayAlreadyUncoveredCoordinate();
            selectCoordinates();
        }
        grid.uncoverCells(x, y);
    }


    private void verifyMarkCellCoordinates() {
        if (grid.isCellMarked(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        if (grid.isCellUncovered(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        grid.markCell(x, y);
    }


    private void verifyUnmarkCellCoordinates() {
        if (grid.isCellUncovered(x, y) && !grid.isCellMarked(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        if (!grid.isCellMarked(x, y)) {
            InputOutput.displayInvalidCellFlag();
            return;
        }

        grid.unmarkCell(x, y);
    }


    private boolean isWinner() {
        if ((grid.getSavedCellsCounter() == 10 && grid.getFlagCounter() >= 0)
                || grid.getUncoveredCells() == EMPTY_CELLS) {
            InputOutput.displayWinner();
            grid.displayGrid();
            return true;
        }
        return false;
    }

    private boolean isLoss() {

        //Problem right here
        if (playerOption == 2) {
            return false;
        }

        if (grid.isCellContainsMine(x, y)) {
            grid.displayBombs();
            return true;
        }
        return false;
    }


    private void selectCoordinates() {
        System.out.print("X COORDINATE (ROW)");
        x = InputOutput.selectPositionXY();
        System.out.print("Y COORDINATE (COLUMN)");
        y = InputOutput.selectPositionXY();
    }


}
