package com.gosuncn.glog;

import android.text.TextUtils;
import android.util.Log;

import com.gosuncn.glog.util.DateUtil;

import java.io.File;
import java.util.Date;

/**
 * 日志文件存储策略示例
 * 策略：默认只保存3天的日志，可设置
 */
public class SimpleLogStoreStrategy implements ILogStoreStrategy {
    private static final String TAG = "SimpleLogStoreStrategy";

    private int mLogFileStoreDay = 3;

    /**
     * 设置日志存储天数，大于0
     *
     * @param day
     */
    public SimpleLogStoreStrategy setLogStoreDay(int day) {
        if (day > 0) {
            mLogFileStoreDay = day;
        }
        return this;
    }

    @Override
    public void execute(String logStoreDir) {
        if (TextUtils.isEmpty(logStoreDir)) {
            Log.i(TAG, "日志保存目录为空 ");
            return;
        }
        String logDir = logStoreDir;
        Log.i(TAG, "日志保存目录: " + logDir);

        File dir = new File(logDir);
        if (!dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        Log.i(TAG, "日志保存目录下文件个数: " + files.length);
        for (File file : files) {
            if (file.isFile()) {
                //方案一：根据文件修改日期判断
                long time = file.lastModified();

                //方案二：根据文件名称判断，局限性较大
                //String dateStr=file.getName().replace("alog-","").replace(".txt","");
                //long time= DateUtil.dateStrToTimestamp(dateStr,FORMAT_DATE);
                if (time == 0) {
                    continue;
                }
                int day = DateUtil.getBetweenDays(new Date(time));
                Log.i(TAG, "与当前日期相差天数： " + day);
                if (Math.abs(day) > mLogFileStoreDay - 1) {
                    file.delete();
                    Log.i(TAG, "已删除: " + file.getName());
                }
            }
        }
    }

}
