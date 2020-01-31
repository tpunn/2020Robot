/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.chargerrobotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.chargerrobotics.commands.ExampleCommand;
import com.chargerrobotics.commands.shooter.ShooterOffCommand;
import com.chargerrobotics.commands.shooter.ShooterOnCommand;
import com.chargerrobotics.commands.colorspinner.ColorSpinnerCommand;
import com.chargerrobotics.commands.drive.BrakeCommand;
import com.chargerrobotics.subsystems.DriveSubsystem;
import com.chargerrobotics.subsystems.ShooterSubsystem;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //subsystems
  private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
  private final DriveSubsystem driveSubsystem = DriveSubsystem.getInstance();

  //commands
  private final ShooterOnCommand shooterOnCommand = new ShooterOnCommand(shooterSubsystem);
  private final ShooterOffCommand shooterOffCommand = new ShooterOffCommand(shooterSubsystem);
  private final BrakeCommand brakeCommand = new BrakeCommand(driveSubsystem);

  private final ColorSpinnerCommand colorSpinnerCommand = new ColorSpinnerCommand();

  //controllers
  public final static XboxController primary = new XboxController(Constants.primary);
  public final static XboxController secondary = new XboxController(Constants.secondary);

  public static XboxController getPrimary() {
    return primary;
  }

  public static XboxController getSecondary() {
    return secondary;
  }

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    setupDashboardValues();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //primary
    primary.buttonB.whileHeld(brakeCommand);

    com.chargerrobotics.utils.XboxController secondary = new com.chargerrobotics.utils.XboxController(Constants.secondary);
    
  }

  public static final double kP = 5e-5;
  public static final double kI = 1e-6;
  public static final double kD = 0;
  public static final double kF = 0;
  public static final double kRpmSetpoint = 1000;

  private void setupDashboardValues() {

    SmartDashboard.putNumber("GainP", kP);
    SmartDashboard.putNumber("GainI", kI);
    SmartDashboard.putNumber("GainD", kD);
    SmartDashboard.putNumber("GainF", kF);
    SmartDashboard.putNumber("RpmSetpoint", kRpmSetpoint);

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}