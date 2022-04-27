package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

import java.util.Random;

public class Grid {

    private final char FLAG = 'F';
    private final char UNCHEKED = ' ';
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

            cells[y][x].putMine(true);
            counter++;
        }
    }


    private void countMinesAround(){

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (checkMinesArround(i,j) && !cells[i][j].isMine()){
                    cells[i][j].setMinesAround(minesAround);
                }
            }
        }

    }



    private boolean checkMinesArround(int x, int y) {

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

        this.x = x;
        this.y = y;


        if (cells[x][y].isMine()) {
            return true;
        }


        if (cells[x][y].isUnchecked()) {
            System.out.println("THIS POSITION IS ALREADY UNCHECKED");
            while (cells[x][y].isUnchecked()) {
                this.x = InputOutput.selectPositionXY();
                this.y = InputOutput.selectPositionXY();
            }
        }

        return false;

    }




    public void displayBombs() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine()) {
                    cells[i][j].setStatus(BOMB);
                }
                System.out.print("\033[0;31m" + "[" + cells[i][j].getStatus() + "] ");
            }
            System.out.println();
        }
    }


    public void displayNumbers(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].isMine()) {
                    cells[i][j].setStatus(BOMB);
                }
                System.out.print( "[" + cells[i][j].getMinesAround() + "] ");
            }
            System.out.println();
        }
    }


    public void displayGrid() {

        int yNumbers = 1;

        for (int i = 0; i < cells.length; i++) {
            System.out.print(yNumbers++ + "  ");
            for (int j = 0; j < cells[0].length; j++) {

                if (cells[i][j].getMinesAround() != 0) {
                    System.out.print("[" + cells[i][j].getMinesAround() + "] ");
                } else System.out.print("[" + cells[i][j].getStatus() + "] ");

            }
            System.out.println();
        }
    }


}
