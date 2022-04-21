package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class FirstOpMode extends LinearOpMode {

    //declare OpMode Members
    private ElapsedTime runtime = new ElapsedTime();
    private  DcMotor frontRightMotor = null;
    private  DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;
    private  DcMotor LinearLiftingMotor = null;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initializes the HardwareMap variables
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        LinearLiftingMotor =hardwareMap.get(DcMotor.class, "LinearLiftingMotor");


        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        double vertical;
        double horizontal;
        double platzhalter = gamepad1.right_trigger + gamepad1.left_trigger ;
        vertical = -gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;


        //set motors to use power TeleOp tells it to use
        frontRightMotor.setPower(-vertical + horizontal);
        frontLeftMotor.setPower(-vertical - horizontal);
        backRightMotor.setPower (-vertical - horizontal);
        backLeftMotor.setPower(-vertical + horizontal);


        //Wait for the game to start
        waitForStart();
        //run until the end of the match
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();


            if (platzhalter == 0){
                vertical = -gamepad1.left_stick_y;
                horizontal = gamepad1.left_stick_x;
                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRightMotor.setPower((-vertical + horizontal)/0.5);
                frontLeftMotor.setPower((-vertical - horizontal)/0.5);
                backRightMotor.setPower((-vertical - horizontal)/0.5);
                backLeftMotor.setPower((-vertical + horizontal)/0.5);
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
            }
            else if (gamepad1.right_trigger < 0.5) {
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
            }

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

            else if (gamepad1.left_trigger < 0.4) {
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
            }
            if (gamepad1.right_stick_x > 0.5){
                    LinearLiftingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    LinearLiftingMotor.setPower(0.75);
                   }
            if(gamepad1.right_stick_x< 0.4 && gamepad1.right_stick_x> -0.4) {
                   LinearLiftingMotor.setPower(0);
               }
            if (gamepad1.right_stick_x<-0.4){
                   LinearLiftingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                   LinearLiftingMotor.setPower(0.75);
               }

        }
    }
}
