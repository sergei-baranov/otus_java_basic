import java.util.Scanner;

interface Question {
    String question();
    // boolean correct();
    int value();
    String humanValue();
    boolean getIsCorrect(int value);
}

enum SmeltSmell implements Question {
    CUCUMBER(1, "Огурцом", true),
    TOMATO(2, "Помидором",  false),
    POTATO(3, "Картошкой", false),
    PUMPKIN(4, "Тыквой", false),
    ONION(4, "Луком", false);

    private final boolean correct;
    private final int value;
    private final String humanValue;

    public final String QUESTION = "Чем пахнет корюшка?";

    public String question() {
        return this.QUESTION;
    }

    SmeltSmell(int value, String humanValue, boolean correct) {
        this.correct = correct;
        this.value = value;
        this.humanValue = humanValue;
    }

    public boolean correct() {
        return this.correct;
    }

    public int value() {
        return this.value;
    }

    public String humanValue() {
        return this.humanValue;
    }

    public boolean getIsCorrect(int value) {
        for (SmeltSmell answer : SmeltSmell.values()) {
            if (answer.value() == value) {
                return answer.correct();
            }
        }
        return false;
    }
}

enum CucumberColor implements Question {
    RED(1, "красный", false),
    GREEN(2, "зелёный",  true),
    BLUE(3, "синий", false);

    private final boolean correct;
    private final int value;
    private final String humanValue;

    public final String QUESTION = "Какого цвета огурец?";

    public String question() {
        return this.QUESTION;
    }

    CucumberColor(int value, String humanValue, boolean correct) {
        this.correct = correct;
        this.value = value;
        this.humanValue = humanValue;
    }

    public boolean correct() {
        return this.correct;
    }

    public int value() {
        return this.value;
    }

    public String humanValue() {
        return this.humanValue;
    }

    public boolean getIsCorrect(int value) {
      for (CucumberColor answer : CucumberColor.values()) {
        if (answer.value() == value) {
            return answer.correct();
        }
      }
      return false;
    }
}

enum TomatoForm implements Question {
    ROUND(1, "круглый", true),
    SQUARE(2, "квадратный",  false),
    TRIANGULAR(3, "треуголный", false);

    private final boolean correct;
    private final int value;
    private final String humanValue;

    public final String QUESTION = "Какой формы помидор?";

    public String question() {
        return this.QUESTION;
    }

    TomatoForm(int value, String humanValue, boolean correct) {
        this.correct = correct;
        this.value = value;
        this.humanValue = humanValue;
    }

    public boolean correct() {
        return this.correct;
    }

    public int value() {
        return this.value;
    }

    public String humanValue() {
        return this.humanValue;
    }

    public boolean getIsCorrect(int value) {
      for (TomatoForm answer : TomatoForm.values()) {
        if (answer.value() == value) {
            return answer.correct();
        }
      }
      return false;
    }
}


public class VeggieTest {

    private static int counterValid = 0;
    private static int counterAll = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Question[][] questions = new Question[][] {
            {TomatoForm.ROUND, TomatoForm.SQUARE, TomatoForm.TRIANGULAR},
            {CucumberColor.RED, CucumberColor.GREEN, CucumberColor.BLUE},
            {SmeltSmell.CUCUMBER, SmeltSmell.TOMATO, SmeltSmell.POTATO, SmeltSmell.PUMPKIN, SmeltSmell.ONION}
        };

        for (Question[] answers : questions) {

            System.out.println("\n" + answers[0].question());
            for (Question answer : answers) {
                System.out.print(answer.value() + ": " + answer.humanValue() + ".    ");
            }
            System.out.println("\nВведите номер правильного ответа");

            int value = sc.nextInt();
            // answers[0], а не answers, так как со статиком мне ещё пока не решить,
            // чтобы запихнуть в один массив разные енумы, как там его унаследовать от общего интерфейса
            boolean correct = answers[0].getIsCorrect(value);
            System.out.println(correct);

            if (correct) {
                VeggieTest.counterValid += 1;
            }
            VeggieTest.counterAll += 1;
        }
        System.out.println("\n\nПравильных ответов: "
                + VeggieTest.counterValid + " из " + VeggieTest.counterAll);
    }
}