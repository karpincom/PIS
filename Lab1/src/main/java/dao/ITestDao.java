package dao;

import entities.Test;

import java.util.List;

public interface ITestDao {

    List<Test> findAll();
    Test findById(long id);
    long save(Test test);
    void update(long id, Test test);
    void delete(long id);
}
