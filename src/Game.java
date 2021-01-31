// For each game
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.util.Random;

public class Game {

    // Creates an empty board
    private static char[][] board = new char[6][7];
    // Creates 2 players
    public Player player1;
    public Player player2;
    private static int turnCount = 1;

    public Game(Player player1, Player player2) {
        // Empty board of '-'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }

        this.player1 = player1;
        this.player2 = player2;
    }

    private void resetBoard() {
        // Empty board of '-'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        // Prints out board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%4c", board[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < board[0].length; i++) {
            System.out.printf("%4d", i + 1);
        }
        System.out.println();
    }

    private void playTurnPlayer(Player player) {
        boolean isValidMove = false;
        int moveColumn = -1;
        System.out.printf("Type in a column number (1-%d) to make a move: ", board[0].length);

        // Gets move
        while (!isValidMove) {
            try {
                Scanner sc = new Scanner(System.console().readLine());

                moveColumn = sc.nextInt();

                if (moveColumn < 1 || moveColumn > board[0].length) {
                    System.out.printf("ERROR: Not a valid column. Type in a column number (1-%d) to make a move: ", board[0].length);
                    isValidMove = false;
                } else if (board[0][moveColumn - 1] != '-') {
                    System.out.printf("ERROR: Column is full. Type in a column number (1-%d) to make a move: ", board[0].length);
                    isValidMove = false;
                } else {
                    sc.close();
                    isValidMove = true;
                }
            } catch (InputMismatchException ime) {
                System.out.printf("ERROR: Not a column number. Type in a column number (1-%d) to make a move: ", board[0].length);
                isValidMove = false;
            } catch (NoSuchElementException nsee) {
                System.out.printf("ERROR: Not a vaid move. Type in a column number (1-%d) to make a move: ", board[0].length);
                isValidMove = false;
            }

        }
        // Puts chip on lowest available row
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][moveColumn - 1] == '-') {
                board[i][moveColumn - 1] = player.playerChar;
                break;
            }
        }

        turnCount ++;

    }

    private void playTurnComputer(Player player) {

        boolean isValidMove = false;
        int moveColumn = -1;
        System.out.printf("Type in a column number (1-%d) to make a move: ", board[0].length);

        while (!isValidMove) {

            // Gets random int (1-column count) as computer move
            Random rand = new Random();
            moveColumn = rand.nextInt(board[0].length);

            // Checks that the column isn't empty
            if (board[0][moveColumn] != '-') {
                System.out.println("Column " + moveColumn + " + 1 is full. Try again.");
                isValidMove = false;
            } else {
                isValidMove = true;
            }
        }

        int printColumn = moveColumn + 1;
        System.out.println(printColumn);

        // Puts chip on lowest available row
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][moveColumn] == '-') {
                board[i][moveColumn] = player.playerChar;
                break;
            }
        }

        turnCount ++;
    }

    private boolean checkWinP1() {
        return checkRowP1() || checkColumnP1() || checkRightDiagonalP1() || checkLeftDiagonalP1();
    }

    private boolean checkWinP2() {
        return checkRowP2() || checkColumnP2() || checkRightDiagonalP2() || checkLeftDiagonalP2();
    }

    private boolean checkTie() {
        return turnCount > 42;
    }

    private boolean turnCountIsOdd() {
        return turnCount % 2 == 1;
    }

    private boolean checkRowP1() {

        int count = 0;
        // For each row:
        for (int i = board.length - 1; i >= 0; i--) {
            // Reset count when switching rows
            count = 0;
            //For each element within the row:
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == player1.playerChar) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRowP2() {

        int count = 0;
        // For each row:
        for (int i = board.length - 1; i >= 0; i--) {
            // Reset count when switching rows
            count = 0;
            //For each element within the row:
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == player2.playerChar) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumnP1() {

        int count = 0;
        // For each column:
        for (int i = board[0].length - 1; i >= 0; i--) {
            // Reset count when switching columns
            count = 0;
            // For each element within the column:
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == player1.playerChar) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumnP2() {

        int count = 0;
        // For each column:
        for (int i = board[0].length - 1; i >= 0; i--) {
            // Reset count when switching columns
            count = 0;
            // For each element within the column:
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == player2.playerChar) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRightDiagonalP1() {

        int count = 0;

        // For each column:
        for (int i = 0; i < board[0].length - 3; i++) {
            // For each element within the column:
            for (int j = 0; j < board.length - 3; j++) {
                // Checking the diagonal
                count = 0;
                for (int x = 0, y = 0; x < board.length && y < board.length; x++, y++) {
                    try {
                        if (board[j+y][i+x] == player1.playerChar) {
                            count += 1;
                        } else {
                            count = 0;
                        }
                        if (count >= 4) {
                            return true;
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                        // Just loop through again
                    }
                }
            }
        }

        return false;
    }

    private boolean checkRightDiagonalP2() {

        int count = 0;

        // For each column:
        for (int i = 0; i < board[0].length - 3; i++) {
            // For each element within the column:
            for (int j = 0; j < board.length - 3; j++) {
                count = 0;
                // Checking the diagonal
                for (int x = 0, y = 0; x < board.length && y < board.length; x++, y++) {
                    try {
                        if (board[j+y][i+x] == player2.playerChar) {
                            count += 1;
                        } else {
                            count = 0;
                        }
                        if (count >= 4) {
                            return true;
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                        // Just loop through again
                    }
                }
            }
        }

        return false;
    }

    private boolean checkLeftDiagonalP1() {

        int count = 0;

        // For each column:
        for (int i = 3; i < board[0].length; i++) {
            // For each element within the column:
            for (int j = 0; j < board.length - 3; j++) {
                count = 0;
                // Checking the diagonal
                for (int x = 0, y = 0; x < board.length && y < board.length; x++, y++) {
                    try {
                        // System.out.printf("Checking %d,%d",j+y,i-x);
                        if (board[j+y][i-x] == player1.playerChar) {
                            count += 1;
                        } else {
                            count = 0;
                        }
                        if (count >= 4) {
                            return true;
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                        // Just loop through again
                    }
                }
            }
        }

        return false;
    }

    private boolean checkLeftDiagonalP2() {

        int count = 0;

        // For each column:
        for (int i = 3; i < board[0].length; i++) {
            // For each element within the column:
            for (int j = 0; j < board.length - 3; j++) {
                count = 0;
                // Checking the diagonal
                for (int x = 0, y = 0; x < board.length && y < board.length; x++, y++) {
                    try {
                        if (board[j+y][i-x] == player2.playerChar) {
                            count += 1;
                        } else {
                            count = 0;
                        }
                        if (count >= 4) {
                            return true;
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                        // Just loop through again
                    }
                }
            }
        }

        return false;
    }

    public void playGame2P() {

        resetBoard();
        turnCount = 1;

        System.out.println("\nGame started between Player 1 and Player 2\n");
        printBoard();
        while(!checkWinP1() && !checkWinP2() && !checkTie()) {
            if (turnCountIsOdd()) {
                playTurnPlayer(player1);
                if (checkWinP1()) {
                    break;
                } else if (checkTie()) {
                    break;
                }
                System.out.println("\nPLAYER 2's TURN: ");
                printBoard();
            } else {
                playTurnPlayer(player2);
                if (checkWinP2()) {
                    break;
                } else if (checkTie()) {
                    break;
                }
                System.out.println("\nPLAYER 1's TURN: ");
                printBoard();
            }
        }
        printBoard();
        if (checkWinP1()) {
            System.out.println("\nGame over. Player 1 wins!\n");
        } else if (checkWinP2()) {
            System.out.println("\nGame over. Player 2 wins!\n");
        } else {
            System.out.println("\nGame over. It's a tie.\n");
        }
    }

    public void playGame1P() {

        resetBoard();
        turnCount = 1;

        System.out.println("\nGame started between Player 1 and Computer\n");
        printBoard();
        while(!checkWinP1() && !checkWinP2() && !checkTie()) {
            if (turnCountIsOdd()) {
                playTurnPlayer(player1);
                if (checkWinP1()) {
                    break;
                } else if (checkTie()) {
                    break;
                }
                System.out.println("\nCOMPUTER's TURN: ");
                printBoard();
            } else {
                playTurnComputer(player2);
                if (checkWinP2()) {
                    break;
                } else if (checkTie()) {
                    break;
                }
                System.out.println("\nPLAYER 1's TURN: ");
                printBoard();
            }
        }
        printBoard();
        if (checkWinP1()) {
            System.out.println("\nGame over. Player 1 wins!\n");
        } else if (checkWinP2()) {
            System.out.println("\nGame over. You lost to the computer.\n");
        } else {
            System.out.println("\nGame over. It's a tie.\n");
        }
    }
}
