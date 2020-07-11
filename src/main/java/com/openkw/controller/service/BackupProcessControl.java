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
package com.openkw.controller.service;

import com.openkw.controller.AppController;
import com.openkw.controller.util.FileSizeUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * This class provides methods to invoke the backup task and to measure the time a backup task is running.
 */
public class BackupProcessControl {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * The timer instance related to the backup timer process.
     */
    private Timer backupTimer;

    /**
     * The timer instance related to the time measuring of the process.
     */
    private Timer timeMeasureTimer;

    /**
     * @param appController The mvc controller instance.
     */
    public BackupProcessControl(AppController appController) {
        this.appController = appController;
    }

    /**
     * This method starts the backup process.
     */
    private void runBackupTask() {
        //In case the backup folder does not exist yet making sure to create it.
        Path buFolderPath = Paths.get(this.appController.getAppModel().getBackupDirPath());
        if (Files.notExists(buFolderPath)) {
            try {
                Files.createDirectory(buFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Update the model backup folder size representation
        String fileSizePresentation = FileSizeUtility.getRecommendedFileSizePresentation(
                this.appController.getAppModel().getBackupDirPath());
        if (fileSizePresentation != null) {
            this.appController.getAppModel().getRunningProcessModel().setBackupFolderSize(
                    fileSizePresentation);
        }

        int interval = this.appController.getAppModel().getCurrentInterval().getMillis();
        this.backupTimer = new Timer();
        BackupTimerTask backupTimerTask = new BackupTimerTask(this.appController);
        this.backupTimer.scheduleAtFixedRate(backupTimerTask, interval, interval);
    }

    /**
     * This method starts the time measuring for the backup process.
     */
    private void runTimeMeasuringTask() {
        this.timeMeasureTimer = new Timer();
        this.timeMeasureTimer.scheduleAtFixedRate(new TimerTask() {
            private int seconds = 0;

            @Override
            public void run() {
                this.seconds += 1;
                long hours = TimeUnit.SECONDS.toHours(this.seconds);
                long minutes = TimeUnit.SECONDS.toMinutes(this.seconds) % 60;
                long seconds = TimeUnit.SECONDS.toSeconds(this.seconds) % 60;
                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                appController.getAppModel().getRunningProcessModel().setTimeElapsed(timeString);
            }
        }, 1000, 1000);
    }

    /**
     * This method starts invokes the backup task and the time measuring task simultaneously.
     */
    public void startBackupProcess() {
        runBackupTask();
        runTimeMeasuringTask();
    }

    /**
     * This method stops the backup timer and the time measuring timer and also resets the model values related
     * to the backup process.
     */
    public void stopBackupProcess() {
        this.backupTimer.cancel();
        this.timeMeasureTimer.cancel();
        resetModelValues();
    }

    /**
     * This method resets the values that are being used for the representation of the backup process.
     */
    private void resetModelValues() {
        this.appController.getAppModel().getRunningProcessModel().setTimeElapsed("00:00:00");
        this.appController.getAppModel().getRunningProcessModel().setBackupFolderSize("0");
        this.appController.getAppModel().getRunningProcessModel().setErrorCount(0);
        this.appController.getAppModel().getRunningProcessModel().setBackupCount(0);
    }
}
