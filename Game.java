package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private final int EMPTY_CELLS;
    private final int CELLS;
    private final int MINES;

    private int x;
    private int y;
    private int playerOption = 0;
    private final Grid grid;


    public Game() {
        this.CELLS = InputOutput.selectLevel();
        this.MINES = InputOutput.generateNumMines(CELLS);
        this.EMPTY_CELLS = ((CELLS * CELLS) - MINES);
        this.grid = new Grid(CELLS, MINES);

        initGame();
    }


    private void initGame() {

        do {
            grid.displayGrid();
            playerOption = InputOutput.selectOption();

            switch (playerOption) {
                case 1 -> isValidToShow();
                case 2 -> {
                    selectCoordinates();
                    grid.toggleFlag(x, y);
                }
            }

        } while (!isLoss() && !isWinner());

    }


    private void isValidToShow() {
        selectCoordinates();

        while (grid.isCellShowed(x, y)) {
            InputOutput.displayAlreadyShowedCell();
            selectCoordinates();
        }
        grid.showCell(x, y);
    }


    private boolean isWinner() {
        if (grid.getShowedCells() == EMPTY_CELLS) {
            InputOutput.displayWinner();
            grid.displayGrid();
            return true;
        }
        return false;
    }


    private boolean isLoss() {

        //Constant -> 2
        if (grid.isCellContainsMine(x, y) && playerOption != 2) {
            grid.displayBombs();
            return true;
        }
        return false;
    }


    private void selectCoordinates() {
        System.out.println("SELECT ROW:");
        x = InputOutput.selectPositionXY();
        System.out.println("SELECT COLUMN:");
        y = InputOutput.selectPositionXY();
    }

}
