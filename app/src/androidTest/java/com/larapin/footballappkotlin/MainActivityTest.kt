package com.larapin.footballappkotlin

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.larapin.footballappkotlin.R.id.*
import com.larapin.footballappkotlin.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Avin on 19/10/2018.
 * MainActivityTest
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Thread.sleep(2000)

        //memeriksa viewpager tampil
        onView(withId(viewpager_event)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan swipe ke kiri
        onView(withId(viewpager_event)).perform(swipeLeft())
        Thread.sleep(2000)

        //memeriksa spinner tampil
        onView(withId(spinner_event_last))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada spinner
        onView(withId(spinner_event_last)).perform(click())
        Thread.sleep(2000)
        //melakukan klik pada item French Ligue 1
        onView(withText("French Ligue 1")).perform(click())
        Thread.sleep(2000)

        //memeriksa daftar event tampil
        onView(withId(list_event_last))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan scroll pada daftar item ke 10
        onView(withId(list_event_last)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(9))
        Thread.sleep(2000)
        //melakukan klik pada daftar item ke 10
        onView(withId(list_event_last)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Thread.sleep(2000)

        //memeriksa tombol tambah favorit tampil
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada tombol tambah favorit
        onView(withId(add_to_favorite)).perform(click())
        //memeriksa text Added to favorite tampil
        onView(withText("Added to favorite"))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //tekan back
        pressBack()
        Thread.sleep(2000)

        //memeriksa navigasi tampil
        onView(withId(navigation))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada navigasi team
        onView(withId(navigation_team)).perform(click())
        Thread.sleep(2000)
        //memeriksa tombol cari tampil
        onView(withId(action_search))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada tombol cari
        onView(withId(action_search)).perform(click())
        Thread.sleep(2000)
        //melakukan search
        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText("Dortmund"))
        Thread.sleep(2000)
        //melakukan klik pada item team
        onView(withId(list_team)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)

        //memeriksa tombol tambah favorit tampil
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada tombol tambah favorit
        onView(withId(add_to_favorite)).perform(click())
        //memeriksa text Added to favorite tampil
        onView(withText("Added to favorite"))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)

        //memeriksa viewpager tampil
        onView(withId(viewpager_team_detail)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan swipe ke kiri
        onView(withId(viewpager_team_detail)).perform(swipeLeft())
        Thread.sleep(2000)

        //tekan back
        pressBack()
        Thread.sleep(2000)

        //melakukan klik pada navigasi favorite
        onView(withId(navigation_favorite)).perform(click())
        Thread.sleep(2000)
        //memeriksa daftar favorite event tampil
        onView(withId(list_favorite_event))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada daftar item ke 1
        onView(withId(list_favorite_event)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)

        //memeriksa tombol tambah favorit tampil
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada tombol tambah favorit
        onView(withId(add_to_favorite)).perform(click())
        //memeriksa text Added to favorite tampil
        onView(withText("Removed from favorite"))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //tekan back
        pressBack()
        Thread.sleep(2000)

        //memeriksa viewpager tampil
        onView(withId(viewpager_favorite)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan swipe ke kiri
        onView(withId(viewpager_favorite)).perform(swipeLeft())
        Thread.sleep(2000)

        //memeriksa daftar favorite team tampil
        onView(withId(list_favorite_team))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada daftar item ke 1
        onView(withId(list_favorite_team)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)

        //memeriksa tombol tambah favorit tampil
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada tombol tambah favorit
        onView(withId(add_to_favorite)).perform(click())
        //memeriksa text Added to favorite tampil
        onView(withText("Removed from favorite"))
                .check(matches(isDisplayed()))
        Thread.sleep(2000)
        //tekan back
        pressBack()
        Thread.sleep(2000)

    }
}