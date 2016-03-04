package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveReverse extends AutonStrategy
{
	public int time = 0;
	
	@Override
	public void intialize()
	{
	}

	@Override
	public void update()
	{
		time++;

		if(time >= 0 && time <= 100)
		{
			//drive backwards
			Robot.bot.drivetrain.drive(0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight Timed - Reverse";
	}

}
