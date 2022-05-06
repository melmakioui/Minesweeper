package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private int EMPTY_CELLS;
    private int x;
    private int y;
    private boolean lost = false;
    private int playerOption = 0;
    private Grid grid;

    private int level;
    private int mines;


    public Game() {
        level = InputOutput.selectLevel();
        mines = InputOutput.generateNumMines(level);
        this.grid = new Grid(level, mines);

        initGame();
    }


    private void initGame() {

        do {
            //System.out.println("FLAGS: " + grid.getFlagCounter());
            grid.displayGrid();
            playerOption = InputOutput.selectOption();
            choosedOption();

        } while (!isLoss() && !isWinner());

    }


    private void choosedOption() {

        selectCoordinates();

        switch (playerOption) {
            case 1 -> verifyUncoverCoordinate();
            case 2 -> verifyMarkCellCoordinate();
            case 3 -> verifyUnmarkCellCoordinate();
        }
    }


    private void verifyUncoverCoordinate() {
        while (grid.isCellCoordinatesUncovered(x, y)) {
            InputOutput.displayAlreadyUncoveredCoordinate();
            selectCoordinates();
        }
        grid.uncoverCells(x, y);
    }


    private void verifyMarkCellCoordinate() {
        if (grid.isCellCoordinatesMarked(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        if (grid.isCellCoordinatesUncovered(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        grid.markCell(x, y);
    }


    private void verifyUnmarkCellCoordinate() {
        if (grid.isCellCoordinatesUncovered(x, y) && !grid.isCellCoordinatesMarked(x, y)) {
            InputOutput.displayInvalidCellToMark();
            return;
        }

        if (!grid.isCellCoordinatesMarked(x, y)) {
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

        //Constant
        if (playerOption == 2) {
            return false;
        }

        if (grid.isCellCoordinatesContainsMine(x, y)) {
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
