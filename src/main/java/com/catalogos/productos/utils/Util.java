package com.catalogos.productos.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public String getFolio() {
        String currentFolio = UUID.randomUUID().toString();
        MDC.put("currentFolio", currentFolio);
        return currentFolio;

    }

    public String getFecha() {

        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 5);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format1.format(cal.getTime());
    }

    public String[] generateServices(String list) {
        if (list.isEmpty()) {
            return new String[0];
        }
        return list.split(",");
    }

}