package server.models;

import java.io.*;
import java.net.ServerSocket;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class DurationTest {




    public static void main(String[] args) {
        splitVideo("src/main/resources/DATA/video_examples/500.mp4",1);

    }

    private static void splitVideo(String inputVideo,int video_id) {
//        String inputVideo = "src/main/resources/DATA/video_examples/500.mp4";
        String outputVideo = "src/main/resources/DATA/video_examples/video"+ video_id + "_part"  ;



        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputVideo);
            grabber.setTimestamp(0);
            grabber.start();

//            int totalFrames = grabber.getLengthInFrames()  ;
//
//            int halfFrames = totalFrames  ;


            long totalTime = grabber.getLengthInTime();
            int partCount = (int) (totalTime/15000000 + 1 +2);
            System.out.println("total parts: " + partCount);
            long partTime = totalTime / partCount;


            int audioBitrate = grabber.getAudioBitrate();
            int videoCodec = grabber.getVideoCodec();
            int videoBitrate = grabber.getVideoBitrate();
            double frameRate = grabber.getFrameRate();
            System.out.println(grabber.getFrameRate());

            for (int i = 0; i < partCount; i++) {

                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideo+ i + ".mp4", grabber.getImageWidth(), grabber.getImageHeight());
                recorder.setVideoQuality(1);
                recorder.setAudioChannels(grabber.getAudioChannels());
                recorder.setAudioBitrate(audioBitrate);
                recorder.setVideoCodec(videoCodec);
                recorder.setFrameRate(frameRate);
                recorder.setVideoBitrate(videoBitrate);
                recorder.setSampleRate(grabber.getSampleRate());
                recorder.start();


                Frame frame;
                while ((frame = grabber.grabFrame()) != null && grabber.getTimestamp() < partTime*(i+1)) {

                    recorder.record(frame);
                }
                recorder.stop();
                System.out.println("part " + i + " saved");
            }
            grabber.stop();


            System.out.println("Video split successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

