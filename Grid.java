package ProjecteMinesweeper;

import java.util.Random;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Grid {

    private final char FLAG = 'F';
    private final char UNCOVERED = ' ';
    private final char BOMB = '*';
    private final char COVER = '-';

    private final int[][] adjacentXY = {
            {-1, 1, 0, 0, -1, -1, 1, 1},
            {0, 0, -1, 1, -1, 1, -1, 1}
    };
    private int flagCounter = 10;
    private Cell cells[][];
    private int mineCounter;
    private int savedCellsCounter;
    private int uncoveredCells;


    public Grid() {
        this.cells = new Cell[10][10];
        this.mineCounter = 0;

        initCells();
        generateMines();
        countMinesAround();
    }


    private void initCells() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setCellStatus(COVER);
            }
        }
    }


    private void generateMines() {
        Random randomMines = new Random();

        int counter = 0;
        int x;
        int y;

        while (counter < 10) {
            x = randomMines.nextInt(cells.length - 1);
            y = randomMines.nextInt(cells[0].length - 1);

            if (cells[x][y].isMine()){
                cells[x][y].putMine(false);
                counter--;
                continue;
            }
            cells[x][y].putMine(true);
            counter++;
        }
    }


    private void countMinesAround() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (hasMinesAround(i, j) ) {
                    cells[i][j].setMinesAround(mineCounter);
                }
            }
        }
    }


    private boolean hasMinesAround(int x, int y) {

        if (cells[x][y].isMine()){
            return false;
        }

        mineCounter = 0;

        for (int row = -1; row <= 1; row++) {
            if (isValidRow(x, row)) {
                for (int col = -1; col <= 1; col++) {
                    if (isValidColumn(x, y, row, col) && (!(row == 0 && col == 0))) {
                        if (cells[x + row][y + col].isMine()) {
                            mineCounter++;
                        }
                    }
                }
            }
        }
        return mineCounter > 0;
    }


    private boolean isValidRow(int x, int row) {

        return ((x + row >= 0) && (x + row < cells.length));
    }


    private boolean isValidColumn(int x, int y, int row, int col) {

        return (y + col >= 0) && (y + col < cells[x + row].length);
    }


    public void uncoverCells(int x, int y) {

        if (isValidPosition(x, y)) {
            return;
        }

        if (cells[x][y].isUncovered()) {
            return;
        }

        cells[x][y].uncoverCell(true);

        if (cells[x][y].getMinesAround() >= 1) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int adjX = x + adjacentXY[0][i];
            int adjY = y + adjacentXY[1][i];

            uncoverCells(adjX, adjY);
        }
    }


    private boolean isValidPosition(int x, int y) {
        return (x < 0) || (y < 0) || x >= (cells.length) || y >= (cells[0].length);
    }


    public boolean markCell(int x, int y) {

        if (isCellContainsMine(x,y)) {
            savedCellsCounter++;
        }

        if (!cells[x][y].isUncovered()) {
            cells[x][y].markCell(true);
            flagCounter--;
            return false;
        }

       return false;
    }


    public void unmarkCell(int x, int y) {

        if (isCellContainsMine(x,y)) {
            savedCellsCounter--;
        }

        cells[x][y].markCell(false);
        cells[x][y].setCellStatus(COVER);
        flagCounter++;
    }

    public void displayBombs() {

        System.out.println();
        System.out.println("\033[0;31m" + "**YOU LOST**");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine()) {
                    cells[i][j].setCellStatus(BOMB);
                }
                System.out.print("[" + cells[i][j].getCellStatus() + "] ");
            }
            System.out.println();
        }
    }


    public void displayGrid() {

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {


                if (cells[row][col].isUncovered() && cells[row][col].getMinesAround() >= 1) {
                    System.out.print("[" + cells[row][col].getMinesAround() + "] ");
                    continue;
                }

                if (cells[row][col].isUncovered()) {
                    cells[row][col].setCellStatus(UNCOVERED);
                    System.out.print("[" + cells[row][col].getCellStatus() + "] ");
                    continue;
                }

                if (cells[row][col].isMarked()) {
                    cells[row][col].setCellStatus(FLAG);
                    System.out.print("[" + cells[row][col].getCellStatus() + "] ");
                    continue;
                }

                System.out.print("[" + cells[row][col].getCellStatus() + "] ");

            }
            System.out.println();
        }

    }


    public boolean isCellContainsMine(int x, int y) {
        return cells[x][y].isMine();
    }

    public boolean isCellUncovered(int x, int y) {
        return cells[x][y].isUncovered();
    }

    public boolean isCellMarked(int x, int y){
        return cells[x][y].isMarked();
    }

    public int getFlagCounter() {
        return flagCounter;
    }

    public int getSavedCellsCounter() {
        return savedCellsCounter;
    }

    public int getUncoveredCells() {

        uncoveredCells = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isUncovered()) {
                    uncoveredCells++;
                }
            }
        }
        return uncoveredCells;
    }


}



