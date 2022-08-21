package dao;

import entities.Question;

import java.util.List;

public interface IQuestionsDao {

    List<Question> findAll();
    Question findById(long id);
    long save(Question question);
    void update(long id, Question question);
    void delete(long id);

}
