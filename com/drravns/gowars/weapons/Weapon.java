package com.drravns.gowars.weapons;

import com.drravns.gowars.actors.Gorilla;
import com.drravns.gowars.actors.Obstacle;
import com.drravns.gowars.observers.DefaultSubject;
import com.drravns.gowars.observers.ISubject;
import com.drravns.gowars.observers.IWeaponObserver;
import greenfoot.Actor;
import greenfoot.World;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Weapon extends Actor implements ISubject<IWeaponObserver> {

    private int angle;
    private double velocity;
    private boolean reverse;
    private IWeaponDecorator weaponDecorator;
    private DefaultSubject<IWeaponObserver> weaponSubject = new DefaultSubject<IWeaponObserver>();

    private int initialX, initialY;
    private boolean inflictDamage = false;
    private boolean endFlight = false;
    private Gorilla target = null;
    private long startTime;
    private static Random r = new Random();

    private int getXDisplacement(double t) {
        double x = velocity * t * Math.cos((float) angle * Math.PI / 180.0);
        if (((int) weaponDecorator.getError()) != 0) {
            x = x + x * (r.nextInt((int) (weaponDecorator.getError() * 100)) / 100);
        }
        if (x == 0) {
            x = 1;
        }

        if (reverse) {
            return initialX - (int) x;
        } else {
            return initialX + (int) x;
        }
    }

    private int getYDisplacement(double t) {
        double y = velocity * t * Math.sin((double) angle * Math.PI / 180.0) - (13.1 * t * t / 2.0);
        if (((int) weaponDecorator.getError()) != 0) {
            if (r.nextBoolean()) {
                y = y + y * (r.nextInt((int) (weaponDecorator.getError() * 100)) / 100);
            } else {
                y = y - y * (r.nextInt((int) (weaponDecorator.getError() * 100)) / 100);
            }
        }
        if (y == 0) {
            y = 1;
        }
        return initialY - (int) y;
    }

    private boolean passThroughObstacle;

    public Weapon(IWeaponDecorator weaponDecorator, int relativeVelocity, int angle, boolean reverse, boolean passThroughObstacle) {
        this.weaponDecorator = weaponDecorator;
        this.passThroughObstacle = passThroughObstacle;
        this.angle = angle;
        this.reverse = reverse;
        this.velocity = (float) relativeVelocity;
        System.out.println("Velocity" + velocity);
        System.out.println("Angle" + angle);
        setImage(weaponDecorator.getImage());
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        initialX = getX();
        initialY = getY();
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public void act() {
        if (endFlight) {
            if (inflictDamage) {
                target.inflictDamage(weaponDecorator.getDamage());
            }
            getWorld().removeObject(this);
            for (IWeaponObserver o : weaponSubject.getObservers()) {
                o.onWeaponFlightEnded(this);
            }
            return;
        }

        if (reverse) {
            setRotation((getRotation() + 10) % 360);
        } else {
            setRotation((getRotation() - 10) % 360);
        }

        double t = (Calendar.getInstance().getTimeInMillis() - startTime) / 400.00;
        int x = getXDisplacement(t);
        int y = getYDisplacement(t);
        setLocation(x, y);
        List gList = getIntersectingObjects(Gorilla.class);
        if (gList.size() > 0) {
            endFlight = true;
            target = (Gorilla) gList.get(0);
            inflictDamage = true;
            return;
        }
        if (!passThroughObstacle) {
            if (getOneIntersectingObject(Obstacle.class) != null) {
                endFlight = true;
                return;
            }
        }

        if (x < -100 || x > 1124) {
            endFlight = true;
            return;
        }

        if (y > 868) {
            endFlight = true;
            return;
        }

    }

    @Override
    public void attach(IWeaponObserver observer) {
        weaponSubject.attach(observer);
    }

    @Override
    public void detach(IWeaponObserver observer) {
        weaponSubject.detach(observer);
    }

    private static String[] weaponNames = {"Banana", "Pick Axe", "Fire Ball"};

    public static String[] getWeapons() {
        return weaponNames;
    }

    public static IWeaponDecorator createWeapon(String name) {
        ConcreteWeapon w = new ConcreteWeapon();
        if (name.equals(weaponNames[0])) {
            return new ConcreteDecoratorBanana(w);
        } else if (name.equals(weaponNames[1])) {
            return new ConcreteDecoratorPickAxe(w);
        } else if (name.equals(weaponNames[2])) {
            return new ConcreteDecoratorFireBall(w);
        } else {
            return w;
        }
    }
}
