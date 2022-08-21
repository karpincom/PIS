package dao;

import dao.ITestDao;
import entities.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDao implements ITestDao {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_TIMELIMIT = "time_limit";
    private Statement statement;


    public TestDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Test getTest(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        long time_limit = resultSet.getLong(COLUMN_TIMELIMIT);

        return new Test(id, name, time_limit);
    }

    @Override
    public List<Test> findAll() {
        String query = "SELECT * FROM tests";
        List<Test> testList = new ArrayList<Test>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Test question = getTest(resultSet);
                testList.add(question);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return testList;
    }



    @Override
    public Test findById(long id){
        String query = "SELECT * FROM tests WHERE tests.id=" + id;

        Test test = new Test();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            test = getTest(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return test;

    };

    @Override
    public long save(Test test) {
        String query = "INSERT INTO tests (name, time_limit) VALUES ('";
        query += test.getName()+"', "
                +test.getTimeLimit()+") RETURNING id" ;

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
    public void update(long id, Test test) {
        String query = "UPDATE tests SET name = '"+test.getName()
                +"', time_limit = "+test.getTimeLimit()
                +" WHERE id="+id;
        System.out.println(query);
        try {
            statement.executeUpdate(query);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };



    @Override
    public void delete(long id) {
        String query = "DELETE FROM tests WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


}
