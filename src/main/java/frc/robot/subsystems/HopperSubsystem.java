// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Pneumatics;

public class HopperSubsystem extends SubsystemBase {
  private final DoubleSolenoid m_hopper;
  private final DoubleSolenoid m_flap;

  private boolean m_dumped = false;

  /** Creates a new HopperSubsystem. */
  public HopperSubsystem(
    int hopperForward, int hopperReverse,
    int flapForward, int flapReverse
  ) {
    m_hopper = new DoubleSolenoid(Pneumatics.PNEUMATICS_MODULE_TYPE, hopperForward, hopperReverse);
    m_flap = new DoubleSolenoid(Pneumatics.PNEUMATICS_MODULE_TYPE, flapForward, flapReverse);
  }

  /**
   * Moves the hopper to the loaded position and retracts the flap.
   * Use halfLoad() instead to only move the hopper without moving the flap.
   */
  public void load() {
    this.flapIn();
    m_hopper.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Moves the hopper to the loaded position, without retracting the flap.
   * Use load() instead to load the hopper and retract the flap.
   */
  public void halfLoad() {
    m_hopper.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Moves the hopper to the dumped position and releases the flap.
   * Use halfDump() instead to only move the hopper without moving the flap.
   */
  public void dump() {
    this.flapOut();
    m_hopper.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Moves the hopper to the dumped position, without releasing the flap.
   * Use dump() instead to dump the hopper and release the flap.
   */
  public void halfDump() {
    m_hopper.set(DoubleSolenoid.Value.kReverse);
  }

  /** Toggles the loaded/dumped state of the hopper. */
  public void toggle() {
    if (m_dumped) {
        this.load();
    } else {
        this.dump();
    }
    m_dumped = !m_dumped;
  }

  /** Turns off the hopper and flap solenoids. */
  public void off() {
    m_hopper.set(DoubleSolenoid.Value.kOff);
    m_flap.set(DoubleSolenoid.Value.kOff);
  }

  /** Retracts the flap on the end of the hopper. */
  public void flapIn() {
    m_flap.set(DoubleSolenoid.Value.kReverse);
  }

  /** Releases the flap on the end of the hopper. */
  public void flapOut() {
    m_flap.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Gets the state of the hopper.
   * @return kForward if loaded, kReverse if dumped, kOff if off.
   */
  public DoubleSolenoid.Value getState() {
    return m_hopper.get();
  }
}
