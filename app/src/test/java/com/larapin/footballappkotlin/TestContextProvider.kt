package com.larapin.footballappkotlin

import com.larapin.footballappkotlin.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Avin on 19/10/2018.
 * TestContextProvider
 */

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}