import javax.swing.*;
import java.util.Random;

public class Bot {
    Random rd = new Random();
    JButton buttons[][] = new JButton[15][15];
    private int size;

    public Bot(int size) {
        this.size = size;

    }

    /**
     * add button to array of buttons
     * @param button button from gui
     * @param line number of line for position of button
     * @param colum number of colum for position of button
     */
    public void addButton(JButton button, int line, int colum) {
        buttons[line][colum] = button;

    }

    /**
     * it's bots logic
     */
    public void click() {
        if (clickLastButton()) return;
        if (click(1,1)) return;
        if (click(0,0)) return;
        if (click(2,2)) return;
        if (click(0,2)) return;
        if (click(2,0)) return;
        clickRandom();
    }

    /**
     * try to click on button and tells if it clicks or not
     * @param line
     * @param column
     * @return if it clicked on button or not
     */
    private boolean click(int line, int column) {
        if (buttons[line][column].isEnabled()) {
            buttons[line][column].doClick();
            return true;
        }
        return false;
    }

    /**
     * clicks on random button
     */
    private void clickRandom() {

        while (true) {
            int a = rd.nextInt(size);
            int b = rd.nextInt(size);
            if (click(a,b)) return;
        }
    }

    /**
     * checks if there's only 1 clickable button left
     * if so it clicks on it
     *
     * @return if there is one or no buttons to be clicked(return true), if there's more buttons(return false)
     */
    private boolean clickLastButton() {
        int count = 0;
        JButton lastButton = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = buttons[i][j];
                if (button.isEnabled()) {
                    count++;
                    lastButton = button;
                }
            }
        }
        if (count == 1) {
            lastButton.doClick();
            return true;
        } else if (count == 0) {
            return true;
        }
        return false;
    }


}
