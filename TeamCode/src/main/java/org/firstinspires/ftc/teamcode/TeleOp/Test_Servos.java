package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.edgeButton;
import org.firstinspires.ftc.teamcode.TeleOp.repeatButton;

@TeleOp
public class Test_Servos extends LinearOpMode {
    public static Servo[] servoList = new Servo[12];
    public static String[] servoNames = {"servoClaw", "servoSlide"};
    //public static String[] servoNames = {"servo0", "servo1", "servo2", "servo3", "servo4"};
    public static int currentServo;

    @Override
    public void runOpMode(){
        for(int i = 0; i < servoNames.length; i++){
            servoList[i] = hardwareMap.get(Servo.class, servoNames[i]);
            servoList[i].setPosition(0.5);
        }
        currentServo = 0;
        edgeButton next = new edgeButton() {
            @Override
            public void trigger() {
                currentServo += 1;
                if(currentServo >= servoNames.length){
                    currentServo = 0;
                }
            }
        };
        edgeButton prev = new edgeButton() {
            @Override
            public void trigger() {
                currentServo -= 1;
                if(currentServo < 0){
                    currentServo = servoNames.length - 1;
                }
            }
        };
        repeatButton forward = new repeatButton() {
            @Override
            public void trigger() {
                Servo servo = servoList[currentServo];
                servo.setPosition(servo.getPosition() + 0.01);
            }
        };
        repeatButton backward = new repeatButton() {
            @Override
            public void trigger() {
                Servo servo = servoList[currentServo];
                servo.setPosition(servo.getPosition() - 0.01);
            }
        };
        telemetry.addLine("Controls\n  Bumpers to switch between servos\n  dpad to change servo positions");
        telemetry.addLine("Controls");
        telemetry.addLine("  Bumpers .to switch between servos");
        telemetry.addLine("  dpad to change servo positions");

        telemetry.addLine("Servo names  -  Default position is 0.5");
        for(int i = 0; i < servoNames.length; i++){
            telemetry.addData(servoNames[i], servoList[i].getPosition());
        }
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            next.update(gamepad1.left_bumper);
            prev.update(gamepad1.right_bumper);
            forward.update(gamepad1.dpad_right);
            backward.update(gamepad1.dpad_left);
            telemetry.addData("Current Servo", "%s %f", servoNames[currentServo], servoList[currentServo].getPosition());
            telemetry.addLine("Servo positions");
            for(int i = 0; i < servoNames.length; i++){
                telemetry.addData(servoNames[i], servoList[i].getPosition());
            }
            telemetry.update();
        }
    }
}