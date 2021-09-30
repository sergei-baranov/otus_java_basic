package com.sergei_baranov.otus_java_basic.W11_DZ;

import java.util.Date;

public class Client implements Comparable<Client> {
    // public-и для скорости разработки, для данного дз неважно

    // уникальность - допустим, в реальности это ПК из бд
    public final int id;
    public final Date birthdate;

    public String fname;
    public String lname;

    public Client(int id, Date birthdate) {
        this.id = id;
        this.birthdate = birthdate;
    }

    // для сортируемых типа TreeSet
    @Override
    public int compareTo(Client anotherClient) {
        return this.id - anotherClient.id;
    }

    // для мапов типа HashMap
    @Override
    public int hashCode() {
        return this.birthdate.getSeconds();
    }

    // для поиска по значению
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return this.id == client.id;
    }
}
