package it.unibz.cspiess;

import it.unibz.cspiess.exception.UserNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by claudio on 18/01/2017.
 */
public class Scoreboards implements Serializable {

    private ArrayList<Scoreboard> scoreboards;

    public Scoreboards() {
        scoreboards = new ArrayList<>();
    }

    public Scoreboard getScoreboardByNickname(String nickname) throws UserNotFoundException {
        for (Scoreboard scoreboard : scoreboards) {
            if (scoreboard.getNickname().equals(nickname)) return scoreboard;
        }

        throw new UserNotFoundException();
    }

    public Scoreboard addUser(String nickname) {
        Scoreboard newUser = new Scoreboard(nickname);
        scoreboards.add(newUser);
        return newUser;
    }

    public void addScoreboard(Scoreboard scoreboard) {
        this.scoreboards.add(scoreboard);
    }

    public ArrayList<Scoreboard> getScoreboards() {
        return scoreboards;
    }

    public boolean scoreboardExists(String nickname) {
        for (Scoreboard scoreboard : scoreboards) {
            if (scoreboard.getNickname().equals(nickname)) return true;
        }
        return false;
    }

    public Scoreboard getWorstScoreboard() {
        Collections.sort(scoreboards,
                (o1, o2) -> Integer.compare(o2.getQuestionsWrong(), o1.getQuestionsWrong()));
        return scoreboards.get(0);
    }

    public Scoreboard getBestScoreboard() {
        Collections.sort(scoreboards,
                (o2, o1) -> Integer.compare(o2.getQuestionsWrong(), o1.getQuestionsWrong()));
        return scoreboards.get(0);
    }
}
