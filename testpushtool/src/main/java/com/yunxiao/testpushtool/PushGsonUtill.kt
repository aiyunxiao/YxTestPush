package com.yunxiao.testpushtool

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.lang.reflect.Type


/**
 * description
 * @author zhaiyaohua createBy 2020/9/10
 */
class PushGsonUtill {
    companion object {
        fun getJsonStr(jsonStr: String): String {
            val gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()
            val jp = JsonParser()
            val je: JsonElement = jp.parse(jsonStr)
            return gson.toJson(je)
        }

        fun <T> fromJson(str: String?, typeOfT: Type?): T {
            return GsonBuilder().setPrettyPrinting().create().fromJson(str, typeOfT)
        }

        fun toJson(obj: Any?): String {
            return getJsonStr(Gson().toJson(obj))
        }
        fun validate(jsonStr: String?): Boolean {
            val jsonElement: JsonElement = try {
                JsonParser().parse(jsonStr)
            } catch (e: Exception) {
                return false
            }
                ?: return false
            return jsonElement.isJsonObject
        }

    }
}
