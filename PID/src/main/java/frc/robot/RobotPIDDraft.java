package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpiutil.math.MathUtils;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

   private Spark leftMotor1 = new Spark(0);
   private Spark leftMotor2 = new Spark(1);
   private Spark rightMotor1 = new Spark(2);
   private Spark rightMotor2 = new Spark(3);

   private Joystick joy1 = new Joystick(0);

    // distance in inches the robot wants to stay from an object
  private static final double kHoldDistance = 12.0;

  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // proportional speed constant
  private static final double kP = 7.0;

  // integral speed constant
  private static final double kI = 0.018;

  // derivative speed constant
  private static final double kD = 1.5;

  private static final PIDController pidcontroller = new PIDController(kD, kI, kP);
  private static final AHRS gyro = new AHRS();


   
  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
  }



  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void teleopInit() {
    pidcontroller.reset();
    }

  @Override
  public void teleopPeriodic() {

    // check for the button
    if (joy1.getRawButton(1)) {
      // reset gyro
      gyro.reset();
      pidcontroller.setSetpoint(90);
    }
    // get angle
    // calculate
    // checkSetPoint

    pidcontroller.getSetpoint();
    if (!pidcontroller.atSetpoint()) {
      double out = MathUtils.clamp(pidcontroller.calculate(gyro.getAngle()), -1, 1);
      leftMotor1.setSpeed(out);
      rightMotor1.setSpeed(out);
      leftMotor2.setSpeed(out);
      rightMotor2.setSpeed(out);
    } // motors stop at setpoint 
      else {
      rightMotor1.stopMotor();
      rightMotor2.stopMotor();
      leftMotor1.stopMotor();
      leftMotor2.stopMotor();
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
