package com.larapin.footballappkotlin.detail.event

import com.larapin.footballappkotlin.model.event.EventDetail

/**
 * Created by Avin on 06/09/2018.
 * class interface EventDetailView
 */
interface EventDetailView{
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data: List<EventDetail>)
}