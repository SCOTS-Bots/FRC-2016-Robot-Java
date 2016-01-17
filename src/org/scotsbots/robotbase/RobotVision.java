package org.scotsbots.robotbase;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * Handles extremely basic vision on Smartdashboard.
 * @author Domenic
 *
 */
public class RobotVision
{
	int session;
    static Image frame;
    static AxisCamera camera;
    
	public static void initialize()
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera("10.47.76.20");
	}
	
	public static void stream()
	{	        
		NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
		camera.getImage(frame);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        CameraServer.getInstance().setImage(frame);
	}
}
