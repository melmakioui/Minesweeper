package ProjecteMinesweeper;


import ProjecteMinesweeper.InputOutput.InputOutput;

public class Cell {

    private final char FLAG = 'F';
    private final char SHOW = ' ';
    private final char BOMB = 'X';
    private final char HIDE = '-';

    private char cell;
    private boolean hided;
    private boolean flag;
    private boolean mine;
    private int minesAround;


    public Cell() {
        this.hided = false;
        this.flag = false;
        this.mine = false;
        this.minesAround = 0;
    }

    public boolean show() {

        if (hided)
            return true;

        if (flag && !mine)
            flag = false;

        hided = true;
        expose();

        if (minesAround >= 1)
            return true;

        return false;
    }


    public void toggleFlag() {

        if (hided){
            InputOutput.displayAlreadyShowedCell();
            return;
        }

        flag = !flag;
        flag();

        if (!flag)
            hide();
    }

    //Getters - Setters
    public boolean isHided() {
        return hided;
    }

    public boolean isMine() {
        return mine;
    }

    public void toggleMine() {
        this.mine = !mine;
    }

    public void incrementCell() {
        this.minesAround++;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void hide() {
        this.cell = HIDE;
    }

    public void bomb() {
        this.cell = BOMB;
    }

    public void expose() {
        this.cell = SHOW;
    }

    public void flag() {
        this.cell = FLAG;
    }

    public char getCell() {
        return cell;
    }

    public void putMinesAround() {
        this.cell = Character.forDigit(minesAround, 10);
    }
}
