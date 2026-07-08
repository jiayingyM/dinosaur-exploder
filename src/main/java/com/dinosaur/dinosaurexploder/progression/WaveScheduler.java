/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the wave batch that is currently being played. A batch is armed for a level
 * when it begins (see {@link #beginWave(int)}); from that point it reports that level and the number
 * of enemy waves that level runs. Unlike the level-dependent views that must always mirror the
 * game's current level, an in-progress batch deliberately keeps the level it was armed with for its
 * whole duration even if the game's level moves on underneath it (for example a boss-reward skip):
 * the wave the player is fighting does not change identity mid-fight. The batch is only re-armed when
 * the next batch begins.
 */
public class WaveScheduler {
  private int waveLevel = 1;

  /** Arm the scheduler for the wave batch that is now beginning at {@code level}. */
  public void beginWave(int level) {
    this.waveLevel = level;
  }

  /** The level of the wave batch currently in progress. */
  public int currentLevel() {
    return waveLevel;
  }

  /** Each level runs one more wave than the last. */
  public int waveCount() {
    return waveLevel;
  }
}
