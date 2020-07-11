/*
   Copyright 2020 Kevin Webermann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

   @Contact: Kevin Webermann - <kevinwebermann@gmail.com>
 */
package com.openkw.view.main;

import com.openkw.controller.AppController;
import com.openkw.controller.listener.mainwindow.AboutMenuItemListener;
import com.openkw.controller.listener.mainwindow.IntervalRadioButtonListener;
import com.openkw.controller.listener.mainwindow.LanguageRadioButtonListener;
import com.openkw.model.Interval;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This frame is the main frame of the program which provides all important settings to the user
 * for the backup process.
 */
public class MainWindow extends JFrame {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * The language resource bundle
     */
    private final ResourceBundle languageResourceBundle;

    /**
     * This panel contains all components to be displayed in this window later.
     */
    private MainPanel mainPanel;

    /**
     * @param appController the MVC controller instance.
     */
    public MainWindow(AppController appController) {
        this.appController = appController;
        this.languageResourceBundle = this.appController.getAppModel().getLanguageResourceBundle();
        this.initFrame();
        this.initMenuBar();
        this.initComponents();
        this.pack();
    }

    /**
     * All components of this window are initialized in this method.
     */
    private void initComponents() {
        this.mainPanel = new MainPanel(this.appController);
        this.add(this.mainPanel);
    }

    /**
     * Initializes all the j-frame related values.
     */
    private void initFrame() {
        this.setSize(new Dimension(450, 200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(this.appController.getAppModel().getLanguageResourceBundle().getString("MainWindow.windowTitle"));
        try {
            URL iconUrl = getClass().getClassLoader().getResource("icon.png");
            if (iconUrl != null) {
                BufferedImage frameIcon = ImageIO.read(iconUrl);
                this.setIconImage(frameIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Here the menu bar is being implemented and added to the frame.
     */
    private void initMenuBar() {
        //Create the settings menu
        JMenu settingsMenu = new JMenu(this.languageResourceBundle.getString("MainWindow.settingsMenu"));
        settingsMenu.add(createIntervalMenu());
        settingsMenu.addSeparator();
        settingsMenu.add(createLanguageMenu());

        //Create the help menu
        JMenu helpMenu = new JMenu(this.languageResourceBundle.getString("MainWindow.helpMenu"));
        JMenuItem aboutMenuItem = new JMenuItem(this.languageResourceBundle.getString("MainWindow.aboutMenuItem"));
        aboutMenuItem.addActionListener(new AboutMenuItemListener(this.appController));
        helpMenu.add(aboutMenuItem);

        //Create the actual bar and add the menu options
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(settingsMenu);
        jMenuBar.add(helpMenu);

        this.setJMenuBar(jMenuBar);
    }

    /**
     * The language settings menu is implemented in this method.
     *
     * @return the language menu as JMenu instance.
     */
    private JMenu createLanguageMenu() {
        JMenu languageMenu = new JMenu(this.languageResourceBundle.getString("MainWindow.languageMenu"));

        JRadioButtonMenuItem englishRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.englishRadioBtn"));
        englishRadioBtn.addActionListener(new LanguageRadioButtonListener(this.appController, Locale.ENGLISH));
        JRadioButtonMenuItem germanRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.germanRadioBtn"));
        germanRadioBtn.addActionListener(new LanguageRadioButtonListener(this.appController, Locale.GERMAN));

        ButtonGroup langButtonGroup = new ButtonGroup();
        langButtonGroup.add(englishRadioBtn);
        langButtonGroup.add(germanRadioBtn);

        languageMenu.add(englishRadioBtn);
        languageMenu.add(germanRadioBtn);

        if (this.appController.getAppModel().getCurrentLanguage().equals(Locale.ENGLISH)) {
            englishRadioBtn.setSelected(true);
        } else if (this.appController.getAppModel().getCurrentLanguage().equals(Locale.GERMAN)) {
            germanRadioBtn.setSelected(true);
        }

        return languageMenu;
    }

    /**
     * The interval menu which is being used to determine how long to wait
     * between each backup during an active backup process is being implemented in this method.
     *
     * @return The interval menu as JMenu instance.
     */
    private JMenu createIntervalMenu() {
        //Create the interval selection buttons
        JRadioButtonMenuItem tenMinRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.tenMinRadioBtn"));
        tenMinRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.TEN));
        JRadioButtonMenuItem twentyMinRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.twentyMinRadioBtn"));
        twentyMinRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.TWENTY));
        JRadioButtonMenuItem thirtyMinRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.thirtyMinRadioBtn"));
        thirtyMinRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.THIRTY));
        JRadioButtonMenuItem fortyMinRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.fortyMinRadioBtn"));
        fortyMinRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.FORTY));
        JRadioButtonMenuItem fiftyMinRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.fiftyMinRadioBtn"));
        fiftyMinRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.FIFTY));
        JRadioButtonMenuItem hourRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.hourRadioBtn"));
        hourRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.SIXTY));
        JRadioButtonMenuItem twoHourRadioBtn = new JRadioButtonMenuItem(this.languageResourceBundle.getString("MainWindow.twoHourRadioBtn"));
        twoHourRadioBtn.addActionListener(new IntervalRadioButtonListener(this.appController, Interval.ONE_HUNDRED_TWENTY));

        //Adding the interval selection buttons to a button group
        ButtonGroup intervalButtonGroup = new ButtonGroup();
        intervalButtonGroup.add(tenMinRadioBtn);
        intervalButtonGroup.add(twentyMinRadioBtn);
        intervalButtonGroup.add(thirtyMinRadioBtn);
        intervalButtonGroup.add(fortyMinRadioBtn);
        intervalButtonGroup.add(fiftyMinRadioBtn);
        intervalButtonGroup.add(hourRadioBtn);
        intervalButtonGroup.add(twoHourRadioBtn);

        //Add the intverval selection buttons to the actual menu
        JMenu intervalMenu = new JMenu(this.languageResourceBundle.getString("MainWindow.intervalMenu"));
        intervalMenu.add(tenMinRadioBtn);
        intervalMenu.add(twentyMinRadioBtn);
        intervalMenu.add(thirtyMinRadioBtn);
        intervalMenu.add(fortyMinRadioBtn);
        intervalMenu.add(fiftyMinRadioBtn);
        intervalMenu.add(hourRadioBtn);
        intervalMenu.add(twoHourRadioBtn);

        //Set the button ,currently present in the model as selected quantity, to selected
        switch (this.appController.getAppModel().getCurrentInterval()) {
            case TEN:
                tenMinRadioBtn.setSelected(true);
                break;
            case TWENTY:
                twentyMinRadioBtn.setSelected(true);
                break;
            case THIRTY:
                thirtyMinRadioBtn.setSelected(true);
                break;
            case FORTY:
                fortyMinRadioBtn.setSelected(true);
                break;
            case FIFTY:
                fiftyMinRadioBtn.setSelected(true);
                break;
            case SIXTY:
                hourRadioBtn.setSelected(true);
                break;
            case ONE_HUNDRED_TWENTY:
                twoHourRadioBtn.setSelected(true);
                break;
        }
        return intervalMenu;
    }

    //Only getters and setters from here.

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
