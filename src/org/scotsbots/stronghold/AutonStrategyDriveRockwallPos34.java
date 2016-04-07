package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveRockwallPos34 extends AutonStrategy
{
	public int time = 0;
	
	@Override
	public void intialize()
	{
		time = 0;
	}

	@Override
	public void update()
	{
		time++;

		if(time >= 0 && time <= 30)
		{
			Robot.bot.drivetrain.drive(-0.25, 0);
		}
		else if(time >= 0 && time <= 60)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		else if(time >= 60 && time <= 150)
		{
			Robot.bot.drivetrain.drive(-0.7, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight - Rockwall (pos3)/(pos4)";
	}

}
