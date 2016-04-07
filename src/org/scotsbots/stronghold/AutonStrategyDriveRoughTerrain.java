package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveRoughTerrain extends AutonStrategy
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
			Robot.bot.drivetrain.drive(-0.3, 0);
		}

		else if(time >= 30 && time <= 185)
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
		return "Drive Straight - Rough Terrain";
	}

}
