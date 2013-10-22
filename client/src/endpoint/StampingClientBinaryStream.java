package endpoint; /**
 * Created with IntelliJ IDEA.
 * User: raghuram
 * Date: 9/26/13
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */

import encoderdecoder.BinaryStreamEncoderDecoder;
import pojo.SimpleBean;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class StampingClientBinaryStream extends Endpoint {

    protected Session session;

    public static void main(String[] args) throws URISyntaxException, DeploymentException, InterruptedException, IOException, EncodeException {
        StampingClientBinaryStream client = new StampingClientBinaryStream();
        SimpleBean bean = new SimpleBean("Hello Binary Stream!");
        client.stamp(bean);
    }

    public void stamp(SimpleBean bean) throws URISyntaxException, DeploymentException, InterruptedException, IOException, EncodeException {
        this.connect();
        try {
            this.session.getBasicRemote().sendObject(bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void connect() throws URISyntaxException, IOException, DeploymentException {
        if(this.session == null) {
            WebSocketContainer wsc = ContainerProvider.getWebSocketContainer();
            ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder
                    .create()
                    .encoders(Arrays.<Class<? extends Encoder>>asList(BinaryStreamEncoderDecoder.class))
                    .decoders(Arrays.<Class<? extends Decoder>>asList(BinaryStreamEncoderDecoder.class))
                    .build();
            this.session = wsc.connectToServer(this, clientEndpointConfig, new URI("ws://localhost:8080/websocket_bug1/StampingServerEndpoint"));
        }
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {

    }
}
