package com.drravns.gowars;

/*
 This file is part of the Greenfoot program.
 Copyright (C) 2010  Poul Henriksen and Michael Kolling

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 This file is subject to the Classpath exception as provided in the
 LICENSE.txt file that accompanied this code.
 */


import bluej.Config;
import greenfoot.export.GreenfootScenarioViewer;
import greenfoot.util.StandalonePropStringManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * The main class for Greenfoot scenarios when they are exported as standalone
 * applications.
 * <p/>
 * <p>This must be a separate class from GreenfootScenarioViewer, and specifically
 * must not be a Swing/AWT derived class, because we need to set the application
 * name property (for Mac OS) before any Swing/AWT classes are propertly initialized.
 *
 * @author Davin McCall
 */
public class Launcher {
    public static String scenarioName;

    /**
     * Start the scenario.
     * <p/>
     * <p/>
     * BlueJ and the scenario MUST be on the classpath.
     *
     * @param args One argument can be passed to this method. The first one
     *             should be the World to be instantiated. If no arguments are
     *             supplied it will read from the properties file. And if that
     *             can't be found either it will use AntWorld.
     */
    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        if (args.length != 3 && args.length != 0) {
            System.err.println("Wrong number of arguments");
        }
        initProperties();
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", scenarioName);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(scenarioName);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);

                new GreenfootScenarioViewer(frame);
                URL resource = this.getClass().getClassLoader().getResource("greenfoot.png");
                ImageIcon icon = new ImageIcon(resource);
                frame.setIconImage(icon.getImage());
                frame.setVisible(true);
                frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
            }
        });
    }

    /**
     * Initialize the project properties.
     */
    public static void initProperties() {
        if (scenarioName != null) {
            return; // already done
        }

        Properties p = new Properties();
        try {
            ClassLoader loader = Launcher.class.getClassLoader();
            InputStream is = loader.getResourceAsStream("standalone.properties");
            p.load(is);
            scenarioName = p.getProperty("project.name");
            Config.initializeStandalone(new StandalonePropStringManager(p));
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
