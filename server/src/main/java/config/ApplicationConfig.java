package config;

import encoderdecoder.BinaryStreamEncoderDecoder;
import encoderdecoder.TextEncoderDecoder;
import endpoint.StampingServerEndpoint;

import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApplicationConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return new HashSet<ServerEndpointConfig>() {
            {
                add(ServerEndpointConfig.Builder
                        .create(StampingServerEndpoint.class, "/StampingServerEndpoint")
                        .encoders(Arrays.<Class<? extends Encoder>>asList(TextEncoderDecoder.class, BinaryStreamEncoderDecoder.class))
                        .decoders(Arrays.<Class<? extends Decoder>>asList(TextEncoderDecoder.class, BinaryStreamEncoderDecoder.class))
//                        .encoders(Arrays.<Class<? extends Encoder>>asList(BinaryStreamEncoderDecoder.class))
//                        .decoders(Arrays.<Class<? extends Decoder>>asList(BinaryStreamEncoderDecoder.class))
                        .build());
            }
        };
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return new HashSet<Class<?>>() {
            {
            }
        };
    }
}