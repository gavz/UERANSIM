/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.utils;

import tr.havelsan.ueransim.utils.console.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ConfigUtils {

    public static Logger createLoggerFor(String name) {
        var logger = new Logger(name);
        loggingToFile(logger, name, false);
        return logger;
    }

    public static void loggingToFile(Logger logger, String name, boolean standardPrint) {
        if (name.contains(".") || name.contains("/"))
            throw new IllegalArgumentException("name contains '.' or '/'");

        logger.getConsole().setStandardPrintEnabled(standardPrint);
        logger.getConsole().addPrintHandler(str -> {
            final Path path = Paths.get("logs/" + name + ".log");
            try {
                Files.write(path, str.getBytes(StandardCharsets.UTF_8),
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
