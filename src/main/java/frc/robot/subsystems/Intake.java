// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final CANBus m_CANBus = new CANBus("rio");
  private TalonFX m_intake1 = new TalonFX(21, m_CANBus);
  private TalonFX m_intake2 = new TalonFX(22, m_CANBus);
  private double m_rollerMotorVoltage;
  private double m_MotorVoltage;
  private TalonFXConfiguration m_rollerMotorConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs m_currentconfig = new CurrentLimitsConfigs();

  /** Creates a new intake. */
  public Intake() {

    m_currentconfig.withSupplyCurrentLimit(80)
        .withSupplyCurrentLimitEnable(true)
        .withStatorCurrentLimit(80)
        .withStatorCurrentLimitEnable(true);
    m_rollerMotorConfig.CurrentLimits = m_currentconfig;
    m_intake1.getConfigurator().apply(m_rollerMotorConfig);
    m_intake2.getConfigurator().apply(m_rollerMotorConfig);

  }

  @Override
  public void periodic() {
    m_intake1.setVoltage(m_MotorVoltage);
    m_intake2.setVoltage(m_rollerMotorVoltage);

    // This method will be called once per scheduler run
  }

  public Command defaultCommand() {
    return run(() -> {
      m_rollerMotorVoltage = 0;
      m_MotorVoltage = 0;
    });
  }

  public Command intakeball() {
    return run(() -> {
      m_rollerMotorVoltage = 12;
      m_MotorVoltage = 12;
    });
  }

  public Command feed() {
    return run(() -> {
      m_rollerMotorVoltage = -12;
      m_MotorVoltage = 12;
    });
  }

}
