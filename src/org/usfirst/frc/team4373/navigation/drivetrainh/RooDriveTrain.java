package org.usfirst.frc.team4373.navigation.drivetrainh;

import edu.wpi.first.wpilibj.Talon;

public class RooDriveTrain {
	public static final int LEFT_PORT = 0;
	public static final int RIGHT_PORT = 1;
	public static final int STRAFFE_PORT = 2;
	
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
}
