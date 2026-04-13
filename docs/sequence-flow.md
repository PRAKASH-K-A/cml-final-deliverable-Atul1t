# Sequence Flow

```text
Client
  │
  ▼
OrdersRest (broker)
  │
  ▼
FIXOrderService (broker)
  │   sends NewOrderSingle / OrderCancelRequest
  ▼
Exchange
  │
  ▼
ExecutionReportService (exchange)
  │   sends ExecutionReport ACK / FILL / CANCEL
  ▼
ExecutionReportService (broker)
  │   updates DB + notifies listeners
  ▼
UI / downstream services
```
