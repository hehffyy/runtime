package com.butone.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.justep.util.JustepConfig;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 将消息发送到MQ的 PushServer中，PushServer订阅此消息，进行2次派发。
 * @author tangkejie
 *
 */
public class MqttMessageDispatcher {

	private static Logger logger = Logger.getLogger(MqttMessageDispatcher.class);
	public static final String PUSH_TOPIC = "/PushServer";
	public static final String TOPICS = "topics";
	public static final String TERMINALS = "terminals";
	public static final String TARGETS = "targets";
	public static final String CONTENT = "content";
	public static final Set<String> BUSINESS_TOPIC = Collections.synchronizedSet(new HashSet<String>());

	public static interface DeliveryComplete {
		void onComplete(IMqttDeliveryToken token);
	}

	static {
		BUSINESS_TOPIC.add("/business");
	}

	public boolean sendMessage(Object message, Collection<String> personIdOrFID, DeliveryComplete callback) {
		return sendMessage(message, personIdOrFID, null, callback);
	}

	public boolean sendMessage(Object message, Collection<String> personIdOrFID, Collection<String> terminals, DeliveryComplete callback) {
		return sendMessage(message, personIdOrFID, terminals, BUSINESS_TOPIC, callback);
	}

	public boolean sendMessage(Object message, Collection<String> personIdOrFID, Collection<String> terminals, Collection<String> topics, DeliveryComplete callback) {
		try {
			if (message == null)
				message = "";
			if (personIdOrFID == null)
				personIdOrFID = new HashSet<String>();
			if (terminals == null)
				terminals = new HashSet<String>();
			if (topics == null)
				topics = new HashSet<String>();

			JSONObject body = new JSONObject();

			JSONArray targetsJson = new JSONArray();
			body.put("targets", targetsJson);
			targetsJson.addAll(personIdOrFID);

			JSONArray topicsJson = new JSONArray();
			body.put("topics", topicsJson);
			topicsJson.addAll(topics);

			JSONArray terminalsJson = new JSONArray();
			body.put("terminals", terminalsJson);
			terminalsJson.addAll(terminals);

			body.put("content", message.toString());
			return sendMessage(PUSH_TOPIC, body.toJSONString().getBytes("UTF-8"), callback);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public static String getClientId() {
		return new Object().hashCode() + ".md";
	}

	public static boolean sendMessage(String topic, byte[] message, final DeliveryComplete callback) {
		MqttClient client = null;
		try {
			String server = JustepConfig.getMqttServer();
			String clientId = getClientId();
			client = new MqttClient(server, clientId);
			client.setCallback(new MqttCallback() {
				public void connectionLost(Throwable cause) {
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					if (callback != null) {
						callback.onComplete(token);
					}
				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
				}
			});
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(JustepConfig.getMqttServerUsername());
			options.setPassword(JustepConfig.getMqttServerPassword().toCharArray());
			client.connect(options);
			MqttMessage msg = new MqttMessage(message);
			// 0:至多一次,发完即丢弃;1:至少一次,需要确认回复;2:只有一次,需要确认回复
			msg.setQos(1);
			// 设置是否在服务器中保存消息体
			msg.setRetained(false);
			client.publish(topic, msg);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (client != null)
				try {
					client.disconnect();
				} catch (MqttException localMqttException2) {
				}
		}
	}
}
