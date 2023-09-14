package com.vtortsev.quizapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "t_answers")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "question"})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String answerText;


    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;


}
