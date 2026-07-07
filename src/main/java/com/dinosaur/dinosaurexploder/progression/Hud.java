/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the HUD: it tracks which level is shown to the player and derives the level
 * label from it. It is kept in step with the game's current level by {@code syncTo(int)}.
 */
public class Hud {
  private int level = 1;

  public void syncTo(int level) {
    this.level = level;
  }

  public int currentLevel() {
    return level;
  }

  /** The level text shown on the HUD. */
  public String levelLabel() {
    return "Level " + level;
  }
}
