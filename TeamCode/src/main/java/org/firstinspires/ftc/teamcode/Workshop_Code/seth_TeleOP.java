package org.firstinspires.ftc.teamcode.Workshop_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name="seth TeleOP",group="Linear OpMode")
public class seth_TeleOP extends LinearOpMode {
    //Declare Motors, Servors & Sensors
private DcMotor motorFL = null;
private DcMotor motorFR = null;
private DcMotor motorBL = null;
private DcMotor motorBR = null;

@Override
public void runOpMode() {
    //Hardware Map
    motorFL = hardwareMap.get(DcMotor.class, "motorFL");
    motorFR = hardwareMap.get(DcMotor.class, "motorFR");
    motorBL = hardwareMap.get(DcMotor.class, "motorBL");
    motorBR = hardwareMap.get(DcMotor.class, "motorBR");

    motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
    motorFR.setDirection(DcMotorSimple.Direction.FORWARD);
    motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
    motorBR.setDirection(DcMotorSimple.Direction.FORWARD);

    //Wait for the Game to start (Driver Presses PLAY)
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    waitForStart();
    //RUN THIS CODE UNTIL THE END OF THE MATCH (Driver presses STOP)
    while (opModeIsActive()) {
        double max;
        double axial = -gamepad1.left_stick_y;
        double yaw = gamepad1.right_stick_x;
        double lateral = gamepad1.left_stick_x;
        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));


        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        if (gamepad1.right_trigger !=0) {
            motorFR.setPower(rightFrontPower * .2);
            motorFL.setPower(leftFrontPower * .2);
            motorBR.setPower(rightBackPower * .2);
            motorBL.setPower(leftBackPower * .2);
        }
        else{
                motorFR.setPower(rightFrontPower);
                motorFL.setPower(leftFrontPower);
                motorBR.setPower(rightBackPower);
                motorBL.setPower(leftBackPower);
            }

        } //closes Tele Op While Loop

    } // closes Run Op Mode

}//Ends Linear OpMode

