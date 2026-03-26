package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;

public final class Autos {
    public static final Command ShootClimbAuto() {
        return new PathPlannerAuto("LeftTrenchShootClimb");
    }
}
