// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.Drivecommand;
import frc.robot.subsystems.RomiDrivetrain;

import java.sql.Driver;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Constants;
import frc.robot.commands.Auto;



public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  private final Auto AutoCommand = new Auto();
  private static final CommandJoystick m_driverController = new CommandJoystick(1); // Assumes port 0


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

  }


  private void configureButtonBindings() 
  {
    m_romiDrivetrain.setDefaultCommand(new Drivecommand(m_romiDrivetrain,m_driverController));
  }






// autonomous code down here

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
      return AutoCommand;
  }
}

