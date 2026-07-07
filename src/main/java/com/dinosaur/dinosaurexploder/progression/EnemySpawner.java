/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the enemy spawner: it tracks which level it is spawning enemies for and
 * derives the enemy spawn interval from that level. It is kept in step with the game's current level
 * by {@code syncTo(int)}.
 */
public class EnemySpawner {
  private int level = 1;

  public void syncTo(int level) {
    this.level = level;
  }

  public int currentLevel() {
    return level;
  }

  /** Enemies spawn faster at higher levels, floored so the game stays playable. */
  public double spawnInterval() {
    return Math.max(0.3, 0.75 * Math.pow(0.9, level - 1));
  }
}
