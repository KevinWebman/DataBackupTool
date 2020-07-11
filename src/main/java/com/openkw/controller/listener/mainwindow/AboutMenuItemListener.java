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

/**
 * This listener opens the app info window.
 */
public class AboutMenuItemListener implements ActionListener {

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * @param appController the mvc controller instance.
     */
    public AboutMenuItemListener(AppController appController) {
        this.appController = appController;
    }

    /**
     * This method opens the app info window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.appController.getAppView().getAppInfoWindow().setVisible(true);
    }
}
