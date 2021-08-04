### Предварительно - создал директорию для дз и файл с java-кодом.

```
s_baranov@nb011:~$ cd ~/otus_java_basic
s_baranov@nb011:~/otus_java_basic$ ls
W02_DZ
s_baranov@nb011:~/otus_java_basic$ mkdir W03_DZ
s_baranov@nb011:~/otus_java_basic$ ls
W02_DZ  W03_DZ
s_baranov@nb011:~/otus_java_basic$ cd ./W03_DZ
s_baranov@nb011:~/otus_java_basic/W03_DZ$ touch ./README.md
s_baranov@nb011:~/otus_java_basic/W03_DZ$ nano ./README.md
s_baranov@nb011:~/otus_java_basic/W03_DZ$ touch ./HomeWork.java
s_baranov@nb011:~/otus_java_basic/W03_DZ$ nano ./HomeWork.java
```

создаём объекты файловой системы
![создаём объекты файловой системы](https://github.com/sergei-baranov/otus_java_basic/blob/master/W03_DZ/console_01.png?raw=true)

сохраняем код в nano
![сохраняем код в nano](https://github.com/sergei-baranov/otus_java_basic/blob/master/W03_DZ/nano.png?raw=true)


### Установить JDK и среду разработки

У меня уже стояло то и то.

Java платформа:

```
s_baranov@nb011:~/otus_java_basic/W03_DZ$ java -version
openjdk version "11.0.11" 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```

IDE - обе видны:

ide_01.png - есть VS Code и IntelliJ Idea


### Открыть данный класс в IDE

ide_02.png - открываю файл в Идее
ide_03.png - вот он открытый

### Скомпилировать данный класс с помощью команды javac.

```
s_baranov@nb011:~/otus_java_basic/W03_DZ$ javac -encoding utf-8 ./HomeWork.java
s_baranov@nb011:~/otus_java_basic/W03_DZ$ ls
console_01.png  HomeWork.class  HomeWork.java  ide_01.png  ide_02.png  ide_03.png  nano.png  README.md
s_baranov@nb011:~/otus_java_basic/W03_DZ$ 
```

console_02.png


### Запустить программу на выполнение с помощью команды java

```
s_baranov@nb011:~/otus_java_basic/W03_DZ$ java HomeWork
Please enter you full name
Sergei Baranov
Program result: U2VyZ2VpIEJhcmFub3Y=
```

```
s_baranov@nb011:~/otus_java_basic/W03_DZ$ java -classpath /home/s_baranov/otus_java_basic/W03_DZ HomeWork
Please enter you full name
Sergei Baranov
Program result: U2VyZ2VpIEJhcmFub3Y=
s_baranov@nb011:~/otus_java_basic/W03_DZ$ 
```

console_03.png


### Запустить программу на выполнение с помощью среды разработки

ide_04.png
ide_05.png

```
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -javaagent:/home/s_baranov/idea-IU/lib/idea_rt.jar=37775:/home/s_baranov/idea-IU/bin -Dfile.encoding=UTF-8 HomeWork
Error: Could not find or load main class HomeWork
Caused by: java.lang.ClassNotFoundException: HomeWork

Process finished with exit code 1
```

Что-то не то...

Но вижу надпись "Project JDK is not defined" и кнопку "Setup SDK".
Нажал, выбрал единственное предложенное из списка - 11...

Опять жму зелёную стрелку типа play.
То же самое.

Пошёл псмотрел запись вебинара. 53-я минута.
Необходима структура директорий проекту вполне определённая.
Иду создавать.

```
s_baranov@nb011:~/otus_java_basic/W03_DZ$ ls
console_01.png  console_03.png  HomeWork.java  ide_02.png  ide_04.png  nano.png
console_02.png  HomeWork.class  ide_01.png     ide_03.png  ide_05.png  README.md
s_baranov@nb011:~/otus_java_basic/W03_DZ$ mkdir -p ./src/main/java
s_baranov@nb011:~/otus_java_basic/W03_DZ$ cp ./HomeWork.java ./src/main/java/HomeWork.java
s_baranov@nb011:~/otus_java_basic/W03_DZ$ ls
console_01.png  console_03.png  HomeWork.java  ide_02.png  ide_04.png  nano.png   src
console_02.png  HomeWork.class  ide_01.png     ide_03.png  ide_05.png  README.md
s_baranov@nb011:~/otus_java_basic/W03_DZ$ ls ./src/main/java
HomeWork.java
s_baranov@nb011:~/otus_java_basic/W03_DZ$ 
```

Переоткрываю проект:

ide_06.png

Далее опять выставляю SDK (11.0.10).
Создаю конфигурацию HomeWork (ide_07.png).
Запускаю:

```
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -javaagent:/home/s_baranov/idea-IU/lib/idea_rt.jar=38189:/home/s_baranov/idea-IU/bin -Dfile.encoding=UTF-8 -classpath /home/s_baranov/otus_java_basic/W03_DZ/src/out/production/W03_DZ HomeWork
Please enter you full name
Sergei Baranov
Program result: U2VyZ2VpIEJhcmFub3Y=

Process finished with exit code 0
```

ide_08.png

### Когда программа запросит - введите свое имя латиницей

Sergei Baranov

### Отправить в чат задания скриншоты обоих запусков и строку результата работы программы

console_03.png
ide_08.png

Program result: U2VyZ2VpIEJhcmFub3Y=

Done