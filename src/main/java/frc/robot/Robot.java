// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

// rev libraries
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {
  // variables

  private Command m_autonomousCommand;

  CANSparkMax top_left_motor = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax top_right_motor = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax bottom_left_motor = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax bottom_right_motor = new CANSparkMax(4, MotorType.kBrushless);
  private RobotContainer m_robotContainer;
  static double SPEEDMOD = 0.8;
  static XboxController controller = new XboxController(0);
  static double DEADZONE = 0.01;

  // functions for the robot _---------

  public void rightside_move(double speed) {
    top_right_motor.set(speed);
    bottom_right_motor.set(speed);

  }

  public void leftside_move(double speed) {
    top_left_motor.set(speed);
    bottom_left_motor.set(speed);
  }

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    double turn = controller.getRawAxis(4);
    double throttle = controller.getRawAxis(3) - controller.getRawAxis(2);
    if (turn > DEADZONE * -1 && turn < DEADZONE) {
      turn = 0;
    }
    rightside_move(turn - throttle);
    leftside_move(turn + throttle);

  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void disabledExit() {

  }

}