package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.ProductoPagoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IpedidosRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MercadoPagoService implements ImercadoPagoService {

    @Autowired
    private IpedidosRepository pedidosRepository;
    private pedidosService pedidoService;

    @Value("${mercadopago.access-token}")
    private String accessToken;

    @Value("${mercadopago.webhook-url}")
    private String webhookUrl;

    /**
     * Se ejecuta al iniciar la aplicación
     * Configura el Access Token UNA SOLA VEZ
     */
    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    @Override
    public String crearPreferencia(List<ProductoPagoDTO> productos, pedidos pedido) {

        try {
            // 1️⃣ Convertir productos a items de Mercado Pago
            List<PreferenceItemRequest> items = productos.stream()
                    .map(p -> PreferenceItemRequest.builder()
                            .title(p.getTitulo())
                            .quantity(p.getCantidad() != null && p.getCantidad() > 0
                                            ? p.getCantidad()
                                            : 1
                            )
                            .unitPrice(BigDecimal.valueOf(p.getPrecio().doubleValue()))
                            .currencyId("COP")
                            .build()
                    )
                    .toList();

            // 2️⃣ Configurar URLs de retorno
            PreferenceBackUrlsRequest backUrls =
                    PreferenceBackUrlsRequest.builder()
                            .success("http://localhost:5173/pago-exitoso")
                            .failure("http://localhost:5173/pago-fallido")
                            .pending("http://localhost:5173/pago-pendiente")
                            .build();

            // 3️⃣ Crear preference
            PreferenceRequest preferenceRequest =
                    PreferenceRequest.builder()
                            .items(items)
                            .externalReference(pedido.getIdPedido().toString())
                            .notificationUrl(webhookUrl)
                            .backUrls(backUrls)
                            .autoReturn("approved")
                            .build();

            System.out.println("===== DATOS MP =====");
            System.out.println("Pedido ID: " + pedido.getIdPedido());
            items.forEach(i -> {
                System.out.println("Titulo: " + i.getTitle());
                System.out.println("Cantidad: " + i.getQuantity());
                System.out.println("Precio: " + i.getUnitPrice());
            });
            System.out.println("====================");

            Preference preference =
                    new PreferenceClient().create(preferenceRequest);

            // 4️⃣ Guardar preferenceId en el pedido
            pedido.setMpPreferenceId(preference.getId());
            pedidosRepository.save(pedido);

            // 5️⃣ Retornar URL de pago al frontend
            return preference.getInitPoint();

        } catch (MPApiException e) {
            System.out.println("==== ERROR MERCADO PAGO ====");
            System.out.println(e.getApiResponse().getStatusCode());
            System.out.println(e.getApiResponse().getContent());
            System.out.println("===========================");
            throw new RuntimeException("Error API Mercado Pago", e);

        } catch (MPException e) {
            throw new RuntimeException("Error SDK Mercado Pago", e);
        }
    }

    public String crearPago(pedidos pedido, BigDecimal total) {

        try {
            MercadoPagoConfig.setAccessToken(accessToken);

            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title("Pedido #" + pedido.getIdPedido())
                    .quantity(1)
                    .unitPrice(total)
                    .currencyId("COP")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(List.of(item))
                    .externalReference(pedido.getIdPedido().toString())
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            // 🔹 Guardar preferenceId en el pedido
            this.pedidoService.actualizarPreference(
                    pedido.getIdPedido(),
                    preference.getId()
            );

            return preference.getInitPoint();

        } catch (MPException | MPApiException e) {
            throw new RuntimeException("Error creando pago en MercadoPago", e);
        }
    }
}