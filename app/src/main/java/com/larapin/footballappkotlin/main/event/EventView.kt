package com.larapin.footballappkotlin.main.event

import com.larapin.footballappkotlin.model.event.Event

/**
 * Created by Avin on 04/09/2018.
 * class interface EventView
 */
interface EventView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}