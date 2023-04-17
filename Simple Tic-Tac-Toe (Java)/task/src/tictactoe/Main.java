package tictactoe;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

class Board {
    private final char[][] field = new char[3][3];

    public Board(String inputString) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = inputString.charAt((i * 3) + j);
            }
        }
    }

    public boolean gameOver() {
        Set<String> results = new HashSet<>();

        results.add(new String(field[0]));
        results.add(new String(field[1]));
        results.add(new String(field[2]));
        results.add(String.valueOf(field[0][0]) + field[1][0] + field[2][0]);
        results.add(String.valueOf(field[0][1]) + field[1][1] + field[2][1]);
        results.add(String.valueOf(field[0][2]) + field[1][2] + field[2][2]);
        results.add(String.valueOf(field[0][0]) + field[1][1] + field[2][2]);
        results.add(String.valueOf(field[2][0]) + field[1][1] + field[0][2]);

        if (results.contains("XXX")) {
            System.out.println("X wins");
            return true;
        } else if (results.contains("OOO")) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("---------");
        for (char[] row: field) {
            System.out.print("| ");
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean validateMove(String input) {
        String[] locationPair = input.split(" ");
        try {
            int row = Integer.parseInt(locationPair[0]);
            int column = Integer.parseInt(locationPair[1]);
            if (row >= 1 && row <= 3 && column >= 1 && column <= 3) {
                if (field[row - 1][column - 1] == ' ') {
                    return true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong number of arguments, should be 2");
        }
        return false;
    }

    public void move(char ch, String input) {
        String[] locationPair = input.split(" ");
        int row = Integer.parseInt(locationPair[0]) - 1;
        int column = Integer.parseInt(locationPair[1]) - 1;
        field[row][column] = ch;
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int turnsCounter = 0;
            char playerTurn = 'X';
            Board board = new Board("         ");
            board.display();
            while (true) {
                String nextMove = scanner.nextLine();
                while (!board.validateMove(nextMove)) {
                    nextMove = scanner.nextLine();
                }
                board.move(playerTurn, nextMove);
                board.display();

                if (board.gameOver()){
                    break;
                }  else if (turnsCounter >= 9) {
                    System.out.println("Draw");
                    break;
                }

                if (playerTurn == 'X') {
                    playerTurn = 'O';
                } else {
                    playerTurn = 'X';
                }
                turnsCounter++;
            }
        }
    }
}