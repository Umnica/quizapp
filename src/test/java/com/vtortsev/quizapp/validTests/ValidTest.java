package com.vtortsev.quizapp.validTests;


import com.vtortsev.quizapp.service.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidTest {
    @Test
    void testValidText() {
        assertTrue(Valid.isValidText("Valid, answer-1 text?"));
    }
    @Test
    void testInvalidTextWithSpecialCharacters() {
        assertFalse(Valid.isValidText("Invalid answer text @#!"));
    }
    @Test
    void  testInvalidTextWithTwoOrMorePunctuationMarksInRow(){
        assertFalse(Valid.isValidText("Invalid answ,,er text"));
        assertFalse(Valid.isValidText("Invalid answ,,,er text"));
    }

    @Test
    void testInvalidTextWithTwoHyphensInRow() {
        assertFalse(Valid.isValidText("Invalid answer-- text!"));
    }

    @Test
    void testInvalidTextIsEmpty() {
        assertFalse(Valid.isValidText(""));
    }

}
