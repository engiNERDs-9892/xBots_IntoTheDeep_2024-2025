package org.firstinspires.ftc.teamcode;

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
public void runOpMode(){
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
    while (opModeIsActive()){
        double max;
    double axial = -gamepad1,left_stick_y;
    double yaw = gamepad1.right_stick_x;
    double lateral = gamepad1.left_stick_x;
    double leftFrontPower =axial + lateral + yaw;
    double rightFrontPower = axial - lateral - yaw;
    double leftBackPower = axial - lateral + yaw;
    double rightBackPower + axial + lateral - yaw;
} //closes Linear OpMode


}//Ends Linear OpMode//

