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
package com.openkw.view.appinfo;

import com.openkw.controller.AppController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This window shows general info about the program such as developer , licence , version and program title
 */
public class AppInfoWindow extends JFrame {

    /**
     * Main content panel of the window.
     */
    private final JPanel contentPanel = new JPanel();

    /**
     * The language resource bundle.
     */
    private final ResourceBundle languageResourceBundle;

    /**
     * The mvc controller of the program
     */
    private final AppController appController;

    /**
     * Represents the program title
     */
    private JLabel programTitleLabel = new JLabel();

    /**
     * Represents the developers
     */
    private JLabel authorLabel = new JLabel();

    /**
     * Represents this programs licence
     */
    private JLabel licenceLabel = new JLabel();

    /**
     * Represents the version of this program.
     */
    private JLabel programVersionLabel = new JLabel();

    /**
     * @param appController The controller of the MVC application
     */
    public AppInfoWindow(AppController appController) {
        this.appController = appController;
        this.languageResourceBundle = this.appController.getAppModel().getLanguageResourceBundle();
        initFrame();
        initContentPanel();
        this.pack();
    }

    /**
     * Initializes all the content the main panel of this windows holds and adds it to the panel.
     */
    private void initContentPanel() {
        this.contentPanel.setLayout(new BoxLayout(this.contentPanel, BoxLayout.PAGE_AXIS));
        this.contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        this.programTitleLabel.setText(this.languageResourceBundle.getString("AboutWindow.contentTitle"));
        this.programVersionLabel.setText(this.languageResourceBundle.getString("AboutWindow.contentVersion"));
        this.authorLabel.setText(this.languageResourceBundle.getString("AboutWindow.contentAuthor"));
        this.licenceLabel.setText(this.languageResourceBundle.getString("AboutWindow.contentLicence"));
        this.contentPanel.add(this.programTitleLabel);
        this.contentPanel.add(this.programVersionLabel);
        this.contentPanel.add(this.authorLabel);
        this.contentPanel.add(this.licenceLabel);

    }

    /**
     * Initializes all the j-frame related values.
     */
    private void initFrame() {
        this.setSize(100, 100);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setTitle(this.languageResourceBundle.getString("AboutWindow.windowTitle"));
        try {
            URL iconUrl = getClass().getClassLoader().getResource("icon.png");
            if (iconUrl != null) {
                BufferedImage frameIcon = ImageIO.read(iconUrl);
                this.setIconImage(frameIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(this.contentPanel);
        this.setLocationRelativeTo(null);
    }

    //Only getters and setters from here.

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JLabel getProgramTitleLabel() {
        return programTitleLabel;
    }

    public void setProgramTitleLabel(JLabel programTitleLabel) {
        this.programTitleLabel = programTitleLabel;
    }

    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    public void setAuthorLabel(JLabel authorLabel) {
        this.authorLabel = authorLabel;
    }

    public JLabel getLicenceLabel() {
        return licenceLabel;
    }

    public void setLicenceLabel(JLabel licenceLabel) {
        this.licenceLabel = licenceLabel;
    }

    public JLabel getProgramVersionLabel() {
        return programVersionLabel;
    }

    public void setProgramVersionLabel(JLabel programVersionLabel) {
        this.programVersionLabel = programVersionLabel;
    }
}
