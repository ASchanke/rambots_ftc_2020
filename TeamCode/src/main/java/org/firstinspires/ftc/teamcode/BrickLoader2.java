package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BrickLoader2 {

    private DcMotor motor;

    public BrickLoader2(DcMotor motor) {
        this.motor = motor;
        //right motor on (full power)
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setPower(0);
    }

    public void setSpeed(float speed){
        motor.setPower(speed);
    }
}