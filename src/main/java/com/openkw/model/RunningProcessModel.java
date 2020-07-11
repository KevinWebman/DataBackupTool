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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class is containing data which is being used to display the current state of the process
 * to the user through the GUI.
 */
public class RunningProcessModel {

    /**
     * The connection from model through view goes through this object.
     */
    private final PropertyChangeSupport propertyChangeSupport;

    /**
     * The counter is being incremented by 1 every time a
     * backup has been created successfully during the backup process.
     */
    private int backupCount;

    /**
     * This counter will be incremented by 1 if and only if an IOException has been thrown during
     * the backup process.
     */
    private int errorCount;

    /**
     * Represents the elapsed time of a currently active process in this format -> hh:mm:ss
     */
    private String timeElapsed;

    /**
     * Represents the backup folder size during an active process in this format -> XXX MB or XXX GB .
     * Check FileUtility.getRecommendedFileSizePresentation() for more information.
     */
    private String backupFolderSize;

    public RunningProcessModel() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.backupCount = 0;
        this.errorCount = 0;
        this.timeElapsed = "";
        this.backupFolderSize = "";
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public int getBackupCount() {
        return backupCount;
    }

    public void setBackupCount(int backupCount) {
        this.propertyChangeSupport.firePropertyChange("backupCount", this.backupCount, backupCount);
        this.backupCount = backupCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.propertyChangeSupport.firePropertyChange("errorCount", this.errorCount, errorCount);
        this.errorCount = errorCount;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.propertyChangeSupport.firePropertyChange("timeElapsed", this.timeElapsed, timeElapsed);
        this.timeElapsed = timeElapsed;
    }

    public String getBackupFolderSize() {
        return backupFolderSize;
    }

    public void setBackupFolderSize(String backupFolderSize) {
        this.propertyChangeSupport.firePropertyChange("backupFolderSize", this.backupFolderSize, backupFolderSize);
        this.backupFolderSize = backupFolderSize;
    }

}
