package ProjecteMinesweeper;

import java.util.Random;

public class Grid {

    //remove
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
    private int savedCellsCounter;
    private int uncoveredCells;
    private int mines;


    public Grid(int length, int mines) {
        this.cells = new Cell[length][length];
        this.mines = mines;

        initCells();
        generateMines();
    }




    private void initCells() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();;
                cells[i][j].hide();
            }
        }
    }


    private void generateMines() {
        Random randomMines = new Random();

        int counter = 0;
        int x;
        int y;

        while (counter < mines) {
            x = randomMines.nextInt(cells.length - 1);
            y = randomMines.nextInt(cells[0].length - 1);

            if (cells[x][y].isMine()) {
                cells[x][y].removeMine();
                counter--;
                continue;
            }
            cells[x][y].putMine();
            incrementNeighbours(x,y);
            counter++;
        }
    }



    private void incrementNeighbours(int x, int y) {

        for (int dx = -1; dx <= 1; dx++) {
            if (isValidRow(x, dx)) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (isValidColumn(x, y, dx, dy) && (!(dx == 0 && dy == 0))) {
                        if (!cells[x + dx][y + dy].isHided() && !cells[x+dx][y+dy].isMine()) {
                            cells[x+dx][y+dy].incrementCell();
                        }
                    }
                }
            }
        }
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


        cells[x][y].show();



        ////////
        if (cells[x][y].isHided()) {
            return;
        }

        if (cells[x][y].isFlag()){
            return;
        }

        cells[x][y].uncoverCell();

        if (cells[x][y].getMinesAround() >= 1) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            uncoverCells(x + adjacentXY[0][i], y + adjacentXY[1][i]);
        }
    }


    private boolean isValidPosition(int x, int y) {
        return (x < 0) || (y < 0) || x >= (cells.length) || y >= (cells[0].length);
    }


    //class cell
    public boolean markCell(int x, int y) {

        if (isCellCoordinatesContainsMine(x, y)) {
            savedCellsCounter++;
        }

        if (!cells[x][y].isHided()) {
            cells[x][y].markCell();
            flagCounter--;
            return false;
        }
        return false;
    }

    //class cell
    public void unmarkCell(int x, int y) {

        if (isCellCoordinatesContainsMine(x, y)) {
            savedCellsCounter--;
        }

        cells[x][y].markCell();
        cells[x][y].hide();
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


                if (cells[row][col].isHided() && cells[row][col].getMinesAround() >= 1) {
                    System.out.print("[" + cells[row][col].getMinesAround() + "] ");
                    continue;
                }

                if (cells[row][col].isHided()) {
                    cells[row][col].setCellStatus(UNCOVERED);
                    System.out.print("[" + cells[row][col].getCellStatus() + "] ");
                    continue;
                }

                if (cells[row][col].isFlag()) {
                    cells[row][col].setCellStatus(FLAG);
                    System.out.print("[" + cells[row][col].getCellStatus() + "] ");
                    continue;
                }

                System.out.print("[" + cells[row][col].getCellStatus() + "] ");

            }
            System.out.println();
        }

    }


    public boolean isCellCoordinatesContainsMine(int x, int y) {
        return cells[x][y].isMine();
    }

    public boolean isCellCoordinatesUncovered(int x, int y) {
        return cells[x][y].isHided();
    }

    public boolean isCellCoordinatesMarked(int x, int y) {
        return cells[x][y].isFlag();
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
                if (cells[i][j].isHided()) {
                    uncoveredCells++;
                }
            }
        }
        return uncoveredCells;
    }


}



