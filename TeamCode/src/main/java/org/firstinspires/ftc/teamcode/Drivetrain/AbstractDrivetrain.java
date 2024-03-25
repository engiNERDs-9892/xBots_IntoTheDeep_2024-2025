package org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class AbstractDrivetrain {
    public DcMotor[] driveMotors;
    public AbstractDrivetrain(DcMotor FLM, DcMotor FRM, DcMotor BLM, DcMotor BRM){

        //FLM == "Front Left Motor" etc.

        FLM.setDirection(DcMotorSimple.Direction.REVERSE);
        BLM.setDirection(DcMotorSimple.Direction.REVERSE);

        driveMotors = new DcMotor[]{FLM, FRM, BLM, BRM};
    }
    public void setDriveDirection(DcMotorSimple.Direction direction){
        switch(direction) {
            case REVERSE:
                driveMotors[0].setDirection(DcMotorSimple.Direction.FORWARD);
                driveMotors[2].setDirection(DcMotorSimple.Direction.FORWARD);
                driveMotors[1].setDirection(DcMotorSimple.Direction.REVERSE);
                driveMotors[3].setDirection(DcMotorSimple.Direction.REVERSE);
            case FORWARD:
                driveMotors[0].setDirection(DcMotorSimple.Direction.REVERSE);
                driveMotors[2].setDirection(DcMotorSimple.Direction.REVERSE);
                driveMotors[1].setDirection(DcMotorSimple.Direction.FORWARD);
                driveMotors[3].setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }
    public void driveBasic(double forwards, double turn) {
        driveMotors[0].setPower(forwards + turn);
        driveMotors[1].setPower(forwards - turn);
        driveMotors[2].setPower(forwards + turn);
        driveMotors[3].setPower(forwards - turn);
    }

    public void setMode(DcMotor.RunMode mode) {
        for(DcMotor motor : driveMotors) {
            motor.setMode(mode);
        }
    }
    public void setZeroBehavior(DcMotor.ZeroPowerBehavior behavior) {
        for(DcMotor motor : driveMotors) {
            motor.setZeroPowerBehavior(behavior);
        }
    }
}