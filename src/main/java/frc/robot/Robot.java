// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

  private DifferentialDrive m_robotDrive;
  private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  
  private RobotContainer m_robotContainer;
  static double SPEEDMOD = 0.8;
  static Joystick controller = new Joystick(0);
  static double DEADZONE = 0.01;

  // functions for the robot _---------

  private final PWMSparkMax m_leftmotor = new PWMSparkMax(0);
  private final PWMSparkMax m_leftmotorfollower = new PWMSparkMax(1);
  private final PWMSparkMax m_rightmotor = new PWMSparkMax(2);
  private final PWMSparkMax m_rightmotorfollower = new PWMSparkMax(3);


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_rightMotor.setInverted(true);

    m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
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


    // Arcade drive with a given forward and turn rate
    
    m_robotDrive.arcadeDrive(-controller.getY(), -controller.getX());


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