package org.dinesh.util;

import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class LogUtil {

    public static  void info(String message)
    {
       log.info(message);
    }

}
