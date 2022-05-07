package ProjecteMinesweeper;

import java.util.Random;

public class Grid {


    private final int[][] adjacentXY = {
            {-1, 1, 0, 0, -1, -1, 1, 1},
            {0, 0, -1, 1, -1, 1, -1, 1}
    };

    private Cell cells[][];
    private int showedCells;
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
                cells[i][j] = new Cell();
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
                cells[x][y].toggleMine();
                counter--;
                continue;
            }
            cells[x][y].toggleMine();
            incrementNeighbours(x, y);
            counter++;
        }
    }


    private void incrementNeighbours(int x, int y) {

        for (int dx = -1; dx <= 1; dx++)
            if (isValidRow(x, dx))
                for (int dy = -1; dy <= 1; dy++)
                    if (isValidColumn(x, y, dx, dy) && (!(dx == 0 && dy == 0)))
                        if (!cells[x + dx][y + dy].isHided() && !cells[x + dx][y + dy].isMine())
                            cells[x + dx][y + dy].incrementCell();
    }


    private boolean isValidRow(int x, int row) {

        return ((x + row >= 0) && (x + row < cells.length));
    }


    private boolean isValidColumn(int x, int y, int row, int col) {

        return (y + col >= 0) && (y + col < cells[x + row].length);
    }


    public void showCell(int x, int y) {

        if (isNotValidCoordinate(x, y)) {
            return;
        }

        if (cells[x][y].show()) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            showCell(x + adjacentXY[0][i], y + adjacentXY[1][i]);
        }
    }


    public void toggleFlag(int x, int y) {
        cells[x][y].toggleFlag();
    }


    private boolean isNotValidCoordinate(int x, int y) {
        return (x < 0) || (y < 0) || x >= (cells.length) || y >= (cells[0].length);
    }


    public void displayBombs() {

        System.out.println();
        System.out.println("\033[0;31m" + "**YOU LOST**");

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine())
                    cells[i][j].bomb();

                System.out.print("[" + cells[i][j].getCell() + "] ");
            }
            System.out.println();
        }
    }



    public void displayGrid() {


        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {

                if (cells[row][col].isHided() && cells[row][col].getMinesAround() >= 1)
                    cells[row][col].putMinesAround();

                System.out.print("[" + cells[row][col].getCell() + "] ");

            }
            System.out.println();

        }
    }

    //Method fowarding
    public boolean isCellContainsMine(int x, int y) {
        return cells[x][y].isMine();
    }

    public boolean isCellShowed(int x, int y) {
        return cells[x][y].isHided();
    }



    //Getters Setters
    public int getShowedCells() {

        showedCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isHided()) {
                    showedCells++;
                }
            }
        }

        return showedCells;
    }

}



