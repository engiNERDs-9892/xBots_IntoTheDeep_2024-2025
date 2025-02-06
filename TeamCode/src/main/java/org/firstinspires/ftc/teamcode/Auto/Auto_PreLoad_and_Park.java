package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="PreLoad and Park)", group="Iterative Opmode")
public class Auto_PreLoad_and_Park extends LinearOpMode {

//Initialize the code - Get it ready to run

    //Create Motor/Servo/Sensor List
    private DcMotor motorFL;
    private DcMotor motorFR;
    private DcMotor motorBL;
    private DcMotor motorBR;
    private DcMotor motorElbow;
    private DcMotor motorBucketArm;
    Servo servoHand;
    Servo servoSlide;
    Servo servoBucket;


    int in = 2;
    int elbow = 57;

    int lift = 11;


    public final void runOpMode(){
        //Create Hardware map so code can send signals to the correct ports
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        motorBucketArm = hardwareMap.dcMotor.get("motorBucketArm");
        motorElbow = hardwareMap.dcMotor.get("motorElbow");
        servoBucket = hardwareMap.servo.get("servoBucket");
        servoHand = hardwareMap.servo.get("servoHand");
        servoSlide = hardwareMap.servo.get("servoSlide");

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
        Left(800,.35);
        Lift(1000,1);
        Left(200, .50);
        Lift(50, 1);
        Dump();
        sleep(1000);
        ResetBucket();
        Lift(50, 1);
        Right(200, 1);




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
    public void Right (int target, double speed) {
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.FORWARD);

        motorBL.setTargetPosition(target*in);
        motorFL.setTargetPosition(target*in);
        motorBR.setTargetPosition(target*in);
        motorFR.setTargetPosition(target*in);

        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFL.setPower(speed);
        motorFR.setPower(speed);
        motorBL.setPower(speed);
        motorBR.setPower(speed);

        while (opModeIsActive() && (motorBL.isBusy())){

        }
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }// Closes Right Function

    public void Left (int target, double speed) {
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFR.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorBL.setTargetPosition(target*in);
        motorFL.setTargetPosition(target*in);
        motorBR.setTargetPosition(target*in);
        motorFR.setTargetPosition(target*in);

        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFL.setPower(speed);
        motorFR.setPower(speed);
        motorBL.setPower(speed);
        motorBR.setPower(speed);

        while (opModeIsActive() && (motorBL.isBusy())){

        }
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }// Closes Left Function

    // Open Hand Function
    public void Open (){
        servoHand.setPosition(0);

    }//Closes Open Function

    //Close Hand Function
    public void Close (){
        servoHand.setPosition(1);

    }//Closes Close Function

    //Dump Bucket Function
    public void Dump (){
        servoBucket.setPosition(0.25);

    }//Closes Dump Function

    //Reset Bucket Function
    public void ResetBucket (){
        servoBucket.setPosition(-0.15);
    }//Closes ResetBucket Function

    //Lift Bucket Function
    public void Lift (int target, double speed) {
        motorBucketArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        motorBucketArm.setDirection(DcMotorSimple.Direction.FORWARD);


        motorBucketArm.setTargetPosition(target*lift);

        motorBucketArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        motorBucketArm.setPower(speed);

        while (opModeIsActive() && (motorBucketArm.isBusy())){

        }
        motorBucketArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }//Closes Lift Bucket  Function

    //Lower Bucket Function
    public void LowerBucket (){

    }//Closes LowerBucket Function

    }//LinearOpMode
