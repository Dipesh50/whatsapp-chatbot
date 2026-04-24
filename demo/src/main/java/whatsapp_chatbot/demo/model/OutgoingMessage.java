package whatsapp_chatbot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutgoingMessage {

    @JsonProperty("status")
    private String status;

    @JsonProperty("to")
    private String to;

    @JsonProperty("reply")
    private String reply;

    @JsonProperty("original_message")
    private String originalMessage;

    @JsonProperty("response_timestamp")
    private Long responseTimestamp;

    @JsonProperty("message_id")
    private String messageId;

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }

    public String getOriginalMessage() { return originalMessage; }
    public void setOriginalMessage(String originalMessage) { this.originalMessage = originalMessage; }

    public Long getResponseTimestamp() { return responseTimestamp; }
    public void setResponseTimestamp(Long responseTimestamp) { this.responseTimestamp = responseTimestamp; }

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
}