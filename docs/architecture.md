# Architecture Overview

This extracted module represents the implemented portion of **G1-M5: Order Event Publishing (WebSocket + Broker)** from a larger capital-markets trading platform.

## End-to-End Flow

1. A client places or cancels an order through the broker API.
2. `OrdersRest` forwards the request to `FIXOrderService`.
3. `FIXOrderService` sends FIX messages to the exchange and updates local status.
4. The exchange processes the order and sends FIX `ExecutionReport` messages using `ExecutionReportService`.
5. The broker receives those reports in its own `ExecutionReportService`.
6. Internal listeners are notified for downstream systems or UI integration.
7. A WebSocket aggregator exists on the exchange side for low-latency streaming.

## Responsibilities Covered

- FIX-based order lifecycle publishing
- Internal event propagation via listener pattern
- Partial WebSocket streaming foundation
- Status persistence and state transitions
