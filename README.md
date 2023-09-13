# quizapp

Spring-boot

мое первое приложение crud


включает в себя работу с:
1. postgresql
2. lombok (красатульки в IntelliJ IDEA) исп. @Data
3. maven
4. spring-boot-starter-data-jpa
5. spring-boot-starter-web
6. spring-boot-starter-test
7. git flow
8. Postman

таблица postgres:

public class Questions
* private Integer id;
* private String category;
* private String level;
* private String questionText;



public class Answers
* private Integer id;
* private String answerText;
* private Integer questionId;






