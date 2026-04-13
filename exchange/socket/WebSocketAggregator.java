import jakarta.websocket.server.ServerEndpoint;

/**
 * Exchange-side WebSocket aggregation endpoint for real-time UI subscriptions.
 * Note: the endpoint exists, but in the original project only part of the
 * order lifecycle was wired into it.
 */
@ServerEndpoint("/ws/aggregator")
public class WebSocketAggregator {

    public static class Message {
        public String version = "v1";
        public String type;
        public Object data;
        public long timestamp;
    }

    public void broadcastOrderUpdate(Order order) {
        Message msg = new Message();
        msg.type = "ORDER_UPDATE";
        msg.data = order;
        msg.timestamp = System.currentTimeMillis();
        sendToSubscribers("ORDERS", msg);
    }

    public void broadcastTrade(Object trade) {
        Message msg = new Message();
        msg.type = "TRADE_UPDATE";
        msg.data = trade;
        msg.timestamp = System.currentTimeMillis();
        sendToSubscribers("TRADES", msg);
    }

    private void sendToSubscribers(String topic, Message msg) {
        // Simplified extracted logic:
        // serialize message and push to active subscribers for the topic
    }
}
