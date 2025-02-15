package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class Field_Centric_Pinpoint extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        Servo servoSlide;
        Servo servoHand;
        DcMotor motorElbow;
        DcMotor motorBucketArm;
        Servo servoBucket;


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
            //backRightMotor.setPower(backRightPower); //Slow mode if left trigger is pused

            //Use Super Slow Mode if Left Trigger is pushed
            if (gamepad1.left_trigger != 0) {
                frontLeftMotor.setPower(.18 * frontLeftPower);
                backLeftMotor.setPower(.18 * backLeftPower);
                frontRightMotor.setPower(.18 * frontRightPower);
                backRightMotor.setPower(.18 * backRightPower);

                //Use Slow Mode if Left Trigger is pushed
            } else if (gamepad1.right_trigger != 0) {
                frontLeftMotor.setPower(.3 * frontLeftPower);
                backLeftMotor.setPower(.3 * backLeftPower);
                frontRightMotor.setPower(.3 * frontRightPower);
                backRightMotor.setPower(.3 * backRightPower);
            }
            //Run at Full Power if not button is pushed
            else {
                frontLeftMotor.setPower(.9 * frontLeftPower);
                backLeftMotor.setPower(.9 * backLeftPower);
                frontRightMotor.setPower(.9 * frontRightPower);
                backRightMotor.setPower(.9 * backRightPower);
            }

            telemetry.update();

            //Arm Code
            //Puts Slide into Extended Position with Controller 2 X button
            if (gamepad2.x) {
                servoSlide.setPosition(.33);

            }
            //Puts Slide in  Neutral Position with Controller 2 A button
            if (gamepad2.a) {
                servoSlide.setPosition(.48);

            }
            //Puts Slide into Retracted Position with Controller 2 B button
            if (gamepad2.b) {
                servoSlide.setPosition(.53);
            }

            //moves arm elbow
            motorElbow.setPower(gamepad2.right_stick_y * -0.25);

            //moves bucket up
           if (gamepad2.left_stick_y !=0) {
               motorBucketArm.setPower(gamepad2.left_stick_y * -1 );
           }

           //moves bucket to dump
            if (gamepad2.left_stick_x > 0.25) {
                servoBucket.setPosition(gamepad2.left_stick_x *0.25);

            }
            else  servoBucket.setPosition(-0.15);



            //opens hand/grabber
            if (gamepad2.right_trigger != 0) {
                servoHand.setPosition(0);

            }


           //closes hand/grabber
           if (gamepad2.left_trigger != 0) {
               servoHand.setPosition(1);

           }





            }// Ends While Loop for OpMode Active
    }//Ends Public Void
}//Ends Linear Op Mode