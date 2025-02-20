package com.sr.scoreboard.game;

public class FootballGame implements Game {

  private final String homeTeam;
  private final String awayTeam;
  private int homeScore;
  private int awayScore;

  public FootballGame(String homeTeam, String awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = 0;
    this.awayScore = 0;
  }

  public static FootballGame of(String homeTeam, String awayTeam) {
    return new FootballGame(homeTeam, awayTeam);
  }

  @Override
  public void updateScore(int homeScore, int awayScore) {
  }

  @Override
  public int getTotalScore() {
    return 0;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public int getHomeScore() {
    return homeScore;
  }

  public int getAwayScore() {
    return awayScore;
  }
}
