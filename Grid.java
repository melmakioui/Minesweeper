package ProjecteMinesweeper;

public class Grid {

    private char status = '-';
    private Square squares[][];


    public Grid(){
        squares = new Square[10][10];

    }



    public void displayGrid(){

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                System.out.print("[" + getStatus() + "] ");
            }
            System.out.println();
        }
    }


    //Getters - Setters


    public void setStatus(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
}
