import java.util.ArrayList;
import java.util.List;

/**
 * Broker-side consumer for incoming ExecutionReport messages.
 * Updates internal order state and notifies listeners for downstream use.
 */
public class ExecutionReportService {

    private OrderDao orderDao;
    private List<ExecutionListener> listeners = new ArrayList<>();

    public void processExecutionReport(ExecutionReport report) {
        OrderEntity order = orderDao.findByClOrdId(report.getClOrdID().getValue());
        String status = report.getOrdStatus().getValue();

        order.setStatus(status);
        orderDao.updateOrder(order);

        notifyExecutionListeners(new ExecutionEvent(report));
    }

    public void addExecutionListener(ExecutionListener listener) {
        listeners.add(listener);
    }

    private void notifyExecutionListeners(ExecutionEvent event) {
        for (ExecutionListener l : listeners) {
            l.onExecution(event);
        }
    }
}
