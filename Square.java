package ProjecteMinesweeper;


public class Square {

    private char status = '-';
    private boolean unchecked = false;
    private boolean marked = false; //"flag"
    private boolean mine = false;
    private int minesAround;


    public Square(){

    }



    //Getters - Setters
    public boolean isUnchecked() {
        return unchecked;
    }

    public void uncheckSquare(boolean unchecked) {
        this.unchecked = unchecked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void markSquare(boolean marked) {
        this.marked = marked;
    }

    public boolean isMine() {
        return mine;
    }

    public void putMine(boolean mine) {
        this.mine = mine;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
