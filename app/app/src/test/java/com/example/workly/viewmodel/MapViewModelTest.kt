package com.example.workly.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workly.repository.LocationRepository
import com.example.workly.service.LocationUpdate
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

   
                                            
   
class MapViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var locationRepository: LocationRepository

    private lateinit var viewModel: MapViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MapViewModel(locationRepository)
    }

    @Test
    fun testCalculateDistance_BetweenTwoPoints_ReturnsCorrectDistance() = runTest(testDispatcher) {
                                                                      
                                      
        val lat1 = -23.561414
        val lon1 = -46.656388
        val lat2 = -23.561414
        val lon2 = -46.586388

              
                                                 
        val method = viewModel.javaClass.getDeclaredMethod(
            "calculateDistance",
            Double::class.java,
            Double::class.java,
            Double::class.java,
            Double::class.java
        ).apply { isAccessible = true }
        
        val distance = method.invoke(viewModel, lat1, lon1, lat2, lon2) as Float

                 
        assert(distance > 0)
        assert(distance < 100000)                   
    }

    @Test
    fun testSetSearchRadius_ValidRadius_UpdatesState() = runTest(testDispatcher) {
                  
        val newRadius = 10000f         

              
        viewModel.setSearchRadius(newRadius)

                 
        assert(viewModel.searchRadius.value == newRadius)
    }

    @Test
    fun testFilterProvidersByDistance_EmptyList_ReturnsEmpty() = runTest(testDispatcher) {
                  
        val emptyProviders = emptyList<com.example.workly.model.ProviderLocationInfo>()

              
        viewModel.filterProvidersByDistance(emptyProviders, 5000f)

                 
        assert(viewModel.providersNearby.value.isEmpty())
    }
}
