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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimerTask;

/**
 * This timer task is invoked when the backup process is being started.
 */
public class BackupTimerTask extends TimerTask {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * The amount of IOErrors thrown during the task process.
     */
    private int errors = 0;

    /**
     * The amount of successful backups during the task process.
     */
    private int backups = 0;

    /**
     * @param appController the mvc controller instance.
     */
    public BackupTimerTask(AppController appController) {
        this.appController = appController;
    }

    /**
     * @return a string representation of the current time in the format -> DAY MONTH HH-MM AM/PM
     */
    private String getTimeStamp() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime time = LocalDateTime.now();
        sb.append(time.getDayOfMonth())
                .append(" ")
                .append(time.getMonth())
                .append(" ")
                .append(new SimpleDateFormat("hh-mm a").format(new Date()));
        return sb.toString();
    }

    /**
     * This method updates the RunningProcessModel to represent the currents backup directory size.
     */
    private void updateBackupFolderSizeRepresentation() {
        String fileSizePresentation = FileSizeUtility.getRecommendedFileSizePresentation(
                this.appController.getAppModel().getBackupDirPath());
        if (fileSizePresentation != null) {
            this.appController.getAppModel().getRunningProcessModel().setBackupFolderSize(
                    fileSizePresentation);
        }
    }

    /**
     * Creates a backup from the currently set data file directory and saves it in the currently set backup directory.
     */
    @Override
    public void run() {
        String backupDirTitle = "Backup " + getTimeStamp();
        String fileDirPath = appController.getAppModel().getDataFileDirPath();
        String backupDirPath = appController.getAppModel().getBackupDirPath();
        try {
            FileUtils.copyDirectoryToDirectory(new File(fileDirPath),
                    new File(backupDirPath + "\\" + backupDirTitle));
            backups++;
            appController.getAppModel().getRunningProcessModel().setBackupCount(backups);
            updateBackupFolderSizeRepresentation();
        } catch (IOException e) {
            e.printStackTrace();
            errors++;
            appController.getAppModel().getRunningProcessModel().setErrorCount(errors);
        }
    }
}

