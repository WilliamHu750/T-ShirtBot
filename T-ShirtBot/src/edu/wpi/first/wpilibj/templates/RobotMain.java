/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    GearTrain frontDrive;
    GearTrain midDrive;
    GearTrain backDrive;
    Shooter shooter;
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	frontDrive = new GearTrain(HWR.FRONT_LEFT_MOTOR, HWR.FRONT_RIGHT_MOTOR);
    	midDrive = new GearTrain(HWR.MID_LEFT_MOTOR, HWR.MID_RIGHT_MOTOR);
    	backDrive = new GearTrain(HWR.BACK_LEFT_MOTOR, HWR.BACK_RIGHT_MOTOR);
    	shooter = new Shooter();
    	DriverStation.prefDouble("shootDelay", 1/16);
    	DriverStation.prefDouble("shotIncrement", 1/16);
    	DriverStation.prefBoolean("randomShooting", false);
    }
    
    public void autonomousInit(){
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }
    
    @SuppressWarnings("deprecation")
	public void teleopInit(){
    	shooter.shotIncrement = SmartDashboard.getDouble("shotIncrement");
    	shooter.randomShooting = SmartDashboard.getBoolean("randomShooting");
    	if (!shooter.randomShooting)
    		shooter.shootDelay = SmartDashboard.getDouble("shootDelay");
    	else
    		shooter.shootDelay = shooter.randomDelay(shooter.shotIncrement);
    	shooter.shoot = false;
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        frontDrive.driveMode();
        midDrive.driveMode();
        backDrive.driveMode();
        shooter.shootMode(shooter.shooterToggle(shooter.shoot));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
