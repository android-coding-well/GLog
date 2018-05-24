# GLog
## 介绍说明
* 此库基于[ALog](https://github.com/Blankj/ALog),对源码做了部分修改。
---
## 使用说明
* 初始化以及反初始化
```
//一般在Application中初始化
val config = ALog.init(this)
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的 /cache/log/ 目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为 "glog"，即写入文件为 "glog-yyyy-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setConsoleFilter(ALog.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(ALog.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1)// log 栈深度，默认为 1
                .setStackOffset(0)// 设置栈偏移，比如二次封装的话就需要设置，默认为 0
                .setLogStoreStrategy(SimpleLogStoreStrategy())//设置日志存储策略
                .setMaxSingleLogFileSize(30)//设置单文件大小，默认15MB


 //在程序退出时使用，以释放相关资源
 ALog.uninit();
```
* 更多详细介绍可以查看[ALog](https://github.com/Blankj/ALog)
---
## JavaDoc文档

* [在线JavaDoc](https://jitpack.io/com/github/huweijian5/GLog/1.0.0/javadoc/index.html)
* 网址：`https://jitpack.io/com/github/huweijian5/GLog/[VersionCode]/javadoc/index.html`
* 其中[VersionCode](https://github.com/huweijian5/GLog/releases)请替换为最新版本号
* 注意文档使用UTF-8编码，如遇乱码，请在浏览器选择UTF-8编码即可

---
## 引用

* 如果需要引用此库,做法如下：
* Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
* and then,add the dependecy:
```
dependencies {
	        compile 'com.github.huweijian5:GLog:latest_version'
}
```
* 其中latest_version请到[releases](https://github.com/huweijian5/GLog/releases)中查看并替换

## 注意
* 为了避免引入第三方库导致工程依赖多个版本的问题，如android support库
* 故建议在个人的工程目录下的build.gradle下加入以下变量，具体请看此[build.gradle](https://github.com/huweijian5/项目名称/blob/master/build.gradle)
```
ext{
     minSdkVersion = 18
    targetSdkVersion = 27
    compileSdkVersion = 27
    buildToolsVersion = '27.0.1'

    // App dependencies
    supportLibraryVersion = '27.1.1'
    junitVersion = '4.12'
    runnerVersion = '1.0.2'
    espressoVersion = '3.0.2'
}
```
* 请注意，对于此库已有的变量，命名请保持一致
