/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the boss spawner: it tracks which level it is scheduling bosses for and
 * decides, from that level, whether the current level is a boss level. It is kept in step with the
 * game's current level by {@code syncTo(int)}.
 */
public class BossSpawner {
  private int level = 1;

  public void syncTo(int level) {
    this.level = level;
  }

  public int currentLevel() {
    return level;
  }

  /** Every fifth level is a boss level. */
  public boolean isBossLevel() {
    return level % 5 == 0;
  }
}
