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
package com.openkw.controller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openkw.controller.AppController;
import com.openkw.model.SerializationData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * This is a utility class to serialize the SerializationData class in JSON format.
 */
public class SettingsSerializer {
    /**
     * The path where the settings.json is being saved.
     */
    public static final String SETTINGS_PATH = System.getProperty("user.home") + "\\Documents\\DataBackupToolSettings";

    /**
     * The prefix for the settings json file
     */
    public static final String SETTINGS_FILE_POST_FIX = "\\settings.json";

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * The object mapper used to de-serialize the settings.json .
     */
    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * @param appController The mvc controller instance.
     */
    public SettingsSerializer(AppController appController) {
        this.appController = appController;
    }

    /**
     * This method checks if settings.json is existing.If it does it de-serializes the file into
     * a SerializationData object and returns it.
     *
     * @return the de-serialized settings.json object. Null if there is no object to de-serialize.
     */
    public static SerializationData checkSettings() {
        ObjectMapper objectMapper = new ObjectMapper();
        File settingsDir = new File(SETTINGS_PATH + SETTINGS_FILE_POST_FIX);
        if (settingsDir.exists()) {
            try {
                return objectMapper.readValue(settingsDir, SerializationData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This is a helper method to update the currently existing SerializationData instance in the model
     * and also to serialize the data into the settings.json file at the same time.
     */
    public void fireSettingsChanged() {
        File file = new File(SETTINGS_PATH);
        if (file.exists() || file.mkdir()) {
            if (file.isDirectory()) {
                this.appController.getAppModel().getSerializationData().updateData(this.appController.getAppModel());
                try {
                    objectMapper.writeValue(Paths.get(SETTINGS_PATH + SETTINGS_FILE_POST_FIX).toFile(),
                            this.appController.getAppModel().getSerializationData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Problem with serializing, the directory could'nt be created.");
        }

    }
}
