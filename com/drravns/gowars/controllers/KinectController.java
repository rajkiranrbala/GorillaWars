package com.drravns.gowars.controllers;

import com.drravns.gowars.actors.Gorilla;
import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;

/**
 * Created by Kitty on 4/29/2014.
 */
public class KinectController extends J4KSDK {

    private IKeyHandler handler;


    public KinectController(IKeyHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onDepthFrameEvent(short[] shorts, int[] ints, int[] ints2) {

    }

    @Override
    public void onSkeletonFrameEvent(float[] floats, boolean[] booleans) {
        
    }

    @Override
    public void onVideoFrameEvent(byte[] bytes) {

    }
}
