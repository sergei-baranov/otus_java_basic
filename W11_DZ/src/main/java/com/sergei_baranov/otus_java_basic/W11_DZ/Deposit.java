package com.sergei_baranov.otus_java_basic.W11_DZ;

import java.util.Date;

public class Deposit implements Comparable<Deposit> {
    // public-и для скорости разработки, для данного дз неважно

    // уникальность - допустим, в реальности это ПК из бд
    public final int id;

    public int coins = 0;

    public Deposit(int id) {
        this.id = id;
    }

    // для сортируемых типа TreeSet
    @Override
    public int compareTo(Deposit anotherDeposit) {
        return this.id - anotherDeposit.id;
    }

    // для мапов типа HashMap
    @Override
    public int hashCode() {
        return this.id;
    }

    // для поиска по значению
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return this.id == deposit.id;
    }
}
