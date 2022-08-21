package entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Test {
    private long id;
    private String name;
    private Long timeLimit;

    public Test() {
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeLimit=" + timeLimit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && Objects.equals(name, test.name) && Objects.equals(timeLimit, test.timeLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, timeLimit);
    }

    public Test(String name, Long timeLimit) {
        this.name = name;
        this.timeLimit = timeLimit;
    }

    public Test(long id, String name, Long timeLimit) {
        this.id = id;
        this.name = name;
        this.timeLimit = timeLimit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }
}