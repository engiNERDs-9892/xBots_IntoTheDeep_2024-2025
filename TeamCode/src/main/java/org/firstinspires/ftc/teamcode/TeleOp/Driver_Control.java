package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class Driver_Control extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        Servo servoSlide;
        Servo servoHand;
        DcMotor motorElbow;
        DcMotor motorBucketArm;
        Servo servoBucket;
        TouchSensor limit;


        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("motorFL");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("motorBL");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("motorFR");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("motorBR");
        servoSlide = hardwareMap.servo.get("servoSlide");
        servoHand = hardwareMap.servo.get("servoHand");
        motorElbow = hardwareMap.dcMotor.get("motorElbow");
        motorBucketArm = hardwareMap.dcMotor.get("motorBucketArm");
        servoBucket = hardwareMap.servo.get("servoBucket");
        limit = hardwareMap.touchSensor.get("limit");




        GoBildaPinpointDriver odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        /////////////////////////////////////////////////////////////////////////////////
        //////////////        ADJUST HUB DIRECTIONS TO SET HEADINGS        //////////////
        /////////////////////////////////////////////////////////////////////////////////
        //Ignore comment above
        //Copied from gobilda sample code please adjust values
        //odo.setOffsets(-84.0, -168.0);
        //odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        servoSlide.setPosition(.48);
        //servoJoint.setPosition(.91);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            //Update the heading given by the pinpoint odometry controller
            odo.update(GoBildaPinpointDriver.readData.ONLY_UPDATE_HEADING);

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.back) {
                odo.resetPosAndIMU();
            }

            double botHeading = odo.getHeading();

            telemetry.addData("botHeading", "%f", botHeading);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            //frontLeftMotor.setPower(frontLeftPower);
            //backLeftMotor.setPower(backLeftPower);
            //frontRightMotor.setPower(frontRightPower);
            //backRightMotor.setPower(backRightPower); //Slow mode if left trigger is pushed

            //Use Super Slow Mode if Left Trigger is pushed
            if (gamepad1.left_trigger != 0) {
                frontLeftMotor.setPower(.18 * frontLeftPower);
                backLeftMotor.setPower(.18 * backLeftPower);
                frontRightMotor.setPower(.18 * frontRightPower);
                backRightMotor.setPower(.18 * backRightPower);

                //Use Slow Mode if Left Trigger is pushed
            } else if (gamepad1.right_trigger != 0) {
                frontLeftMotor.setPower(.4 * frontLeftPower);
                backLeftMotor.setPower(.4 * backLeftPower);
                frontRightMotor.setPower(.4 * frontRightPower);
                backRightMotor.setPower(.4 * backRightPower);
            }
            //Run at Full Power if neither trigger is pushed
            else {
                frontLeftMotor.setPower(.9 * frontLeftPower);
                backLeftMotor.setPower(.9 * backLeftPower);
                frontRightMotor.setPower(.9 * frontRightPower);
                backRightMotor.setPower(.9 * backRightPower);
            }

            telemetry.update();

        //Front Slide Code:
            //Stops the Front Slide
            if (gamepad2.left_trigger == 0 && gamepad2.right_trigger ==0) {
                servoSlide.setPosition(.5);
            }
            //Extends the Front Slide
                //Stops the Slide if the magnetic limit switch is pressedr
                else if (gamepad2.left_trigger != 0 && limit.isPressed()) {
                servoSlide.setPosition(.5);
                }
                //Extends the slide if the limit switch is not pressed
                else if (gamepad2.left_trigger != 0 ) {
                servoSlide.setPosition(.5 +.5 * gamepad2.left_trigger);
                }
            //Retract the Front Slide
            else if (gamepad2.right_trigger != 0) {
                servoSlide.setPosition(.5 - .5 * gamepad2.right_trigger);
            }

        //Moves Elbow
            //Moves Elbow towards ground
            if (gamepad2.right_stick_y < 0.1){
                motorElbow.setPower(gamepad2.right_stick_y * -0.35);
            }
            //Moves Elbow back toward the Bucket
            else if (gamepad2.right_stick_y > 0.1){
                motorElbow.setPower(gamepad2.right_stick_y * -0.25);
            }
            else {motorElbow.setPower(0);}



        //Bucket Code
            //moves Vertical Linear Slide Up and Down with Left Stick
            if (gamepad2.left_stick_y >0.1) {
                motorBucketArm.setPower(gamepad2.left_stick_y * -1 );
            }
            else if (gamepad2.left_stick_y <0.1) {
                motorBucketArm.setPower(gamepad2.left_stick_y * -1 );
            }
            else  {
                motorBucketArm.setPower(0);
            }
            //moves bucket to dump by pressing right on the Left Stick
            if (gamepad2.left_stick_x > 0.35) {
                servoBucket.setPosition(gamepad2.left_stick_x *0.25);
            }
            // Returns the bucket to it's catching position
            else  servoBucket.setPosition(-0.15);


        //Hand/Grabber Code
            //opens hand/grabber
            if (gamepad2.a) {
                servoHand.setPosition(0);
            }
            //closes hand/grabber
            if (gamepad2.x) {
                servoHand.setPosition(1);
            }

        }// Ends While Loop for OpMode Active
    }//Ends Public Void
}//Ends Linear Op Mode