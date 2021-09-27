package com.sergei_baranov.otus_java_basic.W08_DZ;

import java.util.Arrays;

/*
Собирает Question-ы в двумерный массив
 */
public class TestComposer {
    // сейчас ни на что не влияет
    private String[] configNames = {"Veggie"};
    // сейчас ни на что не влияет
    private String[][] configVals = new String[][] {
        {"TomatoFormQuestion", "CucumberColorQuestion", "SmeltSmellQuestion"}
    };

    // сейчас ни на что не влияет
    private String configName;

    TestComposer(String configName) {
        // сейчас ни на что не влияет
        this.configName = configName;
    }

    public int configNum(String configName) {
        return Arrays.asList(configNames).indexOf(configName);
    }

    public Question[][] buildTestQuestions() {
        /* сейчас ни на что не влияет
        // TODO тут всё небезопасно, нет проверок на существование и обработки несуществования
        int configNum = this.configNum(this.configName);
        String[] questionsEnums = this.configVals[configNum];
         */

        Question[][] questions = new Question[3][];

        int itemsLength = TomatoFormQuestion.values().length;
        questions[0] = new TomatoFormQuestion[itemsLength];
        System.arraycopy(TomatoFormQuestion.values(), 0, questions[0], 0, itemsLength);

        itemsLength = CucumberColorQuestion.values().length;
        questions[1] = new CucumberColorQuestion[itemsLength];
        System.arraycopy(CucumberColorQuestion.values(), 0, questions[1], 0, itemsLength);

        itemsLength = SmeltSmellQuestion.values().length;
        questions[2] = new SmeltSmellQuestion[itemsLength];
        /* // same as arraycopy
        for (int i = 0; i < itemsLength; i++) {
            questions[2][i] = SmeltSmellQuestion.values()[i];
        }
         */
        System.arraycopy(SmeltSmellQuestion.values(), 0, questions[2], 0, itemsLength);

        return questions;
    }
}
