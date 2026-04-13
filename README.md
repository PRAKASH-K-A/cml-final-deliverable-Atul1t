# G1-M5 Order Event Publishing
**Extracted, documented, resume-ready module from a capital markets trading platform**

This repository presents the **implemented portion** of the **G1-M5: Order Event Publishing (WebSocket + Broker)** module from a larger multi-service trading system. It focuses on **FIX-based order lifecycle communication**, **internal event propagation**, and the **foundation for real-time WebSocket delivery**.

## Highlights

- Implemented **FIX 4.4 order lifecycle publishing**
  - New order acknowledgement
  - Partial / full fill updates
  - Cancel confirmations
- Built **broker-side execution report consumption**
- Added **listener-based internal event propagation**
- Included **WebSocket aggregation foundation** for low-latency updates
- Documented **architecture, flow, implemented scope, and limitations**

## Why this matters

This module shows practical experience with:

- **Capital markets messaging patterns**
- **FIX protocol event handling**
- **Real-time system design**
- **State transition management**
- **Backend integration across broker and exchange services**

## Folder Structure

```text
G1-M5-Order-Event-Publishing-resume-level/
├── broker/
│   ├── rest/
│   │   └── OrdersRest.java
│   └── service/
│       ├── ExecutionReportService.java
│       └── FIXOrderService.java
├── exchange/
│   ├── service/
│   │   └── ExecutionReportService.java
│   └── socket/
│       └── WebSocketAggregator.java
├── docs/
│   ├── architecture.md
│   ├── implemented-vs-missing.md
│   └── sequence-flow.md
├── examples/
│   ├── order-update-event.json
│   └── trade-update-event.json
└── README.md
```

## Core Components

### 1. Broker REST entry point
`broker/rest/OrdersRest.java`

Starts the order lifecycle by receiving order placement and cancellation requests, persisting them, and passing them to the outbound FIX service.

### 2. Outbound FIX order publishing
`broker/service/FIXOrderService.java`

Sends:
- `NewOrderSingle`
- `OrderCancelRequest`

Also updates local order states such as:
- `PENDING_NEW`
- `PENDING_CANCEL`

### 3. Exchange-side FIX execution publishing
`exchange/service/ExecutionReportService.java`

Publishes:
- `ACK` / order received
- `FILL` / execution update
- `CANCEL` / cancel confirmation

This is the core implementation for FIX-based event publishing back to the broker.

### 4. Broker-side execution report processing
`broker/service/ExecutionReportService.java`

Consumes incoming execution reports, updates database state, and notifies internal listeners for downstream processing.

### 5. WebSocket aggregation layer
`exchange/socket/WebSocketAggregator.java`

Defines a low-latency event delivery foundation using a structured message envelope. In the original project, this layer existed but was only partially wired into the full order lifecycle.

## Event Examples

See the `examples/` folder for sample event shapes:
- `order-update-event.json`
- `trade-update-event.json`

## Architecture Summary

```text
Client → Broker REST → Broker FIX Service → Exchange
       → Exchange Execution Reports → Broker Execution Consumer
       → Internal listeners / UI integration
```

## What was implemented

- FIX-based event publishing for order lifecycle updates
- Internal event propagation on the broker side
- Order state persistence during outbound and inbound flows
- WebSocket delivery foundation


## Suggested Resume Bullet

- Extracted and documented a trading-system submodule for **FIX-based order event publishing**, covering **ACK/FILL/CANCEL execution reports**, broker-side event consumption, and a **WebSocket streaming foundation** for real-time UI updates.

## How to use this repository

This repository is intended as a **portfolio / documentation extract**, not as a standalone runnable service. It is best used to:

- showcase contribution ownership
- explain architecture in viva or interviews
- demonstrate understanding of event-driven trading workflows

## License

For academic / portfolio demonstration.
