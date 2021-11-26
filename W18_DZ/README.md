## Устанавливаем Postgres на локальную машину

```
https://www.digitalocean.com/community/tutorials/how-to-install-postgresql-on-ubuntu-20-04-quickstart-ru
```

```
$ sudo apt update
...
$ sudo apt install postgresql postgresql-contrib
...
```

### Проверяем, что всё встало, смотрим на databases

```
$ sudo -u postgres psql
psql (12.8 (Ubuntu 12.8-0ubuntu0.20.04.1))
Type "help" for help.

postgres=# SELECT version();
version                                                               
----------------------------------------------------------------------
 PostgreSQL 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) on x86_64-pc-linux-gnu,
 compiled by gcc (Ubuntu 9.3.0-17ubuntu1~20.04) 9.3.0, 64-bit
(1 row)


postgres=# \l
  List of databases
   Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges   
-----------+----------+----------+-------------+-------------+-----------------------
 postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
 template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
(3 rows)


postgres=# \l+
  List of databases
   Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges   |  Size   | Tablespace |                Description                 
-----------+----------+----------+-------------+-------------+-----------------------+---------+------------+--------------------------------------------
 postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 |                       | 7977 kB | pg_default | default administrative connection database
 template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +| 7833 kB | pg_default | unmodifiable empty database
           |          |          |             |             | postgres=CTc/postgres |         |            | 
 template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +| 7977 kB | pg_default | default template for new databases
           |          |          |             |             | postgres=CTc/postgres |         |            | 
(3 rows)


postgres=# SELECT datname FROM pg_database;
  datname  
-----------
 postgres
 template1
 template0
(3 rows)


postgres=# SELECT current_database();
 current_database 
------------------
 postgres
(1 row)
```

Аналогия с MySql: database в постгресе соответствует скорее серверу в мускуле.

"В рамках одного подключения к серверу можно обращаться к данным только одной базы — той,
что была выбрана при установлении соединения."

Пользователи создаются на уровне бд в посгресе, примерно как на уровне сервера в мускуле.

Аналог же бд мускула в постгресе - схема.

"База данных содержит одну или несколько именованных схем, которые в свою очередь содержат таблицы.
Схемы также содержат именованные объекты других видов, включая типы данных, функции и операторы."

```
postgres=# \dn
  List of schemas
  Name  |  Owner   
--------+----------
 public | postgres
(1 row)


postgres=# \dn+
                          List of schemas
  Name  |  Owner   |  Access privileges   |      Description       
--------+----------+----------------------+------------------------
 public | postgres | postgres=UC/postgres+| standard public schema
        |          | =UC/postgres         | 
(1 row)


postgres=# show search_path;
   search_path   
-----------------
 "$user", public
(1 row)

```

### Ради инетереса просто посмотрим tablespaces

[tablespace соответствует мускульному понятию - определяет сущности на уровне файловой системы,
какие таблицы расположить рядом друг с дружкой, какие на быстрый физический носитель и т. п.]


```
postgres=# \db
  List of tablespaces
    Name    |  Owner   | Location 
------------+----------+----------
 pg_default | postgres | 
 pg_global  | postgres | 
(2 rows)


postgres=# \db+
  List of tablespaces
    Name    |  Owner   | Location | Access privileges | Options |  Size  | Description 
------------+----------+----------+-------------------+---------+--------+-------------
 pg_default | postgres |          |                   |         | 23 MB  | 
 pg_global  | postgres |          |                   |         | 623 kB | 
(2 rows)
```

Для ускорения исполнения домашки буду всё делать в бд postgres под пользователем postgres.
А схему создам новую. Назовём её veggietest по примеру приложения из предыдущих дз.
(uml диаграмма, по которой сделаем структуру бд - дз по занятию 8)

## Новая схема

