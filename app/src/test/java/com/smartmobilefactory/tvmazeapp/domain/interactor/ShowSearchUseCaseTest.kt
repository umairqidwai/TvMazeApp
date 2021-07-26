package com.smartmobilefactory.tvmazeapp.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.smartmobilefactory.tvmazeapp.TestCoroutineRule
import com.smartmobilefactory.tvmazeapp.domain.interactors.ShowSearchUseCase
import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponse
import com.smartmobilefactory.tvmazeapp.domain.repo.TvMazeRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShowSearchUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var tvMazeRepo: TvMazeRepo

    lateinit var showSearchUseCase: ShowSearchUseCase


    @Before
    fun setUp() {
        showSearchUseCase = ShowSearchUseCase(tvMazeRepo)
    }


    @Test
    fun run_test_pass() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(tvMazeRepo.search(""))
                .thenReturn(
                    Response.success(TvShowResponse())
                )

            showSearchUseCase.run(ShowSearchUseCase.Params(""))
            Mockito.verify(tvMazeRepo).search("")
            Mockito.verifyNoMoreInteractions(tvMazeRepo)
        }


    }

    @After
    fun tearDown() {
    }
}