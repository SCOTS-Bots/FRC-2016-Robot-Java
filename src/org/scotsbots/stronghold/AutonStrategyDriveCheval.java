package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveCheval extends AutonStrategy
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

		if(time >= 0 && time <= 45)
		{
			Robot.bot.drivetrain.drive(-0.55, 0);
		}
		if(time >= 100 && time <= 200)
		{
			Robot.bot.drivetrain.drive(-0.75, 0);
		}

		if(time >= 60 && time < 150)
		{
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(0.8);
			}
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;
				protobot.shroud.set(-0.8);
			}
		}
		if(time >= 150 && time < 230)
		{
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(0.5);
			}
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;
				protobot.shroud.set(-0.5);
			}
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight - Cheval Defrie";
	}

}
