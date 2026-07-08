/*
 * SPDX-FileCopyrightText: 2026 jvondermarck
 * SPDX-License-Identifier: MIT
 */

package com.dinosaur.dinosaurexploder.utils;

import com.dinosaur.dinosaurexploder.constants.GameMode;
import com.dinosaur.dinosaurexploder.progression.BossSpawner;
import com.dinosaur.dinosaurexploder.progression.EnemySpawner;
import com.dinosaur.dinosaurexploder.progression.Hud;
import com.dinosaur.dinosaurexploder.progression.ProgressTracker;
import com.dinosaur.dinosaurexploder.progression.WaveScheduler;

/**
 * This class manages the game levels, including the current level, number of enemies to defeat,
 * enemy spawn rate, and enemy speed. It is the source of truth for the current level and keeps the
 * level-dependent gameplay systems (enemy spawner, boss spawner, wave scheduler, HUD) in step as the
 * level advances and when a new game starts.
 */
public class LevelManager {
  private int currentLevel = 1;
  private int enemiesToDefeat = 5;
  private int defeatedEnemies = 0;
  private int bossesToDefeat = 1;
  private int defeatedBosses = 0;
  private double enemySpawnRate = 0.75;
  private double enemySpeed = 1.5;
  private double asteroidsSpawnRate = 1.5;
  private double asteroidsVerticalSpeed = 0.8;
  private double asteroidsHorizontalSpeed = 0.2;
  private GameMode gameMode = GameMode.NORMAL;

  private final EnemySpawner enemySpawner;
  private final BossSpawner bossSpawner;
  private final WaveScheduler waveScheduler;
  private final Hud hud;
  private final ProgressTracker progressTracker;

  public LevelManager() {
    enemySpawner = new EnemySpawner();
    bossSpawner = new BossSpawner();
    waveScheduler = new WaveScheduler();
    hud = new Hud();
    progressTracker = new ProgressTracker();
  }

  public EnemySpawner getEnemySpawner() {
    return enemySpawner;
  }

  public BossSpawner getBossSpawner() {
    return bossSpawner;
  }

  public WaveScheduler getWaveScheduler() {
    return waveScheduler;
  }

  public Hud getHud() {
    return hud;
  }

  public ProgressTracker getProgressTracker() {
    return progressTracker;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public double getEnemySpawnRate() {
    return enemySpawnRate;
  }

  public double getEnemySpeed() {
    return enemySpeed;
  }

  public float getLevelProgress() {
    return Math.max(
        (float) defeatedEnemies / enemiesToDefeat, (float) defeatedBosses / bossesToDefeat);
  }

  public void incrementDefeatedEnemies() {
    defeatedEnemies++;
  }

  public void incrementDefeatedBosses() {
    defeatedBosses++;
  }

  public boolean shouldAdvanceLevel() {
    return defeatedEnemies >= enemiesToDefeat || defeatedBosses >= bossesToDefeat;
  }

  public double getAsteroidsVerticalSpeed() {
    return asteroidsVerticalSpeed;
  }

  public double getAsteroidsHorizontalSpeed() {
    return asteroidsHorizontalSpeed;
  }

  public double getAsteroidsSpawnRate() {
    return asteroidsSpawnRate;
  }

  private void scaleDifficulty() {
    currentLevel++;
    defeatedEnemies = 0;
    enemiesToDefeat += 5;
    defeatedBosses = 0;

    enemySpawnRate = Math.max(0.3, enemySpawnRate * 0.9);
    enemySpeed += 0.2;
    asteroidsSpawnRate += 0.1;
  }

  public void nextLevel() {
    scaleDifficulty();
    enemySpawner.syncTo(currentLevel);
    bossSpawner.syncTo(currentLevel);
    waveScheduler.syncTo(currentLevel);
    hud.syncTo(currentLevel);
    progressTracker.record(currentLevel);
  }

  /**
   * Advances the level as a reward for defeating a boss. The player skips straight to the next level
   * with its enemy target cleared.
   */
  public void applyBossReward() {
    scaleDifficulty();
    enemySpawner.syncTo(currentLevel);
    bossSpawner.syncTo(currentLevel);
    progressTracker.record(currentLevel);
  }

  /**
   * Restores the manager so a newly started game begins from a clean level-one state, discarding any
   * progression accumulated during a previous game. The configured game mode and boss requirement
   * are left untouched, as the caller re-applies those for the new game.
   */
  public void reset() {
    currentLevel = 1;
    enemiesToDefeat = 5;
    defeatedEnemies = 0;
    defeatedBosses = 0;
    enemySpawnRate = 0.75;
    enemySpeed = 1.5;
    asteroidsSpawnRate = 1.5;
  }

  public int getEnemiesToDefeat() {
    return enemiesToDefeat;
  }

  public void setBossesToDefeat(int bossesToDefeat) {
    this.bossesToDefeat = bossesToDefeat;
  }

  public int getBossesToDefeat() {
    return bossesToDefeat;
  }

  public void setGameMode(GameMode mode) {
    gameMode = mode;
  }

  public GameMode getGameMode() {
    return gameMode;
  }
}
