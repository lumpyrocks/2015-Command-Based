package org.usfirst.frc.team4373.navigation.drivetrainh;

import edu.wpi.first.wpilibj.Talon;

public class RooDriveTrain {
	public static final int LEFT_PORT = 0;
	public static final int RIGHT_PORT = 1;
	public static final int STRAFFE_PORT = 2;
	public static final double TANK_STRAFE_SPEED_RATIO = 1.5088757396;
	
	private static Talon left = null;
	private static Talon right = null;
	private static Talon straffe = null;
	
	public static Talon getLeft() {
		if (left == null)
			left = new Talon(LEFT_PORT);
		return left;
	}
	public static Talon getRight() {
		if (right == null)
			right = new Talon(RIGHT_PORT);
		return right;
	}
	public static Talon getStraffe() {
		if (straffe == null)
			straffe = new Talon(STRAFFE_PORT);
		return straffe;
	}
	public void move(int angle, double magnitude) {
		
	}
	protected int to360(int angle) {
		return (angle*360)/255;
	}
	
	static double tankPowerFromAxes (double forwardAxis, double rightAxis){
		//at full forewards power, I think the shape we're looking for in terms of variation with the right axis
		//is a semi-elipse following the equation y=.509sqrt(1-xx) + 1
		//this function will take that result and multiply it by percentage foreward power in an attempt
		double motorPower;
		motorPower = 1 - Math.pow(rightAxis, 2);
		motorPower = (TANK_STRAFE_SPEED_RATIO-1) * Math.sqrt(motorPower);
		motorPower += 1;
		
		//here we multiply be the forward power so that we can go not full steam ahead
		motorPower *= forwardAxis;
		
		//now to get it back under 1
		motorPower /= TANK_STRAFE_SPEED_RATIO;
		return motorPower;
	}
	
	void getForwardMagnitudeFromFieldwise (double x1, double y1, double a){
		//Cici is a genius and made these
		//takes joystick axes and the angle the robot is at,
		//returns joystick output as if we were point the joysick in a way to get the bot
		//to drive in the direction we are pointing the legit joystick in
		double z1 = Math.sqrt((x1 * x1) + (y1 * y1));
		double b = Math.atan(y1/x1);
		a = (a * Math.PI)/180;
		double c = b+a;
		double y2 = Math.sin(c) * z1;
		if (Math.abs(y2) < .001){
			y2 = 0;
		}
		return y2;
	}
	
	void getRightMagnitudeFromFieldwise (double x1, double y1, double a){
		double z1 = Math.sqrt((x1 * x1) + (y1 * y1));
		double b = Math.atan(y1/x1);
		a = (a * Math.PI)/180;
		double c = b+a;
		double x2 = Math.cos(c) * z1;
		if (Math.abs(x2) < .001){
			x2 = 0;
		}
		return x2;
	}
	
	
}
