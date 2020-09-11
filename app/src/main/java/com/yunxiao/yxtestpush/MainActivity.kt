package com.yunxiao.yxtestpush

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yunxiao.testpushtool.SendPushActivity
import com.yunxiao.testpushtool.pushAPI.PushToolConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHellow.setOnClickListener {
            startActivity(Intent(this, SendPushActivity::class.java))
        }
        val credentials= "1e54fab830b6f700ab635320:7ade33f1f9782b7311dbae74"
        PushToolConfig.configAuth(this, credentials, credentials).setPushExtras(this.applicationContext,ExtrasBean()).initAlias(this,"ghjk").initRegistrationId(this,"ghjk")
    }
}