package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        resetRuntime();

        while (opModeIsActive()) {

        }
    }
}





//if (dpad_click){
  //      second_click = true;
    //    if (gamepad1.dpad_up){
      //  grabberRight.setPosition(highets[5]);
        //grabberLeft.setPosition(highets[5]);
        //}
    //    else if (gamepad1.dpad_right){
      //  grabberRight.setPosition(highets[4]);
        //grabberLeft.setPosition(highets[4]);
      //  }
        //else if (gamepad1.dpad_down) {
 //       grabberRight.setPosition(highets[3]);
   //     grabberLeft.setPosition(highets[3]);
     //   }
       // else if (gamepad1.dpad_left){
        //grabberRight.setPosition(highets[2]);
        //grabberLeft.setPosition(highets[2]);
      //  }
    //    }
  //      if (dpad_click && second_click){
//        grabberRight.setPosition(highets[0]);
        //grabberLeft.setPosition(highets[0]);
      //  }
    //    }
  //      }
//        }


