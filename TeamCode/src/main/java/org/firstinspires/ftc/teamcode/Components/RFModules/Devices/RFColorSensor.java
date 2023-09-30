package org.firstinspires.ftc.teamcode.Components.RFModules.Devices;

import static org.firstinspires.ftc.teamcode.Robots.BasicRobot.op;

import com.qualcomm.hardware.rev.RevColorSensorV3;

/**
 * Harry
 * Class to contain all RFColorSensor functions
 */
public class RFColorSensor {
    private RevColorSensorV3 colorSensor;
    private int WHITE_VALUES[] = {0,0,0};
    private int PURPLE_VALUES[] = {0,0,0};
    private int YELLOW_VALUES[] = {0,0,0};
    private int GREEN_VALUES[] = {0,0,0};

    /**
     * constructor for rfcolorsensor, logs to general with CONFIG severity
     * @param p_deviceName
     */
    public RFColorSensor(String p_deviceName){
        colorSensor = op.hardwareMap.get(RevColorSensorV3.class, p_deviceName);
    }

    /**
     * returns a String of what color is currently closest to sensor readings
     * log to general with INFO severity
     * @return
     */
    public String getColor(){
        int errorW = 0, errorP = 0, errorY = 0, errorG = 0;
        errorW = (WHITE_VALUES[0] - colorSensor.red()) * (WHITE_VALUES[0] - colorSensor.red()) + (WHITE_VALUES[1] - colorSensor.green()) * (WHITE_VALUES[1] - colorSensor.green()) + (WHITE_VALUES[2] - colorSensor.blue())*(WHITE_VALUES[2] - colorSensor.blue());
        errorP = (PURPLE_VALUES[0] - colorSensor.red()) * (PURPLE_VALUES[0] - colorSensor.red()) + (PURPLE_VALUES[1] - colorSensor.green()) * (PURPLE_VALUES[1] - colorSensor.green()) + (PURPLE_VALUES[2] - colorSensor.blue())*(PURPLE_VALUES[2] - colorSensor.blue());
        errorY = (YELLOW_VALUES[0] - colorSensor.red()) * (YELLOW_VALUES[0] - colorSensor.red()) + (YELLOW_VALUES[1] - colorSensor.green()) * (YELLOW_VALUES[1] - colorSensor.green()) + (YELLOW_VALUES[2] - colorSensor.blue())*(YELLOW_VALUES[2] - colorSensor.blue());
        errorG = (GREEN_VALUES[0] - colorSensor.red()) * (GREEN_VALUES[0] - colorSensor.red()) + (GREEN_VALUES[1] - colorSensor.green()) * (GREEN_VALUES[1] - colorSensor.green()) + (GREEN_VALUES[2] - colorSensor.blue())*(GREEN_VALUES[2] - colorSensor.blue());
        //need to add logic here
        return "WHITE";
    }
}