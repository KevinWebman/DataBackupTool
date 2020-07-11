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
import com.openkw.model.Interval;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Instances of this class change the currently set interval in the data model.
 */
public class IntervalRadioButtonListener implements ActionListener {

    /**
     * The interval which will be set when action performed is invoked.
     */
    private final Interval interval;

    /**
     * The mvc controller instance.
     */
    private final AppController appController;

    /**
     * @param appController The mvc controller instance.
     * @param interval      the interval to be set when the action listener is invoked.
     */
    public IntervalRadioButtonListener(AppController appController, Interval interval) {
        this.appController = appController;
        this.interval = interval;
    }

    /**
     * when this method is invoked the currently set interval will be changed and the
     * serialization data in the data model will be updated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.appController.getAppModel().setCurrentInterval(this.interval);
        this.appController.getSettingsSerializer().fireSettingsChanged();
    }
}
