package com.sergei_baranov.otus_java_basic.W08_DZ;

//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Читатель инт-а из консоли, глушащий ошибки
 */
public class IntReader {
    private int lastValue = 0;

    //private Scanner reader = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     *
     * @return int с консоли или ноль, если что-то пошло не так
     */
    public int read() {
        this.lastValue = 0;
        try {
            String nextLine = this.reader.readLine();
            this.lastValue = Integer.parseInt(nextLine);
        } catch (java.io.IOException e) {
            //
        } catch (NumberFormatException e) {
            //
        } catch (Exception e) {
            //
        }
        /*
        try {
            this.lastValue = this.reader.nextInt();
        } catch (Exception e) {
            //
        }
         */
        return this.lastValue;
    }
}
