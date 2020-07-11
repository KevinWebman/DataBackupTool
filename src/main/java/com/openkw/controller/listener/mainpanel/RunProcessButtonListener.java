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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This ActionListener serves the purpose of running the backup process.
 */
public class RunProcessButtonListener implements ActionListener {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * @param appController the mvc controller instance.
     */
    public RunProcessButtonListener(AppController appController) {
        this.appController = appController;
    }

    /**
     * This method disposes every window, sets the processing window visible and starts the backup process
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.appController.getAppView().getAppInfoWindow().dispose();
        this.appController.getAppView().getMainWindow().dispose();
        this.appController.getAppView().getProcessingWindow().setVisible(true);
        this.appController.getBackupProcessControl().startBackupProcess();
    }
}
