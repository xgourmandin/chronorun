package org.gx.chronorun.websocket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testConnectionToWebSocket() throws InterruptedException, ExecutionException, TimeoutException {
        final String URL = "ws://localhost:" + port + "/api/ws-endpoint";

        final List<Transport> transportList = Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
        final WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(transportList));
        stompClient.setMessageConverter(new StringMessageConverter());

        StompSession session = stompClient.connect(URL, new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {

            }

            @Override
            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void handleTransportError(StompSession stompSession, Throwable throwable) {

            }

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {

            }
        }).get(5, TimeUnit.SECONDS);
    }
}
