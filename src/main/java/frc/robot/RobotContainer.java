// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Analog;
import frc.robot.Constants.CAN;
import frc.robot.Constants.Controllers;
import frc.robot.Constants.Digital;
import frc.robot.Constants.Pneumatics;
import frc.robot.subsystems.DrivebaseSubsystem;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.WheelSpinnerSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private DrivebaseSubsystem m_drivebase = new DrivebaseSubsystem(
    CAN.LEFT_MOTOR_1, CAN.LEFT_MOTOR_2,
    CAN.RIGHT_MOTOR_1, CAN.RIGHT_MOTOR_2,
    Digital.LEFT_ENCODER_1, Digital.LEFT_ENCODER_2,
    Digital.RIGHT_ENCODER_1, Digital.RIGHT_ENCODER_2
  );

  private HookSubsystem m_hook = new HookSubsystem(
    CAN.WINCH_MOTOR, Analog.HOOK_POT
  );

  private HopperSubsystem m_hopper = new HopperSubsystem(
    Pneumatics.HOPPER_FORWARD, Pneumatics.HOPPER_REVERSE,
    Pneumatics.FLAP_FORWARD, Pneumatics.FLAP_REVERSE
  );

  private WheelSpinnerSubsystem m_wheelSpinner = new WheelSpinnerSubsystem(CAN.WHEEL_MOTOR);

  private XboxController m_xboxController = new XboxController(Controllers.XBOX);
  private Joystick m_flightStick = new Joystick(Controllers.FLIGHT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_drivebase.setDefaultCommand(
      new RunCommand(
        () -> m_drivebase.set(-m_xboxController.getLeftY(), m_xboxController.getLeftX(), 0),
        m_drivebase
      )
    );

    // halve drivebase speed while left bumper is held
    new JoystickButton(m_xboxController, Button.kLeftBumper.value)
      .whenPressed(() -> m_drivebase.setScale(0.5))
      .whenReleased(() -> m_drivebase.setScale(1));
    
    new JoystickButton(m_xboxController, Button.kRightBumper.value)
      .whenPressed(new InstantCommand(() -> m_wheelSpinner.spin(0.5), m_wheelSpinner))
      .whenReleased(new InstantCommand(() -> m_wheelSpinner.spin(0), m_wheelSpinner));
    

    m_hook.setDefaultCommand(
      new RunCommand(() -> m_hook.move(m_flightStick.getY(), 0), m_hook)
    );

    new JoystickButton(m_flightStick, 1)
      .whenPressed(new InstantCommand(() -> m_hopper.toggle(), m_hopper));
    
    new JoystickButton(m_flightStick, 2)
      .whenPressed(new InstantCommand(() -> m_hopper.flapOut(), m_hopper))
      .whenReleased(new InstantCommand(() -> m_hopper.flapIn(), m_hopper));
    
    new JoystickButton(m_flightStick, 3)
      .whenPressed(new InstantCommand(() -> m_hopper.load(), m_hopper));
    
    new JoystickButton(m_flightStick, 4)
      .whenPressed(new InstantCommand(() -> m_hopper.dump(), m_hopper));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
