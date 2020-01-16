package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TestOp3 extends LinearOpMode {
    Compass imu;
    double[] accel;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Compass.class, "imu");
        waitForStart();

        accel = imu.angles(imu.getAccelerometerRaw());
        telemetry.addData("accel", accel);
        telemetry.update();
    }
}
