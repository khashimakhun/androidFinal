package com.larapin.footballappkotlin.api

import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Avin on 19/10/2018.
 * ApiRepositoryTest
 */
class ApiRepositoryTest{
    @Test
    fun tesDoRequest(){
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}
