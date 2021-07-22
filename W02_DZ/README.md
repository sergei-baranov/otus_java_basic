### система

```
s_baranov@nb011:~$  uname -a

Linux nb011 5.8.0-59-generic #66~20.04.1-Ubuntu SMP Thu Jun 17 11:14:10 UTC 2021 x86_64 x86_64 x86_64 GNU/Linux
```

### узнайте полный путь текущего каталога

```
s_baranov@nb011:~$ pwd

/home/s_baranov
```


### выведите на экран ПОЛНОЕ содержимое текущего каталога

```
s_baranov@nb011:~$ ls -lah

total 6,1M
drwxr-xr-x 37 s_baranov s_baranov 4,0K июл 21 12:24 .
drwxr-xr-x  3 root      root      4,0K янв 11  2021 ..
-rw-rw-r--  1 s_baranov s_baranov  85K мая 24 18:59 1433.pdf
-rw-r--r--  1 s_baranov s_baranov 3,6M мар 31 04:39 baranovTestsGraph.html
-rw-------  1 s_baranov s_baranov  36K июл 19 15:14 .bash_history
-rw-r--r--  1 s_baranov s_baranov  220 янв 11  2021 .bash_logout
-rw-r--r--  1 s_baranov s_baranov 3,7K янв 11  2021 .bashrc
drwxrwxr-x 22 s_baranov s_baranov 4,0K июн 16 02:16 .cache
drwxr-xr-x  3 s_baranov s_baranov 4,0K июн 12 23:46 .cert
-rw-rw-r--  1 s_baranov s_baranov 294K июн 23 01:32 common2.kdbx
-rw-rw-r--  1 s_baranov s_baranov   91 июл 21 12:24 .common2.kdbx.lock
drwx------ 28 s_baranov s_baranov 4,0K июл 19 14:25 .config
-rw-rw-r--  1 s_baranov s_baranov  355 апр  8 17:27 default-soapui-workspace.xml
drwxr-xr-x  2 s_baranov s_baranov 4,0K апр  7 17:16 Desktop
drwxr-xr-x  4 s_baranov s_baranov 4,0K июл  5 14:23 Documents
drwxr-xr-x  5 s_baranov s_baranov 4,0K июл 11 01:24 Downloads
...
```

### создайте каталог otusHW

```
s_baranov@nb011:~$ mkdir -p ./otus_java_basic/otusHW 
```

### перейдите в каталог otusHW

```
s_baranov@nb011:~$ cd ./otus_java_basic/otusHW
s_baranov@nb011:~/otus_java_basic/otusHW$ 
```

### выведите в консоль информацию о файловой системе (точки монтирования, занимаемый размер, свободное место)

*http://manpages.ubuntu.com/manpages/precise/ru/man1/df.1.html*

```
s_baranov@nb011:~/otus_java_basic/otusHW$ df -ahT

Filesystem                Type             Size  Used Avail Use% Mounted on
sysfs                     sysfs               0     0     0    - /sys
proc                      proc                0     0     0    - /proc
udev                      devtmpfs         7,7G     0  7,7G   0% /dev
devpts                    devpts              0     0     0    - /dev/pts
tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run
/dev/mapper/vgubuntu-root ext4             915G   29G  840G   4% /
securityfs                securityfs          0     0     0    - /sys/kernel/security
tmpfs                     tmpfs            7,7G  146M  7,6G   2% /dev/shm
tmpfs                     tmpfs            5,0M  4,0K  5,0M   1% /run/lock
tmpfs                     tmpfs            7,7G     0  7,7G   0% /sys/fs/cgroup
cgroup2                   cgroup2             0     0     0    - /sys/fs/cgroup/unified
cgroup                    cgroup              0     0     0    - /sys/fs/cgroup/systemd
pstore                    pstore              0     0     0    - /sys/fs/pstore
efivarfs                  efivarfs            0     0     0    - /sys/firmware/efi/efivars
none                      bpf                 0     0     0    - /sys/fs/bpf
cgroup                    cgroup              0     0     0    - /sys/fs/cgroup/cpu,cpuacct
cgroup                    cgroup              0     0     0    - /sys/fs/cgroup/perf_event
cgroup                    cgroup              0     0     0    - /sys/fs/cgroup/hugetlb
...
```

