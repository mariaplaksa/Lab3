## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2501`

#### Выполнила: `Плакса Мария Антоновна`

#### Вариант: `13`

### Cодержание:

- Постановка задачи
- Алгоритм
- Программа
- Анализ правильности решения

### 1. Постановка задачи

Разработать программу для классической игры «Крестики-нолики» для двух игроков.
Реализовать функционал создания игрового поля, обработки ходов игроков, проверки
текущего состояния игры и определения победителя.
Описание функционала
1. Создание игрового поля

Создаёт пустое игровое поле размером 3×3 для двух игроков. Принимает на вход
их имена. Поле отображается в формате:

>ИмяПервого- o

>ИмяВторого- x


>| x | - | o |

>| - | - | o |

>| - | x | o |

2. Определение текущего игрока

Возвращает имя игрока, который сейчас должен делать ход.

3. Совершение хода

Навход подаются координаты клетки (два числа от 1 до 3). Ход совершается, если
клетка пуста и игра ещё не завершена. Если условия не выполнены, возвращается
сообщение об ошибке.

4. Проверка на ничью

Определяет, закончилась ли игра ничьей (все 9 клеток заполнены, и победителя
нет).

5. Проверка победителя

Проверяет, является ли игрок с переданным номером (1 или 2) победителем. Игрок
выигрывает, если сумма значений в любой строке, столбце или диагонали равна
3 (для «o») или −3 (для «x»).

6. Вывод состояния игры

Отображает текущее состояние игры:
• «Игра в процессе», если ещё не завершена;
• «Ничья», если ходов больше нет и нет победителя;
• «Победитель: ИмяИгрока», если один из игроков победил.


7. Очистка игрового поля

Сбрасывает поле в исходное состояние, удаляя все ходы, и позволяет начать новую
игру.

### 2. Алгоритм

#### Алгоритм выполнения программы:

1. Создание игрового поля

При создании объекта Game через конструктор Game(String name1, String name2):
Сохраняются имена игроков.

Вызывается метод startNewGame().

В startNewGame():
Массив cells[9] заполняется нулями (0 = пустая клетка).
Счётчик ходов count сбрасывается в 0.
Поле выводится через toString() в нужном формате.

2. Определение текущего игрока

Метод player() возвращает имя текущего игрока:

Текущий игрок определяется по чётности счётчика ходов count:

Если count % 2 = 0, то ходит первый игрок.
Иначе ходит второй игрок.

3. Совершение хода

Метод move(int x, int y) принимает координаты строки (x) и столбца (y) от 1 до 3.

Проверяется, завершена ли игра (Winner(1), Winner(2) или isNoWin()). Если да — возвращается "Игра уже закончена.".

Проверяются корректность координат (1–3). Если нет — ошибка.

Координаты (x, y) преобразуются в индекс pos (0–8) в массиве cells:

(1,1) - 0, (2,1) - 3, (3,1) - 6

(1,2) - 1, (2,2) - 4, (3,2) - 7

(1,3) - 2, (2,3) - 5, (3,3) - 8

Проверяется, свободна ли клетка (cells[pos] == 0). Если занята — "Клетка занята".
Если всё в порядке:

cells[pos] = 1 (если count чётный - игрок 1)

cells[pos] = -1 (если нечётный - игрок 2)

к count прибавляется 1

Возвращается "Ход сделан".

4. Проверка на ничью

Метод isNoWin():

Если сделано меньше 9 ходов - не ничья (return false).
Если 9 ходов сделано и нет победителя (!Winner(1) && !Winner(2)) - ничья.

5. Проверка победителя

Метод Winner(int player):

Для игрока 1 — ищет сумму 3 (три 1 подряд).

Для игрока 2 — ищет сумму -3 (три -1 подряд).

Проверяются:

3 строки: [0,1,2], [3,4,5], [6,7,8]

3 столбца: [0,3,6], [1,4,7], [2,5,8]

2 диагонали: [0,4,8], [2,4,6]

Если любая сумма равна нужному значению — true.

6. Вывод состояния игры

Метод result():

Если Winner(1), то выводит "Победитель: " + name1

Иначе если Winner(2) - "Победитель: " + name2

Иначе если isNoWin() - "Ничья"

Иначе - "Игра в процессе"

### 3. Программа

```java
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
            else if (cells[i] == 1)
                s.append("| o ");
            else
                s.append("| x ");
        }
        s.append("|\n");
        for (int i = 3; i < 6; i++) {
            if (cells[i] == 0)
                s.append("| - ");
            else if (cells[i] == 1)
                s.append("| o ");
            else
                s.append("| x ");
        }
        s.append("|\n");
        for (int i = 6; i < 9; i++) {
            if (cells[i] == 0)
                s.append("| - ");
            else if (cells[i] == 1)
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
        } else {
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
            else if (x == 2)
                pos = 3;
            else
                pos = 6;
        } else {
            if (y == 2) {
                if (x == 1)
                    pos = 1;
                else if (x == 2)
                    pos = 4;
                else
                    pos = 7;
            } else {
                if (x == 1)
                    pos = 2;
                else if (x == 2)
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
        } else {
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
```
### 4. Анализ правильности решения

Тест 1.

Основная программа:

Game g = new Game("Анна", "Вася");
        
out.println(g.toString());
        
g.move(1, 1);
        
g.move(2, 1);

g.move(1, 2);
        
g.move(2, 2);
        
g.move(1, 3);
        
out.println(g.toString());
        
g.result();
        
Output:

Анна - o

Вася - x

| - | - | - |

| - | - | - |

| - | - | - |

Анна - o

Вася - x

| o | o | o |

| x | x | - |

| - | - | - |

Победитель: Анна

Тест 2.

Основная программа:

Game g = new Game("Анна", "Вася");

out.println(g.toString());

g.move(2, 2);

g.move(1, 1);

g.move(1, 3);

g.move(3, 1);

g.move(3, 3);

g.move(1, 2);

g.move(2, 1);

g.move(2, 3);

g.move(3, 2);

out.println(g.toString());

g.result();

Output:

Анна - o

Вася - x

| - | - | - |

| - | - | - |

| - | - | - |

Анна - o

Вася - x

| x | x | o |

| o | o | x |

| x | o | o |

Ничья

Тест 3.

Основная программа:

Game g = new Game("Анна", "Вася");

g.startNewGame();

g.move(1,1);

g.move(2,2);
 
g.move(1,2);

g.move(3,3);

g.move(2,1);

g.move(1,3);

g.move(3,1);

g.move(3,2);

g.startNewGame();

g.move(1,2);

g.move(1,1);

g.move(2,3);

g.move(2,2);

g.move(3,1);

g.move(3,3);

out.println(g.toString());

g.result();

Output:

Анна - o

Вася - x

| x | o | - |

| - | x | o |

| o | - | x |

Победитель: Вася
