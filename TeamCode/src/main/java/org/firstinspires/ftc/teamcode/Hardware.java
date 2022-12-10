package org.firstinspires.ftc.teamcode;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    //Create Motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor LinearLiftingMotor = null;
    public DcMotor ExtentionMotorUp = null;
    public DcMotor ExtentionMotorDown = null;
    public DistanceSensor DS1 = null;
    public DistanceSensor DS2 = null;
    public Servo claw1 = null;
    public Servo claw2 = null;

    //Additional Variables
    HardwareMap hardwareMap = null;
    public ElapsedTime runtime = new ElapsedTime();
    public final static double CLAW2_HOME = 0;
    public final static double CLAW1_HOME = 0;
    public final static double CLAW2_MIN_RANGE = 0.3;
    public final static double CLAW1_MIN_RANGE = 0.3;
    public final static double CLAW2_MAX_RANGE = 0.6;
    public final static double CLAW1_MAX_RANGE = 0.6;

    public Hardware(HardwareMap hwMap) {
        initialize(hwMap);


    }

    private void initialize(HardwareMap hwMap) {
        hardwareMap = hwMap;
        //Connect Motor
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        LinearLiftingMotor = hardwareMap.get(DcMotor.class, "LLM");
        ExtentionMotorUp = hardwareMap.get(DcMotor.class, "EMU");
        ExtentionMotorDown = hardwareMap.get(DcMotor.class, "EMD");
        DS1  = hardwareMap.get(DistanceSensor.class, "DS1");
        DS2 = hardwareMap.get(DistanceSensor.class, "DS2");
        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");




        //Set Up Motor Direction
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        ExtentionMotorUp.setDirection(DcMotorSimple.Direction.REVERSE);
        ExtentionMotorDown.setDirection(DcMotorSimple.Direction.REVERSE);


        //Set Motor Mode
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearLiftingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtentionMotorUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtentionMotorDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinearLiftingMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ExtentionMotorUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ExtentionMotorDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set zero power for Motors
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LinearLiftingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExtentionMotorUp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExtentionMotorDown.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set motors to use no power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        LinearLiftingMotor.setPower(0);
        ExtentionMotorUp.setPower(0);
        ExtentionMotorDown.setPower(0);
    }
    }


