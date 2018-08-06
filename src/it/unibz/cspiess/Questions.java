package it.unibz.cspiess;

import it.unibz.cspiess.exception.QuestionNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by claudio on 26/01/2017.
 */
public class Questions implements Serializable {

    private ArrayList<Question> questions;

    public Questions() {
        questions = new ArrayList<>();
    }

    public Question getRandomQuestion() throws Exception {
        if (questions.size() < 1) throw new Exception("Question not found");
        return questions.get(new Random().nextInt(questions.size()));
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Question addQuestion(Question question) {
        questions.add(question);
        return question;
    }

    public Question getQuestion(int id) throws QuestionNotFoundException {
        for (Question question : questions) {
            if (question.getIdentifier() == id) {
                return question;
            }
        }
        throw new QuestionNotFoundException("Question not found");
    }

    public ArrayList<Question> getIncorrectQuestions(Scoreboard scoreboard) {
        ArrayList<Question> incorrectQuestions = new ArrayList<>();
        for (int id : scoreboard.getWrongQuestionsIds()) {
            try {
                incorrectQuestions.add(getQuestion(id));
            } catch (Exception ex) {
            }
        }
        return incorrectQuestions;
    }

    public Question getIncorrectQuestion(Scoreboard scoreboard) throws QuestionNotFoundException {
        ArrayList<Question> incorrectQuestions = getIncorrectQuestions(scoreboard);
        if ((incorrectQuestions.size() < 1)) throw new QuestionNotFoundException("Incorrect question not found");
        return incorrectQuestions.get(new Random().nextInt(incorrectQuestions.size()));
    }
}
