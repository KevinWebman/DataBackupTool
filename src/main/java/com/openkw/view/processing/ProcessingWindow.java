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
package com.openkw.view.processing;

import com.openkw.controller.AppController;
import com.openkw.controller.listener.processingwindow.ProcessingWindowAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This frame is used to represent a visual feedback to the user while an active backup process is running.
 */
public class ProcessingWindow extends JFrame {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * The main content containing j-panel.
     */
    private final ProcessingPanel processingPanel;

    /**
     * @param appController the MVC controller instance.
     */
    public ProcessingWindow(AppController appController) {
        this.appController = appController;
        initFrame();
        this.processingPanel = new ProcessingPanel(this.appController);
        this.add(this.processingPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Initializes all the j-frame related values.
     */
    private void initFrame() {
        this.setSize(new Dimension(350, 200));
        this.setResizable(false);
        this.setTitle(this.appController.getAppModel().getLanguageResourceBundle().getString("ProcessingWindow.windowTitle"));
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        try {
            URL iconUrl = getClass().getClassLoader().getResource("icon.png");
            if (iconUrl != null) {
                BufferedImage frameIcon = ImageIO.read(iconUrl);
                this.setIconImage(frameIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addWindowListener(new ProcessingWindowAdapter(this.appController));
    }

    //Only getters or setters from here

    public ProcessingPanel getProcessingPanel() {
        return processingPanel;
    }

}
