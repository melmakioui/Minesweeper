package ProjecteMinesweeper;

import java.util.Random;

public class Grid {

    private final char FLAG = 'F';
    private final char UNCHEKED = ' ';
    private final char BOMB = '*';
    private Square squares[][];
    private int x = 0;
    private int y = 0;
    private int mines = 0;


    public Grid() {
        squares = new Square[10][10];
        initSquares();
        generateMines();
    }


    private void initSquares() {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                squares[i][j] = new Square();
            }
        }

    }

    private void generateMines() {
        Random randomMines = new Random();

        int counter = 0;

        while (counter != squares.length + 1) {
            x = randomMines.nextInt(squares[0].length - 1);
            y = randomMines.nextInt(squares.length - 1);

            squares[y][x].putMine(true);
            counter++;
        }
    }


    public boolean checkCoordinates(int x, int y) {


        this.x = x;
        this.y = y;


        if (squares[x][y].isMine()) { //lost
            return true;
        }

        if (!squares[x][y].isUnchecked()) {
            uncheckSquares();
        }

        return false;

    }


    private void uncheckSquares() {

        for (int i = y; i < squares[0].length; i++) {
            if (!squares[x][i].isMine()) {
                if (checkMinesArround()) {
                    break;
                }
                squares[x][i].uncheckSquare(true);
                squares[x][i].setStatus(UNCHEKED);
            }
        }


    }


    private boolean checkMinesArround() { //cuenta minas alrededor

        mines = 0;

        for (int row = -1; row <= 1; row++) {
            if (isValidRow(x, row)) {
                for (int col = -1; col <= 1; col++) {
                    if (isValidColumn(x, y, row, col)) {
                        if (squares[x + row][y + col].isMine()) {
                            updateGrid(x, y, row, col);
                        }
                    }
                }
            }
        }
        return mines > 0;
    }


    private boolean isValidRow(int x, int row) {

        return ((x + row >= 0) && (x + row < squares.length));
    }


    private boolean isValidColumn(int x, int y, int row, int col) {

        return (y + col >= 0) && (y + col < squares[x + row].length) && (!(row == 0 && col == 0));
    }


    private void updateGrid(int x, int y, int row, int col) {

        squares[this.x][this.y].setMinesAround(++mines);
        squares[this.x][this.y].setStatus(Character.forDigit(squares[x + row][y + col].getMinesAround(), 10));
    }


    public void displayBombs() {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {

                if (squares[i][j].isMine()) {
                    squares[i][j].setStatus(BOMB);
                }

                System.out.print("[" + squares[i][j].getStatus() + "] ");
            }
            System.out.println();
        }
    }


    public void displayGrid() {

        int yNumbers = 1;

        for (int i = 0; i < squares.length; i++) {
            System.out.print(yNumbers++ + "  ");
            for (int j = 0; j < squares[0].length; j++) {

                if (squares[i][j].getMinesAround() != 0) {
                    System.out.print("[" + squares[i][j].getMinesAround() + "] ");
                } else System.out.print("[" + squares[i][j].getStatus() + "] ");


            }
            System.out.println();
        }
    }


}
