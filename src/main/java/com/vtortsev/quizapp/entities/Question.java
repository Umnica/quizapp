package com.vtortsev.quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String level;
    private String questionText;
    private String rightAnswer;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
}
