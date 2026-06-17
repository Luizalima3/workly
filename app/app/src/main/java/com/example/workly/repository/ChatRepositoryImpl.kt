package com.example.workly.repository

import com.example.workly.model.ChatMessage
import com.example.workly.service.ChatService
import com.example.workly.service.ChatConversation
import kotlinx.coroutines.flow.Flow

/**
 * Repositório de Chat - RF05
 * Camada de abstração entre ViewModels e o serviço de chat
 */
class ChatRepository(private val chatService: ChatService) {

    suspend fun sendMessage(chatId: String, message: ChatMessage): Boolean {
        val result = chatService.sendMessage(chatId, message)
        if (result) {
            // Atualizar última mensagem do chat
            chatService.updateLastMessage(chatId, message.text)
        }
        return result
    }

    fun observeMessages(chatId: String): Flow<List<ChatMessage>> {
        return chatService.observeMessages(chatId)
    }

    suspend fun getOrCreateChat(userId1: String, userId2: String): String {
        return chatService.getOrCreateChat(userId1, userId2)
    }

    fun getUserChats(userId: String): Flow<List<ChatConversation>> {
        return chatService.getUserChats(userId)
    }

    suspend fun deleteChat(chatId: String): Boolean {
        return chatService.deleteChat(chatId)
    }
}
