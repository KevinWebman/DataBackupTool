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
package com.openkw.view;

import com.openkw.controller.AppController;
import com.openkw.view.appinfo.AppInfoWindow;
import com.openkw.view.main.MainWindow;
import com.openkw.view.processing.ProcessingWindow;

import javax.swing.*;

/**
 * The view in relation to the MVC architecture.It contains all the GUI related instances of this program.
 */
public class AppView {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * The main window of the program.
     */
    private MainWindow mainWindow;

    /**
     * The info window of the program , contains only meta data like version ,licence etc.
     */
    private AppInfoWindow appInfoWindow;

    /**
     * This window is used for the visual representation of a running backup process.
     */
    private ProcessingWindow processingWindow;

    /**
     * @param appController The MVC controller instance.
     */
    public AppView(AppController appController) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.appController = appController;
        this.appController.setAppView(this);
        initComponents();
    }

    /**
     * All the instances of this view instance are initialized here.
     */
    private void initComponents() {
        this.mainWindow = new MainWindow(this.appController);
        this.appInfoWindow = new AppInfoWindow(this.appController);
        this.processingWindow = new ProcessingWindow(this.appController);
    }

    //Only getters and setters from here.

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public AppInfoWindow getAppInfoWindow() {
        return appInfoWindow;
    }

    public void setAppInfoWindow(AppInfoWindow appInfoWindow) {
        this.appInfoWindow = appInfoWindow;
    }

    public ProcessingWindow getProcessingWindow() {
        return processingWindow;
    }

    public void setProcessingWindow(ProcessingWindow processingWindow) {
        this.processingWindow = processingWindow;
    }

}
