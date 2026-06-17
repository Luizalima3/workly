package com.example.workly.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workly.repository.ChatRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

/**
 * Testes Unitários para ChatViewModel - RF05
 */
class ChatViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var chatRepository: ChatRepository

    private lateinit var viewModel: ChatViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ChatViewModel(chatRepository)
    }

    @Test
    fun testSendMessage_WithBlankText_DoesNotSendMessage() = runTest(testDispatcher) {
        // Arrange
        val blankText = "   "
        
        // Act
        viewModel.sendMessage(
            text = blankText,
            senderId = "user_1",
            senderName = "João"
        )

        // Assert
        assert(viewModel.error.value != null)
    }

    @Test
    fun testInitializeChat_ValidUserIds_StartsObservingMessages() = runTest(testDispatcher) {
        // Arrange
        val userId1 = "user_1"
        val userId2 = "user_2"

        // Act
        viewModel.initializeChat(userId1, userId2)

        // Assert
        assert(viewModel.currentChatId.value.isNotEmpty())
    }
}
