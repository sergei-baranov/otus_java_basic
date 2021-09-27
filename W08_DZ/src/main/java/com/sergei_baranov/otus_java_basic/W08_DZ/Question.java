package com.sergei_baranov.otus_java_basic.W08_DZ;

/**
 * Интерфейс для того, чтобы enum-ы разных типов можно было сложить в один массив
 * и единообразно обработат в цикле
 */
public interface Question {
    String question();

    int value();

    String humanValue();

    boolean getIsCorrect(int value);
}

/**
 * Вопросы и ответы хранятся в enum-ах разных типов. В данном - про корюшку.
 */
enum SmeltSmellQuestion implements Question {
    CUCUMBER(1, "Огурцом", true),
    TOMATO(2, "Помидором",  false),
    POTATO(3, "Картошкой", false),
    PUMPKIN(4, "Тыквой", false),
    ONION(5, "Луком", false);

    private final boolean correct;
    private final int value;
    private final String humanValue;

    public final String QUESTION = "Чем пахнет корюшка?";

    public String question() {
        return this.QUESTION;
    }

    SmeltSmellQuestion(int value, String humanValue, boolean correct) {
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
        for (SmeltSmellQuestion answer : SmeltSmellQuestion.values()) {
            if (answer.value() == value) {
                return answer.correct();
            }
        }
        return false;
    }
}

/**
 * Вопросы и ответы хранятся в enum-ах разных типов. В данном - про огурец.
 */
enum CucumberColorQuestion implements Question {
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

    CucumberColorQuestion(int value, String humanValue, boolean correct) {
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
        for (CucumberColorQuestion answer : CucumberColorQuestion.values()) {
            if (answer.value() == value) {
                return answer.correct();
            }
        }
        return false;
    }
}

/**
 * Вопросы и ответы хранятся в enum-ах разных типов. В данном - про помидор.
 */
enum TomatoFormQuestion implements Question {
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

    TomatoFormQuestion(int value, String humanValue, boolean correct) {
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
        for (TomatoFormQuestion answer : TomatoFormQuestion.values()) {
            if (answer.value() == value) {
                return answer.correct();
            }
        }
        return false;
    }
}