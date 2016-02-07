package org.scotsbots.robotbase;

import org.scotsbots.robotbase.utils.Gamepad;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotVisionDualUSB
{
	private final int cam1;
	private final int cam2;
	private int curCam;
	private Image frame;
	private CameraServer server;
	
	public RobotVisionDualUSB()
	{
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        cam1 = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        cam2 = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = cam1;
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(50);
	}
	
	public void init()
	{
		changeCam(cam1);
	}
	
	public void run()
	{
		if(Gamepad.secondaryAttackJoystick.getStart())
		{	
			if(curCam == cam1)
			{
				changeCam(cam2);
			}
			else if(curCam == cam2)
			{
				changeCam(cam1);
			}
		}
		
		updateCam();
	}
	
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
	}
	
	/**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam(int newId)
    {
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	try
    	{
	    	NIVision.IMAQdxGrab(curCam, frame, 1);
	        server.setImage(frame);
    	} catch(Exception e)
    	{
    		System.out.println("Camera error, most likely camera not found.");
    		e.printStackTrace();
    	}
    }
}