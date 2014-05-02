package com.drravns.gowars.controllers.kinect;

import com.drravns.gowars.controllers.IKeyHandler;
import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;

import java.util.Calendar;

/**
 * Created by Kitty on 5/1/2014.
 */
public class KinectController extends J4KSDK {
    private PlayerBehavior p1;
    private PlayerBehavior p2;
    private IKeyHandler handler;

    private KinectController() {
        p1 = new PlayerBehavior(1);
        p2 = new PlayerBehavior(2);
    }

    public void setHandler(IKeyHandler handler) {
        this.handler = handler;
    }

    private static KinectController instance = null;

    public static KinectController getKinectController() {
        if (instance == null) {
            instance = new KinectController();
            System.out.println(instance.start(true, J4KSDK.NUI_IMAGE_RESOLUTION_320x240, J4KSDK.NUI_IMAGE_RESOLUTION_640x480));
        }
        return instance;
    }

    public void enableController() {
        this.enabled = true;
    }

    public void disableController() {
        this.enabled = false;
    }

    private boolean enabled = false;

    @Override
    public void onDepthFrameEvent(short[] shorts, int[] ints, int[] ints2) {

    }

    @Override
    public void onSkeletonFrameEvent(float[] floats, boolean[] booleans) {
        //  System.out.println(Arrays.toString(booleans));
        if (enabled) {
            int count = 0;
            Skeleton[] skeletons = new Skeleton[2];
            for (int i = 0; i < booleans.length; i++) {
                if (booleans[i]) {
                    skeletons[count++] = Skeleton.getSkeleton(i, floats, booleans);
                    if (count == 2) {
                        break;
                    }
                }
            }
            if (count == 2) {
                if (!skeletons[0].isTracked()) {
                    skeletons[0].setIsTracked(true);
                }
                if (!skeletons[1].isTracked()) {
                    skeletons[1].setIsTracked(true);
                }
                if (skeletons[0].get3DJointX(Skeleton.SHOULDER_CENTER) < skeletons[1].get3DJointX(Skeleton.SHOULDER_CENTER)) {
                    p1.onSkeletonFrame(skeletons[0]);
                    p2.onSkeletonFrame(skeletons[1]);
                } else {
                    p2.onSkeletonFrame(skeletons[0]);
                    p1.onSkeletonFrame(skeletons[1]);
                }
            }
        }
    }

    @Override
    public void onVideoFrameEvent(byte[] bytes) {

    }

    public class PlayerBehavior {
        private ISkeletonState state;
        private int id;

        public PlayerBehavior(int id) {
            this.setState(new WeaponSelectState(this));
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public IKeyHandler getHandler() {
            return handler;
        }

        public void setState(ISkeletonState state) {
            this.state = state;
        }


        public void onSkeletonFrame(Skeleton s) {
            s.setIsTracked(true);
            if (s.get3DJointY(J4KSDK.NUI_SKELETON_POSITION_HAND_LEFT) < s.get3DJointY(J4KSDK.NUI_SKELETON_POSITION_SHOULDER_CENTER)) {
                state.leftHandBelowShoulder();
            } else {
                state.leftHandAboveShoulder();
            }
            state.handMoved(s, Calendar.getInstance().getTimeInMillis());
        }
    }
}
