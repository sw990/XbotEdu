package competition.subsystems.drive.commands;



import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.math.PIDManager;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;


public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    PIDManager pid;
    public double goalPosition;
    public double prePosition;
   
    

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose, CommonLibFactory clf) {
        this.drive = driveSubsystem;
        this.pose = pose;
        this.pid = clf.createPIDManager("DriveToPoint");
        pid.setEnableErrorThreshold(true); // turn on distance checking
        pid.setErrorThreshold(0.1);
        pid.setEnableDerivativeThreshold(true); // turn on speed checking
        pid.setDerivativeThreshold(0.1);

        // manually adjust values
        pid.setP(2);
        pid.setD(3);
    }

    public void setTargetPosition(double position) {
        this.goalPosition = position;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
        
    }
    

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
        pid.reset();
    }

    @Override
    public void execute() {
         // - Gets the robot to move to the target position
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        //robot to goal 
        double goal = goalPosition - prePosition;
        //total distance
        double denominator = goalPosition;
        //sets power to slow down at target position
        // double power = (goal/denominator) * 2 - (pose.getPosition() - prePosition) * 3;
        double power = pid.calculate(goal, denominator);
        log.info(power);
        drive.tankDrive(power,power);
        
        prePosition = pose.getPosition();

        
        
       
        

       
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
    
        return pid.isOnTarget();
    }

}
