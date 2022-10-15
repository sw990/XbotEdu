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

        // leftjoysticks value
        double leftValueX = operatorInterface.gamepad.getLeftVector().x;
        double leftValueY = operatorInterface.gamepad.getLeftVector().y;

        drive.tankDrive(leftValueX, leftValueY);
       //continue here
    
    }

}