```
postgres=# CREATE SCHEMA veggietest;
CREATE SCHEMA

postgres=# \dn+
                            List of schemas
    Name    |  Owner   |  Access privileges   |      Description       
------------+----------+----------------------+------------------------
 public     | postgres | postgres=UC/postgres+| standard public schema
            |          | =UC/postgres         | 
 veggietest | postgres |                      | 
(2 rows)


postgres=# show search_path;
   search_path   
-----------------
 "$user", public
(1 row)
```

SET search_path TO ...
и
ALTER ROLE ... SET search_path TO ...
делать не будем, чтобы путаться, и всегда указывать полный пути вида schema.table


### Теперь сделаем базовую таблицу - с вариантами ответов.

```sql
CREATE TABLE answers (
  id    SERIAL,      -- AI PK
  code  varchar(64), -- краткий код
  title varchar(255), -- текст варианта ответа

  CONSTRAINT answers_pkey PRIMARY KEY(id)
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
INSERT INTO answers
  (id, code, title)
VALUES
  (1, 'CUCUMBER', 'Огурцом'),
  (2, 'TOMATO', 'Помидором'),
  (3, 'POTATO', 'Картошкой'),
  (4, 'PUMPKIN', 'Тыквой'),
  (5, 'ONION', 'Луком'),
  (6, 'RED', 'красный'),
  (7, 'GREEN', 'зелёный'),
  (8, 'BLUE', 'синий'),
  (9, 'ROUND', 'круглый'),
  (10, 'SQUARE', 'квадратный'),
  (11, 'TRIANGULAR', 'треуголный')
;
```

```
postgres=# ...
INSERT 0 11
```

### Потом таблицу вопросов

```sql
CREATE TABLE questions (
  id SERIAL,         -- AI PK
  title varchar(255), -- текст вопроса

  CONSTRAINT questions_pkey PRIMARY KEY(id)
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
INSERT INTO questions
  (id, title)
VALUES
  (1, 'Какой формы помидор?'),
  (2, 'Какого цвета огурец?'),
  (3, 'Чем пахнет корюшка?')
;
```

```
postgres=# ...
INSERT 0 3
```

### Потом таблицу привязки вариантов ответов к вопросам

```sql
CREATE TABLE questions_answers (
  question_id INTEGER, -- FK to questions.id
  answer_id   INTEGER, -- FK to answers.id
  correct     boolean, -- метка правильных вариантов
  num         INTEGER, -- позиция варианта в вопросе

  CONSTRAINT questions_answers_pkey PRIMARY KEY(question_id, answer_id),
  CONSTRAINT questions_answers_uniq UNIQUE(answer_id, question_id),

  FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE RESTRICT,
  FOREIGN KEY (answer_id) REFERENCES answers(id) ON DELETE RESTRICT
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
CREATE INDEX question_id_index ON questions_answers (question_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
CREATE INDEX qanswer_id_index ON questions_answers (answer_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
INSERT INTO questions_answers
  (question_id, answer_id, correct, num)
VALUES
  (1, 9, true, 1),
  (1, 10, false, 2),
  (1, 11, false, 3),
  (2, 6, false, 1),
  (2, 7, true, 2),
  (2, 8, false, 3),
  (3, 1, true, 1),
  (3, 2, false, 2),
  (3, 3, false, 3),
  (3, 4, false, 4),
  (3, 5, false, 5)
;
```

```
postgres=# ...
INSERT 0 11
```

### И таблицу конфигураций тестов

```sql
CREATE TABLE configs (
  id SERIAL,          -- AI PK
  title varchar(255), -- название конфига (теста)

  CONSTRAINT configs_pkey PRIMARY KEY(id)
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
INSERT INTO configs
  (id, title)
VALUES
  (1, 'Первый тест'),
  (2, 'Короткий тест')
;
```

```
postgres=# ...
INSERT 0 2
```

### Привязка вопросов к конфигам (к тестам)