### информацию из пункта 5 выведите в файл fsInfo

```
s_baranov@nb011:~/otus_java_basic/otusHW$ df -ahT > fsInfo
s_baranov@nb011:~/otus_java_basic/otusHW$ 
```

### выведите на экран содержимое файла fsInfo

```
s_baranov@nb011:~/otus_java_basic/otusHW$ cat ./fsInfo

Filesystem                Type             Size  Used Avail Use% Mounted on
sysfs                     sysfs               0     0     0    - /sys
proc                      proc                0     0     0    - /proc
udev                      devtmpfs         7,7G     0  7,7G   0% /dev
devpts                    devpts              0     0     0    - /dev/pts
tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run
/dev/mapper/vgubuntu-root ext4             915G   29G  840G   4% /
securityfs                securityfs          0     0     0    - /sys/kernel/security
...
```

### выведите на экран строчки файла fsInfo, в которых есть цифра 1, если таких нет, то цифра 2

*https://www.digitalocean.com/community/tutorials/using-grep-regular-expressions-to-search-for-text-patterns-in-linux*

```
s_baranov@nb011:~/otus_java_basic/otusHW$ grep "[12]" ./fsInfo

tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run
/dev/mapper/vgubuntu-root ext4             915G   29G  840G   4% /
tmpfs                     tmpfs            7,7G  150M  7,6G   2% /dev/shm
...
```

### сделайте копию файла fsInfo - fsInfoСopy

```
s_baranov@nb011:~/otus_java_basic/otusHW$ cp ./fsInfo ./fsInfoCopy
s_baranov@nb011:~/otus_java_basic/otusHW$ ls

fsInfo  fsInfoCopy
```

### сделайте копию файла fsInfo - fsInfoСopy2

```s_baranov@nb011:~/otus_java_basic/otusHW$ cp ./fsInfo ./fsInfoCopy2
s_baranov@nb011:~/otus_java_basic/otusHW$ ls

fsInfo  fsInfoCopy  fsInfoCopy2
```

### переименуйте fsInfoCopy2 в fsInfoCopy3

```
s_baranov@nb011:~/otus_java_basic/otusHW$ mv ./fsInfoCopy2 ./fsInfoCopy3
s_baranov@nb011:~/otus_java_basic/otusHW$ ls

fsInfo  fsInfoCopy  fsInfoCopy3
```

### удалите fsInfoСopy3

```
s_baranov@nb011:~/otus_java_basic/otusHW$ rm fsInfoCopy3
s_baranov@nb011:~/otus_java_basic/otusHW$ ls

fsInfo  fsInfoCopy
```

### выведите на экран первые строки fsInfoСopy

```
s_baranov@nb011:~/otus_java_basic/otusHW$ head ./fsInfoCopy

Filesystem                Type             Size  Used Avail Use% Mounted on
sysfs                     sysfs               0     0     0    - /sys
proc                      proc                0     0     0    - /proc
udev                      devtmpfs         7,7G     0  7,7G   0% /dev
devpts                    devpts              0     0     0    - /dev/pts
tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run
/dev/mapper/vgubuntu-root ext4             915G   29G  840G   4% /
securityfs                securityfs          0     0     0    - /sys/kernel/security
tmpfs                     tmpfs            7,7G  150M  7,6G   2% /dev/shm
tmpfs                     tmpfs            5,0M  4,0K  5,0M   1% /run/lock
```

### выведите на экран последние строки fsInfoСopy

```
s_baranov@nb011:~/otus_java_basic/otusHW$ tail ./fsInfoCopy

tmpfs                     tmpfs            1,6G   72K  1,6G   1% /run/user/1000
gvfsd-fuse                fuse.gvfsd-fuse     0     0     0    - /run/user/1000/gvfs
/dev/fuse                 fuse                0     0     0    - /run/user/1000/doc
tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run/snapd/ns
nsfs                      nsfs                0     0     0    - /run/snapd/ns/snap-store.mnt
nsfs                      nsfs                0     0     0    - /run/snapd/ns/telegram-desktop.mnt
/dev/loop33               squashfs         217M  217M     0 100% /snap/code/70
/dev/loop5                squashfs         304M  304M     0 100% /snap/telegram-desktop/2926
nsfs                      nsfs                0     0     0    - /run/snapd/ns/chromium.mnt
binfmt_misc               binfmt_misc         0     0     0    - /proc/sys/fs/binfmt_misc
```

