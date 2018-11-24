package com.yohanesam.footballmatchschedule.testing

import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class APIRepositoryUnitTest {

    @Test
    fun testToDoRequest() {

        val apiRepository = mock(APIRepository::class.java)
        var url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328"

        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)

    }

}