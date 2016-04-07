package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveCurved extends AutonStrategy
{
	public int time = 0;
	public boolean autoFireMode = false;
	public int autoShootTimer = 0;
	public boolean stopped = false;

	@Override
	public void intialize()
	{
		stopped = false;
	}

	@Override
	public void update()
	{
		if(!stopped)
		{
			time++;
		}
		if(time > 0 && time <= 100)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(1);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(-0.1);
			}
		}
		else
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(0);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(0);
			}
		}
		
		if(time > 20 && time <= 100)
		{
			Robot.bot.drivetrain.drive(-0.6, 0.0001);
		}
		else if(time >= 100 && time <= 120)
		{
			Robot.bot.drivetrain.drive(-0.5, 0.7);
		}
		else if(time >= 121 && time <= 165)
		{
			Robot.bot.drivetrain.drive(-0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Drive Timed Curved";
	}

}
