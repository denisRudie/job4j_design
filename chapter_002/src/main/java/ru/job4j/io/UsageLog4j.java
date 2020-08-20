package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    @SuppressWarnings("checkstyle:OperatorWrap")
    public static void main(String[] args) {
        double var1 = 0.5d;
        float var2 = 0.55f;
        long var3 = 1000L;
        char var4 = ' ';
        boolean var5 = true;
        byte var6 = (byte) 7;
        int var7 = 33;
        short var8 = (short) 12;

        LOG.debug("User info: var1 : {}, var2 : {}, "
                + "var3 : {}, var4 : {}, var5 : {}, "
                + "var6 : {}, var7 : {}, var8 : {}",
                var1, var2, var3, var4, var5, var6, var7, var8);
    }
}