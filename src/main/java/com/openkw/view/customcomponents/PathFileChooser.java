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
package com.openkw.view.customcomponents;

import com.openkw.controller.AppController;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * This class represents a custom prepared JFileChooser which only accepts directories to be chosen by the user.
 */
public class PathFileChooser extends JFileChooser {

    /**
     * The MVC controller instance.
     */
    private final AppController appController;

    /**
     * @param appController the MVC controller instance.
     */
    public PathFileChooser(AppController appController) {
        this.appController = appController;
        this.setDialogTitle(appController.getAppModel()
                .getLanguageResourceBundle().getString("PathFileChooser.dialogTitle"));
        this.setFileSelectionMode(PathFileChooser.DIRECTORIES_ONLY);
        this.setMultiSelectionEnabled(false);
        this.setAcceptAllFileFilterUsed(false);
        this.setFileFilter(new FolderFilter(this.appController));
        this.setApproveButtonText(appController.getAppModel()
                .getLanguageResourceBundle().getString("PathFileChooser.approveButtonText"));
    }

    /**
     * This class has the purpose to make it impossible to choose anything else but a directory inside the
     * custom JFileChooser.
     */
    private static class FolderFilter extends javax.swing.filechooser.FileFilter {

        /**
         * This description is being displayed in the file filter menu in the JFileChooser.
         */
        private final String descriptionText;

        /**
         * @param appController the MVC controller instance.
         */
        public FolderFilter(AppController appController) {
            this.descriptionText = appController.getAppModel()
                    .getLanguageResourceBundle().getString("PathFileChooser.FolderFilter.descriptionText");
        }

        /**
         * Whether the given file is accepted by this filter.
         */
        @Override
        public boolean accept(File f) {
            return f.isDirectory();
        }

        /**
         * The description of this filter. For example: "JPG and GIF Images"
         *
         * @see FileView#getName
         */
        @Override
        public String getDescription() {
            return this.descriptionText;
        }
    }
}
