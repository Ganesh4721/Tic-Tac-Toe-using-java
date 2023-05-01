import java.util.Scanner;

public class Main {
    private static final int SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board;
    private static char currentPlayer;
    private static boolean gameOver;

    public static void main(String[] args) {
        board = new char[SIZE][SIZE];
        currentPlayer = PLAYER_X;
        gameOver = false;
        initializeBoard();
        printBoard();
        playGame();
    }

    public static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            System.out.print("Player " + currentPlayer + ", enter row (1-" + SIZE + "): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Player " + currentPlayer + ", enter column (1-" + SIZE + "): ");
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                printBoard();

                if (checkWin(row, col)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        scanner.close();
    }

    public static boolean isValidMove(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
            return true;
        }
        return false;
    }

    public static boolean checkWin(int row, int col) {
        char player = board[row][col];

        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true;
        }

        // Check column
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }

        // Check diagonal
        if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        // Check anti-diagonal
        if (row + col == SIZE - 1 && board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}


