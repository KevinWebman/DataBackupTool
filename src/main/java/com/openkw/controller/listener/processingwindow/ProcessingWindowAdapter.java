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
package com.openkw.controller.listener.processingwindow;

import com.openkw.controller.AppController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class serves the purpose to cancel the running backup process.
 */
public class ProcessingWindowAdapter extends WindowAdapter {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * @param appController the mvc controller instance.
     */
    public ProcessingWindowAdapter(AppController appController) {
        this.appController = appController;
    }

    /**
     * This method stops the running backup process,disposing the processing window and opens the main window.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        this.appController.getBackupProcessControl().stopBackupProcess();
        this.appController.getAppView().getProcessingWindow().dispose();
        this.appController.getAppView().getMainWindow().setVisible(true);
    }
}
