/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.chargerrobotics.commands.autonomous;

import com.chargerrobotics.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BackupCommand extends CommandBase {
  /**
   * Creates a new BackupCommand.
   */
  private final DriveSubsystem driveSubsystem;
  private final long maxDriveMs;
  private long startMs;
  private boolean isDone;

  public BackupCommand(DriveSubsystem driveSubsystem, long maxDriveMs) {
    this.driveSubsystem = driveSubsystem;
    this.maxDriveMs = maxDriveMs;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startMs = System.currentTimeMillis();
    isDone = false;
    driveSubsystem.setThrottle(-0.4, -0.4);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    long time = System.currentTimeMillis();
    if (time > startMs + maxDriveMs)
      isDone = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.setThrottle(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}
