package com.sr.scoreboard;

import com.sr.scoreboard.game.GameDto;
import java.util.List;

public class WorldCupScoreboard implements Scoreboard {

  public WorldCupScoreboard() {
  }

  @Override
  public void startGame(String homeTeam, String awayTeam) {
  }

  @Override
  public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
  }

  @Override
  public void finishGame(String homeTeam, String awayTeam) {
  }

  @Override
  public List<GameDto> getSummary() {
    return null;
  }
}
