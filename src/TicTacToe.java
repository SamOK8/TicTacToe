
public class TicTacToe {

    private int size;

    public TicTacToe(int size, int strike) {
        this.size = size;
        this.strike = strike;
    }

    private int strike = 3;

    private int[][] p;

    private int playerOnTurn = 1;

    public boolean isWin() {
        return win;
    }

    private boolean win;

    public int getPlayerOnTurn() {
        return playerOnTurn;
    }

    /**
     * with each click on any game button it switches players and checks if someone won
     *
     * @param i number of line position of clicked button
     * @param j number of colum position of clicked button
     * @return who is winner in String
     */
    public String tic(int i, int j) {
        p[i][j] = playerOnTurn;
        win = check();
        String winner = getPlayerSymbol();
        if (playerOnTurn == 1) {
            playerOnTurn = 2;
        } else {
            playerOnTurn = 1;
        }
        if (win) {
            return "winner is " + winner;
        } else {
            return "on turn: " + getPlayerSymbol();
        }

    }


    /**
     * checks if someone is winner using another methods
     *
     * @return if someone is winner
     */
    public boolean check() {
        return checkDiagonal() || checkLinesAndColumns();
    }

    /**
     * checks if someone is winner on diagonal
     *
     * @return if someone is winner on diagonal
     */
    private boolean checkDiagonal() {
        //1. radek  2. sloupec
        int diag = 0;
        int diag2 = 0;
        int diag3 = 0;
        int diag4 = 0;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i; j++) {

                if (p[j][j + i] == playerOnTurn) {
                    diag++;
                } else {
                    diag = 0;
                }
                if (p[j + i][j] == playerOnTurn) {
                    diag2++;
                } else {
                    diag2 = 0;
                }

                if (p[j][size - j - 1 - i] == playerOnTurn) {
                    diag3++;
                } else {
                    diag3 = 0;
                }

                if (p[j + i][size - j - 1] == playerOnTurn) {
                    diag4++;
                } else {
                    diag4 = 0;
                }

                if (diag == strike || diag2 == strike || diag3 == strike || diag4 == strike) {
                    return true;
                }

            }
            diag = 0;
            diag2 = 0;
            diag3 = 0;
            diag4 = 0;
        }
        return false;
    }

    /**
     * checks if someone is winner on the line or colum
     *
     * @return if someone is winner on the line or colum
     */
    private boolean checkLinesAndColumns() {
        for (int i = 0; i < size; i++) {
            int line = 0;
            int colum = 0;

            for (int j = 0; j < size; j++) {
                if (p[i][j] == playerOnTurn) {
                    line++;
                } else {
                    line = 0;
                }
                if (p[j][i] == playerOnTurn) {
                    colum++;
                } else {
                    colum = 0;
                }

                if (line == strike || colum == strike) {
                    return true;
                }

            }
        }
        return false;
    }


    public int getSize() {
        return size;
    }

    /**
     * it sets the size of an array that is used for checking the winner
     * @param size size of the game
     */
    public void setSize(int size) {
        this.size = size;
        p = new int[size][size];
    }

    /**
     * checks which symbol does the player on turn have
     *
     * @return player symbol
     */
    public String getPlayerSymbol() {
        return playerOnTurn == 1 ? "x" : "o";
    }
}
