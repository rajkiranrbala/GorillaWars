package com.drravns.gowars.actors;

import com.drravns.gowars.controllers.IPlayerControlTarget;
import com.drravns.gowars.observers.DefaultSubject;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.observers.ISubject;
import com.drravns.gowars.weapons.IWeaponDecorator;
import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;
import greenfoot.World;

public class Gorilla extends Actor implements ISubject<IPlayerObserver>,
        IPlayerControlTarget {
    private static int count = 0;
    private int id;
    private int angle = 0;
    private int velocity = 0;
    private boolean reverse;
    private int selectedWeaponIndex = 0;
    private Obstacle obstacle;
    private int obstaclePositionY = 0;
    DefaultSubject<IPlayerObserver> playerSubject = new DefaultSubject<IPlayerObserver>();

    public Gorilla(boolean reverse, Obstacle o) {
        id = ++count;
        this.reverse = reverse;
        this.obstacle = o;
        if (reverse) {
            setImage("images/gorilla_right.png");
        } else {
            setImage("images/gorilla_left.png");
        }
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        act();
    }

    public int getId() {
        return this.id;
    }

    public void act() {
        if (obstaclePositionY != obstacle.getY()) {
            obstaclePositionY = obstacle.getY();
            int yPosition = obstacle.getY() - 384 - 38;
            int xPosition = obstacle.getX();
            setLocation(xPosition, yPosition);
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
        if (angle <= 90 && angle >= 0) {
            this.angle = angle;
            for (IPlayerObserver o : playerSubject.getObservers()) {
                o.onAngleChanged(this.id, angle);
            }
        }
    }

    @Override
    public void setVelocity(int velocity) {
        if (velocity <= 200 && velocity >= 1) {
            this.velocity = velocity;
            for (IPlayerObserver o : playerSubject.getObservers()) {
                o.onVelocityChanged(this.id, velocity);
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
        selectedWeaponIndex = (selectedWeaponIndex + Weapon.getWeapons().length - 1)
                % Weapon.getWeapons().length;
        for (IPlayerObserver o : playerSubject.getObservers()) {
            o.onWeaponChanged(id, Weapon.getWeapons()[selectedWeaponIndex]);
        }
    }

    @Override
    public void launchWeapon() {
        IWeaponDecorator d = Weapon
                .createWeapon(Weapon.getWeapons()[selectedWeaponIndex]);

        Weapon w = null;
        if (obstacle instanceof MovingObstacle) {
            w = new Weapon(d, velocity, angle, reverse, true);
        } else {
            w = new Weapon(d, velocity, angle, reverse, false);
        }
        getWorld().addObject(w, getX(), getY() - 120);
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