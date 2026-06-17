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
                  
        val blankText = "   "
        
              
        viewModel.sendMessage(
            text = blankText,
            senderId = "user_1",
            senderName = "João"
        )

                 
        assert(viewModel.error.value != null)
    }

    @Test
    fun testInitializeChat_ValidUserIds_StartsObservingMessages() = runTest(testDispatcher) {
                  
        val userId1 = "user_1"
        val userId2 = "user_2"

              
        viewModel.initializeChat(userId1, userId2)

                 
        assert(viewModel.currentChatId.value.isNotEmpty())
    }
}
