// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Hook;

public class HookSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_winchMotor;
  private final AnalogPotentiometer m_potentiometer;

  private double m_pow = 0;
  private int m_priority = 0;

  /** Creates a new HookSubsystem. */
  public HookSubsystem(int winchMotor, int potentiometer) {
    m_winchMotor = new WPI_TalonSRX(winchMotor);
    m_potentiometer = new AnalogPotentiometer(
      potentiometer,
      Hook.HOOK_POTENTIOMETER_RANGE,
      Hook.HOOK_POTENTIOMETER_MIN
    );
  }

  /**
   * Extends the hook.
   * @param priority Priority for this action. Only the highest priority action is run each cycle.
   */
  public void extend(int priority) {
    if (priority >= m_priority) {
        m_pow = 1;
        priority = m_priority;
    }
  }

  /**
   * Retracts the hook.
   * @param priority Priority for this action. Only the highest priority action is run each cycle.
   */
  public void retract(int priority) {
      if (priority >= m_priority) {
          m_pow = -1;
          priority = m_priority;
      }
  }

  /**
   * Moves the hook by a given amount.
   * @param pow Power to give the hook motor. -1 is full-speed retract, 1 is full-speed extend.
   * @param priority Priority for this action. Only the highest priority action is run each cycle.
   */
  public void move(double pow, int priority) {
      if (priority >= m_priority) {
          m_pow = pow;
          priority = m_priority;
      }
  }

  /**
   * Gets the current height of the hook.
   * @return Height between 0 and 1. 0 is fully retracted, 1 is fully extended.
   */
  public double getHeight() {
      return m_potentiometer.get();
  }

  @Override
  public void periodic() {
    m_winchMotor.set(m_pow);
    m_pow = 0;
    m_priority = 0;
  }
}