```sql
CREATE TABLE configs_questions (
  config_id   INTEGER, -- FK to configs.id
  question_id INTEGER, -- FK to questions.id
  num         INTEGER, -- позиция вопроса в тесте

  CONSTRAINT configs_questions_pkey PRIMARY KEY(config_id, question_id),
  CONSTRAINT configs_questions_uniq UNIQUE(question_id, config_id),

  FOREIGN KEY (config_id) REFERENCES configs(id) ON DELETE RESTRICT,
  FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE RESTRICT
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
CREATE INDEX config_id_index ON configs_questions (config_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
CREATE INDEX configs_question_id_index ON configs_questions (question_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
INSERT INTO configs_questions
  (config_id, question_id, num)
VALUES
  (1, 1, 1),
  (1, 2, 2),
  (1, 3, 3),
  (2, 3, 1)
;
```

```
postgres=# ...
INSERT 0 4
```

### И наверное надо создать таблицы для прохождений и результатов

Студенты

```sql
CREATE TABLE students (
  id SERIAL,          -- AI PK
  fname varchar(255), -- имя
  lname varchar(255), -- фамилия
  mname varchar(255), -- отчество

  CONSTRAINT students_pkey PRIMARY KEY(id)
);
```

```
postgres=# ...
CREATE TABLE
```

Результаты
(можно нормализовать на три таблицы, сессии, ответы и связка; но для дз-версии нет смысла)

```sql
CREATE TABLE results (
  id          SERIAL,       -- AI PK
  student_id  INTEGER,      -- FK to students.id
  session     varchar(255), -- идентификатор попытки (можно проходить много раз тесты)
  config_id   INTEGER,      -- набор вопросов в сессии, FK to configs.id
  question_id INTEGER,      -- вопрос в сессии, FK to questions.id
  answer_id   INTEGER,      -- ответ на вопрос, FK to answers.id
  correct     boolean,      -- вообще это можно высчитывать; но тут будет историчность, например если questions_answers.correct изменится

  CONSTRAINT results_pkey PRIMARY KEY(id),

  FOREIGN KEY (student_id)  REFERENCES students(id)  ON DELETE RESTRICT,
  FOREIGN KEY (config_id)   REFERENCES configs(id)   ON DELETE RESTRICT,
  FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE RESTRICT,
  FOREIGN KEY (answer_id)   REFERENCES answers(id)   ON DELETE RESTRICT
);
```

```
postgres=# ...
CREATE TABLE
```

```sql
CREATE INDEX student_id_index ON results (student_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
CREATE INDEX results_config_id_index ON results (config_id);
```
```
postgres=# ...
CREATE INDEX
```

```sql
CREATE INDEX results_question_id_index ON results (question_id);
```

```
postgres=# ...
CREATE INDEX
```

```sql
CREATE INDEX results_answer_id_index ON results (answer_id);
```

```
postgres=# ...
CREATE INDEX
```

## Исследуем

### Таблицы

```
postgres=# SELECT table_name FROM information_schema.tables  WHERE table_schema='public' ORDER BY table_name;
    table_name     
-------------------
 answers
 configs
 configs_questions
 questions
 questions_answers
 results
 students
(7 rows)
```

```
postgres=# SELECT table_name, column_name FROM information_schema.columns WHERE table_schema='public';
    table_name     | column_name 
-------------------+-------------
 students          | lname
 students          | id
 students          | mname
 answers           | code
 results           | student_id
 questions         | title
 results           | question_id
 questions_answers | correct
 configs           | title
 questions_answers | question_id
 configs_questions | question_id
 questions_answers | num
 questions_answers | answer_id
 configs           | id
 configs_questions | config_id
 questions         | id
 configs_questions | num
 results           | answer_id
 results           | config_id
 students          | fname
 answers           | id
 results           | id
 answers           | title
 results           | session
 results           | correct
(25 rows)
```

### Индексы

