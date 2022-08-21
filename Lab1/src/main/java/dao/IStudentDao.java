package dao;

import entities.Student;

import java.util.List;

public interface IStudentDao {

    List<Student> findAll();
    Student findById(long id);
    long save(Student student);
    void update(long id, Student student);
    void delete(long id);
}
