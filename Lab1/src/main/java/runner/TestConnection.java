package runner;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.ITestDao;
import dao.IQuestionsDao;
import dao.IStudentDao;
import entities.Test;
import entities.Question;
import entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestConnection {
    public static void main(String args[]) {

        System.out.println("\n");
        testQuestionDao();
        System.out.println("\n\n");
        testTestDao();

        System.out.println("\n\n");
        testStudentDao();
        System.out.println("\n\n");
        testConnectionPool();




    }


    private static void testQuestionDao(){
        try {
            System.out.println("–––QuestionDao–––");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IQuestionsDao questionDao = DaoFactory.createQuestionDao(connection1);

            System.out.println("\t§ Looking for question by id 2:");
            System.out.println(questionDao.findById(2).toString());

            System.out.println("\n\t§ Inserting new question:");
            Question question1 = new Question("Temp question", "answer1", 2);
            long new_id = questionDao.save(question1);
            printAll(questionDao.findAll());

            System.out.println("\n\t§ Updating question:");
            question1.setQuestion("Temp question updated");
            questionDao.update(new_id , question1);
            printAll(questionDao.findAll());

            System.out.println("\n\t§ Deleting question:");
            questionDao.delete(new_id);
            printAll(questionDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("¬¬¬¬¬¬¬¬¬¬¬");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testTestDao(){
        try {
            System.out.println("–––TestDao–––");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            ITestDao testDao = DaoFactory.createTestDao(connection1);

            System.out.println("\t§ Looking for test by id 1:");
            System.out.println(testDao.findById(1).toString());

            System.out.println("\n\t§ Inserting new test:");
            Test test1 = new Test("Temp test", (long) 60);
            long new_id = testDao.save(test1);
            printAll(testDao.findAll());

            System.out.println("\n\t§ Updating test:");
            test1.setName("Temp test updated");
            testDao.update(new_id , test1);
            printAll(testDao.findAll());

            System.out.println("\n\t§ Deleting test:");
            testDao.delete(new_id);
            printAll(testDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("¬¬¬¬¬¬¬¬¬¬¬");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testStudentDao(){
        try {
            System.out.println("–––StudentDao–––");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IStudentDao studentDao = DaoFactory.createStudentDao(connection1);

            System.out.println("\t§ Looking for student by id 5:");
            System.out.println(studentDao.findById(5).toString());

            System.out.println("\n\t§ Inserting new student:");
            Student student1 = new Student("TempName", "TempSurname", "TempEmail");
            long new_id = studentDao.save(student1);
            printAll(studentDao.findAll());

            System.out.println("\n\t§ Updating student:");
            student1.setName("NameUpdated");
            studentDao.update(new_id , student1);
            printAll(studentDao.findAll());

            System.out.println("\n\t§ Deleting student:");
            studentDao.delete(new_id);
            printAll(studentDao.findAll());

            connectionPool.releaseConnection(connection1);
            System.out.println("¬¬¬¬¬¬¬¬¬¬¬");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testConnectionPool(){
        System.out.println("–––Connections–––");
        try {
            List<Connection> connectionList = new ArrayList<>();
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            System.out.print("Initial amount of coonections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  § Getting 20 connections from the pool");

            for (int i = 0; i < 20; i++)
                connectionList.add(connectionPool.getConnection());

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  § Free all 20 connections from the pool ");

            for (int i = 19; i > -1; i--) {
                connectionPool.releaseConnection(connectionList.get(i));
                connectionList.remove(i);
            }

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("____________________________");
    }

    private static void printAll(List questionList){
        questionList.forEach((question) -> {
            System.out.println(question);
        });
        return;
    }


}


//    12. Система Быстрого Тестирования Студентов. Студент
//        регистрируется э-мейлом, к нему привязуеться его Успешность и на него
//        будут приходить сообщения о результате тестов. В системе существует
//        каталог Тестов по темам, авторизованный Студент может проходить тесты. В
//        конце теста должна на странице отобразится форма где показано ошибки
//        студента. Все данные об успеваемости и пройденных курсах отображаются
//        на странице Администратора как сводная таблица по всем Студентам.
