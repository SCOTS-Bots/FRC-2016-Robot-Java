package org.scotsbots.robotbase;

import java.io.IOException;

import com.ni.vision.NIVision;
import com.ni.vision.VisionException;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * Handles extremely basic vision on Smartdashboard.
 * @author Domenic
 *
 */
public class RobotVision
{
	protected int session;
    protected Image frame;
    protected AxisCamera camera;
    protected String ip;
    protected String cam;
    
    public RobotVision(String ip, String cam)
    {
    	this.ip = ip;
    	this.cam = cam;
    }

    public void initialize()
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera(ip);   
	}
    
    public void initUSBCam()
    {
    	CameraServer camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture(cam);
    }
	
	public void stream()
	{	        
		camera.getImage(frame);
        CameraServer.getInstance().setImage(frame);
	}
	
	/**
	 * Runs GRIP image processing on the RoboRIO. Required for GRIP vision usage.
	 * @param filename name of GRIP file on RoboRIO.
	 */
	public static void runGRIP(String filename)
	{
		try
		{
			Runtime.getRuntime().exec(new String[]{"/usr/local/frc/JRE/bin/java", "-jar", "grip.jar", filename});
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
