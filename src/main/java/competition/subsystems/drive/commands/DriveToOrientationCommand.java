package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

public class DriveToOrientationCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    public double goalPos;
    public double prePos;

    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }

    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.
        this.goalPos = heading;
        
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.

    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:

        // - Gets the robot to turn to the target orientation

        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!

        double currentPos = pose.getCurrentHeading().getDegrees();
       
        double goal = (goalPos - currentPos) + 360;
       
        double denominator = goalPos;
        double power = (goal/denominator)  - (currentPos - prePos);
        drive.tankDrive(-power, power);
       
        
        
        // reversed the issue, now turning to postive goals don't work

        prePos = pose.getCurrentHeading().getDegrees();
        
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        if(pose.getCurrentHeading().getDegrees() < goalPos){
            return true;
        }
        return false;
    }
}