```
postgres=# select tablename, indexname, indexdef from pg_indexes where tablename not like 'pg%';
     tablename     |         indexname         |                                                  indexdef                                                   
-------------------+---------------------------+-------------------------------------------------------------------------------------------------------------
 answers           | answers_pkey              | CREATE UNIQUE INDEX answers_pkey ON public.answers USING btree (id)
 questions         | questions_pkey            | CREATE UNIQUE INDEX questions_pkey ON public.questions USING btree (id)
 questions_answers | questions_answers_pkey    | CREATE UNIQUE INDEX questions_answers_pkey ON public.questions_answers USING btree (question_id, answer_id)
 questions_answers | questions_answers_uniq    | CREATE UNIQUE INDEX questions_answers_uniq ON public.questions_answers USING btree (answer_id, question_id)
 questions_answers | question_id_index         | CREATE INDEX question_id_index ON public.questions_answers USING btree (question_id)
 questions_answers | qanswer_id_index          | CREATE INDEX qanswer_id_index ON public.questions_answers USING btree (answer_id)
 configs           | configs_pkey              | CREATE UNIQUE INDEX configs_pkey ON public.configs USING btree (id)
 configs_questions | configs_questions_pkey    | CREATE UNIQUE INDEX configs_questions_pkey ON public.configs_questions USING btree (config_id, question_id)
 configs_questions | configs_questions_uniq    | CREATE UNIQUE INDEX configs_questions_uniq ON public.configs_questions USING btree (question_id, config_id)
 configs_questions | config_id_index           | CREATE INDEX config_id_index ON public.configs_questions USING btree (config_id)
 configs_questions | configs_question_id_index | CREATE INDEX configs_question_id_index ON public.configs_questions USING btree (question_id)
 students          | students_pkey             | CREATE UNIQUE INDEX students_pkey ON public.students USING btree (id)
 results           | results_pkey              | CREATE UNIQUE INDEX results_pkey ON public.results USING btree (id)
 results           | student_id_index          | CREATE INDEX student_id_index ON public.results USING btree (student_id)
 results           | results_config_id_index   | CREATE INDEX results_config_id_index ON public.results USING btree (config_id)
 results           | results_question_id_index | CREATE INDEX results_question_id_index ON public.results USING btree (question_id)
 results           | results_answer_id_index   | CREATE INDEX results_answer_id_index ON public.results USING btree (answer_id)
(17 rows)
```

### Констрейнты

```
postgres=# select conname, conkey, conrelid FROM pg_constraint;
              conname               | conkey | conrelid 
------------------------------------+--------+----------
 cardinal_number_domain_check       |        |        0
 yes_or_no_check                    |        |        0
 answers_pkey                       | {1}    |    16387
 questions_pkey                     | {1}    |    16395
 questions_answers_pkey             | {1,2}  |    16401
 questions_answers_uniq             | {2,1}  |    16401
 questions_answers_question_id_fkey | {1}    |    16401
 questions_answers_answer_id_fkey   | {2}    |    16401
 configs_pkey                       | {1}    |    16422
 configs_questions_pkey             | {1,2}  |    16428
 configs_questions_uniq             | {2,1}  |    16428
 configs_questions_config_id_fkey   | {1}    |    16428
 configs_questions_question_id_fkey | {2}    |    16428
 students_pkey                      | {1}    |    16449
 results_pkey                       | {1}    |    16460
 results_student_id_fkey            | {2}    |    16460
 results_config_id_fkey             | {4}    |    16460
 results_question_id_fkey           | {5}    |    16460
 results_answer_id_fkey             | {6}    |    16460
(19 rows)
```

## Выборки

### Короткий тест

```sql
SELECT
  c.title, q.title, a.code, a.title, qa.correct
FROM
  configs c
  INNER JOIN configs_questions cq ON cq.config_id = c.id
  INNER JOIN questions q ON q.id = cq.question_id
  INNER JOIN questions_answers qa ON qa.question_id = q.id
  INNER JOIN answers a ON a.id = qa.answer_id
WHERE
  c.id = 2
ORDER BY
  cq.num ASC, qa.num ASC
;
```

