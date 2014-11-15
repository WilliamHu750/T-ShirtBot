package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
	Timer shootTimer;
	private final boolean OPEN = true;
	private final boolean CLOSE = false;
	private Solenoid shootingSolenoid;
	
	double shootDelay; //to be adjusted
	boolean randomShooting;
	double shotIncrement;
	
	boolean shoot;
	
	public Shooter(){
		shootingSolenoid = new Solenoid(HWR.SOLENOID_SLOT_12_VOLT, HWR.SHOOTING_PISTON);
		shootTimer = new Timer();
	}
	
	public void fire(){
		shootingSolenoid.set(OPEN);
		if (shootTimer.get()>=shootDelay)
		{
			shootingSolenoid.set(CLOSE);
			shootTimer.stop();
			shootTimer.reset();
			shoot = false;
		}
	}
	
	public double randomDelay(double increment){
		return increment + increment*Math.random();
	}
	
	public void shootMode(boolean run)
	{
		if (run)
		{
			fire();
		}
	}
	
	public boolean shooterToggle(boolean shoot)
	{
		if (DriverStation.antiBounce(HWR.AUX_JOYSTICK, HWR.SHOOT))
		{
			if (!shoot)
				shoot = true;
			else if (shoot)
				shoot = false;
			shootTimer.start();
		}
		return shoot;
		
	}
	
}
