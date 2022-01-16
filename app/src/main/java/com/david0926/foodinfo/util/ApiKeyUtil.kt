package com.david0926.foodinfo.util

import com.david0926.foodinfo.BuildConfig
import java.net.URLDecoder

object ApiKeyUtil {
    fun getApiKey(): String = URLDecoder.decode(BuildConfig.API_KEY, "utf-8")
}