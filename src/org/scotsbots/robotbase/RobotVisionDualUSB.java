package org.scotsbots.robotbase;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotVisionDualUSB
{
	public final int cam1;
	public final int cam2;
	public int curCam;
	private Image frame;
	
	public RobotVisionDualUSB()
	{
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		cam1 = NIVision.IMAQdxOpenCamera("cam3", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        cam2 = NIVision.IMAQdxOpenCamera("cam4", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = cam1;
        CameraServer.getInstance().setQuality(50);
	}
	
	public void init()
	{
		changeCam(cam1);
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
	        CameraServer.getInstance().setImage(frame);
    	} catch(Exception e)
    	{
    		System.out.println("Camera error, most likely camera not found.");
    		e.printStackTrace();
    	}
    }
}