package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    public static IQuestionsDao createQuestionDao (Connection connection) throws SQLException {
        return new QuestionsDao(connection);
    }
    public static ITestDao createTestDao (Connection connection) throws SQLException {
        return new TestDao(connection);
    }
    public static IStudentDao createStudentDao (Connection connection) throws SQLException {
        return new StudentDao(connection);
    }

}
