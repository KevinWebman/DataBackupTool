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
package com.openkw.controller;

import com.openkw.controller.service.BackupProcessControl;
import com.openkw.controller.util.SettingsSerializer;
import com.openkw.model.AppModel;
import com.openkw.view.AppView;

/**
 * This class represents the controller of the program in relation to the MVC architecture
 */
public class AppController {
    /**
     * The mvc data model
     */
    private AppModel appModel;
    /**
     * The mvc view
     */
    private AppView appView;
    /**
     * The serializing utility to serialize the settings
     */
    private SettingsSerializer settingsSerializer;
    /**
     * Instance to start the backup process
     */
    private BackupProcessControl backupProcessControl;

    /**
     * @param appModel the mvc data model
     */
    public AppController(AppModel appModel) {
        this.appModel = appModel;
        this.settingsSerializer = new SettingsSerializer(this);
        this.backupProcessControl = new BackupProcessControl(this);
    }

    /**
     * This method rebuilds the view to make it possible to reload the view after changing the language in the menu
     * Maybe later this method can be replaced by custom components to avoid rebuilding the whole view.
     */
    public void rebuildView() {
        this.getAppView().getAppInfoWindow().dispose();
        this.getAppView().getMainWindow().dispose();
        this.setAppView(new AppView(this));
        this.getAppView().getMainWindow().setVisible(true);
    }

    //Only getter and setter from here.

    public AppModel getAppModel() {
        return appModel;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public AppView getAppView() {
        return appView;
    }

    public void setAppView(AppView appView) {
        this.appView = appView;
    }

    public SettingsSerializer getSettingsSerializer() {
        return settingsSerializer;
    }

    public void setSettingsSerializer(SettingsSerializer settingsSerializer) {
        this.settingsSerializer = settingsSerializer;
    }

    public BackupProcessControl getBackupProcessControl() {
        return backupProcessControl;
    }

    public void setBackupProcessControl(BackupProcessControl backupProcessControl) {
        this.backupProcessControl = backupProcessControl;
    }
}
