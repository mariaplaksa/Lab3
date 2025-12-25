import java.io.PrintStream;
import java.util.Scanner;
class Game {
    private String name1;
    private String name2;
    int[] cells = new int[9];
    private int count = 0;
    public Game(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
        startNewGame();
    }
    public void startNewGame() {
        for (int i = 0; i < 9; i++){
            cells[i] = 0;
        }
        count = 0;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(name1).append(" - o\n");
        s.append(name2).append(" - x\n");
        for (int i = 0; i < 3; i++) {
            if (cells[i] == 0)
                s.append("| - ");
            else
                if (cells[i] == 1)
                    s.append("| o ");
                else
                    s.append("| x ");
        }
        s.append("|\n");
        for (int i = 3; i < 6; i++) {
            if (cells[i] == 0)
                s.append("| - ");
            else
                 if (cells[i] == 1)
                    s.append("| o ");
                else
                     s.append("| x ");
        }
        s.append("|\n");
        for (int i = 6; i < 9; i++) {
            if (cells[i] == 0)
                s.append("| - ");
            else
                if (cells[i] == 1)
                    s.append("| o ");
                else
                    s.append("| x ");
        }
        s.append("|\n");
        return s.toString();
    }
    public String player() {
        if (count % 2 == 0) {
            return name1;
        }
        else {
            return name2;
        }
    }
    public String move(int x, int y) {
        if (Winner(1) || Winner(2) || isNoWin()) {
            return "Игра уже закончена.";
        }
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            return "Введите числа от 1 до 3.";
        }
        int pos;
        if (y == 1) {
            if (x == 1)
                pos = 0;
            else
                if (x == 2)
                    pos = 3;
                else
                    pos = 6;
        }
        else {
            if (y == 2) {
                if (x == 1)
                    pos = 1;
                else
                    if (x == 2)
                        pos = 4;
                    else
                        pos = 7;
            }
            else {
                if (x == 1)
                    pos = 2;
                else
                    if (x == 2)
                        pos = 5;
                    else
                        pos = 8;
            }
        }
        if (cells[pos] != 0) {
            return "Клетка занята";
        }
        if (count % 2 == 0) {
            cells[pos] = 1;
        }
        else {
            cells[pos] = -1;
        }
        count++;
        return "Ход сделан";
    }
    public boolean isNoWin() {
        if (count < 9) {
            return false;
        }
        return !Winner(1) && !Winner(2);
    }
    public boolean Winner(int player) {
        int sum;
        if (player == 1)
            sum = 3;
        else
            sum = -3;
        int a1 = cells[0] + cells[1] + cells[2];
        int a2 = cells[3] + cells[4] + cells[5];
        int a3 = cells[6] + cells[7] + cells[8];
        int b1 = cells[0] + cells[3] + cells[6];
        int b2 = cells[1] + cells[4] + cells[7];
        int b3 = cells[2] + cells[5] + cells[8];
        int d1 = cells[0] + cells[4] + cells[8];
        int d2 = cells[2] + cells[4] + cells[6];
        if (a1 == sum || a2 == sum || a3 == sum || b1 == sum || b2 == sum || b3 == sum || d1 == sum || d2 == sum) {
            return true;
        }
        return false;
    }
    public void result() {
        if (Winner(1)) {
            System.out.println("Победитель: " + name1);
        }
        else
            if (Winner(2)) {
            System.out.println("Победитель: " + name2);
            }
            else {
                if (isNoWin()) {
                    System.out.println("Ничья");
                }
                else {
                    System.out.println("Игра в процессе");
                }
        }
    }
}

