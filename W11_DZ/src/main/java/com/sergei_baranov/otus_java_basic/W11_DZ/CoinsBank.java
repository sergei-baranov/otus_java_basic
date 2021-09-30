package com.sergei_baranov.otus_java_basic.W11_DZ;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * буду делать два хешмапа;
 * ХешМап первый: ключ - Клиент, значение - ХешСет из Депозитов;
 * ХешМап второй: ключ - Депозит, значение - Клиент
 *
 * Вопрос: глобальная консистентность - как это два хешмапа откоррелированы?
 * Ответ: никак, чисто на уровне программной логики, а вообще домашка  - про
 * потренироваться с hashCode() и equals(), а не про консистентные структуры.
 */
public class CoinsBank {
    public static ArrayList<Deposit> deposit4test = new ArrayList<Deposit>();
    public static ArrayList<Client> client4test = new ArrayList<Client>();

    public static HashMap<Client, TreeSet<Deposit>> DepositByClient = new HashMap<Client, TreeSet<Deposit>>();
    public static HashMap<Deposit, Client> ClientByDeposit = new HashMap<Deposit, Client>();

    public static void main(String[] args) {
        CoinsBank.fillInitData(1_000_000);

        // tests

        long startMillis, finMillis, deltaMillis; // тайминги

        Client testClient = CoinsBank.client4test.get(5555);
        System.out.println("testClient: " + testClient.id + " (" + testClient.birthdate.toString() + ")\n");

        startMillis = java.lang.System.currentTimeMillis();
        TreeSet<Deposit> foundDeposits = CoinsBank.getAccounts(testClient);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.print("foundDeposits: ");
        foundDeposits.forEach(x -> System.out.print(x.id + ", "));
        System.out.println("\n(" + deltaMillis + " msec)\n");

        Deposit testDeposit = CoinsBank.deposit4test.get(777);
        System.out.println("testDeposit: " + testDeposit.id + "\n");

        startMillis = java.lang.System.currentTimeMillis();
        Client foundClient = CoinsBank.getClient(testDeposit);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.println("foundClient: " + foundClient.id + " (" + foundClient.birthdate.toString() + ")");
        System.out.println("(" + deltaMillis + " msec)\n");

        startMillis = java.lang.System.currentTimeMillis();
        Client foundClient2 = CoinsBank.findClient(testDeposit);
        finMillis = java.lang.System.currentTimeMillis();
        deltaMillis = finMillis - startMillis;
        System.out.println("foundClient2: " + foundClient2.id + " (" + foundClient2.birthdate.toString() + ")");
        System.out.println("(" + deltaMillis + " msec)\n");
    }

    private static void fillInitData(int clientsQuantity) {
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date hundredYearsAgo = new Date(now - aDay * 365 * 100);
        Date eighteenYearsAgo = new Date(now - aDay * 365 * 18);
        long startMillis = hundredYearsAgo.getTime();
        long endMillis = eighteenYearsAgo.getTime();
        long randomMillisSinceEpoch;
        Date randomDate;

        int i, j;
        Client client;
        Deposit deposit;
        TreeSet<Deposit> pairDeposits;
        for (i = 1; i <= clientsQuantity; i++) {
            randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
            randomDate = new Date(randomMillisSinceEpoch);
            client = new Client(i, randomDate);
            CoinsBank.client4test.add(client);
            pairDeposits = new TreeSet<Deposit>();

            for (j = i * 2 - 1; j <= i * 2; j++) {
                deposit = new Deposit(j);
                CoinsBank.deposit4test.add(deposit);
                CoinsBank.ClientByDeposit.put(deposit, client);
                pairDeposits.add(deposit);
            }
            CoinsBank.DepositByClient.put(client, pairDeposits);
        }

        System.out.println("\n" + clientsQuantity + " Clients (" + (clientsQuantity * 2) + " Deposits) was inited\n");
    }

    /**
     * Просто берёт по индексу из ХешМап-а DepositByClient
     * @param client key
     * @return TreeSet<Deposit>
     */
    public static TreeSet<Deposit> getAccounts(Client client) {
        return CoinsBank.DepositByClient.get(client);
    }

    /**
     * Просто берёт по индексу из ХешМап-а ClientByDeposit
     * @param deposit key
     * @return
     */
    public static Client getClient(Deposit deposit) {
        return CoinsBank.ClientByDeposit.get(deposit);
    }

    /**
     * Если ТЗ было работать по одной структуре (DepositByClient)
     * @param deposit key
     * @return
     */
    public static Client findClient(Deposit deposit) {
        Client client = null;
        Client nextClient;
        TreeSet<Deposit> nextDeposits;
        for(Map.Entry<Client, TreeSet<Deposit>> entry : CoinsBank.DepositByClient.entrySet()) {
            nextClient = entry.getKey();
            nextDeposits = entry.getValue();

            if (nextDeposits.contains(deposit)) {
                client = nextClient;
                break;
            }
        }

        return client;
    }
}