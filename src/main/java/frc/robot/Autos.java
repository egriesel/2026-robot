package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;

public final class Autos {
    public static final String DEFAULT_AUTO_NAME = "LeftTrenchShootClimb";

    private Autos() {
    }

    public static Command ShootClimbAuto() {
        return new PathPlannerAuto(DEFAULT_AUTO_NAME);
    }
}
