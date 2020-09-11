package com.yunxiao.testpushtool

import android.app.AppOpsManager
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationManagerCompat
import java.lang.reflect.InvocationTargetException

internal class NotificationsCheckUtil {
    companion object {
        fun areNotificationsEnabled(context: Context): Boolean {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return true
            }
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                isEnableV19(context)
            } else {
                isEnableV26(context)
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private fun isEnableV19(context: Context): Boolean {
            val CHECK_OP_NO_THROW = "checkOpNoThrow"
            val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"
            val mAppOps =
                context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val appInfo = context.applicationInfo
            val pkg = context.applicationContext.packageName
            val uid = appInfo.uid
            var appOpsClass: Class<*>? = null /* Context.APP_OPS_MANAGER */
            try {
                appOpsClass = Class.forName(AppOpsManager::class.java.name)
                val checkOpNoThrowMethod = appOpsClass.getMethod(
                    CHECK_OP_NO_THROW,
                    Integer.TYPE,
                    Integer.TYPE,
                    String::class.java
                )
                val opPostNotificationValue =
                    appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
                val value = opPostNotificationValue[Int::class.java] as Int
                return checkOpNoThrowMethod.invoke(
                    mAppOps,
                    value,
                    uid,
                    pkg
                ) as Int == AppOpsManager.MODE_ALLOWED
            } catch (e: ClassNotFoundException) {
            } catch (e: NoSuchMethodException) {
            } catch (e: NoSuchFieldException) {
            } catch (e: InvocationTargetException) {
            } catch (e: IllegalAccessException) {
            } catch (e: Exception) {
            }
            return false
        }

        private fun isEnableV26(context: Context): Boolean {
            val appInfo = context.applicationInfo
            val pkg = context.applicationContext.packageName
            val uid = appInfo.uid
            return try {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val sServiceField =
                    notificationManager.javaClass.getDeclaredMethod("getService")
                sServiceField.isAccessible = true
                val sService = sServiceField.invoke(notificationManager)
                val method = sService.javaClass.getDeclaredMethod(
                    "areNotificationsEnabledForPackage"
                    , String::class.java, Integer.TYPE
                )
                method.isAccessible = true
                method.invoke(sService, pkg, uid) as Boolean
            } catch (e: Exception) {
                true
            }
        }
    }
}