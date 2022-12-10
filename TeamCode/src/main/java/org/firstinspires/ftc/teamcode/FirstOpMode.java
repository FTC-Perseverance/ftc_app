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
    private DcMotor ExtentionMotorUp = null;
    private DcMotor ExtentionMotorDown = null;
    private DistanceSensor DS1 = null;
    private Servo claw2 =null;
    private Servo claw1 = null;


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
        ExtentionMotorUp = hardwareMap.get(DcMotor.class,"ExtentionMotorUp");
        ExtentionMotorDown = hardwareMap.get(DcMotor.class,"ExtentionMotorDown");
        DS1 = hardwareMap.get(DistanceSensor.class,"DS1");
        claw1 = hwMap.servo.get("claw1");
        claw2 = hwMap.servo.get("claw2");

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        ExtentionMotorUp.setDirection(DcMotorSimple.Direction.FORWARD);
        ExtentionMotorDown.setDirection(DcMotorSimple.Direction.FORWARD);

        int LiftingSpace = 0;
        int LiftingSpace2 = 0;
        int ExtentionMotorSpace =0;
        int ExtentionMotorSpace2=0;
        int DistanceSpace = 0;
        int GradSpace = 0;
        double j=1;
        double k = 1.1;
        double p = 0.3;
        double m = 0;
        double DS1Value = 0;
        double l = 0.5;
        long time;
        long time2;
        int GradSpace2 = 0;
        double CLAW2_HOME = 0;
        double CLAW1_HOME = 0;
        double CLAW2_MIN_RANGE = 0.3;
        double CLAW1_MIN_RANGE = 0.3;
        double CLAW2_MAX_RANGE = 0.6;
        double CLAW1_MAX_RANGE = 0.6;

        double platzhalter = gamepad1.right_trigger + gamepad1.left_trigger;
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)DS1;


        int a = 1;
        int b = 1;

        //Wait for the game to start
        waitForStart();
        //run until the end of the match
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            telemetry.addData("deviceName",DS1.getDeviceName() );

            if(gamepad1.y == true){
                int c=1;
                if(gamepad1.y==true){
                    c=1;
                }
                while(c==1&&a==1){
                    ExtentionMotorDown.setDirection(DcMotorSimple.Direction.FORWARD);
                    ExtentionMotorDown.setPower(0.1);
                    b=1;
                    if(gamepad1.y==true){
                        c=1;
                    }
                    if(gamepad1.y==false){
                        c=0;
                    }
                }
                while(c==1&&a==0){
                    ExtentionMotorDown.setDirection(DcMotorSimple.Direction.REVERSE);
                    ExtentionMotorDown.setPower(0.1);
                    if(gamepad1.y==true){
                        c=1;
                    }
                    if(gamepad1.y==false){
                        c=0;
                    }
                    b=0;}
                if(b==1){
                    a=0;
                }
                else{
                    a=1;
                }

            }
            if (platzhalter == 0) {
                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                x = gamepad1.left_stick_x;
                if(x<0.2&&x>-0.2){
                    x=0;
                }
                if(y>0){
                    m=p;
                }
                if(y<0){
                    m=l;
                }

                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setPower((y + x + rx)/(k+m));
                backLeftMotor.setPower((y - x + rx)/(k+m));
                frontRightMotor.setPower((y - x - rx)/j);
                backRightMotor.setPower((y + x - rx)/j);
            }
            x = gamepad1.left_stick_x;
            //turn right
            if (gamepad1.right_trigger > 0.5) {
                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRightMotor.setPower(0.75);
                frontLeftMotor.setPower(1);
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
            //turn arround plate
            if (gamepad1.b == true) {
                if(ExtentionMotorSpace2 == 0){
                    ExtentionMotorDown.setDirection(DcMotorSimple.Direction.FORWARD);
                    ExtentionMotorDown.setPower(1);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+2430) {
                        time2 = System.currentTimeMillis();
                        if (platzhalter == 0) {
                            y = -gamepad1.left_stick_y; // Remember, this is reversed!
                            x = gamepad1.left_stick_x;
                            if(x<0.2&&x>-0.2){
                                x=0;
                            }
                            if(y>0){
                                m=p;
                            }
                            if(y<0){
                                m=l;
                            }

                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setPower((y + x + rx)/(k+m));
                            backLeftMotor.setPower((y - x + rx)/(k+m));
                            frontRightMotor.setPower((y - x - rx)/j);
                            backRightMotor.setPower((y + x - rx)/j);
                        }
                        x = gamepad1.left_stick_x;
                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                            backLeftMotor.setPower(0);}

                    }
                    ExtentionMotorDown.setPower(0);
                    ExtentionMotorSpace = 1;
                }



                if (ExtentionMotorSpace2 == 1){
                    ExtentionMotorDown.setDirection(DcMotorSimple.Direction.REVERSE);
                    ExtentionMotorDown.setPower(2);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+2500) {
                        time2 = System.currentTimeMillis();

                        if (platzhalter == 0) {
                            y = -gamepad1.left_stick_y; // Remember, this is reversed!
                            x = gamepad1.left_stick_x;
                            if(x<0.2&&x>-0.2){
                                x=0;
                            }
                            if(y>0){
                                m=p;
                            }
                            if(y<0){
                                m=l;
                            }

                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setPower((y + x + rx)/(k+m));
                            backLeftMotor.setPower((y - x + rx)/(k+m));
                            frontRightMotor.setPower((y - x - rx)/j);
                            backRightMotor.setPower((y + x - rx)/j);
                        }
                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                    ExtentionMotorDown.setPower(0);
                    ExtentionMotorSpace = 0;
                }

                if(ExtentionMotorSpace == 0){
                    ExtentionMotorSpace2 = 0;
                }
                if(ExtentionMotorSpace ==1){
                    ExtentionMotorSpace2 = 1;
                }
            }
            if (gamepad1.a == true) {
                if(LiftingSpace2 == 0){
                    LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                    LinearLiftingMotor.setPower(1);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+1000) {
                        time2 = System.currentTimeMillis();
                        if (platzhalter == 0) {
                            y = -gamepad1.left_stick_y; // Remember, this is reversed!
                            x = gamepad1.left_stick_x;
                            if(x<0.2&&x>-0.2){
                                x=0;
                            }
                            if(y>0){
                                m=p;
                            }
                            if(y<0){
                                m=l;
                            }

                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setPower((y + x + rx)/(k+m));
                            backLeftMotor.setPower((y - x + rx)/(k+m));
                            frontRightMotor.setPower((y - x - rx)/j);
                            backRightMotor.setPower((y + x - rx)/j);
                        }
                        x = gamepad1.left_stick_x;
                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                            backLeftMotor.setPower(0);}

                    }
                    LinearLiftingMotor.setPower(0);
                    LiftingSpace = 1;
                }



                if (LiftingSpace2 == 1){
                    LinearLiftingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    LinearLiftingMotor.setPower(1);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+1000) {
                        time2 = System.currentTimeMillis();

                        if (platzhalter == 0) {
                            y = -gamepad1.left_stick_y; // Remember, this is reversed!
                            x = gamepad1.left_stick_x;
                            if(x<0.2&&x>-0.2){
                                x=0;
                            }
                            if(y>0){
                                m=p;
                            }
                            if(y<0){
                                m=l;
                            }

                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setPower((y + x + rx)/(k+m));
                            backLeftMotor.setPower((y - x + rx)/(k+m));
                            frontRightMotor.setPower((y - x - rx)/j);
                            backRightMotor.setPower((y + x - rx)/j);
                        }
                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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

                if(LiftingSpace == 0){
                    LiftingSpace2 = 0;
                }
                if(LiftingSpace ==1){
                    LiftingSpace2 = 1;
                }
            }
            if(gamepad1.x == true) {

                if(GradSpace2 == 1){
                    /*ExtentionMotorUp.setDirection(DcMotorSimple.Direction.FORWARD);
                    ExtentionMotorUp.setPower(0.5);
                    GradSpace = 0;

                     */
                    claw2.setPosition(CLAW2_MIN_RANGE);
                    claw1.setPosition(CLAW1_MIN_RANGE);
                    GradSpace = 0;

                }
                if(GradSpace2 == 0){
                    claw2.setPosition(CLAW2_MAX_RANGE);
                    claw1.setPosition(CLAW1_MAX_RANGE);
                }
                   /* ExtentionMotorUp.setDirection(DcMotorSimple.Direction.REVERSE);
                    ExtentionMotorUp.setPower(0.5);
                    time = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    while(time2 < time+200) {
                        time2 = System.currentTimeMillis();

                        if (platzhalter == 0) {
                            y = -gamepad1.left_stick_y; // Remember, this is reversed! down is -1 up is 1
                            x = gamepad1.left_stick_x;
                            if(x<0.2&&x>-0.2){
                                x=0;
                            }
                            if(y>0){
                                m=p;
                            }
                            if(y<0){
                                m=l;
                            }

                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setPower((y + x + rx)/(k+m));
                            backLeftMotor.setPower((y - x + rx)/(k+m));
                            frontRightMotor.setPower((y - x - rx)/j);
                            backRightMotor.setPower((y + x - rx)/j);
                        }
                        //turn right
                        if (gamepad1.right_trigger > 0.5) {
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                    ExtentionMotorUp.setPower(0);
                    GradSpace = 1;
                }
                */
            }

            while(LiftingSpace2==1){
                DS1Value = DS1.getDistance(DistanceUnit.CM);
                int dis =0;
                if (LiftingSpace2 == 1 && DS1Value < 25){
                    DistanceSpace = 1;
                    while(DistanceSpace == 1){



                        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        frontRightMotor.setPower(1);
                        frontLeftMotor.setPower(1);
                        backRightMotor.setPower(1);
                        backLeftMotor.setPower(1);
                        sleep(50);
                        frontRightMotor.setPower(0);
                        frontLeftMotor.setPower(0);
                        backRightMotor.setPower(0);
                        backLeftMotor.setPower(0);
                        DS1Value = DS1.getDistance(DistanceUnit.CM);
                        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        frontRightMotor.setPower(0.1);
                        frontLeftMotor.setPower(0.1);
                        backRightMotor.setPower(0.1);
                        backLeftMotor.setPower(0.1);
                        while(DS1Value > 20) {
                            DS1Value = DS1.getDistance(DistanceUnit.CM);
                        }
                        frontRightMotor.setPower(0);
                        frontLeftMotor.setPower(0);
                        backRightMotor.setPower(0);
                        backLeftMotor.setPower(0);
                        if(DS1Value>12.65){

                            while(DS1Value>12){
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontRightMotor.setPower(1);
                                frontLeftMotor.setPower(1);
                                backRightMotor.setPower(1);
                                backLeftMotor.setPower(1);
                            }
                            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontRightMotor.setPower(1);
                            frontLeftMotor.setPower(1);
                            backRightMotor.setPower(1);
                            backLeftMotor.setPower(1);
                            sleep(30);
                            frontRightMotor.setPower(0);
                            frontLeftMotor.setPower(0);
                            backRightMotor.setPower(0);
                            backLeftMotor.setPower(0);
                        }
                        if(DS1Value<13.1){
                            while(DS1Value<12.65){
                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontRightMotor.setPower(1);
                                frontLeftMotor.setPower(1);
                                backRightMotor.setPower(1);
                                backLeftMotor.setPower(1);
                            }
                            frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                            frontRightMotor.setPower(1);
                            frontLeftMotor.setPower(1);
                            backRightMotor.setPower(1);
                            backLeftMotor.setPower(1);
                            sleep(30);
                            frontRightMotor.setPower(0);
                            frontLeftMotor.setPower(0);
                            backRightMotor.setPower(0);
                            backLeftMotor.setPower(0);
                        }
                        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        LinearLiftingMotor.setPower(1);
                        sleep(2000);
                        LinearLiftingMotor.setPower(0);

                        DistanceSpace = 0;
                    }

                }

                if (platzhalter == 0) {
                    y = -gamepad1.left_stick_y; // Remember, this is reversed!
                    x = gamepad1.left_stick_x;
                    if(x<0.2&&x>-0.2){
                        x=0;
                    }
                    if(y>0){
                        m=p;
                    }
                    if(y<0){
                        m=l;
                    }

                    frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                    frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                    backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    frontLeftMotor.setPower((y + x + rx)/(k+m));
                    backLeftMotor.setPower((y - x + rx)/(k+m));
                    frontRightMotor.setPower((y - x - rx)/j);
                    backRightMotor.setPower((y + x - rx)/j);
                }
                //turn right
                if (gamepad1.right_trigger > 0.5) {
                    frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                    backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                if (gamepad1.a == true) {
                    if(LiftingSpace2 == 0){
                        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        LinearLiftingMotor.setPower(1);
                        time = System.currentTimeMillis();
                        time2 = System.currentTimeMillis();
                        while(time2 < time+1000) {
                            time2 = System.currentTimeMillis();
                            if (platzhalter == 0) {
                                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                                x = gamepad1.left_stick_x;
                                if(x<0.2&&x>-0.2){
                                    x=0;
                                }
                                if(y>0){
                                    m=p;
                                }
                                if(y<0){
                                    m=l;
                                }

                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setPower((y + x + rx)/(k+m));
                                backLeftMotor.setPower((y - x + rx)/(k+m));
                                frontRightMotor.setPower((y - x - rx)/j);
                                backRightMotor.setPower((y + x - rx)/j);
                            }

                            //turn right
                            if (gamepad1.right_trigger > 0.5) {
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                        LiftingSpace = 1;
                    }



                    if (LiftingSpace2 == 1){
                        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        LinearLiftingMotor.setPower(1);
                        time = System.currentTimeMillis();
                        time2 = System.currentTimeMillis();
                        while(time2 < time+1000) {
                            time2 = System.currentTimeMillis();

                            if (platzhalter == 0) {
                                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                                x = gamepad1.left_stick_x;
                                if(x<0.2&&x>-0.2){
                                    x=0;
                                }
                                if(y>0){
                                    m=p;
                                }
                                if(y<0){
                                    m=l;
                                }

                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setPower((y + x + rx)/(k+m));
                                backLeftMotor.setPower((y - x + rx)/(k+m));
                                frontRightMotor.setPower((y - x - rx)/j);
                                backRightMotor.setPower((y + x - rx)/j);
                            }
                            //turn right
                            if (gamepad1.right_trigger > 0.5) {
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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

                    if(LiftingSpace == 0){
                        LiftingSpace2 = 0;
                    }
                    if(LiftingSpace ==1){
                        LiftingSpace2 = 1;
                    }
                }
                if (gamepad1.b == true) {
                    if(ExtentionMotorSpace2 == 0){
                        ExtentionMotorDown.setDirection(DcMotorSimple.Direction.FORWARD);
                        ExtentionMotorDown.setPower(1);
                        time = System.currentTimeMillis();
                        time2 = System.currentTimeMillis();
                        while(time2 < time+2430) {
                            time2 = System.currentTimeMillis();
                            if (platzhalter == 0) {
                                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                                x = gamepad1.left_stick_x;
                                if(x<0.2&&x>-0.2){
                                    x=0;
                                }
                                if(y>0){
                                    m=p;
                                }
                                if(y<0){
                                    m=l;
                                }

                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setPower((y + x + rx)/(k+m));
                                backLeftMotor.setPower((y - x + rx)/(k+m));
                                frontRightMotor.setPower((y - x - rx)/j);
                                backRightMotor.setPower((y + x - rx)/j);
                            }
                            //turn right
                            if (gamepad1.right_trigger > 0.5) {
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                                backLeftMotor.setPower(0);}

                        }
                        ExtentionMotorDown.setPower(0);
                        ExtentionMotorSpace = 1;
                    }



                    if (ExtentionMotorSpace2 == 1){
                        ExtentionMotorDown.setDirection(DcMotorSimple.Direction.REVERSE);
                        ExtentionMotorDown.setPower(2);
                        time = System.currentTimeMillis();
                        time2 = System.currentTimeMillis();
                        while(time2 < time+2500) {
                            time2 = System.currentTimeMillis();

                            if (platzhalter == 0) {
                                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                                x = gamepad1.left_stick_x;
                                if(x<0.2&&x>-0.2){
                                    x=0;
                                }
                                if(y>0){
                                    m=p;
                                }
                                if(y<0){
                                    m=l;
                                }

                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setPower((y + x + rx)/(k+m));
                                backLeftMotor.setPower((y - x + rx)/(k+m));
                                frontRightMotor.setPower((y - x - rx)/j);
                                backRightMotor.setPower((y + x - rx)/j);
                            }
                            x = gamepad1.left_stick_x;
                            //turn right
                            if (gamepad1.right_trigger > 0.5) {
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                        ExtentionMotorDown.setPower(0);
                        ExtentionMotorSpace = 0;
                    }

                    if(ExtentionMotorSpace == 0){
                        ExtentionMotorSpace2 = 0;
                    }
                    if(ExtentionMotorSpace ==1){
                        ExtentionMotorSpace2 = 1;
                    }
                }
                if(gamepad1.x == true) {

                    if(GradSpace2 == 1){
                        ExtentionMotorUp.setDirection(DcMotorSimple.Direction.FORWARD);
                        ExtentionMotorUp.setPower(0.5);
                        GradSpace = 0;
                    }
                    if(GradSpace2 == 0){
                        ExtentionMotorUp.setDirection(DcMotorSimple.Direction.REVERSE);
                        ExtentionMotorUp.setPower(0.5);
                        time = System.currentTimeMillis();
                        time2 = System.currentTimeMillis();
                        while(time2 < time+200) {
                            time2 = System.currentTimeMillis();

                            if (platzhalter == 0) {
                                y = -gamepad1.left_stick_y; // Remember, this is reversed!
                                x = gamepad1.left_stick_x;
                                if(x<0.2&&x>-0.2){
                                    x=0;
                                }
                                if(y>0){
                                    m=p;
                                }
                                if(y<0){
                                    m=l;
                                }

                                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setPower((y + x + rx)/(k+m));
                                backLeftMotor.setPower((y - x + rx)/(k+m));
                                frontRightMotor.setPower((y - x - rx)/j);
                                backRightMotor.setPower((y + x - rx)/j);
                            }
                            //turn right
                            if (gamepad1.right_trigger > 0.5) {
                                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
                                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
                        ExtentionMotorUp.setPower(0);
                        GradSpace = 1;
                    }
                    if(GradSpace ==1){
                        GradSpace2 =1;

                    }
                    if(GradSpace ==0){
                        GradSpace2 =0;
                    }

                }
            }
        }}}
