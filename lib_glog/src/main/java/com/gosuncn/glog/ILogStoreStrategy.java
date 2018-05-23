package com.gosuncn.glog;

/**
 * 日志存储策略接口
 */
public interface ILogStoreStrategy {
    /**
     * 执行策略
     * 请在此实现文件的存储策略，可以只保存几天的日志，可以将日志进行上送，用户自主决定
     * @param logStoreDir 日志存储目录
     */
    void execute(String logStoreDir);


}
