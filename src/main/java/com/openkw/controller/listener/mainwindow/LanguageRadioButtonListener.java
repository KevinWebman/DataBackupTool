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
package com.openkw.controller.listener.mainwindow;

import com.openkw.controller.AppController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * When this listener is invoked the currently set language in the data model is changed
 * and a view rebuild is invoked through the controller.
 */
public class LanguageRadioButtonListener implements ActionListener {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;
    /**
     * The language which is set when this listener is invoked.
     */
    private final Locale language;

    /**
     * @param appController the mvc controller instance.
     * @param language      the language which is being set when this listener is invoked.
     */
    public LanguageRadioButtonListener(AppController appController, Locale language) {
        this.appController = appController;
        this.language = language;
    }

    /**
     * When this method is invoked the currently set language in the data model is changed
     * and a view rebuild is invoked through the controller.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.appController.getAppModel().setCurrentLanguage(this.language);
        this.appController.getAppModel().setLanguageResourceBundle(
                ResourceBundle.getBundle("language", this.language));
        this.appController.getSettingsSerializer().fireSettingsChanged();
        this.appController.rebuildView();
    }
}
