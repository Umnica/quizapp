package com.vtortsev.quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "t_answers")
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String answerText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;
}
