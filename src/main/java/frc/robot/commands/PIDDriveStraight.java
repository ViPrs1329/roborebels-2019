package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class PIDDriveStraight extends Command {
    private static final double k_P = 0.0025;
    private static final double k_I = 0.0;
    private static final double k_D = 0.0;

    private PIDController left;
    private PIDController right;

    public PIDDriveStraight() {
        requires(Robot.drivetrain);
        left = new PIDController(k_P, k_I, k_D, Robot.drivetrain.getLeftEncoder(), Robot.drivetrain.getLeft());
        left.setOutputRange(0.0, 0.5);
        right = new PIDController(k_P, k_I, k_D, Robot.drivetrain.getRightEncoder(), Robot.drivetrain.getRight());
        right.setOutputRange(0.0, 0.5);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        left.setSetpoint(20.0);
        right.setSetpoint(20.0);
        left.enable();
        right.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Left error", left.getError());
        SmartDashboard.putNumber("Right error", right.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        left.disable();
        right.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        this.end();
    }
}