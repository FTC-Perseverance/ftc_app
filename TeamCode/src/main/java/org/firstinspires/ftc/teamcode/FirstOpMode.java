package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FirstOpMode extends LinearOpMode {
    //declare OpModde Members
    private ElapsedTime runtime = new ElapsedTime();
    private  DcMotor frontRightMotor = null;
    private  DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;


    @Override
        public void runOpMode () {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");


        //initializes the HardwareMap variables
            frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
            //Wait for the game to start
            waitForStart();
            //run until the end of the match
            while (opModeIsActive()) {
                telemetry.addData("Status", "Running");
                telemetry.update();

                frontRightMotor.setPower(1);
                frontLeftMotor.setPower(1);
                backRightMotor.setPower(1);
                backLeftMotor.setPower(1);

                sleep(5000);

                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
            }
            while (opModeIsActive()){

                    double vertical;
                    double horizontal;
                    double pivot;
                    vertical = -gamepad1.left_stick_y;
                    horizontal = gamepad1.left_stick_x;
                    pivot = gamepad1.right_stick_x;

                    //set motors to use power TeleOp tells it to use
                    frontRightMotor.setPower(pivot + (-vertical + horizontal));
                    frontLeftMotor.setPower(pivot + (-vertical - horizontal));
                    backRightMotor.setPower(pivot + (-vertical - horizontal));
                    backLeftMotor.setPower(pivot + (-vertical + horizontal));
        }
    }
}