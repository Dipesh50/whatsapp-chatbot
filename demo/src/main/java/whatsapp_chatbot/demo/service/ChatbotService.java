package whatsapp_chatbot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whatsapp_chatbot.demo.model.IncomingMessage;
import whatsapp_chatbot.demo.model.MessageLog;
import whatsapp_chatbot.demo.model.OutgoingMessage;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ChatbotService {

    private static final Logger log = LoggerFactory.getLogger(ChatbotService.class);

    private final List<MessageLog> messageHistory = new CopyOnWriteArrayList<>();

    private static final Map<String, String> REPLY_MAP = new LinkedHashMap<>();

    static {
        REPLY_MAP.put("hi",        "Hello! How can I help you today?");
        REPLY_MAP.put("hello",     "Hello there! What can I do for you?");
        REPLY_MAP.put("hey",       "Hey! What's up?");
        REPLY_MAP.put("bye",       "Goodbye! Have a great day!");
        REPLY_MAP.put("goodbye",   "Goodbye! Take care!");
        REPLY_MAP.put("thanks",    "You're welcome!");
        REPLY_MAP.put("thank you", "You're welcome! Happy to help!");
        REPLY_MAP.put("help",      "You can say: Hi, Hello, Help, About, or Bye.");
        REPLY_MAP.put("about",     "I'm a WhatsApp Chatbot built with Java & Spring Boot!");
    }

    public OutgoingMessage processMessage(IncomingMessage incoming) {
        String sender = incoming.getFrom();
        String text = incoming.getText() != null ? incoming.getText().trim() : "";

        log.info("Incoming | From: {} | Text: {}", sender, text);

        String reply = generateReply(text);

        log.info("Reply | To: {} | Reply: {}", sender, reply);

        MessageLog entry = new MessageLog();
        entry.setLogId(UUID.randomUUID().toString());
        entry.setFrom(sender);
        entry.setReceivedText(text);
        entry.setBotReply(reply);
        entry.setReceivedAt(LocalDateTime.now());
        entry.setMessageId(incoming.getMessageId());
        messageHistory.add(entry);

        OutgoingMessage response = new OutgoingMessage();
        response.setStatus("success");
        response.setTo(sender);
        response.setReply(reply);
        response.setOriginalMessage(text);
        response.setResponseTimestamp(System.currentTimeMillis());
        response.setMessageId(incoming.getMessageId());
        return response;
    }

    private String generateReply(String text) {
        String normalized = text.toLowerCase().trim();
        if (REPLY_MAP.containsKey(normalized)) return REPLY_MAP.get(normalized);
        for (Map.Entry<String, String> entry : REPLY_MAP.entrySet()) {
            if (normalized.contains(entry.getKey())) return entry.getValue();
        }
        return "Sorry, I didn't understand that. Type 'help' to see what I can do!";
    }

    public List<MessageLog> getAllLogs() {
        List<MessageLog> reversed = new ArrayList<>(messageHistory);
        Collections.reverse(reversed);
        return reversed;
    }

    public int getMessageCount() { return messageHistory.size(); }

    public void clearLogs() {
        messageHistory.clear();
        log.warn("Message history cleared.");
    }
}