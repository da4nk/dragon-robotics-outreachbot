// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// packages
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {  
  // variables

  private Command m_autonomousCommand;

  private DifferentialDrive m_robotDrive;

  
  private RobotContainer m_robotContainer;
  static double SPEEDMOD = 0.8;
  static Joystick controller = new Joystick(0);
  static double DEADZONE = 0.01;

  // functions for the robot _---------

  private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  private final PWMSparkMax m_leftMotorFollower = new PWMSparkMax(1);
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(2);
  private final PWMSparkMax m_rightMotorFollower = new PWMSparkMax(3);
  Timer timer = new Timer();

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_rightMotor.setInverted(true);

    m_rightMotorFollower.setInverted(true);
    m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    timer.reset();
    timer.start();

  }

  @Override
  public void autonomousPeriodic() {

    if(timer.get() < 2.0)
    {
      m_robotDrive.arcadeDrive(0, 0.5);
      m_leftMotorFollower.set(m_leftMotor.get());
      m_rightMotorFollower.set(m_rightMotor.get());
    }
    m_robotDrive.arcadeDrive(0, 0);
    m_leftMotorFollower.set(m_leftMotor.get());
    m_rightMotorFollower.set(m_rightMotor.get());
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
    m_leftMotorFollower.set(m_leftMotor.get());
    m_rightMotorFollower.set(m_rightMotor.get());


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