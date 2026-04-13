/**
 * Broker-side outbound FIX order publishing.
 * Sends new orders and cancel requests to the exchange.
 */
public class FIXOrderService {

    private OrderDao orderDao;
    private SessionID sessionID;

    public void sendNewOrder(OrderEntity order) {
        NewOrderSingle newOrder = new NewOrderSingle();
        newOrder.set(new ClOrdID(order.getClOrdId()));
        newOrder.set(new Symbol(order.getSymbol()));
        Session.sendToTarget(newOrder, sessionID);

        order.setStatus("PENDING_NEW");
        orderDao.updateOrder(order);
    }

    public void sendCancelOrder(OrderEntity order) {
        OrderCancelRequest cancelRequest = new OrderCancelRequest();
        cancelRequest.set(new ClOrdID(order.getClOrdId()));
        Session.sendToTarget(cancelRequest, sessionID);

        order.setStatus("PENDING_CANCEL");
        orderDao.updateOrder(order);
    }
}
