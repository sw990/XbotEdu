package competition.subsystems.pose;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.properties.PropertyFactory;
import xbot.common.subsystems.pose.BasePoseSubsystem;

@Singleton
public class PoseSubsystem extends BasePoseSubsystem {

    private final DriveSubsystem drive;

    @Inject
    public PoseSubsystem(CommonLibFactory clf, PropertyFactory propManager, DriveSubsystem drive) {
        super(clf, propManager);
        this.drive = drive;
    }

    public double getPosition() {
        return (getLeftDriveDistance() + getRightDriveDistance()) / 2.0; 
    }

    @Override
    protected double getLeftDriveDistance() {
        return drive.frontLeft.getSelectedSensorPosition(0);
    }

    @Override
    protected double getRightDriveDistance() {
        return drive.frontRight.getSelectedSensorPosition(0);
    }

}