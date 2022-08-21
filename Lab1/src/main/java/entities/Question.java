package entities;

import java.util.Objects;

public class Question {

    private long id;
    private String question;
    private String answer;
    private long testId;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", testId=" + testId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id == question1.id && testId == question1.testId && Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, testId);
    }

    public Question(String question, String answer, long testId) {
        this.question = question;
        this.answer = answer;
        this.testId = testId;
    }

    public Question() {
    }

    public Question(long id, String question, String answer, long testId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.testId = testId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }
}

