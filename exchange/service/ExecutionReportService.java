/**
 * Exchange-side service responsible for publishing FIX 4.4 ExecutionReport
 * messages back to the broker after order lifecycle events.
 */
public class ExecutionReportService {

    private SessionID sessionID;

    public void sendAck(Order order) throws Exception {
        ExecutionReport report = createBaseReport(order);
        report.set(new ExecType(ExecType.NEW));
        report.set(new OrdStatus(OrdStatus.NEW));
        Session.sendToTarget(report, sessionID);
    }

    public void sendFill(Order order, double lastQty, double lastPx) throws Exception {
        ExecutionReport report = createBaseReport(order);
        report.set(new ExecType(ExecType.TRADE));
        report.set(new OrdStatus(order.isFilled() ? OrdStatus.FILLED : OrdStatus.PARTIALLY_FILLED));
        report.set(new LastQty(lastQty));
        report.set(new LastPx(lastPx));
        Session.sendToTarget(report, sessionID);
    }

    public void sendCancel(Order order) throws Exception {
        ExecutionReport report = createBaseReport(order);
        report.set(new ExecType(ExecType.CANCELED));
        report.set(new OrdStatus(OrdStatus.CANCELED));
        Session.sendToTarget(report, sessionID);
    }

    private ExecutionReport createBaseReport(Order order) {
        ExecutionReport report = new ExecutionReport();
        report.set(new OrderID(order.getOrderId()));
        report.set(new ClOrdID(order.getClOrdId()));
        report.set(new Symbol(order.getSymbol()));
        report.set(new Side(order.getSide()));
        return report;
    }
}
