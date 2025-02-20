package com.sr.scoreboard;

import com.sr.scoreboard.game.Game;
import java.util.List;

public interface Scoreboard {

  void startGame(String homeTeam, String awayTeam);

  void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

  void finishGame(String homeTeam, String awayTeam);

  List<Game> getSummary();
}
