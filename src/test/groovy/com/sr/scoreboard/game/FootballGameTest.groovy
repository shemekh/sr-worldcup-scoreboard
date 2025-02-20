package com.sr.scoreboard.game

import spock.lang.Specification

class FootballGameTest extends Specification {

  static HOME_TEAM = "Mexico"
  static AWAY_TEAM = "Canada"

  def "should properly create FootballGame object"() {
    when:
      def game = FootballGame.of(HOME_TEAM, AWAY_TEAM)
    then:
      game.homeTeam == HOME_TEAM
      game.awayTeam == AWAY_TEAM
      game.homeScore == 0
      game.awayScore == 0
      game.totalScore == 0
  }

  def "should properly update score"() {
    given:
      def game = FootballGame.of(HOME_TEAM, AWAY_TEAM)
    when:
      game.updateScore(3, 1)
    then:
      game.homeScore == 3
      game.awayScore == 1
      game.totalScore == 4
  }

  def "should properly update score multiple times"() {
    given:
      def game = FootballGame.of(HOME_TEAM, AWAY_TEAM)
    when:
      game.updateScore(3, 1)
      game.updateScore(3, 2)
      game.updateScore(3, 3)
      game.updateScore(4, 3)
    then:
      game.homeScore == 4
      game.awayScore == 3
      game.totalScore == 7
  }

  def "should throw exception while updating with negative score"() {
    given:
      def game = FootballGame.of(HOME_TEAM, AWAY_TEAM)
    when:
      game.updateScore(homeScore, awayScore)
    then:
      thrown(IllegalArgumentException)
    where:
      homeScore | awayScore
      -1        | 2
      3         | -5
      -3        | -2
  }
}
