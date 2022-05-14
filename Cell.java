package ProjecteMinesweeper;

public class Cell {

    private boolean visible;
    private boolean flag;
    private boolean mine;
    private int minesAround;


    public Cell() {
        this.visible = false;
        this.flag = false;
        this.mine = false;
        this.minesAround = 0;
    }

    public boolean show() {

        if (visible)
            return true;

        if (flag && !mine)
            flag = false;

        visible = true;

        if (minesAround >= 1)
            return true;

        return false;
    }

    public boolean toggleFlag() {

        if (visible){
            return false;
        }

        flag = !flag;
        return true;
    }

    public void putMine() {
        this.mine = !mine;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isMine() {
        return mine;
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

    @Override
    public String toString() {
        if (isVisible() && isMine()) return "[*] ";
        if (isVisible() && getMinesAround() >= 1) return "[" + minesAround + "] ";
        if (isFlag()) return "[F] ";
        if (isVisible()) return "[ ] ";
        return "[-] ";
    }
}
