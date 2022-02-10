// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  /**
   * Constants for CAN IDs
   */
  public static final class CAN {
    public static final int LEFT_MOTOR_1 = 1;
    public static final int LEFT_MOTOR_2 = 2;
    public static final int RIGHT_MOTOR_1 = 3;
    public static final int RIGHT_MOTOR_2 = 4;
    public static final int WINCH_MOTOR = 5;
    public static final int WHEEL_MOTOR = 6;
  }

  /**
   * Constants for controller ports
   */
  public static final class Controllers {
    public static final int XBOX = 0;
    public static final int FLIGHT = 1;
  }

  /**
   * Constants for I2C ports
   */
  public static final class I2C {
    public static final edu.wpi.first.wpilibj.I2C.Port COLOUR_SENSOR = edu.wpi.first.wpilibj.I2C.Port.kOnboard;
  }
  
  /**
   * Enum for colours detected by colour sensor
   */
  public static enum Colour {
    NONE,
    RED,
    BLUE,
    GREEN,
    YELLOW;
  }

  /**
   * Constants for thresholds where a colour is recognized
   */
  public static final class ColourThresholds {
    public static final double RED_THRESHOLD = 0.37;
    public static final double BLUE_THRESHOLD = 0.3;
    public static final double GREEN_THRESHOLD = 0.5;
    public static final double YELLOW_RED_THRESHOLD = 0.3;
  }

  /**
   * Constants for Pneumatics Control Module ports
   */
  public static final class Pneumatics {
    public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;
    public static final int HOPPER_FORWARD = 1;
    public static final int HOPPER_REVERSE = 2;
    public static final int FLAP_FORWARD = 7;
    public static final int FLAP_REVERSE = 6;
  }

  /**
   * Constants for RoboRIO analog pins
   */
  public static final class Analog {
    public static final int HOOK_POT = 0;
  }

  /**
   * Constants for RoboRIO digital pins
   */
  public static final class Digital {
    public static final int LEFT_ENCODER_1 = 0;
    public static final int LEFT_ENCODER_2 = 1;
    public static final int RIGHT_ENCODER_1 = 2;
    public static final int RIGHT_ENCODER_2 = 3;
  }

  /**
   * Constants related to robot driving
   */
  public static final class Drive {
    public final static double ENCODER_PULSES_PER_REVOLUTION = 360;
    public final static double WHEEL_DIAMETER = 6;
    public final static double DISTANCE_PER_ENCODER_PULSE = WHEEL_DIAMETER * Math.PI / ENCODER_PULSES_PER_REVOLUTION;
  }

  /**
   * Constants related to climbing hook
   */
  public final static class Hook {
    public final static double HOOK_POTENTIOMETER_MIN = 0;
    public final static double HOOK_POTENTIOMETER_MAX = 1;
    public final static double HOOK_POTENTIOMETER_RANGE = HOOK_POTENTIOMETER_MAX - HOOK_POTENTIOMETER_MIN;
  }
}
