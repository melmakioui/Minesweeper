package ProjecteMinesweeper;


public class Cell {

    private final char FLAG = 'F';
    private final char UNCOVERED = ' ';
    private final char BOMB = '*';
    private final char COVER = '-';

    private char status;  // error de plantetjament
    private boolean hided;
    private boolean flag; //"flag"
    private boolean mine;
    private int minesAround;


    public Cell(){
        this.hided = false;
        this.flag = false;
        this.mine = false;
        this.minesAround = 0;

    }

    public boolean toggleFlag(){

        if (hided)
            return false;

        flag = !flag;

        return true;
    }

    public boolean show(){
        if ( mine ) {
            // tot el tractament de perdre partida
        }

        hided = false;

        if ( flag )
            flag = false;

        return true;

    }

    //Getters - Setters
    public boolean isHided() {
        return hided;
    }

    public void uncoverCell() {
        this.status = UNCOVERED;
        this.hided = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public void markCell() {

        if (isMine()) {
            //savedCellsCounter++;
            //incrementSavedCellsCounter
        }

        if (!isHided()) {
            flag = true;
            //decrementflagCounter--;
        }
        this.status = FLAG;
        this.flag = true;
    }

    public void unmarkCell(){
        this.status = COVER;
        this.flag = false;
    }

    public boolean isMine() {
        return mine;
    }

    public void putMine() {
        this.mine = true;
    }

    public void removeMine(){
        this.mine = false;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void incrementCell() {
        this.minesAround ++;
    }

    public char getCellStatus() {
        return status;
    }

    public void setCellStatus(char status) {
        this.status = status;
    }

    public void hide(){
        this.status = COVER;
    }


}
