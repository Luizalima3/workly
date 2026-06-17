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

/**
 * Testes Unitários para MapViewModel - RF02
 */
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
        // Arrange - São Paulo (Av. Paulista) → São Paulo (Av. Brasil)
        // Distância aproximada: 15 km
        val lat1 = -23.561414
        val lon1 = -46.656388
        val lat2 = -23.561414
        val lon2 = -46.586388

        // Act
        // Chamando método privado via reflection
        val method = viewModel.javaClass.getDeclaredMethod(
            "calculateDistance",
            Double::class.java,
            Double::class.java,
            Double::class.java,
            Double::class.java
        ).apply { isAccessible = true }
        
        val distance = method.invoke(viewModel, lat1, lon1, lat2, lon2) as Float

        // Assert
        assert(distance > 0)
        assert(distance < 100000) // Menos de 100 km
    }

    @Test
    fun testSetSearchRadius_ValidRadius_UpdatesState() = runTest(testDispatcher) {
        // Arrange
        val newRadius = 10000f // 10 km

        // Act
        viewModel.setSearchRadius(newRadius)

        // Assert
        assert(viewModel.searchRadius.value == newRadius)
    }

    @Test
    fun testFilterProvidersByDistance_EmptyList_ReturnsEmpty() = runTest(testDispatcher) {
        // Arrange
        val emptyProviders = emptyList<com.example.workly.model.ProviderLocationInfo>()

        // Act
        viewModel.filterProvidersByDistance(emptyProviders, 5000f)

        // Assert
        assert(viewModel.providersNearby.value.isEmpty())
    }
}
