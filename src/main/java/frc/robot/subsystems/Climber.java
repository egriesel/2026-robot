package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Rotation;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private final TalonFX leftClimb;
    private final TalonFX rightClimb;
    private final PositionVoltage positionRequest = new PositionVoltage(0);

    boolean isCalibrated = false;

    public Climber() {
        leftClimb = new TalonFX(33);
        rightClimb = new TalonFX(30);

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kP = 0.1;
        var leftConfig = new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive);
        var rightConfig = new MotorOutputConfigs().withInverted(InvertedValue.CounterClockwise_Positive);
        leftClimb.getConfigurator().apply(config.withMotorOutput(leftConfig));
        rightClimb.getConfigurator().apply(config.withMotorOutput(rightConfig));

        leftClimb.setPosition(0);
        rightClimb.setPosition(0);

        setClimb(false);
    }

    @Override
    public void periodic() {
    }

    public void setClimb(boolean climb) {
        var position = positionRequest.withPosition(climb ? 29 : 0);
        leftClimb.setControl(position);
        rightClimb.setControl(position);
    }

    public Command climb() {
        return run(() -> setClimb(true));
    }

    public Command decline() {
        return run(() -> setClimb(false));
    }

    public Command calibrate() {
        return calibrate(leftClimb).alongWith(calibrate(rightClimb)).unless(() -> isCalibrated);
    }

    private Command calibrate(TalonFX motorToCalibrate) {
        return new Command() {
            int currentCount = 0;

            public void initialize() {
                // Initialize stuff here
                currentCount = 0;
            }

            public void execute() {
                // Set motor speed to

                // Get the current of the motor (probably getStatorCurrent?)
                double current = 0; // replace with actual current from motor

                System.out.println("Motor current: " + current);

                // Check if the current is above a threshold (probably 150, will need to check
                // via print)

                // If it is, add to count
                if (current > 150) {
                    currentCount++;
                } else {
                    // Clear the count since we aren't consecutive
                    currentCount = 0;
                }
            }

            public boolean isFinished() {
                // True if we've seen current spike over consecutive cycles, tune how many
                // cycles we need
                return currentCount >= 3;
            }

            public void end(boolean interrupted) {
                // Stop this motor

                // Reset the encoders
            }
        };
    }
}