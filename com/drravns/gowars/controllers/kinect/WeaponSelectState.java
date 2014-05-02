package com.drravns.gowars.controllers.kinect;

import edu.ufl.digitalworlds.j4k.Skeleton;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Kitty on 5/1/2014.
 */
public class WeaponSelectState implements ISkeletonState {

    private KinectController.PlayerBehavior k;

    public WeaponSelectState(KinectController.PlayerBehavior k) {
        this.k = k;
    }

    @Override
    public void leftHandBelowShoulder() {

    }

    @Override
    public void leftHandAboveShoulder() {
        System.out.println("Kinect: " + k.getId() + ": Switching to fire state");
        k.setState(new WeaponFireState(k));
    }

    @Override
    public void handMoved(Skeleton s, long timeStamp) {
        //   System.out.println(s.get3DJointX(Skeleton.HAND_RIGHT));
        if (this.timeStamp.get() < timeStamp) {
            this.timeStamp.set(timeStamp);
            if (s.get3DJointY(Skeleton.HAND_RIGHT) < s.get3DJointY(Skeleton.SHOULDER_RIGHT)) {
                if (!started) {
                    if (s.get3DJointX(Skeleton.HAND_RIGHT) > s.get3DJointX(Skeleton.SHOULDER_RIGHT)) {
                        started = true;
                        startX = s.get3DJointX(Skeleton.HAND_RIGHT);
                        startTimeStamp = timeStamp;
                        rtl = true;
                    } else if (s.get3DJointX(Skeleton.HAND_RIGHT) < s.get3DJointX(Skeleton.SHOULDER_LEFT)) {
                        started = true;
                        startTimeStamp = timeStamp;
                        startX = s.get3DJointX(Skeleton.HAND_RIGHT);
                        rtl = false;
                    } else {
                        started = false;
                    }
                } else {
                    if (rtl) {
                        if (s.get3DJointX(Skeleton.HAND_RIGHT) > startX) {
                            started = false;
                        } else {
                            if (s.get3DJointX(Skeleton.HAND_RIGHT) < s.get3DJointX(Skeleton.SHOULDER_LEFT)) {
                                if (timeStamp - startTimeStamp > 2000) {
                                    started = false;
                                    return;
                                }
                                System.out.println("Kinect:" + k.getId() + ": Previous Weapon");
                                if (k.getId() == 1) {
                                    System.out.println("Kinect:" + k.getId() + ": Sending q");
                                    k.getHandler().handleKey("q");
                                } else {
                                    System.out.println("Kinect:" + k.getId() + ": Sending i");
                                    k.getHandler().handleKey("i");
                                }

                                started = false;
                            }
                        }

                    } else {
                        if (s.get3DJointX(Skeleton.HAND_RIGHT) < startX) {
                            started = false;
                        } else {
                            if (s.get3DJointX(Skeleton.HAND_RIGHT) > s.get3DJointX(Skeleton.SHOULDER_RIGHT)) {
                                if (timeStamp - startTimeStamp > 2000) {
                                    started = false;
                                    return;
                                }
                                System.out.println("Kinect: " + k.getId() + ": Next Weapon");
                                if (k.getId() == 1) {
                                    System.out.println("Kinect: " + k.getId() + ": Sending e");
                                    k.getHandler().handleKey("e");
                                } else {
                                    System.out.println("Kinect: " + k.getId() + ": Sending p");
                                    k.getHandler().handleKey("p");
                                }
                                started = false;
                            }
                        }
                    }
                }
            } else {
                started = false;
            }
        }
    }


    private AtomicInteger i = new AtomicInteger(0);
    private boolean started = false;
    private boolean rtl = false;
    private double startX;
    private long startTimeStamp = 0;
    private AtomicLong timeStamp = new AtomicLong(0);

}
