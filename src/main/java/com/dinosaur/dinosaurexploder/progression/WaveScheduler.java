/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the wave scheduler: it tracks which level it is scheduling waves for and
 * derives how many enemy waves that level runs. It is kept in step with the game's current level by
 * {@code syncTo(int)}.
 */
public class WaveScheduler {
  private int level = 1;

  public void syncTo(int level) {
    this.level = level;
  }

  public int currentLevel() {
    return level;
  }

  /** Each level runs one more wave than the last. */
  public int waveCount() {
    return level;
  }
}
