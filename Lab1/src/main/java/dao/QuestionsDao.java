package dao;

import entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDao implements IQuestionsDao{
    private final String SELECT_QUESTIONS = "SELECT questions.question";
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_QUESTION = "question";
    private final static String COLUMN_ANSWER = "answer";
    private final static String COLUMN_TESTID = "test_id";
    private Statement statement;



    public QuestionsDao(final Connection connection) throws SQLException{
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Question getQuestion(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String question = resultSet.getString(COLUMN_QUESTION);
        String answer = resultSet.getString(COLUMN_ANSWER);
        long test_id = resultSet.getLong(COLUMN_TESTID);


        return new Question(id, question, answer, test_id);
    }

    @Override
    public List<Question> findAll() {
        String query = "SELECT * FROM questions";
        List<Question> questionList = new ArrayList<Question>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Question question = getQuestion(resultSet);
                questionList.add(question);
            }

            resultSet.close();
        } catch (
        SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }



    @Override
    public Question findById(long id){
        String query = "SELECT * FROM questions WHERE questions.id=" + id;

        Question question = new Question();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            question = getQuestion(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return question;

    };

    @Override
    public long save(Question question) {
        String query = "INSERT INTO questions (question, answer, test_id) VALUES ('";
        query += question.getQuestion()+"','"
                +question.getAnswer()+"',"
                +question.getTestId()+") RETURNING id" ;

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
    public void update(long id, Question question) {
        String query = "UPDATE questions SET question = '"+question.getQuestion()
                +"', test_id = "+question.getTestId()+", answer = '"+question.getAnswer()
                +"' WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    public void delete(long id) {
        String query = "DELETE FROM questions WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };

}








