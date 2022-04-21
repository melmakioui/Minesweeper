package ProjecteMinesweeper;

import ProjecteMinesweeper.InputOutput.InputOutput;

public class Game {

    private boolean win = false;
    private boolean lost = false;
    private int option = 0;
    private Grid grid;


    public Game() {
        this.grid = new Grid();

        initGame();
    }


    private void initGame() {

        do {
            option = 0;
            option = InputOutput.selectOption();
            selectedOption();


        } while (!win && !lost);


    }

    private void selectedOption() {

        switch (option) {
            case 1:
                InputOutput.selectPositionXY();
                //metode on haura de desmarcar caselles.
                break;
            case 2:
                InputOutput.selectPositionXY();
                //metode on haura de insertar una bandera
                break;
            case 3:
                //metode on haure de llevar una bandera
                break;
        }
    }


}
