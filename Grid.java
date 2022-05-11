package ProjecteMinesweeper;

import java.util.Random;

public class Grid {


    private final int[][] adjacentXY = {
            {-1, 1, 0, 0, -1, -1, 1, 1},
            {0, 0, -1, 1, -1, 1, -1, 1}};
    private Cell cells[][];
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

            if (cells[x][y].isMine())
                continue;


            cells[x][y].putMine();
            incrementNeighbours(x, y);
            counter++;
        }
    }


    private void incrementNeighbours(int x, int y) {

        for (int dx = -1; dx <= 1; dx++)
            if (isValidRow(x, dx))
                for (int dy = -1; dy <= 1; dy++)
                    if (isValidColumn(x, y, dx, dy) && (!(dx == 0 && dy == 0)))
                        if (!cells[x + dx][y + dy].isVisible() && !cells[x + dx][y + dy].isMine())
                            cells[x + dx][y + dy].incrementCell();
    }


    private boolean isValidRow(int x, int row) {

        return ((x + row >= 0) && (x + row < cells.length));
    }


    private boolean isValidColumn(int x, int y, int row, int col) {

        return (y + col >= 0) && (y + col < cells[x + row].length);
    }


    public void showCell(int x, int y) {

        if (isNotValidCoordinate(x, y))
            return;


        if (cells[x][y].show())
            return;


        for (int i = 0; i < 8; i++)
            showCell(x + adjacentXY[0][i], y + adjacentXY[1][i]);

    }


    public boolean toggleFlag(int x, int y) {
        return cells[x][y].toggleFlag();
    }


    private boolean isNotValidCoordinate(int x, int y) {
        return (x < 0) || (y < 0) || x >= (cells.length) || y >= (cells[0].length);
    }


    public void displayGrid() {

        System.out.println();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {

                System.out.print(cells[row][col]);
            }
            System.out.println();
        }
    }


    public boolean isMineCell(int x, int y) {
        return cells[x][y].isMine();
    }

    public boolean isVisibleCell(int x, int y) {
        return cells[x][y].isVisible();
    }

    public boolean isFlagCell(int x, int y) {
        return cells[x][y].isFlag();
    }

    public int getVisibleCells() {

        int visibleCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isVisible()) {
                    visibleCells++;
                }
            }
        }

        return visibleCells;
    }

}



