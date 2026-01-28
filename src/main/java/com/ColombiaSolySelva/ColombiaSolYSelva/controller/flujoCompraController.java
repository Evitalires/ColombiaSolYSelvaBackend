    package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

    import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CheckoutRequest;
    import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
    import com.ColombiaSolySelva.ColombiaSolYSelva.service.IpedidosService;
    import com.ColombiaSolySelva.ColombiaSolYSelva.service.MercadoPagoService;
    import org.springframework.security.core.Authentication;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.math.BigDecimal;
    import java.util.Map;

    @RestController
    @RequestMapping("/checkout")
    @CrossOrigin(
            origins = {"*"}
    )
    public class flujoCompraController {

        @Autowired
        private IpedidosService pedidoService;

        @Autowired
        private MercadoPagoService mercadoPagoService;

        @PostMapping("/confirmar")

        public ResponseEntity<?> confirmarCheckout(

                @RequestBody CheckoutRequest request,
                Authentication auth

        ) {

            // 1️⃣ Crear pedido completo desde carrito
            pedidos pedido = pedidoService.crearPedidoDesdeCarrito(
                    auth,
                    request.getCarrito()
            );

            // 2️⃣ Crear pago MercadoPago
            String initPoint = mercadoPagoService.crearPago(
                    pedido,
                    pedido.getValorPedido()
            );

            return ResponseEntity.ok(
                    Map.of("initPoint", initPoint)
            );
        }
    }
