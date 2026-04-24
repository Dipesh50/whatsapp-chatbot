package whatsapp_chatbot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class MessageLog {

    @JsonProperty("log_id")
    private String logId;

    @JsonProperty("from")
    private String from;

    @JsonProperty("received_text")
    private String receivedText;

    @JsonProperty("bot_reply")
    private String botReply;

    @JsonProperty("received_at")
    private LocalDateTime receivedAt;

    @JsonProperty("message_id")
    private String messageId;

    // Getters and Setters
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getReceivedText() { return receivedText; }
    public void setReceivedText(String receivedText) { this.receivedText = receivedText; }

    public String getBotReply() { return botReply; }
    public void setBotReply(String botReply) { this.botReply = botReply; }

    public LocalDateTime getReceivedAt() { return receivedAt; }
    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
}