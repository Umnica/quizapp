package com.vtortsev.quizapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "t_questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String level;
    private String questionText;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_question_category",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY) // Один вопрос имеет много ответов
    private List<Answer> answers = new ArrayList<>();

}
