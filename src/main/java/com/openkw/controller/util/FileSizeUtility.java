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

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A utility class to deal with common file size related things.
 */
public class FileSizeUtility {

    /**
     * This method returns the recommended file size representation for any given dir or file in the format
     * 12.30 MB and if bigger then 1 GB 34.5 GB for example.
     *
     * @param path the path of the file/directory.
     * @return string representation of the file/dir size, -1 if the file does not exist.
     */
    public static String getRecommendedFileSizePresentation(String path) {
        double size = convertedSize(path, FileSizeType.MB);
        if (size != -1) {
            String prefix = "MB";
            if (size > 1000) {
                size = size / 1024;
                prefix = "GB";
            }
            return String.format("%.2f ", size) + prefix;
        } else {
            return null;
        }
    }

    /**
     * Gets the file/dir size in bytes.
     *
     * @param path the path to the file/dir.
     * @return the file size, -1 if the file does not exist.
     */
    public static long getFileSizeBytes(String path) {
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            return size(Paths.get(path));
        } else {
            return -1;
        }
    }

    /**
     * Gets the file size in kb of the given file/dir path.
     *
     * @param path the path of the given file/dir.
     * @return the file size in kb, -1 if the file does not exist.
     */
    public static double getFileSizeKb(String path) {
        return convertedSize(path, FileSizeType.KB);
    }

    /**
     * Gets the file size in mb of the given file/dir path.
     *
     * @param path the path of the given file/dir.
     * @return the file size in mb, -1 if the file does not exist.
     */
    public static double getFileSizeMB(String path) {
        return convertedSize(path, FileSizeType.MB);
    }

    /**
     * Gets the file size in gb of the given file/dir path.
     *
     * @param path the path of the given file/dir.
     * @return the file size in gb, -1 if the file does not exist.
     */
    public static double getFileSizeGB(String path) {
        return convertedSize(path, FileSizeType.GB);
    }

    /**
     * Get the size of the given file / dir path in the given file size type(kb,mb,gb etc..).
     *
     * @param path         the path of the dir/file.
     * @param fileSizeType the required converted file size type.
     * @return the file size determined by the file size type , -1 if the file does not exist.
     */
    private static double convertedSize(String path, FileSizeType fileSizeType) {
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            return (double) size(Paths.get(path)) / fileSizeType.getByteDivisor();
        } else {
            return -1;
        }
    }

    /**
     * returns the size in byte of the given file by path.
     *
     * @param path the path of the file.
     * @return the size in bytes.
     */
    private static long size(Path path) {
        final AtomicLong size = new AtomicLong(0);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.out.println("skipped: " + file + " (" + exc + ")");
                    // Skip folders that can't be traversed
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    if (exc != null)
                        System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                    // Ignore errors traversing a folder
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
        }
        return size.get();
    }

    /**
     * This enum represents the different file size types and provides
     * the divisor to make the conversion of bytes to common file size types like mb or gb easier.
     */
    enum FileSizeType {
        GB(1024 * 1024 * 1024), MB(1024 * 1024), KB(1024), B(1);

        /**
         * The corresponding file type divisor related to [byte - size type] conversion
         */
        private final int byteDivisor;

        /**
         * @param byteDivisor the file type divisor related to [byte - size type] conversion
         */
        FileSizeType(int byteDivisor) {
            this.byteDivisor = byteDivisor;
        }

        //Only getters from here
        public int getByteDivisor() {
            return byteDivisor;
        }
    }
}
