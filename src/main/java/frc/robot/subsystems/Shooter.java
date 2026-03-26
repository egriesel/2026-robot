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

public class Shooter extends SubsystemBase {
  private final CANBus m_CANBus = new CANBus("rio");
  private TalonFX m_shoot = new TalonFX(31, m_CANBus);
  private double m_MotorVoltage;
  private TalonFXConfiguration m_rollerMotorConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs m_currentconfig = new CurrentLimitsConfigs();

  /** Creates a new intake. */
  public Shooter() {
    m_currentconfig.withSupplyCurrentLimit(80)
        .withSupplyCurrentLimitEnable(true)
        .withStatorCurrentLimit(80)
        .withStatorCurrentLimitEnable(true);
    m_rollerMotorConfig.CurrentLimits = m_currentconfig;
    m_shoot.getConfigurator().apply(m_rollerMotorConfig);
  }

  @Override
  public void periodic() {
    m_shoot.setVoltage(m_MotorVoltage);
    // This method will be called once per scheduler run
  }

  public Command shoot() {
    return runEnd(() -> m_MotorVoltage = -4,
        () -> m_MotorVoltage = 0);
  }
}
