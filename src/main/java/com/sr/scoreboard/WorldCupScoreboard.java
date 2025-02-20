package com.sr.scoreboard;

import com.sr.scoreboard.game.FootballGame;
import com.sr.scoreboard.game.Game;
import com.sr.scoreboard.game.GameDto;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedMap;
import java.util.Set;

public class WorldCupScoreboard implements Scoreboard {

  private final SequencedMap<String, Game> games;
  private final Set<String> teams;

  public WorldCupScoreboard() {
    this.games = new LinkedHashMap<>();
    this.teams = new HashSet<>();
  }

  @Override
  public void startGame(String homeTeam, String awayTeam) {
    registerTeam(homeTeam);
    registerTeam(awayTeam);

    String gameKey = getGameKey(homeTeam, awayTeam);
    validateGameIsUnique(gameKey);

    games.putFirst(gameKey, FootballGame.of(homeTeam, awayTeam));
  }

  @Override
  public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    final String gameKey = getGameKey(homeTeam, awayTeam);
    validateGameExists(gameKey);
    final Game game = games.get(gameKey);
    game.updateScore(homeScore, awayScore);
  }

  @Override
  public void finishGame(String homeTeam, String awayTeam) {
    String gameKey = getGameKey(homeTeam, awayTeam);
    games.remove(gameKey);
    teams.remove(homeTeam.toLowerCase());
    teams.remove(awayTeam.toLowerCase());
  }

  @Override
  public List<GameDto> getSummary() {
    return games.values().stream()
        .sorted(Comparator.comparingInt(Game::getTotalScore)
            .reversed())
        .map(Game::toDto)
        .toList();
  }

  private void registerTeam(String teamName) {
    validateTeamName(teamName);
    validateTeamIsUnique(teamName);
    teams.add(teamName.toLowerCase());
  }

  private void validateTeamName(String teamName) {
    if (teamName == null || teamName.isBlank()) {
      throw new IllegalArgumentException("Team name cannot be null, nor blank!");
    }
  }

  private void validateTeamIsUnique(String teamName) {
    if (teams.contains(teamName.toLowerCase())) {
      throw new IllegalStateException("Team %s is already playing a game.".formatted(teamName));
    }
  }

  private void validateGameIsUnique(String gameKey) {
    if (games.containsKey(gameKey)) {
      throw new IllegalStateException(
          "Game %s already exist in the scoreboard!".formatted(gameKey));
    }
  }

  private void validateGameExists(String gameKey) {
    if (!games.containsKey(gameKey)) {
      throw new IllegalStateException("Cannot update score, because game does not exist!");
    }
  }

  private String getGameKey(String homeTeam, String awayTeam) {
    return homeTeam + "-" + awayTeam;
  }
}
