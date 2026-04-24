package whatsapp_chatbot.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whatsapp_chatbot.demo.model.IncomingMessage;
import whatsapp_chatbot.demo.model.MessageLog;
import whatsapp_chatbot.demo.model.OutgoingMessage;
import whatsapp_chatbot.demo.service.ChatbotService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public ResponseEntity<OutgoingMessage> receiveMessage(@RequestBody IncomingMessage incomingMessage) {
        log.info("Webhook triggered by: {}", incomingMessage.getFrom());
        return ResponseEntity.ok(chatbotService.processMessage(incomingMessage));
    }

    @GetMapping("/logs")
    public ResponseEntity<Map<String, Object>> getLogs() {
        List<MessageLog> logs = chatbotService.getAllLogs();
        Map<String, Object> response = new HashMap<>();
        response.put("total_messages", chatbotService.getMessageCount());
        response.put("messages", logs);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "WhatsApp Chatbot Backend");
        health.put("messages_processed", chatbotService.getMessageCount());
        health.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(health);
    }

    @DeleteMapping("/logs")
    public ResponseEntity<Map<String, String>> clearLogs() {
        chatbotService.clearLogs();
        Map<String, String> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "Message history cleared.");
        return ResponseEntity.ok(res);
    }
}