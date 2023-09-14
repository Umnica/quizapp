package com.vtortsev.quizapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "t_questions")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "id"}) // Игнорируем ненужные поля
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String level;
    private String questionText;
}