```
postgres=#
     title     |        title        |   title   | correct |   code   
---------------+---------------------+-----------+---------+----------
 Короткий тест | Чем пахнет корюшка? | Огурцом   | t       | CUCUMBER
 Короткий тест | Чем пахнет корюшка? | Помидором | f       | TOMATO
 Короткий тест | Чем пахнет корюшка? | Картошкой | f       | POTATO
 Короткий тест | Чем пахнет корюшка? | Тыквой    | f       | PUMPKIN
 Короткий тест | Чем пахнет корюшка? | Луком     | f       | ONION
(5 rows)
```


### Первый тест

```sql
SELECT
  c.title, q.title, a.code, a.title, qa.correct
FROM
  configs c
  INNER JOIN configs_questions cq ON cq.config_id = c.id
  INNER JOIN questions q ON q.id = cq.question_id
  INNER JOIN questions_answers qa ON qa.question_id = q.id
  INNER JOIN answers a ON a.id = qa.answer_id
WHERE
  c.id = 1
ORDER BY
  cq.num ASC, qa.num ASC
;
```

```
postgres=#
    title    |        title         |    code    |   title    | correct 
-------------+----------------------+------------+------------+---------
 Первый тест | Какой формы помидор? | ROUND      | круглый    | t
 Первый тест | Какой формы помидор? | SQUARE     | квадратный | f
 Первый тест | Какой формы помидор? | TRIANGULAR | треуголный | f
 Первый тест | Какого цвета огурец? | RED        | красный    | f
 Первый тест | Какого цвета огурец? | GREEN      | зелёный    | t
 Первый тест | Какого цвета огурец? | BLUE       | синий      | f
 Первый тест | Чем пахнет корюшка?  | CUCUMBER   | Огурцом    | t
 Первый тест | Чем пахнет корюшка?  | TOMATO     | Помидором  | f
 Первый тест | Чем пахнет корюшка?  | POTATO     | Картошкой  | f
 Первый тест | Чем пахнет корюшка?  | PUMPKIN    | Тыквой     | f
 Первый тест | Чем пахнет корюшка?  | ONION      | Луком      | f
```

## Explain

```sql
EXPLAIN
SELECT
  c.title, q.title, a.code, a.title, qa.correct
FROM
  configs c
  INNER JOIN configs_questions cq ON cq.config_id = c.id
  INNER JOIN questions q ON q.id = cq.question_id
  INNER JOIN questions_answers qa ON qa.question_id = q.id
  INNER JOIN answers a ON a.id = qa.answer_id
WHERE
  c.id = 2
ORDER BY
  cq.num ASC, qa.num ASC
;
```

```
QUERY PLAN
-------------------------------------------------------------------------------------------------------------------
 Sort  (cost=60.59..60.95 rows=142 width=1703)
   Sort Key: cq.num, qa.num
   ->  Hash Join  (cost=27.68..55.51 rows=142 width=1703)
         Hash Cond: (qa.answer_id = a.id)
         ->  Nested Loop  (cost=15.21..42.65 rows=142 width=1045)
               ->  Nested Loop  (cost=15.06..34.95 rows=10 width=1044)
                     ->  Index Scan using configs_pkey on configs c  (cost=0.14..8.16 rows=1 width=520)
                           Index Cond: (id = 2)
                     ->  Hash Join  (cost=14.91..26.69 rows=10 width=532)
                           Hash Cond: (q.id = cq.question_id)
                           ->  Seq Scan on questions q  (cost=0.00..11.40 rows=140 width=520)
                           ->  Hash  (cost=14.79..14.79 rows=10 width=12)
                                 ->  Bitmap Heap Scan on configs_questions cq  (cost=4.23..14.79 rows=10 width=12)
                                       Recheck Cond: (config_id = 2)
                                       ->  Bitmap Index Scan on config_id_index  (cost=0.00..4.23 rows=10 width=0)
                                             Index Cond: (config_id = 2)
               ->  Index Scan using question_id_index on questions_answers qa  (cost=0.15..0.67 rows=10 width=13)
                     Index Cond: (question_id = q.id)
         ->  Hash  (cost=11.10..11.10 rows=110 width=666)
               ->  Seq Scan on answers a  (cost=0.00..11.10 rows=110 width=666)
(20 rows)
```


