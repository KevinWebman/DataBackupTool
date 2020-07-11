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
package com.openkw.controller.listener.mainpanel;

import com.openkw.controller.AppController;
import com.openkw.view.customcomponents.PathFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This listener opens a file chooser for a specific path , either the backup path directory or the
 * data path directory.
 */
public class ChoosePathButtonListener implements ActionListener {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * The path affiliation for this listener instance.
     */
    private final PathAffiliation pathAffiliation;

    /**
     * @param appController   the mvc controller instance.
     * @param pathAffiliation the path affiliation for this listener instance.
     */
    public ChoosePathButtonListener(AppController appController, PathAffiliation pathAffiliation) {
        this.appController = appController;
        this.pathAffiliation = pathAffiliation;
    }

    /**
     * this method opens up a custom path file chooser to pick a directory for either
     * the backup directory path or the data directory path.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PathFileChooser pathFileChooser = new PathFileChooser(this.appController);
        int confirmCode = pathFileChooser.showOpenDialog(this.appController.getAppView().getMainWindow());
        if (confirmCode == JFileChooser.APPROVE_OPTION) {
            File chosenDirectory = pathFileChooser.getSelectedFile();
            if (chosenDirectory != null && chosenDirectory.exists()) {
                String path = chosenDirectory.getAbsolutePath();
                if (this.pathAffiliation == PathAffiliation.BACKUP) {
                    this.appController.getAppModel().setBackupDirPath(path);
                } else {
                    this.appController.getAppModel().setDataFileDirPath(path);
                }
                this.appController.getSettingsSerializer().fireSettingsChanged();
            }
        }

    }

    /**
     * This enum has the purpose to be used to determine which path to set when calling the path file chooser ,
     * either the backup path or the data origin path.
     */
    public enum PathAffiliation {
        BACKUP, DATA
    }
}
