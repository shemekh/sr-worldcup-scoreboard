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
    if (homeScore < 0 || awayScore < 0) {
      throw new IllegalArgumentException("Score value should never be negative!");
    }
    this.homeScore = homeScore;
    this.awayScore = awayScore;
  }

  @Override
  public int getTotalScore() {
    return homeScore + awayScore;
  }

  @Override
  public GameDto toDto() {
    return new GameDto(homeTeam, awayTeam, homeScore, awayScore);
  }

  @Override
  public String toString() {
    return "%s %d - %d %s".formatted(homeTeam, homeScore, awayScore, awayTeam);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FootballGame that = (FootballGame) o;
    return homeTeam.equals(that.homeTeam) && awayTeam.equals(that.awayTeam);
  }

  @Override
  public int hashCode() {
    int result = homeTeam.hashCode();
    result = 31 * result + awayTeam.hashCode();
    return result;
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
