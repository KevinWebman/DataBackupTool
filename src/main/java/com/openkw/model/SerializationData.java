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
package com.openkw.model;

import java.util.Locale;

/**
 * This class has the purpose to cache settings data and will be always updated if any of the important settings will
 * be changed.
 * It's being used by the class SettingsSerializer to serialize and deserialize settings data
 * in JSON format.
 */
public class SerializationData {

    /**
     * The currently set data directory path.
     */
    private String dataFileDirPath;

    /**
     * The currently set backup directory path.
     */
    private String backupDirPath;

    /**
     * The currently set interval to make backups.
     */
    private Interval currentInterval;

    /**
     * The currently set language of the GUI.
     */
    private Locale currentLanguage;

    /**
     * This constructor is there to initialize default values which are being used in case there is no
     * serialized data yet.
     */
    public SerializationData() {
        String userDir = System.getProperty("user.home");
        this.dataFileDirPath = userDir + "\\Documents";
        this.backupDirPath = userDir + "\\Documents\\DataBackup";
        this.currentInterval = Interval.TEN;
        this.currentLanguage = Locale.getDefault();
    }

    /**
     * This method inserts the data which is later going to be serialized
     * from the model into this object.
     *
     * @param appModel the instance of the currently used model
     */
    public void updateData(AppModel appModel) {
        this.dataFileDirPath = appModel.getDataFileDirPath();
        this.backupDirPath = appModel.getBackupDirPath();
        this.currentInterval = appModel.getCurrentInterval();
        this.currentLanguage = appModel.getCurrentLanguage();
    }

    //Only getters and setters from here

    public String getDataFileDirPath() {
        return dataFileDirPath;
    }

    public void setDataFileDirPath(String dataFileDirPath) {
        this.dataFileDirPath = dataFileDirPath;
    }

    public String getBackupDirPath() {
        return backupDirPath;
    }

    public void setBackupDirPath(String backupDirPath) {
        this.backupDirPath = backupDirPath;
    }

    public Interval getCurrentInterval() {
        return currentInterval;
    }

    public void setCurrentInterval(Interval currentInterval) {
        this.currentInterval = currentInterval;
    }

    public Locale getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(Locale currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

}
