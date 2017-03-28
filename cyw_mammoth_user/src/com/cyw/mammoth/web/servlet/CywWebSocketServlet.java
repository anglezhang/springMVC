/**
 * 
 */
package com.cyw.mammoth.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wexl@163.com
 *
 */
//@WebServlet(urlPatterns = "/echo.ws")
public class CywWebSocketServlet// extends WebSocketServlet
{
	
	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

/*	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest arg1) {
		logger.info("request ws servlet");
		
		// 方法仍然是返回一个StreamInbound实例，这里采用实现他的子类MessageInbound
        // 只用实现下面四个事件处理函数(其实onClose和onOpen有缺省实现)
        return new MessageInbound() {
            // WebSocket关闭事件，参数status应该来自org.apache.catalina.websocket.Constants中定义的几个常量，可以参考文档或者核对一下Tomcat的源码
            @Override
            protected void onClose(int status) {
                // Log
                logger.info("Web Socket Closed: " + status);
            }
 
            // WebSocket握手完成，创建完毕，WsOutbound用于向客户端发送数据
            @Override
            protected void onOpen(WsOutbound outbound) {
                // Log
            	AppBaseCfg.wsOutBound=outbound;
                logger.info("Web Socket Open!");
            }
 
            // 有二进制消息数据到达
            @Override
            protected void onBinaryMessage(ByteBuffer buffer)
                    throws IOException {
                // Log
                logger.info("Binary Message Receive: " + buffer.remaining());
                // Nothing
            }
 
            // 有文本消息数据到达
            @Override
            protected void onTextMessage(CharBuffer buffer) throws IOException {
                // Log
                logger.info("Text Message Receive: " + buffer.remaining());
                // getWsOutbound可以返回当前的WsOutbound，通过他向客户端回传数据，这里采用的是nio的CharBuffer
                getWsOutbound().writeTextMessage(buffer);
            }
        };
	}*/

}
