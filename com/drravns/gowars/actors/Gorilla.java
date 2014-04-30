package com.drravns.gowars.actors;

import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;
import com.drravns.gowars.controllers.IPlayerControlTarget;
import com.drravns.gowars.observers.DefaultSubject;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.observers.ISubject;
import com.drravns.gowars.weapons.IWeaponDecorator;

public class Gorilla extends Actor implements ISubject<IPlayerObserver>,
        IPlayerControlTarget {
    private static int count = 0;
    private int id;
    private int angle;
    private int velocity;
    private boolean reverse;
    private int selectedWeaponIndex = 0;
    private Obstacle obstacle;
    private int obstaclePositionY = 0;
    DefaultSubject<IPlayerObserver> playerSubject = new DefaultSubject<IPlayerObserver>();

    public Gorilla(boolean reverse, Obstacle o) {
        id = ++count;
        this.reverse = reverse;
        this.obstacle = o;
        obstaclePositionY = o.getY();
    }

    public int getId() {
        return this.id;
    }

    public void act() {
        if (obstaclePositionY != obstacle.getY()) {
            obstaclePositionY = obstacle.getY();
            int xPosition = obstacle.getX()
                    + (obstacle.getImage().getWidth() - getImage().getWidth())
                    / 2;
            setLocation(xPosition, obstaclePositionY - getImage().getHeight()
                    - 1);
        }
    }

    @Override
    public void attach(IPlayerObserver observer) {
        playerSubject.attach(observer);
    }

    @Override
    public void detach(IPlayerObserver observer) {
        playerSubject.detach(observer);
    }

    @Override
    public void increaseVelocity() {
        setVelocity(velocity + 1);
    }

    @Override
    public void decreaseVelocity() {
        setVelocity(velocity - 1);
    }

    @Override
    public void increaseAngle() {
        setAngle(angle + 1);
    }

    @Override
    public void decreaseAngle() {
        setAngle(angle - 1);
    }

    @Override
    public void setAngle(int angle) {
        if (angle <= 180 && angle >= 0) {
            this.angle = angle;
            for (IPlayerObserver o : playerSubject.getObservers()) {
                o.onAngleChanged(this.id, angle);
            }
        }
    }

    @Override
    public void setVelocity(int v) {
        if (v <= 100 && v >= 1) {
            this.velocity = v;
            for (IPlayerObserver o : playerSubject.getObservers()) {
                o.onVelocityChanged(this.id, v);
            }
        }
    }

    @Override
    public void nextWeapon() {
        selectedWeaponIndex = (selectedWeaponIndex + 1)
                % Weapon.getWeapons().length;
        for (IPlayerObserver o : playerSubject.getObservers()) {
            o.onWeaponChanged(id, Weapon.getWeapons()[selectedWeaponIndex]);
        }
    }

    @Override
    public void previousWeapon() {
        selectedWeaponIndex = (selectedWeaponIndex - 1)
                % Weapon.getWeapons().length;
        for (IPlayerObserver o : playerSubject.getObservers()) {
            o.onWeaponChanged(id, Weapon.getWeapons()[selectedWeaponIndex]);
        }
    }

    @Override
    public void launchWeapon() {
        IWeaponDecorator d = Weapon
                .createWeapon(Weapon.getWeapons()[selectedWeaponIndex]);
        Weapon w = new Weapon(d, velocity, angle, reverse);
        getWorld().addObject(w, getX() + 8, getY() - 36);
        for (IPlayerObserver o : playerSubject.getObservers()) {
            o.onWeaponFired(id, w);
        }
    }

    public void inflictDamage(double damage) {
        for (IPlayerObserver o : playerSubject.getObservers()) {
            o.onDamageReceived(id, damage);
        }
    }
}