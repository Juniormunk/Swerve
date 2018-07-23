package frc.robot.utils;

public class PID
{
	public double p;
	public double i;
	public double d;
	public double f;

	public PID(double P, double I, double D, double F)
	{
		this.p = P;
		this.i = I;
		this.d = D;
		this.f = F;
	}
}