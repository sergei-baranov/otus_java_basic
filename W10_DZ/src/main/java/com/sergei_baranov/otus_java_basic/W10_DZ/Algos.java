package com.sergei_baranov.otus_java_basic.W10_DZ;

import java.util.ArrayList;

public class Algos {

    public Algos() {
        // do nothing
    }

    public void sortBySelection(ArrayList<Integer> target) {
        int i, j, jk;
        int iElem, jElem, tmpElem, minElem;
        int size = target.size();
        for (i = 0; i < size; i++) {
            iElem = target.get(i);
            minElem = iElem;
            jk = i;
            for (j = i+1; j < size; j++) {
                jElem = target.get(j);
                if (jElem < minElem) {
                    minElem = jElem;
                    jk = j;
                }
            }
            if (jk != i) {
                tmpElem = iElem;
                target.set(i, minElem);
                target.set(jk, tmpElem);
            }
        }
    }

    public void sortByBubble(ArrayList<Integer> target) {
        int i, j, n;
        int iElem, jElem, tmpElem;
        int size = target.size();
        boolean changes = false;
        for (n = 0; n < size; n++) {
            changes = false;
            for (i = 0; i < size - 1; i++) {
                j = i + 1;
                iElem = target.get(i);
                jElem = target.get(j);
                if (jElem < iElem) {
                    tmpElem = iElem;
                    target.set(i, jElem);
                    target.set(j, tmpElem);
                    changes = true;
                }
            }
            if (!changes) {
                break;
            }
        }
    }
}
