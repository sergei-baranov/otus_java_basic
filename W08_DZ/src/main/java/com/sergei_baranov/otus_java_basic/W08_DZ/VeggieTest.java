package com.sergei_baranov.otus_java_basic.W08_DZ;

public class VeggieTest {
    private static TestComposer composer;
    private static TestExecutor executor;

    public static void main(String[] args) {
        VeggieTest.composer = new TestComposer("Veggie");
        VeggieTest.executor = new TestExecutor(VeggieTest.composer);
        VeggieTest.executor.execute();
    }
}