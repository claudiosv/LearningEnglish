package it.unibz.cspiess;

import java.io.Serializable;
import java.util.ArrayList;

public class Scoreboard implements Serializable {
    private String nickname;
    private int questionsShown, questionsCorrect, timeSpent;
    private ArrayList<Integer> wrongQuestionsIds;

    public Scoreboard(String nickname) {
        wrongQuestionsIds = new ArrayList<>();
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public int getQuestionsShown() {
        return questionsShown;
    }

    public void setQuestionsShown(int questionsShown) {
        this.questionsShown = questionsShown;
    }

    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    public void incrementQuestionsCorrect() {
        this.questionsCorrect++;
    }

    public void incrementQuestionsShown() {
        this.questionsShown++;
    }

    public int getQuestionsWrong() {
        return wrongQuestionsIds.size();
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getAverageTime() {
        if (questionsShown > 0)
            return timeSpent / questionsShown;
        else
            return 0;
    }

    public int getSuccessPercentage() {
        float percentage;
        if ((questionsCorrect + getQuestionsWrong()) > 0)
            percentage = (float) ((float) questionsCorrect / ((float) questionsCorrect + (float) getQuestionsWrong())) * 100;
        else
            percentage = 0;
        return Math.round(percentage);
    }

    public ArrayList<Integer> getWrongQuestionsIds() {
        return wrongQuestionsIds;
    }

    public void addWrongQuestionId(int id) {
        if (!this.wrongQuestionsIds.contains(id)) this.wrongQuestionsIds.add(id);
    }

    public void incrementTimeSpent(int seconds) {
        timeSpent += seconds;
    }
}