package encoderdecoder;

import pojo.SimpleBean;
import sun.misc.IOUtils;

import javax.websocket.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BinaryStreamEncoderDecoder implements Encoder.BinaryStream<SimpleBean>, Decoder.BinaryStream<SimpleBean> {
   @Override
   public void init(EndpointConfig ec) { }
   @Override
   public void destroy() { }

    @Override
    public void encode(SimpleBean bean, OutputStream outputStream) throws EncodeException, IOException {
        byte[] bytes = bean.getContent().getBytes();
        outputStream.write(bytes);
        outputStream.close();
    }

    @Override
    public SimpleBean decode(InputStream stream) throws DecodeException, IOException {
        byte[] data = IOUtils.readFully(stream, -1, true);
        String content = new String(data);
        SimpleBean bean = new SimpleBean(content);
        return bean;
    }
}