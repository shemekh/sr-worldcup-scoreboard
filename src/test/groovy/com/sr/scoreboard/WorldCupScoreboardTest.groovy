package com.sr.scoreboard

import spock.lang.Specification
import spock.lang.Subject

class WorldCupScoreboardTest extends Specification {

  @Subject
  Scoreboard scoreboard

  def setup() {
    scoreboard = new WorldCupScoreboard() // new instance before each testcase
  }

  def "should starting game be added to scoreboard with 0:0 score"() {
  }

  def "should throw exception when starting game is already on scoreboard"() {
  }

  def "should throw exception when starting game has at least 1 team that is already on board"() {
  }

  def "should properly update score of the game"() {
  }

  def "should throw exception when updated game does not exist on scoreboard"() {
  }

  def "should remove the game from scoreboard when finished the game"() {
  }

  def "should return games summary sorted by total score and last added as sort tiebreaker"() {
  }

}
