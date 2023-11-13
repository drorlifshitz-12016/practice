package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineTest extends OpenCvPipeline {

    Mat submat;

    @Override
    public void init(Mat mat) {
        submat = mat.submat(0, 50 , 0, 50);
    }

    @Override
    public Mat processFrame(Mat input) {
        return submat;
    }


}
