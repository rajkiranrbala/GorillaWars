package com.drravns.gowars.controllers.kinect;

import edu.ufl.digitalworlds.j4k.Skeleton;

/**
 * Created by Kitty on 5/1/2014.
 */
public interface ISkeletonState {
    public void leftHandBelowShoulder();

    public void leftHandAboveShoulder();

    public void handMoved(Skeleton s, long timeStamp);
}
