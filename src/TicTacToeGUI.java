import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class TicTacToeGUI {
    TicTacToe ticTacToe;
    private JFrame jframe;
    private Bot bot;
    private boolean botChecked;
    private JCheckBox useBot;
    private JComboBox strikeComboBox;

    public TicTacToeGUI() {
        init();
    }

    /**
     * generates gui
     */
    public void init() {
        jframe = new JFrame("Tic Tac Toe");
        jframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jframe.setVisible(true);


        JButton button = new JButton("3x3");
        JButton button2 = new JButton("4x4");
        JButton button3 = new JButton("5x5");
        JButton button4 = new JButton("10x10");
        JButton button5 = new JButton("15x15");
        button.setBackground(Color.white);
        button2.setBackground(Color.white);
        button3.setBackground(Color.white);
        button4.setBackground(Color.white);
        button5.setBackground(Color.white);

        JPanel menu = new JPanel();
        menu.setSize(200, 100);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        useBot = new JCheckBox("Bot");
        menu.add(useBot);
        useBot.addActionListener(e -> {
            botChecked = useBot.isSelected();
            if (botChecked) {
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                button5.setEnabled(false);
            }else {
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
                button5.setEnabled(true);
            }
        });

        JPanel menu1 = new JPanel();
        menu1.setSize(200, 40);
        menu1.setLayout(new BoxLayout(menu1, BoxLayout.X_AXIS));
        menu1.add(button);
        menu1.add(button2);
        menu1.add(button3);
        menu1.add(button4);
        menu1.add(button5);

        JPanel menu2 = new JPanel();
        menu2.setSize(100, 40);
        menu2.setLayout(new BoxLayout(menu2, BoxLayout.X_AXIS));
        JLabel strikeLabel = new JLabel("strike: ");
        Integer[] strings = {3, 4, 5};
        strikeComboBox = new JComboBox(strings);
        strikeComboBox.addItemListener(e -> {
            botChecked = useBot.isSelected();
            Integer strike = (Integer) e.getItem();
            if (strike>3){
                button.setEnabled(false);
            }else {
                button.setEnabled(true);
            }
            if (strike>4){
                button2.setEnabled(false);
            }else if (!botChecked){
                button2.setEnabled(true);
            }

        });

        menu2.add(strikeLabel);
        menu2.add(strikeComboBox);
        menu.add(menu2);
        menu.add(menu1);

        jframe.setLayout(new FlowLayout());
        jframe.add(menu);
        jframe.add(menu);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setVisible(false);
        jframe.add(panel2);

        JTextField text = new JTextField();
        text.setEditable(false);

        panel2.add(text);

        button.addActionListener(e -> createGame(menu,panel2,text,3));
        button2.addActionListener(e -> createGame(menu,panel2,text,4));
        button3.addActionListener(e -> createGame(menu,panel2,text,5));
        button4.addActionListener(e -> createGame(menu,panel2,text,10));
        button5.addActionListener(e -> createGame(menu,panel2,text,15));

        jframe.pack();
    }

    /**
     * this method creates game with adjustable size
     * @param menu menu that will be hidden
     * @param panel2 panel for game
     * @param text text for writing who is on turn
     * @param size size of the game
     */
    private void createGame(JPanel menu,JPanel panel2,JTextField text, int size){
        int strike = (Integer) strikeComboBox.getSelectedItem();
        ticTacToe = new TicTacToe(size, strike);
        botChecked = useBot.isSelected();
        if (botChecked) {
            bot = new Bot(size);
        }
        text.setText("on turn: "+ticTacToe.getPlayerSymbol());
        ticTacToe.setSize(size);
        menu.setVisible(false);
        panel2.setVisible(true);
        generateMap(panel2,text);
        jframe.pack();
    }

    /**
     * this method generates buttons for the game
     * @param panel2 panel where the game will be generated
     * @param text text for writing who is on turn
     */
    private void generateMap(JPanel panel2, JTextField text) {
        for (int i = 0; i < ticTacToe.getSize(); i++) {
            JPanel panel = new JPanel();
            generateLineOfButtons(i, panel, text);
            panel2.add(panel);
        }
        JButton newGameButton = new JButton("new game");
        newGameButton.setBackground(Color.white);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
                init();
            }
        });
        panel2.add(newGameButton);
    }

    /**
     * generates line of buttons
     * @param line to know what is the line of the buttons
     * @param panel panel where will be the buttons
     * @param text text for writing who won
     */
    private void generateLineOfButtons(int line, JPanel panel, JTextField text) {
        for (int i = 0; i < ticTacToe.getSize(); i++) {
            JButton b = new JButton();
            b.setBackground(Color.white);
            b.setPreferredSize(new Dimension(50,30));
            int column = i;
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!ticTacToe.isWin()) {
                        b.setText(ticTacToe.getPlayerSymbol());
                        String out = ticTacToe.tic(line, column);
                        text.setText(out);

                        b.setEnabled(false);
                        if (ticTacToe.getPlayerOnTurn()==2 && !ticTacToe.isWin() && botChecked){
                            bot.click();
                        }
                    }
                }
            });
            panel.add(b);
            if (botChecked) {
                bot.addButton(b, line, column);
            }
        }
    }





}
