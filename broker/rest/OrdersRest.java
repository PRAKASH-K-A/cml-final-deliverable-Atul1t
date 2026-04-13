import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * REST entry point that starts the order lifecycle.
 */
@Path("/api/orders")
public class OrdersRest {

    private OrderDao orderDao;
    private FIXOrderService fixOrderService;

    @POST
    public Response createOrder(OrderEntity order) {
        orderDao.save(order);
        fixOrderService.sendNewOrder(order);
        return Response.ok(order).build();
    }

    @POST
    @Path("/{clOrdId}/cancel")
    public Response cancelOrder(@PathParam("clOrdId") String id) {
        OrderEntity order = orderDao.find(id);
        fixOrderService.sendCancelOrder(order);
        return Response.ok(order).build();
    }
}
