package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.operator_interface.OperatorInterface;
import competition.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
    DriveSubsystem drive;
    OperatorInterface operatorInterface;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oInterface) {
      drive = driveSubsystem;
      operatorInterface = oInterface;
      this.addRequirements(drive);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        // joysticks value
       double leftValueX = operatorInterface.gamepad.getLeftVector().x;
       double leftValueY = operatorInterface.gamepad.getLeftVector().y;
     // turn left (-1,0)
     if(leftValueX < 0 && leftValueY == 0){
       drive.arcadeDrive(-1, 1);
     }
     //turn right(1,0)
     if(leftValueX > 0 && leftValueY == 0){
       drive.arcadeDrive(1, -1);
     }
     //forward(0,1)
     if(leftValueX == 0 && leftValueY > 0){
       drive.arcadeDrive(1, 1);
     }
     //backwards(0,-1)
     if(leftValueX == 0 && leftValueY < 0){
       drive.arcadeDrive(-1, -1);
     }

     if(leftValueX + leftValueY == 0){
      drive.arcadeDrive(0, 0);
    }
      //continue here
    }

}
