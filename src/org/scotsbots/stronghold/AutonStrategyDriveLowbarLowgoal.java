package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveLowbarLowgoal extends AutonStrategy
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
			Robot.bot.drivetrain.drive(-0.2, 0);
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(0.75);
			}
		}
		else if(time >= 0 && time <= 60)
		{
			Robot.bot.drivetrain.drive(-0.4, 0);
		}
		else if(time >= 60 && time <= 200)
		{
			Robot.bot.drivetrain.drive(-0.6, 0);
		}
		else if(time >= 200 && time <= 218)
		{
			Robot.bot.drivetrain.tankDrive(-0.64, 0.64);
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(-0.75);
			}
		}
		else if(time >= 219 && time <= 230)
		{
			Robot.bot.drivetrain.drive(-0.65, 0);
		}
		else if(time > 231 && time <= 280)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(1);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.intake.set(1);
			}
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
			
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(0);				

			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.intake.set(0);
			}
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight - Lowbar - Lowgoal";
	}

}
