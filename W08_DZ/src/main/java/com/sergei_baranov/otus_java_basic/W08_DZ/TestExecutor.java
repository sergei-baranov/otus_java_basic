package com.sergei_baranov.otus_java_basic.W08_DZ;

/**
 * отрабатывает массив Question-ов
 */
public class TestExecutor {
    private int counterValid = 0;
    private int counterAll = 0;

    private IntReader intReader;

    private Question[][] questions;

    private TestComposer composer;

    /**
     *
     * @param composer Объект TestComposer для формирования массива вопросов под отработку
     */
    TestExecutor(TestComposer composer) {
        this.composer = composer;
    }

    /**
     *
     */
    public void execute() {
        this.counterValid = 0;
        this.counterAll = 0;
        this.intReader = new IntReader();
        this.questions = this.composer.buildTestQuestions();

        for (Question[] answers : questions) {

            System.out.println("\n" + answers[0].question());
            for (Question answer : answers) {
                System.out.print(answer.value() + ": " + answer.humanValue() + ".    ");
            }
            System.out.println("\nВведите номер правильного ответа");

            int value = this.intReader.read();

            // answers[0], а не answers, так как со статиком мне ещё пока не решить,
            // чтобы запихнуть в один массив разные енумы, как там его унаследовать от общего интерфейса
            boolean correct = answers[0].getIsCorrect(value);
            System.out.println(correct);

            if (correct) {
                this.counterValid += 1;
            }
            this.counterAll += 1;
        }

        // сводный результат
        System.out.println("\n\nПравильных ответов: "
                + this.counterValid + " из " + this.counterAll);
    }
}
