package com.sr.scoreboard.game;

public record GameDto(String homeTeam, String awayTeam, int homeScore, int awayScore) {

  @Override
  public String toString() {
    return "%s %d - %d %s".formatted(homeTeam, homeScore, awayScore, awayTeam);
  }
}
