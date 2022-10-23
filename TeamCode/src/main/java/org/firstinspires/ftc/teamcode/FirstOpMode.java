package org.firstinspires.ftc.teamcode;
import android.os.AsyncTask;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

@TeleOp
public class FirstOpMode extends LinearOpMode {

    //declare OpMode Members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRightMotor = null;
    private DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor LinearLiftingMotor = null;

    private DistanceSensor DS1 = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initializes the HardwareMap variables
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        LinearLiftingMotor = hardwareMap.get(DcMotor.class, "LinearLiftingMotor");

        DS1 = hardwareMap.get(DistanceSensor.class,"DS1");

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        int LiftingSpace = 0;
        long time;
        long time2;
        double vertical;
        double horizontal;
        double platzhalter = gamepad1.right_trigger + gamepad1.left_trigger;
        vertical = -gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)DS1;




        //Wait for the game to start
        waitForStart();
        //run until the end of the match
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            telemetry.addData("deviceName",DS1.getDeviceName() );


            if (platzhalter == 0) {
                vertical = -gamepad1.left_stick_y;
                horizontal = gamepad1.left_stick_x;
                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRightMotor.setPower((-vertical + horizontal) / 0.5);
                frontLeftMotor.setPower((-vertical - horizontal) / 0.5);
                backRightMotor.setPower((-vertical - horizontal) / 0.5);
                backLeftMotor.setPower((-vertical + horizontal) / 0.5);
            }

            //turn right
            if (gamepad1.right_trigger > 0.5) {
                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontRightMotor.setPower(0.75);
                frontLeftMotor.setPower(0.75);
                backRightMotor.setPower(0.75);
                backLeftMotor.setPower(0.75);
            } else if (gamepad1.right_trigger < 0.5) {
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
            }
            //turn left
            if (gamepad1.left_trigger > 0.5) {
                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRightMotor.setPower(0.75);
                frontLeftMotor.setPower(0.75);
                backRightMotor.setPower(0.75);
                backLeftMotor.setPower(0.75);
            }
            //prevent two commands happen at the same time
            else if (gamepad1.left_trigger < 0.4) {
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
            }
            //lifting Motors
            if (gamepad1.a ==true) {
                if(LiftingSpace == 0){
                LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                LinearLiftingMotor.setPower(1);
                time = System.currentTimeMillis();
                time2 = System.currentTimeMillis();
                while(time2 < time+1000){
                    time2 = System.currentTimeMillis();
                    if (platzhalter == 0) {
                        vertical = -gamepad1.left_stick_y;
                        horizontal = gamepad1.left_stick_x;
                        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        frontRightMotor.setPower((-vertical + horizontal) / 0.5);
                        frontLeftMotor.setPower((-vertical - horizontal) / 0.5);
                        backRightMotor.setPower((-vertical - horizontal) / 0.5);
                        backLeftMotor.setPower((-vertical + horizontal) / 0.5);
                    }

                    //turn right
                    if (gamepad1.right_trigger > 0.5) {
                        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        frontRightMotor.setPower(0.75);
                        frontLeftMotor.setPower(0.75);
                        backRightMotor.setPower(0.75);
                        backLeftMotor.setPower(0.75);
                    } else if (gamepad1.right_trigger < 0.5) {
                        frontRightMotor.setPower(0);
                        frontLeftMotor.setPower(0);
                        backRightMotor.setPower(0);
                        backLeftMotor.setPower(0);
                    }
                    //turn left
                    if (gamepad1.left_trigger > 0.5) {
                        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        frontRightMotor.setPower(0.75);
                        frontLeftMotor.setPower(0.75);
                        backRightMotor.setPower(0.75);
                        backLeftMotor.setPower(0.75);
                    }
                    //prevent two commands happen at the same time
                    else if (gamepad1.left_trigger < 0.4) {
                        frontRightMotor.setPower(0);
                        frontLeftMotor.setPower(0);
                        backRightMotor.setPower(0);
                        backLeftMotor.setPower(0);
                    }

                        LinearLiftingMotor.setPower(0);
                LiftingSpace = 1;
                } }


                if (LiftingSpace == 1){
                    LinearLiftingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    LinearLiftingMotor.setPower(1);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+1000) {
                        time2 = System.currentTimeMillis();

                        if (platzhalter == 0) {
                            vertical = -gamepad1.left_stick_y;
                            horizontal = gamepad1.left_stick_x;
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontRightMotor.setPower((-vertical + horizontal) / 0.5);
                            frontLeftMotor.setPower((-vertical - horizontal) / 0.5);
                            backRightMotor.setPower((-vertical - horizontal) / 0.5);
                            backLeftMotor.setPower((-vertical + horizontal) / 0.5);
                        }

                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontRightMotor.setPower(0.75);
                            frontLeftMotor.setPower(0.75);
                            backRightMotor.setPower(0.75);
                            backLeftMotor.setPower(0.75);
                        } else if (gamepad1.right_trigger < 0.5) {
                            frontRightMotor.setPower(0);
                            frontLeftMotor.setPower(0);
                            backRightMotor.setPower(0);
                            backLeftMotor.setPower(0);
                        }
                        //turn left
                        if (gamepad1.left_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontRightMotor.setPower(0.75);
                            frontLeftMotor.setPower(0.75);
                            backRightMotor.setPower(0.75);
                            backLeftMotor.setPower(0.75);
                        }
                        //prevent two commands happen at the same time
                        else if (gamepad1.left_trigger < 0.4) {
                            frontRightMotor.setPower(0);
                            frontLeftMotor.setPower(0);
                            backRightMotor.setPower(0);
                            backLeftMotor.setPower(0);

                        }
                    }
                    LinearLiftingMotor.setPower(0);
                    LiftingSpace = 0;
                }
            }


            if (DS1.getDistance(DistanceUnit.CM) < 20 && LiftingSpace == 1){
                sleep(1000);
            }



        }
    }
}
