package com.drravns.gowars.actors;
import com.drravns.gowars.weapons.ConcreteWeapon;

public interface IGameState
{
    void setAngle(int angle);
    void setVelcity(int velocity);
    void setWeapon(ConcreteWeapon weapon);
    void fire();
}
