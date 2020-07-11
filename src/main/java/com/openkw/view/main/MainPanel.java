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
import com.openkw.controller.listener.mainpanel.ChoosePathButtonListener;
import com.openkw.controller.listener.mainpanel.RunProcessButtonListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

/**
 * This class contains all components to represent the main view of the program.
 */
public class MainPanel extends JPanel implements PropertyChangeListener {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * The language resource bundle.
     */
    private ResourceBundle languageResourceBundle;

    /**
     * The title of the data path text field
     */
    private JLabel dataPathTitle;

    /**
     * Represents the currently set data directory path.
     */
    private JTextField dataPathTextField;

    /**
     * The title of the backup path text field
     */
    private JLabel backupPathTitle;

    /**
     * Represents the currently set backup directory path.
     */
    private JTextField backupPathTextField;

    /**
     * Starts a new backup process.
     */
    private JButton runProcessButton;

    /**
     * Opens a file chooser to set the data directory path.
     */
    private JButton setDataPathButton;

    /**
     * Opens a file chooser to set the backup directory path.
     */
    private JButton setBackupPathButton;


    /**
     * @param appController the MVC controller instance.
     */
    public MainPanel(AppController appController) {
        this.appController = appController;
        this.initPanel();
        this.initComponents();
        this.addComponents();
    }

    /**
     * Initializes all the j-panel related values.
     */
    private void initPanel() {
        this.appController.getAppModel().addPropertyChangeListener(this);
        this.languageResourceBundle = this.appController.getAppModel().getLanguageResourceBundle();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * All containing components are initialized in this method.
     */
    private void initComponents() {
        this.dataPathTitle = new JLabel(languageResourceBundle.getString("MainPanel.dataPathLabel"));

        this.dataPathTextField = new JTextField(40);
        this.dataPathTextField.setEnabled(false);
        this.dataPathTextField.setDisabledTextColor(Color.BLACK);
        this.dataPathTextField.setText(this.appController.getAppModel().getDataFileDirPath());

        this.backupPathTitle = new JLabel(languageResourceBundle.getString("MainPanel.backupPathLabel"));

        this.backupPathTextField = new JTextField(40);
        this.backupPathTextField.setText(this.appController.getAppModel().getBackupDirPath());
        this.backupPathTextField.setEnabled(false);
        this.backupPathTextField.setDisabledTextColor(Color.BLACK);

        this.runProcessButton = new JButton(languageResourceBundle.getString("MainPanel.runProcessButton"));
        this.runProcessButton.addActionListener(new RunProcessButtonListener(this.appController));

        this.setBackupPathButton = new JButton(languageResourceBundle.getString("MainPanel.setBackupPathButton"));
        this.setBackupPathButton.addActionListener(
                new ChoosePathButtonListener(this.appController, ChoosePathButtonListener.PathAffiliation.BACKUP));

        this.setDataPathButton = new JButton(languageResourceBundle.getString("MainPanel.setDataPathButton"));
        this.setDataPathButton.addActionListener(
                new ChoosePathButtonListener(this.appController, ChoosePathButtonListener.PathAffiliation.DATA));
    }

    /**
     * All components are being added to this panel in this method after they have been initialized.
     */
    private void addComponents() {
        JPanel pathInputPanel = new JPanel();
        pathInputPanel.setLayout(new BoxLayout(pathInputPanel, BoxLayout.PAGE_AXIS));
        pathInputPanel.add(this.dataPathTitle);
        pathInputPanel.add(this.dataPathTextField);
        pathInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        pathInputPanel.add(this.backupPathTitle);
        pathInputPanel.add(this.backupPathTextField);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
        controlPanel.add(this.setDataPathButton);
        controlPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlPanel.add(this.setBackupPathButton);
        controlPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlPanel.add(this.runProcessButton);

        this.add(pathInputPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.PAGE_END);
    }

    /**
     * This method is invoked if any model data is being updated.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("saveFileDirPath")) {
            SwingUtilities.invokeLater(() -> this.dataPathTextField.setText((String) evt.getNewValue()));
        } else if (evt.getPropertyName().equals("backupDirPath")) {
            SwingUtilities.invokeLater(() -> this.backupPathTextField.setText((String) evt.getNewValue()));
        }
    }

    //Only getters and setters from here.

    public JLabel getDataPathTitle() {
        return dataPathTitle;
    }

    public void setDataPathTitle(JLabel dataPathTitle) {
        this.dataPathTitle = dataPathTitle;
    }

    public JTextField getDataPathTextField() {
        return dataPathTextField;
    }

    public void setDataPathTextField(JTextField dataPathTextField) {
        this.dataPathTextField = dataPathTextField;
    }

    public JLabel getBackupPathTitle() {
        return backupPathTitle;
    }

    public void setBackupPathTitle(JLabel backupPathTitle) {
        this.backupPathTitle = backupPathTitle;
    }

    public JTextField getBackupPathTextField() {
        return backupPathTextField;
    }

    public void setBackupPathTextField(JTextField backupPathTextField) {
        this.backupPathTextField = backupPathTextField;
    }

    public JButton getRunProcessButton() {
        return runProcessButton;
    }

    public void setRunProcessButton(JButton runProcessButton) {
        this.runProcessButton = runProcessButton;
    }

    public JButton getSetDataPathButton() {
        return setDataPathButton;
    }

    public void setSetDataPathButton(JButton setDataPathButton) {
        this.setDataPathButton = setDataPathButton;
    }

    public JButton getSetBackupPathButton() {
        return setBackupPathButton;
    }

    public void setSetBackupPathButton(JButton setBackupPathButton) {
        this.setBackupPathButton = setBackupPathButton;
    }
}