```sql
EXPLAIN ANALYZE
SELECT
  c.title, q.title, a.code, a.title, qa.correct
FROM
  configs c
  INNER JOIN configs_questions cq ON cq.config_id = c.id
  INNER JOIN questions q ON q.id = cq.question_id
  INNER JOIN questions_answers qa ON qa.question_id = q.id
  INNER JOIN answers a ON a.id = qa.answer_id
WHERE
  c.id = 1
ORDER BY
  cq.num ASC, qa.num ASC
;
```

```
QUERY PLAN
-------------------------------------------------------------------------------------------------------------------------------------------------------------
 Sort  (cost=60.59..60.95 rows=142 width=1703) (actual time=0.193..0.200 rows=11 loops=1)
   Sort Key: cq.num, qa.num
   Sort Method: quicksort  Memory: 26kB
   ->  Hash Join  (cost=27.68..55.51 rows=142 width=1703) (actual time=0.125..0.167 rows=11 loops=1)
         Hash Cond: (qa.answer_id = a.id)
         ->  Nested Loop  (cost=15.21..42.65 rows=142 width=1045) (actual time=0.076..0.108 rows=11 loops=1)
               ->  Nested Loop  (cost=15.06..34.95 rows=10 width=1044) (actual time=0.064..0.075 rows=3 loops=1)
                     ->  Index Scan using configs_pkey on configs c  (cost=0.14..8.16 rows=1 width=520) (actual time=0.017..0.019 rows=1 loops=1)
                           Index Cond: (id = 1)
                     ->  Hash Join  (cost=14.91..26.69 rows=10 width=532) (actual time=0.042..0.049 rows=3 loops=1)
                           Hash Cond: (q.id = cq.question_id)
                           ->  Seq Scan on questions q  (cost=0.00..11.40 rows=140 width=520) (actual time=0.004..0.006 rows=3 loops=1)
                           ->  Hash  (cost=14.79..14.79 rows=10 width=12) (actual time=0.020..0.021 rows=3 loops=1)
                                 Buckets: 1024  Batches: 1  Memory Usage: 9kB
                                 ->  Bitmap Heap Scan on configs_questions cq  (cost=4.23..14.79 rows=10 width=12) (actual time=0.014..0.017 rows=3 loops=1)
                                       Recheck Cond: (config_id = 1)
                                       Heap Blocks: exact=1
                                       ->  Bitmap Index Scan on config_id_index  (cost=0.00..4.23 rows=10 width=0) (actual time=0.007..0.007 rows=3 loops=1)
                                             Index Cond: (config_id = 1)
               ->  Index Scan using question_id_index on questions_answers qa  (cost=0.15..0.67 rows=10 width=13) (actual time=0.004..0.007 rows=4 loops=3)
                     Index Cond: (question_id = q.id)
         ->  Hash  (cost=11.10..11.10 rows=110 width=666) (actual time=0.037..0.037 rows=11 loops=1)
               Buckets: 1024  Batches: 1  Memory Usage: 9kB
               ->  Seq Scan on answers a  (cost=0.00..11.10 rows=110 width=666) (actual time=0.019..0.025 rows=11 loops=1)
 Planning Time: 1.704 ms
 Execution Time: 0.344 ms
(26 rows)
```