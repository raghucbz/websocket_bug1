package encoderdecoder;

import pojo.SimpleBean;

import javax.websocket.*;

public class TextEncoderDecoder implements Encoder.Text<SimpleBean>, Decoder.Text<SimpleBean> {
   @Override
   public void init(EndpointConfig ec) { }
   @Override
   public void destroy() { }


    @Override
    public SimpleBean decode(String content) throws DecodeException {
        SimpleBean bean = new SimpleBean(content);
        return bean;
    }

    @Override
    public boolean willDecode(String content) {
        return content != null;
    }

    @Override
    public String encode(SimpleBean simpleBean) throws EncodeException {
        String content = simpleBean.getContent();
        return content;
    }
}