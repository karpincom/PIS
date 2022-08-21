package dao;

import entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_SURNAME = "surname";
    private final static String COLUMN_EMAIL = "email";
    private Statement statement;



    public StudentDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        String surname = resultSet.getString(COLUMN_SURNAME);
        String email = resultSet.getString(COLUMN_EMAIL);

        return new Student(id, name, surname,email);
    }

    @Override
    public List<Student> findAll() {
        String query = "SELECT * FROM Student";
        List<Student> studentList = new ArrayList();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Student student = getStudent(resultSet);
                studentList.add(student);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }



    @Override
    public Student findById(long id){
       String query = "SELECT * FROM Student WHERE Student.id=" + id;

        Student student = new Student();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            student = getStudent(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return student;

    };

    @Override
    public long save(Student student) {
        String query = "INSERT INTO Student (name, surname, email) VALUES ('";
        query += student.getName()+"', '"
                +student.getSurname()+"', '"
                +student.getEmail()+"') RETURNING id" ;

        long id = 0;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }


            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return id;
    };

    @Override
    public void update(long id, Student student) {
        String query = "UPDATE Student SET name = '"+student.getName()
                +"', surname = '"+student.getSurname()
                +"', email = '"+student.getEmail()+"'"
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    public void delete(long id) {
        String query = "DELETE FROM Student WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };



}
