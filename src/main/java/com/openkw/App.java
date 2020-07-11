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
package com.openkw;

import com.openkw.controller.AppController;
import com.openkw.model.AppModel;
import com.openkw.view.AppView;

import javax.swing.*;


/**
 * The main class of the application.
 * Contains only the main method which builds the MVC architecture.
 */
public class App {

    public static void main(String[] args) {
        //Implementing the Model-View-Controller (MVC) architecture of the program here
        AppModel appModel = new AppModel();
        AppController appController = new AppController(appModel);
        SwingUtilities.invokeLater(() -> {
            AppView appView = new AppView(appController);
            appView.getMainWindow().setVisible(true);
        });
    }

}
