package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;



public class TurnLeft90DegreesCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    public double goal;
    public double goalPos;
    public double currentPos;
    public double prePos;
    public double power;
    public double upperDif;
    public double lowerDif;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
        
    }

    @Override
    public void initialize() {
         goalPos =  (pose.getCurrentHeading().getDegrees() + 90);
         if(goalPos > 180 ){
            goalPos =  (pose.getCurrentHeading().getDegrees() + 90) - 360 ;
         }else if(goalPos < -180){
             goalPos =(pose.getCurrentHeading().getDegrees() + 90) + 360;
         }
    }

    @Override
    public void execute() {
        
        currentPos = pose.getCurrentHeading().getDegrees();
        
        // calculate different approaches
        goal = goalPos - currentPos;
        upperDif =(goal + 360) - currentPos;
        lowerDif =(goal - 360) - currentPos;

        // Find the smallest approach
        double bestDifference = 9999;

        if(Math.abs(upperDif) < Math.abs(bestDifference)){
            bestDifference = upperDif;
        }
        if(Math.abs(lowerDif) < Math.abs(bestDifference)){
            bestDifference = lowerDif;
        }
        if(Math.abs(goal) <  Math.abs(bestDifference)){
            bestDifference = goal;
        }

        

        // set power based on shortest approach
        power = Math.abs(bestDifference) * .02  - (currentPos - prePos) * 0.3;

        // power = goal * (constant) - (currentPos-prePos) * differentconstant
        
       
        drive.tankDrive(-power, power);
        prePos = pose.getCurrentHeading().getDegrees();
        
        

    }

    public boolean isFinished(){
       if(pose.getCurrentHeading().getDegrees() == goalPos){
           return true;
       }
       return false;
    }

}
