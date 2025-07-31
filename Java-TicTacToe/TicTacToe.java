import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        initializeBoard(board);

        char currentPlayer = 'X';
        boolean gameOver = false;
        int moveCount = 0;
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (!gameOver && moveCount < 9) {
            printBoard(board);
            System.out.println("\n👉 Player " + currentPlayer + ", it's your turn.");
            System.out.print("Enter row and column (0, 1, or 2): ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!isValidMove(board, row, col)) {
                System.out.println("❌ Invalid move. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;
            moveCount++;

            if (hasWon(board, currentPlayer)) {
                printBoard(board);
                System.out.println("\n🎉 Player " + currentPlayer + " wins! Congratulations! 🎉");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        if (!gameOver) {
            printBoard(board);
            System.out.println("\n🤝 It's a draw! Good game!");
        }

        scanner.close();
    }

    static void initializeBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    static void printWelcomeMessage() {
        System.out.println("═══════════════════════════════════");
        System.out.println("🎮 Welcome to Java Tic Tac Toe!");
        System.out.println("Players: X and O");
        System.out.println("Enter row and column values between 0 and 2");
        System.out.println("═══════════════════════════════════");
    }

    static void printBoard(char[][] board) {
        System.out.println("\n    0   1   2");
        System.out.println("  -------------");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col] + " |");
            }
            System.out.println("\n  -------------");
        }
    }

    static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean hasWon(char[][] board, char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }

        // Diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }
}
