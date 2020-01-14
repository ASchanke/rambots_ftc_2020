package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    private Servo hand;
    private EncodedServo wrist;
    private EncodedServo elbow;
    private CRServo shoulderRotate;
    private DcMotor shoulderElevate;
    private static final int armA = 420;//mm
    private static final int armB = 225;//mm
    public Telemetry telemetry;

    public Claw(Servo hand, CRServo wrist, CRServo elbow, CRServo shoulderRotate, DcMotor shoulderElevate, Telemetry telemetry, LSM303a compass0, LSM303a compass1, LSM303a compass2){
        this.hand = hand;
        this.wrist = new EncodedServo(wrist, compass2, compass1);
        this.elbow = new EncodedServo(elbow, compass1, compass0);
        this.wrist.start();
        this.elbow.start();
        this.shoulderRotate = shoulderRotate;
        this.shoulderElevate = shoulderElevate;
        shoulderElevate.setDirection(DcMotor.Direction.FORWARD); //might need to reverse
        this.wrist.setTargetPosition(0);
        this.elbow.setTargetPosition(0);
        shoulderRotate.setDirection(DcMotorSimple.Direction.FORWARD);
        shoulderRotate.setPower(0);
        shoulderElevate.setTargetPosition(0);
        shoulderElevate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.telemetry = telemetry;
    }

    /**
     *
     * @param moveX in mm
     * @param moveY in mm
     * @param rotateSpeed between -1 and 1
     */
    public void move(int moveX, int moveY, double rotateSpeed){
        int r = armA * armA + armB * armB - 2 * armA * armB * (int) Math.cos(elbow.getPosition());
        int x = (int) Math.cos(shoulderElevate.getCurrentPosition()) * r;
        int y = (int) Math.sin(shoulderElevate.getCurrentPosition()) * r;
        x+=moveX;
        y+=moveY;
        shoulderElevate.setTargetPosition((int)((Math.atan(y/x)/(2*Math.PI))*288));
        elbow.setTargetPosition(Math.acos(x*x+y*y-armA*armA-armB*armB+2*armA*armB));
        shoulderRotate.setPower(rotateSpeed);
        wrist.setTargetPosition(-(elbow.getTargetPosition()+shoulderElevate.getTargetPosition()*2*Math.PI));
    }

    public void setHandPosition(double position){
        hand.setPosition(position);
    }
}