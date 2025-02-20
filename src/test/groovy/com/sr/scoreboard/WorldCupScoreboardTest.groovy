package com.sr.scoreboard

import com.sr.scoreboard.game.GameDto
import spock.lang.Specification
import spock.lang.Subject

class WorldCupScoreboardTest extends Specification {

  static HOME_TEAM = "Spain"
  static AWAY_TEAM = "Brazil"

  @Subject
  Scoreboard scoreboard

  def setup() {
    scoreboard = new WorldCupScoreboard() // new instance before each testcase
  }

  def "should starting game be added to scoreboard with 0:0 score"() {
    when:
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
    then:
      def gamesSummary = scoreboard.getSummary()
      gamesSummary.size() == 1
      with(gamesSummary[0]) {
        it.homeTeam() == HOME_TEAM
        it.awayTeam() == AWAY_TEAM
        it.homeScore() == 0
        it.awayScore() == 0
      }
  }

  def "should throw exception when starting game is already on scoreboard"() {
    given:
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
    when:
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
    then:
      thrown(IllegalStateException)
  }

  def "should throw exception when starting game has at least 1 team that is already on board"() {
    given:
      def otherTeam = "Italy"
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
    when:
      scoreboard.startGame(otherTeam, AWAY_TEAM)
    then:
      thrown(IllegalStateException)
  }

  def "should properly update score of the game"() {
    given:
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
    when:
      scoreboard.updateScore(HOME_TEAM, AWAY_TEAM, 2, 1)
    then:
      def gamesSummary = scoreboard.getSummary()
      gamesSummary.size() == 1
      with(gamesSummary[0]) {
        it.homeTeam() == HOME_TEAM
        it.awayTeam() == AWAY_TEAM
        it.homeScore() == 2
        it.awayScore() == 1
      }
  }

  def "should throw exception when updated game does not exist on scoreboard"() {
    when:
      scoreboard.updateScore(HOME_TEAM, AWAY_TEAM, 5, 2)
    then:
      thrown(IllegalStateException)
  }

  def "should remove the game from scoreboard when finished the game"() {
    given:
      scoreboard.startGame(HOME_TEAM, AWAY_TEAM)
      scoreboard.updateScore(HOME_TEAM, AWAY_TEAM, 2, 1)
    when:
      scoreboard.finishGame(HOME_TEAM, AWAY_TEAM)
    then:
      def gamesSummary = scoreboard.getSummary()
      gamesSummary.size() == 0
  }

  def "should return games summary sorted by total score and started at as sort tiebreaker"() {
    given:
      // expected on output index = 2
      scoreboard.startGame("Mexico", "Canada")
      scoreboard.updateScore("Mexico", "Canada", 0, 5)
      // expected on output index = 1
      scoreboard.startGame("Spain", "Brazil")
      scoreboard.updateScore("Spain", "Brazil", 10, 2)
      // expected on output index = 4
      scoreboard.startGame("Germany", "France")
      scoreboard.updateScore("Germany", "France", 2, 2)
      // expected on output index = 0
      scoreboard.startGame("Uruguay", "Italy")
      scoreboard.updateScore("Uruguay", "Italy", 6, 6)
      // expected on output index = 3
      scoreboard.startGame("Argentina", "Australia")
      scoreboard.updateScore("Argentina", "Australia", 3, 1)
    when:
      def summary = scoreboard.getSummary()
    then:
      summary.size() == 5
      summary.get(0) == new GameDto("Uruguay", "Italy", 6, 6)
      summary.get(1) == new GameDto("Spain", "Brazil", 10, 2)
      summary.get(2) == new GameDto("Mexico", "Canada", 0, 5)
      summary.get(3) == new GameDto("Argentina", "Australia", 3, 1)
      summary.get(4) == new GameDto("Germany", "France", 2, 2)
  }
}
