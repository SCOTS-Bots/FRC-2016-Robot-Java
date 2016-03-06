package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyStraightShootBackup extends AutonStrategy
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
		
		if(time >= 0 && time <= 100)
		{
			Robot.bot.drivetrain.drive(0.6, 0);
		}
		else if(time >= 301 && time <=350)
		{
			Robot.bot.drivetrain.drive(-0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
		if(time > 100 && time <= 260)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;
							
				if(protobot.autoShoot())
				{
					time = 261;
				}
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
							
				if(protobot.autoShoot())
				{
					time = 261;
				}
			}
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight Shoot Backup - Reverse";
	}

}
