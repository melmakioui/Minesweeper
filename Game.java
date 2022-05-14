package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private final int EMPTY_CELLS;
    private final int CELLS;
    private final int MINES;
    private final int SHOW_CELL_OPTION = 1;
    private final int FLAG_CELL_OPTION = 2;

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
                case SHOW_CELL_OPTION ->{
                    selectCoordinates();
                    if (isValidToShow()){
                        grid.showCell(x, y);
                    }

                }

                case FLAG_CELL_OPTION -> {
                    selectCoordinates();
                    if (!grid.toggleFlag(x, y))
                        InputOutput.dispalyVisibleCell();
                }
            }

        } while (!isWinner());

    }


    private boolean isValidToShow() {

        if (grid.isFlagCell(x,y)){
            InputOutput.displayIsFlagCell();
            return false;
        }

        if (grid.isVisibleCell(x, y)) {
            InputOutput.dispalyVisibleCell();
            return false;
        }
        return true;
    }


    private boolean isWinner() {
        if (grid.getVisibleCells() == EMPTY_CELLS) {
            System.out.println("\033[0;32m" + "**YOU WIN**");
            grid.displayGrid();
            return true;
        }

        if (grid.isMineCell(x, y) && playerOption != FLAG_CELL_OPTION) {
            System.out.println("\033[0;31m" + "**YOU LOSE**");
            grid.displayGrid();
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
