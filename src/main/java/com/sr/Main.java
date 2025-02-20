package com.sr;

import com.sr.scoreboard.Scoreboard;
import com.sr.scoreboard.WorldCupScoreboard;

public class Main {

  public static void main(String[] args) {
    final Scoreboard wc = new WorldCupScoreboard();
    wc.startGame("Mexico", "Canada");
    wc.updateScore("Mexico", "Canada", 0, 5);

    wc.startGame("Spain", "Brazil");
    wc.updateScore("Spain", "Brazil", 10, 2);

    wc.startGame("Germany", "France");
    wc.updateScore("Germany", "France", 2, 2);

    wc.startGame("Uruguay", "Italy");
    wc.updateScore("Uruguay", "Italy", 6, 6);

    wc.startGame("Argentina", "Australia");
    wc.updateScore("Argentina", "Australia", 3, 1);

    wc.getSummary().forEach(System.out::println);
  }
}
