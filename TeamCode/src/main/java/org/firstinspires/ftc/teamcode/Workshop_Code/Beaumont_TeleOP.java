package org.firstinspires.ftc.teamcode.Workshop_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="Beaumont TeleOp", group="Linear OpMode")
public class Beaumont_TeleOP extends LinearOpMode {

    //Declare Motors, Servos & Sensors
private DcMotor motorFL = null;
private DcMotor motorFR = null;
private DcMotor motorBL = null;
private DcMotor motorBR = null;

@Override
public void runOpMode(){
//Put Variable Here

//Hardware Map
    motorFL = hardwareMap.get(DcMotor.class,"motorFL");
    motorFR = hardwareMap.get(DcMotor.class,"motorFR");
    motorBL = hardwareMap.get(DcMotor.class,"motorBL");
    motorBR = hardwareMap.get(DcMotor.class,"motorBR");
//Set Motor Directions
    motorFL.setDirection(DcMotor.Direction.REVERSE);
    motorFR.setDirection(DcMotor.Direction.FORWARD);
    motorBL.setDirection(DcMotor.Direction.REVERSE);
    motorBR.setDirection(DcMotor.Direction.FORWARD);

//Wait for the Game to start (Driver Presses PLAY)
    telemetry.addData("Status", "Initialized");
    telemetry.update();

waitForStart();
//RUN THIS CODE UNTIL THE END OF THE MATCH (Driver presses STOP)
while (opModeIsActive()){

//Drive Motor Variables
double max;
 double axial = -gamepad1.left_stick_y;
 double lateral = gamepad1.left_stick_x;
 double yaw = gamepad1.right_stick_x;
 //Drive Motor Calculations
  double leftFrontPower = axial + lateral + yaw;
  double rightFrontPower =  axial - lateral - yaw;
  double leftBackPower = axial - lateral + yaw;
  double rightBackPower = axial +lateral - yaw;

  max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
  max = Math.max(max, Math.abs(leftBackPower));
  max = Math.max(max, Math.abs(rightBackPower));

  if (max >1.0) {
      leftFrontPower /= max;
      rightFrontPower /= max;
      leftBackPower /= max;
      rightBackPower /= max;
    }

  if (gamepad1.right_trigger !=0) {
      motorFR.setPower(rightFrontPower * .2);
      motorBR.setPower(rightBackPower *.2);
      motorFL.setPower(leftFrontPower *.2);
      motorBL.setPower(leftBackPower *.2);
  }
  else {
      motorFR.setPower(rightFrontPower);
      motorBR.setPower(rightBackPower);
      motorFL.setPower(leftFrontPower);
      motorBL.setPower(leftBackPower);
  }


}// Closes TeleOp While Loop

}// Closes RunOpMode

}//Ends Linear OpMOde