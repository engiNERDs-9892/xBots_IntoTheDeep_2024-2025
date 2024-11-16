package org.firstinspires.ftc.teamcode.Workshop_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Owen_Teleop", group = "Linior OpMode")
public class Owen_Teliop extends LinearOpMode {

    //Decare Moters, Servos & Sensors
    private DcMotor motorFL = null;
    private DcMotor motorFR = null;
    private DcMotor motorBL = null;
    private DcMotor motorBR = null;

    @Override
    public void runOpMode() {

        //put variables here


//Hardware map
        motorFL = hardwareMap.get(DcMotor.class, "motorFL");
        motorFR = hardwareMap.get(DcMotor.class, "motorFR");
        motorBL = hardwareMap.get(DcMotor.class, "motorBL");
        motorBR = hardwareMap.get(DcMotor.class, "motorBR");
        //set motor directions
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorFR.setDirection(DcMotor.Direction.FORWARD);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.FORWARD);

        //wait for game to start/driver presses play
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    waitForStart();
    //run this code until end of match
    while (opModeIsActive()) {
        double max;
        double axial = -gamepad1.left_stick_y;
        double yaw = gamepad1.right_stick_x;
        double lateal = gamepad1.left_stick_x;

        double leftFrontPower = axial + lateal + yaw;
        double rightFrontPower = axial - lateal - yaw;
        double leftBackPower = axial - lateal + yaw;
        double rightBackPower = axial + lateal - yaw;



     max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
     max = Math.max(max, Math.abs(leftBackPower));
     max = Math.max(max, Math.abs(rightBackPower));


if (max >1.0) {
    leftFrontPower /=max;
    rightFrontPower /=max;
    leftBackPower /=max;
    rightBackPower /=max;

}


if (gamepad1.right_trigger !=0) {
    motorFR.setPower(rightFrontPower * .2);
    motorBR.setPower(rightBackPower * .2);
    motorFL.setPower(leftFrontPower * .2);
    motorBL.setPower(leftBackPower * .2);
}
else {
    motorFR.setPower(rightFrontPower);
    motorBR.setPower(rightBackPower);
    motorFL.setPower(leftFrontPower);
    motorBL.setPower(leftBackPower );
}
    }//closes Teliop while loop

    }//Closes run op mode


}//Ends Linear OpMode