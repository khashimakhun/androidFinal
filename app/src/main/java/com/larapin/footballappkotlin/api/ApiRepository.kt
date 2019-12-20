package com.larapin.footballappkotlin.api

import java.net.URL

/**
 * Created by Avin on 04/09/2018.
 * Class ApiRepository
 */
class ApiRepository{

    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}