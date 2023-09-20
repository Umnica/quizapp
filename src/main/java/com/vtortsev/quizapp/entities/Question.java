package com.vtortsev.quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity(name = "t_questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String level;
    private String questionText;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_question_category",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER) // Один вопрос имеет много ответов
    private List<Answer> answers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId()) &&
                Objects.equals(getLevel(), question.getLevel()) &&
                Objects.equals(getQuestionText(), question.getQuestionText()) &&
                Objects.equals(getCategories(), question.getCategories()) &&
                Objects.equals(getAnswers(), question.getAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLevel(), getQuestionText(), getCategories(), getAnswers());
    }


}
