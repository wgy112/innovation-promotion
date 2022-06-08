package org.jeecg.modules.message.websocket;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import org.jeecg.common.base.BaseMap;
import org.jeecg.common.constant.WebsocketConst;
import org.jeecg.common.modules.redis.client.JeecgRedisClient;
import org.jeecg.modules.message.enums.ExpireEnum;
import org.jeecg.modules.message.service.RedisService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author scott
 * @Date 2019/11/29 9:41
 * @Description: 此注解相当于设置访问URL
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}") //此注解相当于设置访问URL
public class WebSocket{

    private Session session;

    private String userId;

    private static final String REDIS_TOPIC_NAME = "socketHandler";
    @Resource
    private JeecgRedisClient jeecgRedisClient;

    /**
     * 缓存 webSocket连接到单机服务class中（整体方案支持集群）
     */
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {
            this.session = session;
            this.userId = userId;
            webSockets.add(this);
            sessionPool.put(userId, session);
            log.info("【websocket消息】有新的连接，总数为:" + webSockets.size());
//            TestSocketController testScoketController=new TestSocketController();
//            testScoketController.pullUnreadMessage(userId);
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            sessionPool.remove(this.userId);
            log.info("【websocket消息】连接断开，总数为:" + webSockets.size());
        } catch (Exception e) {
        }
    }


    /**
     * 服务端推送消息
     *
     * @param userId
     * @param message
     */
    public void pushMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                //log.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务器端推送消息
     */
    public void pushMessage(String message) {
        try {
            webSockets.forEach(ws -> ws.session.getAsyncRemote().sendText(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnMessage
    public void onMessage(String message) {
        //todo 现在有个定时任务刷，应该去掉
        //log.debug("【websocket消息】收到客户端消息:" + message);
        //log.info("【websocket消息】收到客户端消息:" + message);
        JSONObject obj = new JSONObject();
        //业务类型
        obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_CHECK);
        //消息内容
        obj.put(WebsocketConst.MSG_TXT, "心跳响应");
        for (WebSocket webSocket : webSockets) {
            webSocket.pushMessage(message);
        }
    }

//    @OnMessage
//    public void onMessage(String message) {
//        JSONObject obj = (JSONObject) JSON.parse(message);
//        //System.out.println(obj.getString("nickname"));
//        //JSON.parse(message)
//        log.debug("【websocket消息】收到客户端消息:" + message);
//        log.info("【websocket消息】收到客户端消息:" + message);
//        //业务类型
//        obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_CHECK);
//        //消息内容
//        obj.put(WebsocketConst.MSG_TXT, "心跳响应");
//        Session session = sessionPool.get(obj.getString("toid"));
//        //sendMessage(obj.getString("toid"),obj.toJSONString());
//        if (session != null && session.isOpen()) {
//            try {
//                log.info("【websocket消息】 单点消息:" + obj.getString("content"));
//                session.getAsyncRemote().sendText(obj.getString("content"));
//                //sendMessage(obj.getString("toid"),obj.toJSONString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//        for (WebSocket webSocket : webSockets) {
//            //webSocket.pushMessage(message);
//            //webSocket.pushMessage(obj.getString("content"));
//            webSocket.pushMessage(obj.getString("toid"),obj.getString("content"));
//            System.out.println(webSocket);
//        }




    /**
     * 后台发送消息到redis
     *
     * @param message
     */
    public void sendMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        BaseMap baseMap = new BaseMap();
        baseMap.put("userId", "");
        baseMap.put("message", message);
        jeecgRedisClient.sendMessage(REDIS_TOPIC_NAME, baseMap);
    }
    public void sduSendMessage(String senderId,String message) {
        //log.info("【websocket消息】除自己的推广消息:" + message);
        System.out.println(senderId);
        //Session noSession = sessionPool.get(senderId);
        //webSockets.forEach(ws -> {if (ws.session != noSession){ws.session.getAsyncRemote().sendText(message)};);
        //webSockets.forEach(ws -> ws.session.getAsyncRemote().sendText(message));
        for (WebSocket webSocket : webSockets) {
            if(!webSocket.session.equals(sessionPool.get(senderId))){
                try {
                    webSocket.session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        webSockets.forEach(ws -> ws.session.getAsyncRemote().sendText(message));
//        Iterator<WebSocket> iter =webSockets.iterator();
//        while(iter.hasNext()){
//            if(iter.next().session.equals(sessionPool.get(senderId))){
//                iter.next().session.getAsyncRemote().sendText(message);
//            }
//        }
    }
    /**
     * 此为单点消息
     *
     * @param userId
     * @param message
     */
    public void sendMessage(String userId, String message) {
        BaseMap baseMap = new BaseMap();
        baseMap.put("userId", userId);
        baseMap.put("message", message);
        jeecgRedisClient.sendMessage(REDIS_TOPIC_NAME, baseMap);
    }
    @Resource(name = "redisServiceImpl")
    private RedisService redisService;
    public void sendToUserNotOnline(String userId,String message){
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
//                log.info("【websocket消息】 单点消息:" + message);
//                session.getAsyncRemote().sendText(message);

                BaseMap baseMap = new BaseMap();
                baseMap.put("userId", userId);
                baseMap.put("message", message);
                jeecgRedisClient.sendMessage(REDIS_TOPIC_NAME, baseMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            String listKey = "stomp-websocket:unread_msg:"+userId;
            //log.info(MessageFormat.format("消息接收者{0}还未建立WebSocket连接，发送的消息【{1}】将被存储到Redis的【{2}】列表中", userId, message, listKey));
            redisService.addToListRight(listKey, ExpireEnum.UNREAD_MSG,message);
        }

    }
    /**
     * 此为单点消息(多人)
     *
     * @param userIds
     * @param message
     */
    public void sendMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            sendMessage(userId, message);
        }
    }

}
