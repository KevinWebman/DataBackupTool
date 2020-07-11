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
import com.openkw.controller.listener.processingpanel.CancelButtonActionListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

/**
 * This class contains all components for the representation of an active process for the user.
 */
public class ProcessingPanel extends JPanel implements PropertyChangeListener {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * The language resource bundle.
     */
    private ResourceBundle languageRB;

    /**
     * Title of the backup count
     */
    private JLabel backupCountTitleLabel;

    /**
     * Title of the error count
     */
    private JLabel errorCountTitleLabel;

    /**
     * Title of the elapsed time
     */
    private JLabel timeElapsedTitleLabel;

    /**
     * Title of the backup folder size representation
     */
    private JLabel backupFolderSizeTitleLabel;

    /**
     * Used to represent the number of successful backups during a process
     */
    private JLabel backupCountLabel;

    /**
     * Represents the number of IO errors during a backup process.
     */
    private JLabel errorCountLabel;

    /**
     * Represents the elapsed time of an active backup process.
     */
    private JLabel timeElapsedLabel;

    /**
     * Represents the folder size in this format [00 MB] or [00 GB]
     */
    private JLabel backupFolderSizeLabel;

    /**
     * One option to cancel an active backup process
     */
    private JButton cancelButton;

    /**
     * @param appController the MVC controller instance.
     */
    public ProcessingPanel(AppController appController) {
        this.appController = appController;
        this.appController.getAppModel().getRunningProcessModel().addPropertyChangeListener(this);
        this.initPanel();
        this.initComponents();
        this.addComponents();
    }

    /**
     * All contained components are being added to the panel here.
     */
    private void addComponents() {
        this.add(backupCountTitleLabel);
        this.add(backupCountLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(errorCountTitleLabel);
        this.add(errorCountLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(backupFolderSizeTitleLabel);
        this.add(backupFolderSizeLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(timeElapsedTitleLabel);
        this.add(timeElapsedLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(this.cancelButton);
    }

    /**
     * Initializes all the containing components.
     */
    private void initComponents() {
        this.backupCountTitleLabel = new JLabel(this.languageRB.getString("ProcessingPanel.backupCountTitleLabel"));
        this.errorCountTitleLabel = new JLabel(this.languageRB.getString("ProcessingPanel.errorCountTitleLabel"));
        this.timeElapsedTitleLabel = new JLabel(this.languageRB.getString("ProcessingPanel.timeElapsedTitleLabel"));
        this.backupFolderSizeTitleLabel = new JLabel(this.languageRB.getString("ProcessingPanel.backupFolderSizeTitleLabel"));
        this.backupCountLabel = new JLabel("0");
        this.errorCountLabel = new JLabel("0");
        this.timeElapsedLabel = new JLabel("00:00:00");
        this.backupFolderSizeLabel = new JLabel("0 MB");
        this.cancelButton = new JButton(this.languageRB.getString("ProcessingPanel.cancelButton"));
        this.cancelButton.addActionListener(new CancelButtonActionListener(this.appController));
    }

    /**
     * Takes care of all J-panel related values which are not bound to the components.
     */
    private void initPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.languageRB = this.appController.getAppModel().getLanguageResourceBundle();
        this.setBorder(BorderFactory.createEmptyBorder(15, 60, 15, 60));
    }

    /**
     * This method is invoked if any model data of the RunningProcessModel is being updated.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SwingUtilities.invokeLater(() -> {
            switch (evt.getPropertyName()) {
                case "backupCount":
                    this.backupCountLabel.setText(evt.getNewValue().toString());
                    break;
                case "errorCount":
                    this.errorCountLabel.setText(evt.getNewValue().toString());
                    break;
                case "timeElapsed":
                    this.timeElapsedLabel.setText(evt.getNewValue().toString());
                    break;
                case "backupFolderSize":
                    this.backupFolderSizeLabel.setText(evt.getNewValue().toString());
                    break;
            }
        });
    }

    //Only getters and setters from here

    public JLabel getBackupCountTitleLabel() {
        return backupCountTitleLabel;
    }

    public void setBackupCountTitleLabel(JLabel backupCountTitleLabel) {
        this.backupCountTitleLabel = backupCountTitleLabel;
    }

    public JLabel getErrorCountTitleLabel() {
        return errorCountTitleLabel;
    }

    public void setErrorCountTitleLabel(JLabel errorCountTitleLabel) {
        this.errorCountTitleLabel = errorCountTitleLabel;
    }

    public JLabel getTimeElapsedTitleLabel() {
        return timeElapsedTitleLabel;
    }

    public void setTimeElapsedTitleLabel(JLabel timeElapsedTitleLabel) {
        this.timeElapsedTitleLabel = timeElapsedTitleLabel;
    }

    public JLabel getBackupFolderSizeTitleLabel() {
        return backupFolderSizeTitleLabel;
    }

    public void setBackupFolderSizeTitleLabel(JLabel backupFolderSizeTitleLabel) {
        this.backupFolderSizeTitleLabel = backupFolderSizeTitleLabel;
    }

    public JLabel getBackupCountLabel() {
        return backupCountLabel;
    }

    public void setBackupCountLabel(JLabel backupCountLabel) {
        this.backupCountLabel = backupCountLabel;
    }

    public JLabel getErrorCountLabel() {
        return errorCountLabel;
    }

    public void setErrorCountLabel(JLabel errorCountLabel) {
        this.errorCountLabel = errorCountLabel;
    }

    public JLabel getTimeElapsedLabel() {
        return timeElapsedLabel;
    }

    public void setTimeElapsedLabel(JLabel timeElapsedLabel) {
        this.timeElapsedLabel = timeElapsedLabel;
    }

    public JLabel getBackupFolderSizeLabel() {
        return backupFolderSizeLabel;
    }

    public void setBackupFolderSizeLabel(JLabel backupFolderSizeLabel) {
        this.backupFolderSizeLabel = backupFolderSizeLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
