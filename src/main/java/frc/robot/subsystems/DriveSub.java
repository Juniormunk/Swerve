/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.utils.TalonSRXPID;
import frc.robot.RobotMap;

/**
 * Drive Train Subsystem.
 */
public class DriveSub extends Subsystem {
  // Drive Talons
  TalonSRX fr, fl, br, bl;

  // Stear Talons
  TalonSRX frs, fls, brs, bls;

  TalonSRXPID frsPID, flsPID, brsPID, blsPID;

  private ADXRS450_Gyro gyro;

  public DriveSub() {
    // Drive Talons
    fr = new TalonSRX(RobotMap.FRTalon);
    fl = new TalonSRX(RobotMap.FLTalon);
    br = new TalonSRX(RobotMap.BRTalon);
    bl = new TalonSRX(RobotMap.BLTalon);

    // Stear Talons
    frs = new TalonSRX(RobotMap.FRSTalon);
    fls = new TalonSRX(RobotMap.FLSTalon);
    brs = new TalonSRX(RobotMap.BRSTalon);
    bls = new TalonSRX(RobotMap.BLSTalon);

    frsPID = new TalonSRXPID(frs, RobotMap.SteerPID);
    flsPID = new TalonSRXPID(fls, RobotMap.SteerPID);
    brsPID = new TalonSRXPID(brs, RobotMap.SteerPID);
    blsPID = new TalonSRXPID(bls, RobotMap.SteerPID);

    gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    gyro.calibrate();
  }

  public void driveFieldOR(double y, double x, double rotate) {
    Double gyroAngleBind = (gyro.getAngle() % 360);
    double CONVERT = 180.0 / Math.PI;
    float ang = 0;
    float chx = (float) (x - 0);
    float chy = (float) (y - 0);
    if (chy == 0) {
      if (chx >= 0)
        ang = 0;
      else
        ang = 180;
    } else if (chy > 0) { // X AND Y ARE REVERSED BECAUSE OF MOTION PROFILER STUFF
      if (chx > 0) {
        ang = (float) (90 - CONVERT * (Math.atan(chx / chy)));

      } else {
        ang = (float) (90 - CONVERT * (Math.atan(chx / chy)));

      }
    } else {
      if (chx > 0) {
        ang = (float) (270 - CONVERT * (Math.atan(chx / chy)));

      } else {
        ang = (float) (270 - CONVERT * (Math.atan(chx / chy)));
      }
    }
    System.out.println(ang);
    Double wheelAngle = ang - gyroAngleBind;
  }

  public void drive(double y, double x, double rotate) {

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
}
