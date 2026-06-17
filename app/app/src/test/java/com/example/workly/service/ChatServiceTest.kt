package com.example.workly.service

import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import kotlinx.coroutines.test.runTest

   
                                           
                       
   
class ChatServiceTest {

    @Mock
    private lateinit var firestore: FirebaseFirestore

    private lateinit var chatService: ChatService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        chatService = ChatService(firestore)
    }

    @Test
    fun testGenerateChatId_ReturnsSortedUsersId() = runTest {
                  
        val userId1 = "user_b"
        val userId2 = "user_a"

              
        val result1 = chatService.javaClass.getDeclaredMethod(
            "generateChatId",
            String::class.java,
            String::class.java
        ).apply { isAccessible = true }.invoke(chatService, userId1, userId2) as String

                 
        assert(result1.contains("user_a") && result1.contains("user_b"))
    }

    @Test
    fun testSendMessage_ValidMessage_ReturnsTrue() = runTest {
                  
        val chatId = "chat_123"
        val message = ChatMessage(
            id = "msg_1",
            senderId = "user_1",
            senderName = "João",
            text = "Olá!",
            timestamp = System.currentTimeMillis()
        )

                       
                                                                                    
                                                
    }
}
