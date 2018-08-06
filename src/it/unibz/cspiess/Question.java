package it.unibz.cspiess;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private int identifier;
    private String questionSentence;
    private String questionWord;
    private String correctMeaning;
    private int incorrectCount;
    private int correctCount;
    private ArrayList<String> wordMeanings;

    public Question() {
        wordMeanings = new ArrayList<>();
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void setIncorrectCount(int incorrectCount) {
        this.incorrectCount = incorrectCount;
    }

    public ArrayList<String> getWordMeanings() {
        return wordMeanings;
    }

    public void addMeaning(String meaning) {
        this.wordMeanings.add(meaning);
    }

    public String getQuestionSentence() {
        return questionSentence;
    }

    public void setQuestionSentence(String questionSentence) {
        this.questionSentence = questionSentence;
    }

    public String getQuestionWord() {
        return questionWord;
    }

    public void setQuestionWord(String questionWord) {
        this.questionWord = questionWord;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getCorrectMeaning() {
        return correctMeaning;
    }

    public void setCorrectMeaning(String correctMeaning) {
        this.correctMeaning = correctMeaning;
    }

    public void incrementIncorrectCount() {
        this.incorrectCount++;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public void incrementCorrectCount() {
        this.correctCount++;
    }

    public int getErrorPercentage() {
        float percentage;
        if ((correctCount + incorrectCount) > 0)
            percentage = (float) ((float) incorrectCount / ((float) incorrectCount + (float) correctCount)) * 100;
        else
            percentage = 0;
        return Math.round(percentage);
    }
}