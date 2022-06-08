package org.jeecg.modules.message.websocket;

import com.alibaba.fastjson.JSON;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.WebsocketConst;
import org.jeecg.modules.message.entity.SduMessage;
import org.jeecg.modules.message.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/socketTest")
public class TestSocketController {

    @Autowired
    private WebSocket webSocket;
	//推广方案
    @PostMapping("/sendAll")
    public Result<String> sendAll(@RequestBody JSONObject jsonObject) {
    	Result<String> result = new Result<String>();
    	String message = jsonObject.getString("message");
		String senderId = jsonObject.getString("senderId");
    	JSONObject obj = new JSONObject();
    	obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_TOPIC);
		obj.put(WebsocketConst.MSG_ID, "M0000");//M000是推广方案符号**************
		obj.put(WebsocketConst.MSG_TXT, message);
    	//webSocket.sendMessage(obj.toJSONString());
		webSocket.sduSendMessage(senderId,obj.toJSONString());
        result.setResult("群发！");
        return result;
    }

    @PostMapping("/sendUser")
    public Result<String> sendUser(@RequestBody JSONObject jsonObject) {
    	Result<String> result = new Result<String>();
    	String userId = jsonObject.getString("userId");
		//System.out.println(userId);
		String message = jsonObject.getString("message");
		//String userName = jsonObject.getString("userName");
		String senderId = jsonObject.getString("senderId");
		String senderName = jsonObject.getString("senderName");
		String date = jsonObject.getString("date");
    	JSONObject obj = new JSONObject();
    	//obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_USER);
    	obj.put(WebsocketConst.MSG_USER_ID, userId);
		obj.put(WebsocketConst.MSG_ID, "M0001");//M001是私信符号**************
		obj.put(WebsocketConst.MSG_TXT, message);
		//obj.put("userName", userName);
		obj.put("senderId", senderId);
		obj.put("senderName", senderName);
		obj.put("date", date);
        //webSocket.sendMessage(userId, obj.toJSONString());
		webSocket.sendToUserNotOnline(userId, obj.toJSONString());
        result.setResult("单发");
        return result;
    }
	//发送消息
	@PostMapping("/messageSendUser")
	public Result<String> messageSendUser(@RequestBody JSONObject jsonObject) {
		Result<String> result = new Result<String>();
		String userId = jsonObject.getString("userId");
		String message = jsonObject.getString("message");
		String userName = jsonObject.getString("userName");
		String senderId = jsonObject.getString("senderId");
		String senderName = jsonObject.getString("senderName");
		String date = jsonObject.getString("date");
		JSONObject obj = new JSONObject();
		//obj.put(WebsocketConst.MSG_CMD, WebsocketConst.CMD_USER);
		obj.put(WebsocketConst.MSG_USER_ID, userId);
		obj.put(WebsocketConst.MSG_ID, "M0002");//M002是发送消息的符号******
		obj.put(WebsocketConst.MSG_TXT, message);
		obj.put("userName", userName);
		obj.put("senderId", senderId);
		obj.put("senderName", senderName);
		obj.put("date", date);
		//webSocket.sendMessage(userId, obj.toJSONString());
		webSocket.sendToUserNotOnline(userId, obj.toJSONString());
		result.setResult("单发");
		return result;
	}
	@Resource(name = "redisServiceImpl")
	private RedisService redisService;

//	@PostMapping("/pullUnreadMessage")
//	@ResponseBody
//	public Map<String, Object> pullUnreadMessage(String userId){
//		Map<String, Object> result = new HashMap<>();
//		try {
//			//存储消息的Redis列表名
//			String listKey = "stomp-websocket:unread_msg:" + userId;
//			//从Redis中拉取所有未读消息
//			List<Object> messageList = redisService.rangeList(listKey, 0, -1);
//
//			result.put("code", "200");
//			if(messageList !=null && messageList.size() > 0){
//				//删除Redis中的这个未读消息列表
//				redisService.delete(listKey);
//				//将数据添加到返回集，供前台页面展示
//				result.put("result", messageList);
//			}
//		}catch (Exception e){
//			result.put("code", "500");
//			result.put("msg", e.getMessage());
//		}
//
//		return result;
//	}

	@PostMapping("/pullUnreadMessage")
	@ResponseBody
	public Result<List<Object>> pullUnreadMessage(@RequestParam String userId){
		Result<List<Object>> result = new Result<>();
		try {
			//存储消息的Redis列表名
			String listKey = "stomp-websocket:unread_msg:" + userId;
			//从Redis中拉取所有未读消息
			List<Object> messageList = redisService.rangeList(listKey, 0, -1);

			//result.put("code", "200");
			if(messageList !=null && messageList.size() > 0){
				//删除Redis中的这个未读消息列表
				redisService.delete(listKey);
				//将数据添加到返回集，供前台页面展示

				result.setResult(JSON.parseArray(String.valueOf(messageList)));
			}
		}catch (Exception e){
			result.setSuccess(false);
		}
		return result;
	}

}
