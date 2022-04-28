package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

import java.util.Random;

public class Grid {

    private final char FLAG = 'F';
    private final char UNCOVERED = ' ';
    private final char BOMB = '*';

    private Cell cells[][];
    private int x;
    private int y;
    private int minesAround;


    public Grid() {
        this.cells = new Cell[10][10];
        this.x = 0;
        this.y = 0;
        this.minesAround = 0;

        initCells();
        generateMines();
        countMinesAround();
    }


    private void initCells() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }

    }


    private void generateMines() {
        Random randomMines = new Random();

        int counter = 0;

        while (counter != cells.length + 1) {
            x = randomMines.nextInt(cells[0].length - 1);
            y = randomMines.nextInt(cells.length - 1);

            cells[x][y].putMine(true);
            counter++;
        }
    }


    private void countMinesAround() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (hasMinesAround(i, j) && !cells[i][j].isMine()) {
                    cells[i][j].setMinesAround(minesAround);
                    //cells[i][j].setCellStatus(Character.forDigit(cells[i][j].getMinesAround(),10));
                }
            }
        }
    }


    private boolean hasMinesAround(int x, int y) {

        minesAround = 0;

        for (int row = -1; row <= 1; row++) {
            if (isValidRow(x, row)) {
                for (int col = -1; col <= 1; col++) {
                    if (isValidColumn(x, y, row, col)) {
                        if (cells[x + row][y + col].isMine()) {
                            minesAround++;
                        }
                    }
                }
            }
        }
        return minesAround > 0;
    }


    private boolean isValidRow(int x, int row) {

        return ((x + row >= 0) && (x + row < cells.length));
    }


    private boolean isValidColumn(int x, int y, int row, int col) {

        return (y + col >= 0)
                && (y + col < cells[x + row].length)
                && (!(row == 0 && col == 0));
    }


    //refactorizar metodo
    public boolean checkCoordinates(int x, int y) {

        this.x = y;
        this.y = y;

        if (cells[x][y].isMine()) {
            return true;
        }


        if (cells[x][y].isUncovered()) {
            System.out.println("THIS POSITION IS ALREADY UNCHECKED");
            while (cells[x][y].isUncovered()) {
                this.x = InputOutput.selectPositionXY();
                this.y = InputOutput.selectPositionXY();
            }
        }


        if (!cells[x][y].isUncovered()) {
            uncoverBrotherCells(x, y);
        }



        return false;

    }


    private void uncoverBrotherCells(int x, int y) {

        if (validCoordinates(x, y)) {
            return;
        }

        if (cells[x][y].isUncovered()) {
            return;
        }

        cells[x][y].uncoverCell(true);
        cells[x][y].setCellStatus(UNCOVERED);

        if (cells[x][y].getMinesAround() >= 1) {
            return;
        }


        int dx[] = {-1, 1, 0, 0, -1, -1, 1, 1};
        int dy[] = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int adjx = x+dx[i];
            int adjy = y+dy[i];

            uncoverBrotherCells(adjx,adjy);
        }
    }



    private boolean validCoordinates(int x, int y) {
        return (x < 0) || (y < 0) || x >= (cells.length) || y >= (cells[0].length);
    }


    public void displayBombs() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine()) {
                    cells[i][j].setCellStatus(BOMB);
                }
                System.out.print("\033[0;31m" + "[" + cells[i][j].getCellStatus() + "] ");
            }
            System.out.println();
        }
    }


    public void displayNumbers() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine()) {
                    cells[i][j].setCellStatus(BOMB);
                }
                System.out.print("[" + cells[i][j].getMinesAround() + "] ");
            }
            System.out.println();
        }
    }


    public void displayGrid() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isUncovered() && cells[i][j].getMinesAround() >= 1) {
                    System.out.print("[" + cells[i][j].getMinesAround() + "] ");
                    continue;
                }

                if (cells[i][j].isUncovered()) {
                    System.out.print("[" + cells[i][j].getCellStatus() + "] ");
                    continue;
                }

/*                if (cells[i][j].isMarked()) {
                    cells[i][j].setCellStatus(FLAG);
                    System.out.print("[" + cells[i][j].getCellStatus() + "] ");
                    continue;
                }*/

                System.out.print("[" + cells[i][j].getCellStatus() + "] ");
            }
            System.out.println();
        }

    }
}



