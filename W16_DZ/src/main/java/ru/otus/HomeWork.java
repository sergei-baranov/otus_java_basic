package ru.otus;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import ru.otus.assertions.Assertions;
import ru.otus.game.*;

/**
 * Ошибки в итоге такие:
 * 1). рандомный результат броска Dice не загоняет в границы [1..6]
 * 2). при одинаковом результате броска Game не организует переброс (просто выигрывает второй)
 */
public class HomeWork {

    /*
        Заготовка для ДЗ представляет собой игру в кости.
        При вызове game.playGame(), два игрока кидают кости.
        Выигрывает игрок, у кого результат (1-6) больше

        Написать тесты (минимум 4) на классы DiceImpl и Game.
        Тесты должны найти не менее двух ошибок.

        Информацию о пройденном тесте предлагается выводить в System.out, а об упавшем в System.err
     */
    public static void main(String[] args) {
        new DiceImplTest().testRollLessThanSeven();
        new DiceImplTest().testRollPositive();
        new GameTest().testOutput();
        new GameTest().testEqualRolls();
    }
    
}

/**
 * такой Dice, который два раза отдаёт 1-цу,
 * на 3-й двойку, на 4-й 1-цу
 * и только потом рандомы начинает давать
 */
class DiceImplStub implements Dice {
    static int counter = 0;
    @Override
    public int roll() {
        int value;
        if (counter < 2 || counter == 3) {
            value = 1;
        } else if (counter == 2) {
            value = 2;
        } else {
            value = new Random().nextInt();
        }
        counter += 1;
        return value;
    }
}

class DiceImplTest {
    /**
     * ролл меньше 7-ми
     */
    public void testRollLessThanSeven() {
        String scenario = "Тест ролл меньше семи";
        Dice dice = new DiceImpl();
        int rollResult = dice.roll();
        try {
            Assertions.assertLessThan(7, rollResult);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.out.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    /**
     * ролл больше ноля
     */
    public void testRollPositive() {
        String scenario = "Тест ролл больше ноля";
        Dice dice = new DiceImpl();
        int rollResult = dice.roll();
        try {
            Assertions.assertGreaterThan(0, rollResult);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.out.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}

class GameTest {
    /**
     * одинаковые роллы не могут быть (класс должен организовать переброс);
     * для теста подсунем в playGame такой dice, который первые два раза
     * и 4-й раз выбрасывает единичку, на 3-й двойку,
     * и только со второго уже рандом;
     * при таком поведении Dice и при условии, что Game организует переброс
     * при равных результатах, должен победить первый игрок (но реально победит второй уже
     * на фазе, когда они оба получат по 1-це).
     */
    public void testEqualRolls() {
        // перехватим stdout и поглядим, что он там println-ит
        // std-s до нас
        PrintStream stdout = System.out;
        PrintStream stderr = System.err;
        // заменим на свои
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream out = new PrintStream(outputStream) ;
        System.setOut(out);
        System.setErr(out);

        // perform game
        //String consoleText;
        String scenario = "Тест переброса при одинаковом результате";
        try {
            String gamerName1 = "Gamer1";
            String gamerName2 = "Gamer2";
            String expected = "Победитель: " + gamerName1 + "\n";
            Dice dice = new DiceImplStub(); // stub
            GameWinnerPrinter winnerPrinter = new GameWinnerConsolePrinter();
            Game game = new Game(dice, winnerPrinter);
            game.playGame(new PlayerImpl(gamerName1), new PlayerImpl(gamerName2)); // PlayerImpl
            String actual = outputStream.toString();
            Assertions.assertEquals(expected, actual);

            //consoleText = String.format("\"%s\" passed %n", scenario);

            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            //consoleText = String.format("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());

            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            System.out.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        } finally {
            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            //
            //System.out.println(consoleText);
        }
    }

    /**
     * создадим обоих игроком с именем Петя, и проверим, что аутпут возвращает "Победитель: Петя\n"
     */
    public void testOutput() {
        // перехватим stdout и поглядим, что он там println-ит
        // std-s до нас
        PrintStream stdout = System.out;
        PrintStream stderr = System.err;
        // заменим на свои
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream out = new PrintStream(outputStream) ;
        System.setOut(out);
        System.setErr(out);

        // perform game
        //String consoleText;
        String scenario = "Тест вывода имени";
        try {
            String gamerName = "Петя";
            String expected = "Победитель: " + gamerName + "\n";
            Dice dice = new DiceImpl();
            GameWinnerPrinter winnerPrinter = new GameWinnerConsolePrinter();
            Game game = new Game(dice, winnerPrinter);
            game.playGame(new PlayerImpl(gamerName), new PlayerImpl(gamerName)); // PlayerImpl
            String actual = outputStream.toString();
            Assertions.assertEquals(expected, actual);

            //consoleText = String.format("\"%s\" passed %n", scenario);

            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            //consoleText = String.format("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());

            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            System.out.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        } finally {
            // вернём стлаут и стдерр на те, что были до нас
            System.setOut(stdout);
            System.setErr(stderr);
            //
            //System.out.println(consoleText);
        }
    }
}