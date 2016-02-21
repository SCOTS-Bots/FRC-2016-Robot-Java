package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDump extends AutonStrategy
{
	int time = 0;
	public boolean stopped = false;
	
	@Override
	public void intialize()
	{
		time = 0;
		stopped = false;
	}

	@Override
	public void update()
	{
		time++;
		
		if(time <= 200)
		{
			Robot.bot.drivetrain.drive(0.75, 0);
		}
		else if(time >= 201 && time <= 250)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot bot = (RobotHardwarePrototypeBot)Robot.bot;
				bot.scoopMotor.set(1);
			}
			else if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot bot = (RobotHardwareCompbot)Robot.bot;
				bot.scoopMotor.set(1);
			}
		}
	}

	@Override
	public String getName()
	{
		return "Dump Ball";
	}
	
}