### создайте файл runIt

```
s_baranov@nb011:~/otus_java_basic/otusHW$ touch ./runIt
s_baranov@nb011:~/otus_java_basic/otusHW$ ls -lah

total 24K
drwxrwxr-x 2 s_baranov s_baranov 4,0K июл 21 13:48 .
drwxrwxr-x 3 s_baranov s_baranov 4,0K июл 21 13:14 ..
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:28 fsInfo
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:41 fsInfoCopy
-rw-rw-r-- 1 s_baranov s_baranov    0 июл 21 13:48 runIt
```

### в vi откройте файл runIt

```
vi ./runIt
 
~
~
...
```

### в файл runIt добавьте команду копирования fsInfo в fsInfoFinal

набираю :i, жму Enter

напбираю текст
cp ./fsInfo ./fsInfoFinal

нажимаю Esc

набираю :wq, жму Enter

в итоге у меня сохранилось как-то :wq в строке,

опять открываю vi, пытаюсь удалить строку через :dd, он ругается, что
"E492: Not an editor command: dd", удаляю через :d

КАК-ТО всё получилось в итоге

```
s_baranov@nb011:~/otus_java_basic/otusHW$ cat ./runIt
cp ./fsInfo ./fsInfoFinal
```

### сделайте runIt исполняемым

смотрим его права до:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ ls -lah ./runIt
-rw-rw-r-- 1 s_baranov s_baranov 26 июл 21 16:40 ./runIt
```

ставим флаг исполняемости:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ sudo chmod +x ./runIt
[sudo] password for s_baranov:
```

смотрим его права после:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ ls -lah ./runIt
-rwxrwxr-x 1 s_baranov s_baranov 26 июл 21 16:40 ./runIt
```

### выполните runIt и убедитесь, что он отработал как надо работа сделана

состояние директории до:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ ls -lah

total 28K
drwxrwxr-x 2 s_baranov s_baranov 4,0K июл 21 16:40 .
drwxrwxr-x 4 s_baranov s_baranov 4,0K июл 21 14:13 ..
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:28 fsInfo
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:41 fsInfoCopy
-rwxrwxr-x 1 s_baranov s_baranov   26 июл 21 16:40 runIt
```

запускаю runIt:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ ./runIt
```

проверяю, отработал ли как задумано:
```
s_baranov@nb011:~/otus_java_basic/otusHW$ ls -lah

total 36K
drwxrwxr-x 2 s_baranov s_baranov 4,0K июл 21 16:44 .
drwxrwxr-x 4 s_baranov s_baranov 4,0K июл 21 14:13 ..
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:28 fsInfo
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 13:41 fsInfoCopy
-rw-rw-r-- 1 s_baranov s_baranov 6,4K июл 21 16:44 fsInfoFinal
-rwxrwxr-x 1 s_baranov s_baranov   26 июл 21 16:40 runIt
```

новый файл появился;
смотрим его содержимое:

```
s_baranov@nb011:~/otus_java_basic/otusHW$ head ./fsInfoFinal

Filesystem                Type             Size  Used Avail Use% Mounted on
sysfs                     sysfs               0     0     0    - /sys
proc                      proc                0     0     0    - /proc
udev                      devtmpfs         7,7G     0  7,7G   0% /dev
devpts                    devpts              0     0     0    - /dev/pts
tmpfs                     tmpfs            1,6G  2,5M  1,6G   1% /run
/dev/mapper/vgubuntu-root ext4             915G   29G  840G   4% /
securityfs                securityfs          0     0     0    - /sys/kernel/security
tmpfs                     tmpfs            7,7G  150M  7,6G   2% /dev/shm
tmpfs                     tmpfs            5,0M  4,0K  5,0M   1% /run/lock
```