package ProjecteMinesweeper;

import java.util.Random;
import ProjecteMinesweeper.Square;

public class Grid {

    private final char FLAG = 'F';
    private final char UNCHEKED = ' ';
    private final char BOMB = '*';
    private Square squares[][];
    private int x = 0;
    private int y = 0;


    public Grid() {
        squares = new Square[10][10];

        initSquares();
        generateMines();
    }


    private void initSquares(){

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length ; j++) {
                squares[i][j] = new Square();
            }
        }

    }

    private void generateMines() {
        Random randomMines = new Random();

        int counter = 0;

        while (counter != squares.length + 1) {
            x = randomMines.nextInt(squares[0].length);
            y = randomMines.nextInt(squares.length);

            squares[y][x].putMine(true);
            counter++;
        }
    }



    private void uncheckSquares(int x, int y){

        this.x = x;
        this.y = y;



    }

    public void displayGrid() {

        int yNumbers = 1;

        for (int i = 0; i < squares.length; i++) {
            System.out.print(yNumbers++ + "  ");
            for (int j = 0; j < squares[0].length; j++) {
                System.out.print("[" + squares[i][j].getStatus() + "] ");
            }
            System.out.println();
        }
    }






}
