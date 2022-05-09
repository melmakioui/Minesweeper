package ProjecteMinesweeper;


import ProjecteMinesweeper.InputOutput.InputOutput;

public class Cell {

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

    public boolean isFlag(){
        return this.flag;
    }

    public void hide() {
        this.cell = '-';
    }

    public void bomb() {
        this.cell = 'X';
    }

    public void expose() {
        this.cell = ' ';
    }

    public void flag() {
        this.cell = 'F';
    }

    public char getCell() {
        return cell;
    }

    public void putMinesAround() {
        this.cell = Character.forDigit(minesAround, 10);
    }
}
