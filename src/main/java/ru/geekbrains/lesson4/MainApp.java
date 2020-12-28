package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainApp {
    private static final int SIZE = 5;
    private static final int CELLS = 4;
    private static final int MARGEN = SIZE - CELLS;
    private static final char[][] map = new char[SIZE][SIZE]; // присвоение недопустимо,но добавление допустимо

    private static final char DOT_EMPTY = '·';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        initializeGame(); // Alt + Enter to create method
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X)) break;
            computerTurn();
            printMap();
            if (checkEndGame(DOT_0)) break;
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (checkWin(symbol)) {
            System.out.println(symbol==DOT_X ? "Человек победил!" : "Машина победила!");
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (char[] rows : map) {
            for (char cellValue : rows) {
                if (cellValue == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char symbol) {
        //secondShiftDiagonal("Left", symbol, MARGEN) ||
        //secondShiftDiagonal("Right", symbol, MARGEN))
        // mainShiftDiagonal("Left", symbol, MARGEN) ||
        // mainShiftDiagonal("Right", symbol, MARGEN)
        return (checkRows(symbol) ||
                checkColumns(symbol) ||
                mainShiftDiagonal("Left", symbol, MARGEN) ||
                mainShiftDiagonal("Right", symbol, MARGEN) ||
                secondShiftDiagonal("Left", symbol, MARGEN) ||
                secondShiftDiagonal("Right", symbol, MARGEN));
    }

    private static boolean checkRows(char symbol) {
        int counterChecker = 0;
        int result = 0;
        for (char[] row: Arrays.asList(map)) {
            for (char element : row) {
                if (element == symbol) {
                    counterChecker ++;
                    if (counterChecker == CELLS) {
                        result = 1;
                        break;
                    }
                }
                else {
                    counterChecker = 0;
                }
            }
        }
        return result == 1;
    }
    private static boolean checkColumns(char symbol) {
        int counterChecker = 0;
        int result = 0;
        for (int column=0; column < SIZE; column++) {
            for (char[] row : Arrays.asList(map)) {
                if (row[column] == symbol) {
                    counterChecker ++;
                    if (counterChecker == CELLS) {
                        result ++;
                        break;
                    }
                }
                else {
                    counterChecker = 0;
                }
            }
        }
        return result == 1;
    }


    private static boolean mainShiftDiagonal(String method, char symbol, int distance) {
        int result = 0;
        int multiplier = 1;
        if (method == "Left") multiplier = -1;
        for (int dist = 0; dist <=distance; dist ++) {
            int counterChecker = 0;
            for (int i = 0; i <SIZE; i++) {
                if (i == 0 && method == "Left" && dist != 0) continue;
                else if (i==SIZE-1 && method =="Right" && dist !=0) continue;
                else {
                    if (map[i][i+dist*multiplier] == symbol) {
                        counterChecker++;
                    }
                    else {
                        counterChecker = 0;
                    }
                    if (counterChecker == CELLS) {
                        result ++;
                        break;
                    }
                }
            }
        }
        return result == 1;
    }

    private static boolean secondShiftDiagonal(String method, char symbol, int distance) {
        int multiplier = 1;
        int result = 0;
        if (method == "Left") multiplier = -1;
        for (int dist = 0; dist <= distance; dist ++) {
            int counterChecker = 0;
            for (int i = 0; i < SIZE; i++) {
                if (i == 0 && method == "Right" && dist != 0) continue;
                else if (i==SIZE-1 && method =="Left" && dist !=0) continue;
                else {
                    if (map[i][SIZE-1-i+dist*multiplier] == symbol) {
                        counterChecker++;
                    }
                    else {
                        counterChecker = 0;
                    }
                    if (counterChecker == CELLS) {
                        result++;
                        break;
                    }
                }
            }
        }
        return result == 1;
    }

    private static void computerTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        Random rand = new Random();
        do {
            rowIndex = rand.nextInt(SIZE); // [0:3)
            colIndex = rand.nextInt(SIZE);
        } while (!isCellValid(rowIndex, colIndex));

        map[rowIndex][colIndex] = DOT_0;
    }

    private static void humanTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        /*
        String data = SCANNER.nextLine();
        String[] parts = data.split(" ");
        int rowIndex = Integer.parseInt(parts[0]) - 1;
        int colIndex = Integer.parseInt(parts[1]) - 1;
        */
        do {
            System.out.print("Введите номер строки: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            rowIndex = SCANNER.nextInt() - 1;

            System.out.print("Введите номер столбца: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            colIndex = SCANNER.nextInt() - 1;
        } while (!isCellValid(rowIndex, colIndex));

        map[rowIndex][colIndex] = DOT_X;
    }

    private static boolean isCellValid(int rowIndex, int colIndex) {
        if (!isArrayIndexValid(rowIndex) | (!isArrayIndexValid(colIndex))) {
            System.out.println("Индекс не верный!");
            return false;
        }
        if (map[rowIndex][colIndex] != DOT_EMPTY) {
            System.out.println("Ячейка занята!");
            return false;
        }
        return true;
    }

    private static boolean isArrayIndexValid(int index) {
        return index >= 0 && index < SIZE;
    }

    private static void printMap() {
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            System.out.print((rowIndex+1) + " ");
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                System.out.print(map[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) { // rowi hotkey
            for (int colIndex = 0; colIndex < SIZE ; colIndex++) {
                map[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
    }
}
