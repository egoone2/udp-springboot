package com.itm.udpserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SystemConfig {
    private static final Logger logger = LoggerFactory.getLogger(SystemConfig.class);
    private static boolean needStop = false;    ///<Не пора ли остановить сервис?

    public static boolean isNeedStop() {
        return needStop;
    }

    public static void setNeedStop(boolean needStop) {
        SystemConfig.needStop = needStop;
    }


    public static void pauseSec(long l) {
        try {
            TimeUnit.SECONDS.sleep(l);
        } catch (InterruptedException ex) {
            logger.warn("Pause breaking.");
        }
    }

    /**
     * @brief Пауза l секунд. Прекращается, если пришла команда на выключение.
     * @param l Длительность паузы в секундах.
     * @details Проверяет раз в 5 сек не было ли команды на выключение.
     */
    public static void pauseSecWithPeriod5(long l) {
        for(int i=0; i<l && !SystemConfig.isNeedStop(); i+=5){
            SystemConfig.pauseSec(5L);
        }
    }
}
