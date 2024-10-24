package org.firstinspires.ftc.teamcode;

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



}// Closes TeleOp While Loop

}// Closes RunOpMode

}//Ends Linear OpMOde