package com.sergei_baranov.otus_java_basic.W13_DZ;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * - Придумать несколько взаимосвязанных
 * классов, в которых использовать три
 * способа обработки исключений (вывод
 * ошибки, rethrow и еще один, на выбор)
 * - А так же использована конструкция try-withresource
 * - Вместо обращения к реальным ресурсам
 * можно использовать классы-заглушки
 */
public class ErrsHandling {
    public static void main(String args[]) {
        // вывод ошибки
        System.out.println("вывод ошибки\n====");
        try {
            DummyFile myFile1 = new DummyFile("aaa/bbb/ccc");
            myFile1.open("qwerty");
        } catch (FileNotFoundException e) {
            System.out.println("can not open file qwerty (original message: "
                    + e.getMessage() + ")");
        } finally {
            System.out.println("====\n");
        }

        // try-with-resources
        System.out.println("try-with-resources\n====");
        try (
            DummyFile myFile2 = new DummyFile("eee/sss");
        ) {
            System.out.println("try-with-resources body");
        } catch (FileNotFoundException e) {
            System.out.println("try-with-resources catch");
        } finally {
            System.out.println("====\n");
        }
        //  я не делал close(), но должен отобразиться лог вида "inner log: closing 123 (asdfg)"

        // и ещё один на выбор - пускай будет повторная попытка
        System.out.println("retry\n====");
        try {
            DummyFile myFile3 = new DummyFile("will/read/with/retry");
            String s = null;
            for (int i = 0; i < 4; i ++) {
                try {
                    s = myFile3.read();
                } catch (CanNotReadFileException ignored) {
                }
                if (null != s) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ee) {
                    System.out.println("Ексепшн на ексепшне ездит, ексепшном погоняет");
                }
            }
            myFile3.close();
            if (null == s) {
                throw new CanNotReadFileException("Can not read in 4 times");
            } else {
                System.out.println("s: " + s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("этого не произойдёт, но требуется отработать," +
                    "так как FileNotFoundException - checked");
        } finally {
            System.out.println("====\n");
        }

        // rethrow - отправить выше в обёртке
        System.out.println("rethrow and stacktrace\n====");
        try {
            DummyFile myFile = new DummyFile("boo/moo");
            myFile.open("boo/moo"); // FileNotFoundException
            myFile.close();
        } catch (FileNotFoundException e) {
            // отправить выше в обёртке
            throw new CanNotOpenFileException("Почему-то файл не открывается, беда", e);
            // и в консоли мы увидим StackTrace
        }
    }
}

/**
 * класс, имитирующий ресурс типа файл или т.п.;
 * во все его внутренние действия вставлен вывод на стдаут
 * сообщений вида "inner log: ...", чтобы мы при исполнении
 * вызывающего кода видели на консоли, что всё работает как и задумано
 */
class DummyFile implements AutoCloseable {
    private int descriptor = 0;
    private String uri = "";
    private int readRetryCounter = 0;

    public DummyFile(String uri) throws FileNotFoundException {
        this.open(uri);
    }

    public void close() {
        System.out.println("inner log: closing " + this.descriptor + " (" + this.uri + ")");
        this.uri = "";
        this.descriptor = 0;
    }

    public int open(String uri) throws FileNotFoundException {
        if (0 != this.descriptor) {
            throw new FileNotFoundException("inner log: error: busy descriptor (" + this.descriptor + ")");
        }

        this.uri = uri;
        this.descriptor = (int) ((Math.random() * (32_000 - 1)) + 1);

        System.out.println("inner log: opened " + this.descriptor + " (" + this.uri + ")");

        return this.descriptor;
    }

    /**
     * имитируем ошибку чтения каждые две из трёх попыток
     * @return Random().toString()
     */
    public String read() {
        if (this.readRetryCounter < 2) {
            this.readRetryCounter += 1;
            System.out.println("inner log: read retry failure");
            throw new CanNotReadFileException("oops; try later");
        }

        this.readRetryCounter = 0;
        System.out.println("inner log: read retry success");
        return new Random().toString();
    }

    public int getDescriptor() {
        return this.descriptor;
    }

    public String getUri() {
        return this.uri;
    }
}

/**
 * custom unchecked Exception CanNotOpenFileException
 */
class CanNotOpenFileException extends RuntimeException {
    public CanNotOpenFileException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public CanNotOpenFileException(String errorMessage) {
        super(errorMessage);
    }
}

/**
 * custom unchecked Exception CanNotReadFileException
 */
class CanNotReadFileException extends RuntimeException {
    public CanNotReadFileException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public CanNotReadFileException(String errorMessage) {
        super(errorMessage);
    }
}