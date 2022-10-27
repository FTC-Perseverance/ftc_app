package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class Mechanumdrive extends LinearOpMode {

    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initializes the HardwareMap variables
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");


        double platzhalter = gamepad1.right_trigger + gamepad1.left_trigger;
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        while (opModeIsActive()) {


            y = -gamepad1.left_stick_y; // Remember, this is reversed!
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;

            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeftMotor.setPower((y + x + rx) / 0.5);
            backLeftMotor.setPower((y - x + rx) / 0.5);
            frontRightMotor.setPower((y - x - rx) / 0.5);
            backRightMotor.setPower((y + x - rx) / 0.5);

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

    }