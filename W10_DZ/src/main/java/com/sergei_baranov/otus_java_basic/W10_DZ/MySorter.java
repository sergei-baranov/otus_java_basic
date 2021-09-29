package com.sergei_baranov.otus_java_basic.W10_DZ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class MySorter {
    private final static ArrayList<Integer> listToSort = new ArrayList<Integer>();
    private final static Algos algos = new Algos();

    public static void main(String[] args) {
        // fill listToSort
        int sortLength = MySorter.fillListToSort(100_000);// 20, 100_000
        System.out.println("list was inited with " + sortLength + " values");

        MySorter.compareAlgos();
    }

    public static void compareAlgos() {
        // тайминги
        long startMillis, finMillis, deltaMillis;

        // клон списка, чтобы сортировка шла по одному и тому же датасету
        ArrayList<Integer> listClone;

        // для определения, что можно и распечатать
        int size = MySorter.listToSort.size();

        // Selection Sort
        listClone = cloneListToSort();
        // print not sorted
        if (size < 21) {
            System.out.println("\nNot sorted:");
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }
        startMillis = java.lang.System.currentTimeMillis();
        MySorter.algos.sortBySelection(listClone);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.println("\nSelection Sort:\ndelta: " + deltaMillis);
        if (size < 21) {
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }

        // Bubble Sort
        listClone = cloneListToSort();
        // print not sorted
        if (size < 21) {
            System.out.println("\nNot sorted:");
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }
        startMillis = java.lang.System.currentTimeMillis();
        MySorter.algos.sortByBubble(listClone);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.println("\nBubble Sort:\ndelta: " + deltaMillis);
        if (size < 21) {
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }

        // Collections.sort()
        listClone = cloneListToSort();
        // print not sorted
        if (size < 21) {
            System.out.println("\nNot sorted:");
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }
        startMillis = java.lang.System.currentTimeMillis();
        Collections.sort(listClone);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.println("\nCollections.sort():\ndelta: " + deltaMillis);
        if (size < 21) {
            listClone.forEach(x -> System.out.print(x + " "));
            System.out.println(" ");
        }
    }

    private static ArrayList<Integer> cloneListToSort() {
        int size = MySorter.listToSort.size();
        ArrayList<Integer> listClone = new ArrayList<Integer>(size);

        for (Integer i : MySorter.listToSort) {
            listClone.add(i);
        }

        return listClone;
    }

    private static int fillListToSort(int size) {
        int i, v;
        for (i = 0; i < size; i++) {
            v = ThreadLocalRandom.current().nextInt(-32000, 32001);
            MySorter.listToSort.add(v);
        }

        return MySorter.listToSort.size();
    }
}