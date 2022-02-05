// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSpinnerSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_wheelMotor;

  private double m_spinPow = 0;

  /** Creates a new WheelSpinnerSubsystem. */
  public WheelSpinnerSubsystem(int wheelMotor) {
    m_wheelMotor = new WPI_TalonSRX(wheelMotor);
  }

  /**
   * Spins the wheel spinner at a specified power level.
   * @param spinPow Speed to spin the wheel. -1 is full backwards, 1 is full forwards.
   */
  public void spin(double spinPow) {
    m_spinPow = spinPow;
  }

  @Override
  public void periodic() {
    m_wheelMotor.set(m_spinPow);
    m_spinPow = 0;
  }
}
