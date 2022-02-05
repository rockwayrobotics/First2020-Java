// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colour;
import frc.robot.Constants.ColourThresholds;

public class ColourSensorSubsystem extends SubsystemBase {
  private final ColorSensorV3 m_colourSensor;

  private boolean m_active = false;
  private Colour m_currentColour = Colour.NONE;
  private Colour m_startingColour = Colour.NONE;
  private int m_rotations = 0;
  private boolean m_onStartingColour = false;

  /** Creates a new ColourSensorSubsystem. */
  public ColourSensorSubsystem(I2C.Port sensorPort) {
    m_colourSensor = new ColorSensorV3(sensorPort);
  }

  /**
   * Processes input from the colour sensor, updating the current colour and number of wheel rotations.
   * Does nothing if subsystem is inactive.
   */
  public void update() {
    if (!m_active) {
      return;
    }

    Color colour = m_colourSensor.getColor();
    if (colour.red > ColourThresholds.RED_THRESHOLD) {
      m_currentColour = Colour.RED;
    } else if (colour.blue > ColourThresholds.BLUE_THRESHOLD) {
      m_currentColour = Colour.BLUE;
    } else if (colour.green > ColourThresholds.GREEN_THRESHOLD) {
      if (colour.red > ColourThresholds.YELLOW_RED_THRESHOLD) {
        m_currentColour = Colour.YELLOW;
      } else {
        m_currentColour = Colour.GREEN;
      }
    } else {
      m_currentColour = Colour.NONE;
    }

    if (m_startingColour == Colour.NONE && m_currentColour != Colour.NONE) {
      m_startingColour = m_currentColour;
      m_onStartingColour = true;
    } else if (m_startingColour == m_currentColour && !m_onStartingColour) {
      m_rotations++;
      m_onStartingColour = true;
    } else {
      m_onStartingColour = false;
    }
  }

  /**
   * Sets whether the subsystem should actively process colour data.
   * @param state true to activate the subsystem, false to deactivate.
   */
  public void setActive(boolean state) {
    m_active = state;
  }

  /**
   * Returns whether the subsystem should actively process colour data.
   * @return true if active, false if inactive.
   */
  public boolean isActive() {
    return m_active;
  }

  /**
   * Returns the number of detected rotations of the colour wheel, based on colour sensor data.
   * @return int representing detected full rotations of the colour wheel.
   */
  public int getRotations() {
    return m_rotations / 2;
  }

}
