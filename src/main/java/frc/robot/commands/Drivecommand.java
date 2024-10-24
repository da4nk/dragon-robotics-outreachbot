// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;


/** An example command that uses an example subsystem. */
public class Drivecommand extends Command {
  private final RomiDrivetrain m_subsystem;

  static double SPEED = 0.8;
  static CommandJoystick m_driver_controller;
  static double DEADZONE = 0.01;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drivecommand(RomiDrivetrain subsystem, CommandJoystick controller ) {
    m_subsystem = subsystem;
    m_driver_controller = controller; 
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_subsystem.arcadeDrive(-m_driver_controller.getY() * SPEED, -m_driver_controller.getX() * SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
