package ProjecteMinesweeper;


public class Cell {

    private char status = '-';
    private boolean uncovered;
    private boolean marked; //"flag"
    private boolean mine;
    private int minesAround;


    public Cell(){
        this.uncovered = false;
        this.marked = false;
        this.mine = false;
        this.minesAround = 0;

    }


    //Getters - Setters
    public boolean isUncovered() {
        return uncovered;
    }

    public void uncoverCell(boolean unchecked) {
        this.uncovered = unchecked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void markCell(boolean marked) {
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

    public char getCellStatus() {
        return status;
    }

    public void setCellStatus(char status) {
        this.status = status;
    }
}
