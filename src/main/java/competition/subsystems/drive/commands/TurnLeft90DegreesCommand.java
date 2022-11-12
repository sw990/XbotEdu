package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;



public class TurnLeft90DegreesCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    public double goalPos;
    public double currentPos;
    public double prePos;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
        
    }

    @Override
    public void initialize() {
         goalPos =  pose.getCurrentHeading().getDegrees() + 90;
    }

    @Override
    public void execute() {
        
        double currentPos = pose.getCurrentHeading().getDegrees();
        double goal = goalPos - currentPos;
        double denominator = goalPos;
        double power = (goal/denominator)*10 - (currentPos - prePos );
        drive.tankDrive(-power, power);
        prePos = pose.getCurrentHeading().getDegrees();
        
        // continue here : verify command reports successfully finished
        

    }

}
