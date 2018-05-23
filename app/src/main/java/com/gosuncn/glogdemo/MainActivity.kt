package com.gosuncn.glogdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import com.gosuncn.glog.ALog


class MainActivity : AppCompatActivity() ,View.OnClickListener{

    private val TAG = "CMJ"

    private var tvAboutLog: TextView? = null

    private val mConfig = ALog.getConfig()

    private var dir: String? = ""
    private var globalTag = ""
    private var log = true
    private var console = true
    private var head = true
    private var file = false
    private var border = true
    private var single = true
    private var consoleFilter = ALog.V
    private var fileFilter = ALog.V

    private val UPDATE_LOG = 0x01
    private val UPDATE_CONSOLE = 0x01 shl 1
    private val UPDATE_TAG = 0x01 shl 2
    private val UPDATE_HEAD = 0x01 shl 3
    private val UPDATE_FILE = 0x01 shl 4
    private val UPDATE_DIR = 0x01 shl 5
    private val UPDATE_BORDER = 0x01 shl 6
    private val UPDATE_SINGLE = 0x01 shl 7
    private val UPDATE_CONSOLE_FILTER = 0x01 shl 8
    private val UPDATE_FILE_FILTER = 0x01 shl 9

    private val mRunnable = Runnable {
        ALog.v("verbose")
        ALog.d("debug")
        ALog.i("info")
        ALog.w("warn")
        ALog.e("error")
        ALog.a("assert")
    }

    private var longStr: String

    init {
        var  sb = StringBuilder()
        sb.append("len = 10400\ncontent = \"")
        var i=0
        for(i in 0..800){
            sb.append("Hello world. ")
        }
        sb.append("\"")
        longStr = sb.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_toggle_log).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_console).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_tag).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_head).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_border).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_single).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_file).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_dir).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_conole_filter).setOnClickListener(this)
        findViewById<View>(R.id.btn_toggle_file_filter).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_no_tag).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_with_tag).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_in_new_thread).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_null).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_many_params).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_long).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_file).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_json).setOnClickListener(this)
        findViewById<View>(R.id.btn_log_xml).setOnClickListener(this)
        tvAboutLog = findViewById<View>(R.id.tv_about_log) as TextView?
        updateAbout(0)
    }

    override
    fun onClick(view: View) {
        when (view.getId()) {
            R.id.btn_toggle_log -> updateAbout(UPDATE_LOG)
            R.id.btn_toggle_console -> updateAbout(UPDATE_CONSOLE)
            R.id.btn_toggle_tag -> updateAbout(UPDATE_TAG)
            R.id.btn_toggle_head -> updateAbout(UPDATE_HEAD)
            R.id.btn_toggle_file -> updateAbout(UPDATE_FILE)
            R.id.btn_toggle_dir -> updateAbout(UPDATE_DIR)
            R.id.btn_toggle_border -> updateAbout(UPDATE_BORDER)
            R.id.btn_toggle_single -> updateAbout(UPDATE_SINGLE)
            R.id.btn_toggle_conole_filter -> updateAbout(UPDATE_CONSOLE_FILTER)
            R.id.btn_toggle_file_filter -> updateAbout(UPDATE_FILE_FILTER)
            R.id.btn_log_no_tag -> {
                ALog.v("verbose")
                ALog.d("debug")
                ALog.i("info")
                ALog.w("warn")
                ALog.e("error")
                ALog.a("assert")
            }
            R.id.btn_log_with_tag -> {
                ALog.vTag("customTag", "verbose")
                ALog.dTag("customTag", "debug")
                ALog.iTag("customTag", "info")
                ALog.wTag("customTag", "warn")
                ALog.eTag("customTag", "error")
                ALog.aTag("customTag", "assert")
            }
            R.id.btn_log_in_new_thread -> {
                val thread = Thread(mRunnable)
                thread.start()
            }
            R.id.btn_log_null -> {
                ALog.v(null as Any?)
                ALog.d(null as Any?)
                ALog.i(null as Any?)
                ALog.w(null as Any?)
                ALog.e(null as Any?)
                ALog.a(null as Any?)
            }
            R.id.btn_log_many_params -> {
                ALog.v("verbose0", "verbose1")
                ALog.vTag("customTag", "verbose0", "verbose1")
                ALog.d("debug0", "debug1")
                ALog.dTag("customTag", "debug0", "debug1")
                ALog.i("info0", "info1")
                ALog.iTag("customTag", "info0", "info1")
                ALog.w("warn0", "warn1")
                ALog.wTag("customTag", "warn0", "warn1")
                ALog.e("error0", "error1")
                ALog.eTag("customTag", "error0", "error1")
                ALog.a("assert0", "assert1")
                ALog.aTag("customTag", "assert0", "assert1")
            }
            R.id.btn_log_long -> ALog.d(longStr)
            R.id.btn_log_file -> for (i in 0..99) {
                ALog.file("test0 log to file")
                ALog.file(ALog.I, "test0 log to file")
            }
            R.id.btn_log_json -> {
                val json = "{\"tools\": [{ \"name\":\"css format\" , \"site\":\"http://tools.w3cschool.cn/code/css\" },{ \"name\":\"json format\" , \"site\":\"http://tools.w3cschool.cn/code/json\" },{ \"name\":\"pwd check\" , \"site\":\"http://tools.w3cschool.cn/password/my_password_safe\" }]}"
                ALog.json(json)
                ALog.json(ALog.I, json)
            }
            R.id.btn_log_xml -> {
                val xml = "<books><book><author>Jack Herrington</author><title>PHP Hacks</title><publisher>O'Reilly</publisher></book><book><author>Jack Herrington</author><title>Podcasting Hacks</title><publisher>O'Reilly</publisher></book></books>"
                ALog.xml(xml)
                ALog.xml(ALog.I, xml)
            }
        }
    }

    private fun updateAbout(args: Int) {
        when (args) {
            UPDATE_LOG -> log = !log
            UPDATE_CONSOLE -> console = !console
            UPDATE_TAG -> globalTag = if (globalTag == TAG) "" else TAG
            UPDATE_HEAD -> head = !head
            UPDATE_FILE -> file = !file
            UPDATE_DIR -> if (getDir().contains("test")) {
                dir = null
            } else {
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    dir = Environment.getExternalStorageDirectory().getPath() + System.getProperty("file.separator") + "test"
                }
            }
            UPDATE_BORDER -> border = !border
            UPDATE_SINGLE -> single = !single
            UPDATE_CONSOLE_FILTER -> consoleFilter = if (consoleFilter == ALog.V) ALog.W else ALog.V
            UPDATE_FILE_FILTER -> fileFilter = if (fileFilter == ALog.V) ALog.I else ALog.V
        }
        mConfig.setLogSwitch(log)
                .setConsoleSwitch(console)
                .setGlobalTag(globalTag)
                .setLogHeadSwitch(head)
                .setLog2FileSwitch(file)
                .setDir(dir)
                .setBorderSwitch(border)
                .setSingleTagSwitch(single)
                .setConsoleFilter(consoleFilter)
                .setFileFilter(fileFilter)
        tvAboutLog!!.text = mConfig.toString()
    }

    private fun getDir(): String {
        return mConfig.toString().split(System.getProperty("line.separator").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[5].substring(5)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
