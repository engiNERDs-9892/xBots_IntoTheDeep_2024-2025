package org.firstinspires.ftc.teamcode.Workshop_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@Autonomous(name="OwenOwe-0-Burrito(0-0)", group="Iterative Opmode")
public class Owen extends LinearOpMode {

//Initialize the code - Get it ready to run

    //Create Motor/Servo/Sensor List
    private DcMotor motorFL;
    private DcMotor motorFR;
    private DcMotor motorBL;
    private DcMotor motorBR;

    int in = 45;

    public final void runOpMode(){
        //Create Hardware map so code can send signals to the correct ports
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBR = hardwareMap.dcMotor.get("motorBR");

        int in = 45;
        //Set Motor Power to 0 to start
        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);

        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);






        // Tell the Code to wait to start after initialization
        waitForStart();

        // Give it commands to run during auto
        Forward(24,.50);
        TurnLeft(69, 60);
        Forward(13, 50);
        Back(13, 69);
        TurnRight(69, 60);
        Forward(35, 40);
        TurnRight(69, 58);
        Back(78, 47);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);
        TurnLeft(16, 30);







        //This is the code to Begin 2024



    }// ends public final void runOpMode

//enter functions here
 public void Forward (int target, double speed) {
      motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
      motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
      motorBR.setDirection(DcMotorSimple.Direction.FORWARD);
      motorFR.setDirection(DcMotorSimple.Direction.FORWARD);

      motorBL.setTargetPosition(target*in);
      motorFL.setTargetPosition(target*in);
      motorBR.setTargetPosition(target*in);
      motorFR.setTargetPosition(target*in);

      motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      motorBL.setPower(speed);
      motorBR.setPower(speed);
      motorFL.setPower(speed);
      motorFR.setPower(speed);

      while (opModeIsActive() && (motorBL.isBusy())){

      }
      motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
 }//closes forward function

    public void Back (int target, double speed) {
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorBL.setTargetPosition(target*in);
        motorFL.setTargetPosition(target*in);
        motorBR.setTargetPosition(target*in);
        motorFR.setTargetPosition(target*in);

        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBL.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed);
        motorFR.setPower(speed);

        while (opModeIsActive() && (motorBL.isBusy())){

        }
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }//closes Back function


    public void TurnRight(int target, double speed) {
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorBL.setTargetPosition(target*in);
        motorFL.setTargetPosition(target*in);
        motorBR.setTargetPosition(target*in);
        motorFR.setTargetPosition(target*in);

        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBL.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed);
        motorFR.setPower(speed);

        while (opModeIsActive() && (motorBL.isBusy())){

        }
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }//closes TurnRight function

    public void TurnLeft(int target, double speed) {
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFR.setDirection(DcMotorSimple.Direction.FORWARD);

        motorBL.setTargetPosition(target*in);
        motorFL.setTargetPosition(target*in);
        motorBR.setTargetPosition(target*in);
        motorFR.setTargetPosition(target*in);

        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBL.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed);
        motorFR.setPower(speed);

        while (opModeIsActive() && (motorBL.isBusy())){

        }
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }//closes TurnLeft function

}//LinearOpMode
