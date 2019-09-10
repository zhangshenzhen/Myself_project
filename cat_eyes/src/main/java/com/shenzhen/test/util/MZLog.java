package com.shenzhen.test.util;

import android.util.Log;

/***
 * Android log工具类
 *
 * @author Mourinho.Zhu on 16-11-24.
 */
public final class MZLog {
    /* **************************************************************
     * Define debug levels
     */
    public static final int LEVEL_TEST = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_NONE = 5;

    //Default base tag
    private static String mBaseTag = "MZLog";

    //Default debug level
    private static int mDebugLevel = LEVEL_TEST;

    //规定每段显示的长度
    private static int mLogMaxLength = 2000;

    private MZLog() {
    }

    /***
     * Set base log tag
     *
     * @param baseTag base log tag
     */
    public static void setBaseTag(String baseTag) {
        mBaseTag = baseTag;
    }

    /***
     * Set debug level
     *
     * @param level debug log level
     */
    public static void setDebugLevel(int level) {
        if (level >= LEVEL_TEST && level <= LEVEL_NONE) {
            mDebugLevel = level;
        } else {
            e(mBaseTag, "setDebugLevel fail bad level --> " + level);
        }
    }

    /**
     * Output test level log same as i
     *
     * @param subTag log sub tag
     * @param msg    log message
     * @param extras extra params
     */
    public static void t(String subTag, String msg, Object... extras) {
        i(subTag, msg, extras);
    }

    /**
     * Output test level log
     *
     * @param subTag log sub tag
     * @param msg    log message
     * @param extras extra params
     */
    public static void i(String subTag, String msg, Object... extras) {
        if (mDebugLevel <= LEVEL_TEST) {
            String log = getLogMessage(subTag, msg, extras);
            log(LEVEL_TEST, mBaseTag, log);
        }
    }

    /**
     * Output debug level log
     *
     * @param subTag log sub tag
     * @param msg    log message
     * @param extras extra params
     */
    public static void d(String subTag, String msg, Object... extras) {
        if (mDebugLevel <= LEVEL_DEBUG) {
            String log = getLogMessage(subTag, msg, extras);
            log(LEVEL_DEBUG, mBaseTag, log);
        }
    }

    /**
     * Output warning level log
     *
     * @param subTag log sub tag
     * @param msg    log message
     * @param extras extra params
     */
    public static void w(String subTag, String msg, Object... extras) {
        if (mDebugLevel <= LEVEL_WARNING) {
            String log = getLogMessage(subTag, msg, extras);
            log(LEVEL_WARNING, mBaseTag, log);
        }
    }

    /**
     * Output error level log
     *
     * @param subTag log sub tag
     * @param msg    log message
     * @param extras extra params
     */
    public static void e(String subTag, String msg, Object... extras) {
        if (mDebugLevel <= LEVEL_ERROR) {
            String log = getLogMessage(subTag, msg, extras);
            log(LEVEL_ERROR, mBaseTag, log);
        }
    }

    /**
     * Output error level log
     *
     * @param subTag    log sub tag
     * @param throwable throwable
     */
    public static void e(String subTag, Throwable throwable) {
        if (mDebugLevel <= LEVEL_ERROR) {
            Log.e(subTag, "", throwable);
        }
    }

    /**
     * Output method name like "Log.d(TAG,"--onCreate--")" when use it in onCreate
     * method
     *
     * @param subTag log sub tag
     */
    public static void m(String subTag, Object... extras) {
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        if (null != traces && traces.length > 3 && mDebugLevel <= LEVEL_DEBUG) {
            String message = traces[3].getMethodName();
            String log = getLogMessage(subTag, message, extras);
            log(LEVEL_DEBUG, mBaseTag, log);
        }
    }

    //格式化log
    private static String getLogMessage(String subTag, String msg, Object... extras) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + subTag + "] " + msg);
        if (null != extras) {
            for (int i = 0; i < extras.length; ++i) {
                if (i % 2 == 0) {
                    sb.append(" , ");
                }
                sb.append(extras[i]);
                if (i % 2 == 0) {
                    sb.append(" --> ");
                }
            }
        }
        return sb.toString();
    }

    private static void log(int level, String tag, String log) {
        int strLength = log.length();
        int start = 0;
        int end = mLogMaxLength;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                log(level, tag, log, start, end);
                start = end;
                end = end + mLogMaxLength;
            } else {
                log(level, tag, log, start, strLength);
                break;
            }
        }
    }

    private static void log(int level, String tag, String log, int start, int end) {
        switch (level) {
            case LEVEL_TEST:
                Log.d(tag, log.substring(start, end));
                break;
            case LEVEL_DEBUG:
                Log.d(tag, log.substring(start, end));
                break;
            case LEVEL_WARNING:
                Log.w(tag, log.substring(start, end));
                break;
            case LEVEL_ERROR:
                Log.e(tag, log.substring(start, end));
                break;
            case LEVEL_NONE:
            default:
                break;
        }
    }
}
