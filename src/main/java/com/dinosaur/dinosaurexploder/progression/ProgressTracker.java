/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.progression;

/**
 * Progression view of the run's high-water mark: it records the furthest level the player has reached
 * this session so the "best level" shown on the results / continue screen keeps climbing. Unlike the
 * level-dependent gameplay systems, it does not mirror the game's current level — it only ever moves
 * up — and it deliberately survives the start of a new game so a session best is not lost when a run
 * restarts. {@code record(int)} reports a level the player has reached.
 */
public class ProgressTracker {
  private int bestLevel = 1;

  /** Report a level the player has reached; the tracker keeps the highest seen. */
  public void record(int level) {
    if (level > bestLevel) {
      bestLevel = level;
    }
  }

  /** The furthest level reached this session. */
  public int bestLevel() {
    return bestLevel;
  }
}
