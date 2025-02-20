package com.sr.scoreboard.game;

public interface Game {

  void updateScore(int homeScore, int awayScore);

  int getTotalScore();
}
