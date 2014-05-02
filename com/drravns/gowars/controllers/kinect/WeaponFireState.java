package com.drravns.gowars.controllers.kinect;

import edu.ufl.digitalworlds.j4k.Skeleton;

import java.util.HashMap;

/**
 * Created by Kitty on 5/1/2014.
 */
public class WeaponFireState implements ISkeletonState {

    private KinectController.PlayerBehavior k;

    private static HashMap<Integer, Long> timeStampCollection = new HashMap<Integer, Long>();
    private long lTimeStamp = 0;

    public WeaponFireState(KinectController.PlayerBehavior k) {
        this.k = k;
    }

    @Override
    public void leftHandBelowShoulder() {
        if (waveStarted) {
            if (startTimeStamp - lTimeStamp < 600) {

            } else {
                if (currentTimeStamp - startTimeStamp > 200) {
                    check(waveEndCoordinate, waveStartCoordinate, currentTimeStamp - startTimeStamp);
                }
                lTimeStamp = currentTimeStamp;
            }
        }
        System.out.println("Kinect:" + k.getId() + ": Weapon Select Mode");
        k.setState(new WeaponSelectState(k));

    }

    @Override
    public void leftHandAboveShoulder() {

    }


    @Override
    public void handMoved(Skeleton s, long timeStamp) {
        double[] shoulder = s.get3DJoint(Skeleton.SHOULDER_RIGHT);
        double[] hand = s.get3DJoint(Skeleton.HAND_RIGHT);
        double[] head = s.get3DJoint(Skeleton.HEAD);
        if (!waveStarted) {
            if (hand[0] > shoulder[0] && hand[1] > shoulder[1] && hand[1] < head[1]) {
                waveStarted = true;
                startTimeStamp = timeStamp;
                currentTimeStamp = timeStamp;
                waveStartCoordinate = hand;
                waveEndCoordinate = hand;
            }
        } else {
            if (hand[1] < shoulder[1]) {
                waveStarted = false;
            } else {
                currentTimeStamp = timeStamp;
                waveEndCoordinate = hand;
            }
        }
    }

    private void check(double[] waveEndCoordinate, double[] waveStartCoordinate, long duration) {

        double[] c = {waveEndCoordinate[0] - waveStartCoordinate[0],
                waveEndCoordinate[1] - waveStartCoordinate[1], waveEndCoordinate[2] - waveStartCoordinate[2]};
        double distance = Math.sqrt(Math.pow(c[0], 2.0) + Math.pow(c[1], 2.0) + Math.pow(c[2], 2.0));
        double velocity = distance / (duration / 1000.00);

        double sinValue = (c[1] * waveStartCoordinate[1]) / (waveStartCoordinate[1] *
                Math.sqrt(Math.pow(c[0], 2.0) + Math.pow(c[1], 2.0) + Math.pow(c[2], 2.0)));
        double angle = Math.asin(sinValue) * 180 / Math.PI;
        System.out.println("Kinect: " + k.getId() + ":Distance: " + distance);
        System.out.println("Kinect: " + k.getId() + ":Duration: " + duration);
        velocity = (velocity - 0.28) * 150 / 4.11 + 75;
        System.out.println("Kinect: " + k.getId() + ":Velocity: " + velocity);
        int an = (int) angle;
        int v = (int) velocity;
        String angleKey = "_|" + k.getId() + "|a|" + an;
        String velocityKey = "_|" + k.getId() + "|v|" + v;
        System.out.println("Kinect: " + k.getId() + ": " + angleKey);
        System.out.println("Kinect: " + k.getId() + ": " + velocityKey);
        k.getHandler().handleKey(angleKey);
        k.getHandler().handleKey(velocityKey);
        if (k.getId() == 1) {
            System.out.println("Kinect: " + k.getId() + ": Sending space");
            k.getHandler().handleKey("space");
        } else {
            System.out.println("Kinect: " + k.getId() + ": Sending enter");
            k.getHandler().handleKey("enter");
        }

    }

    private boolean waveStarted = false;
    private long startTimeStamp = 0;
    private long currentTimeStamp = 0;
    private double[] waveStartCoordinate = {0, 0, 0};
    private double[] waveEndCoordinate = {0, 0, 0};


}
