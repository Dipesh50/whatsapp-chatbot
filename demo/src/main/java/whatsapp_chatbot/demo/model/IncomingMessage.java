package whatsapp_chatbot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomingMessage {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("from")
    private String from;

    @JsonProperty("text")
    private String text;

    @JsonProperty("timestamp")
    private Long timestamp;

    // Getters and Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
}