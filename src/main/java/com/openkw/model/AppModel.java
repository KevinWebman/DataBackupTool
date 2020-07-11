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


import com.openkw.controller.util.SettingsSerializer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class represents the data model of the program in relation to the MVC architecture
 */
public class AppModel {

    /**
     * The connection from model through view goes through this object.
     */
    private final PropertyChangeSupport propertyChangeSupport;

    /**
     * This resource bundle contains language resources which are being used in the GUI.
     */
    private ResourceBundle languageResourceBundle;

    /**
     * This object contains serialized data which will be loaded by the model through the SettingsSerializer.
     */
    private SerializationData serializationData;

    /**
     * This locale represents the currently set language which can be chosen through the menu by the user.
     */
    private Locale currentLanguage;

    /**
     * Represents the interval between each backups made , chosen by the user menu.
     */
    private Interval currentInterval;

    /**
     * This model is containing data which is being used to display the current state of the process
     * to the user through the GUI.
     */
    private RunningProcessModel runningProcessModel;

    /**
     * The currently set path which will be used by the BackupProcessControl to determine where to save the backup.
     */
    private String backupDirPath;

    /**
     * The currently set path which will be used by the BackupProcessControl to determine where to
     * read the data from for the backup.
     */
    private String dataFileDirPath;

    /**
     * The constructor of the model takes care of the initialization of already serialized data
     */
    public AppModel() {
        this.runningProcessModel = new RunningProcessModel();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.serializationData = loadInitialSerializationData();
        this.languageResourceBundle = ResourceBundle.getBundle("language", serializationData.getCurrentLanguage());
        this.currentLanguage = serializationData.getCurrentLanguage();
        this.dataFileDirPath = serializationData.getDataFileDirPath();
        this.backupDirPath = serializationData.getBackupDirPath();
        this.currentInterval = serializationData.getCurrentInterval();
    }

    /**
     * This method returns the serialized data which has been loaded by the settings serializer
     * if any previous settings have been saved in json format.
     * Its purpose is to restore the model state and therefore the settings which
     * have been made by the user the last time the program has been used.
     * If there are not serialized settings yet it returns a new
     * Serialization data instance with default values to load for the model
     *
     * @return Serialized data to load for the model
     */
    private SerializationData loadInitialSerializationData() {
        SerializationData serializationData = SettingsSerializer.checkSettings();
        if (serializationData != null) {
            return serializationData;
        }
        return new SerializationData();
    }

    /**
     * This method was implemented to make it easier for a property change listener to register itself with the
     * property change support instance of the model.
     *
     * @param propertyChangeListener an instance of a property change listener to be registered with the
     *                               property change support of the model.
     */
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    /**
     * This method was implemented to make it easier for a property change listener to remove itself from the
     * property change support instance of the model.
     *
     * @param propertyChangeListener an instance of a property change listener to be removed from the
     *                               property change support of the model.
     */
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    //Only getters and setters from here

    public String getDataFileDirPath() {
        return dataFileDirPath;
    }

    public void setDataFileDirPath(String dataFileDirPath) {
        this.propertyChangeSupport.firePropertyChange("saveFileDirPath", this.dataFileDirPath, dataFileDirPath);
        this.dataFileDirPath = dataFileDirPath;
    }

    public String getBackupDirPath() {
        return backupDirPath;
    }

    public void setBackupDirPath(String backupDirPath) {
        this.propertyChangeSupport.firePropertyChange("backupDirPath", this.backupDirPath, backupDirPath);
        this.backupDirPath = backupDirPath;
    }

    public ResourceBundle getLanguageResourceBundle() {
        return languageResourceBundle;
    }

    public void setLanguageResourceBundle(ResourceBundle languageResourceBundle) {
        this.languageResourceBundle = languageResourceBundle;
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

    public SerializationData getSerializationData() {
        return serializationData;
    }

    public void setSerializationData(SerializationData serializationData) {
        this.serializationData = serializationData;
    }

    public RunningProcessModel getRunningProcessModel() {
        return runningProcessModel;
    }

    public void setRunningProcessModel(RunningProcessModel runningProcessModel) {
        this.runningProcessModel = runningProcessModel;
    }
}
