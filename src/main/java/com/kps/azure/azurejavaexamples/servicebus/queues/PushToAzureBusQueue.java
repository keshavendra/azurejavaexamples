package com.kps.azure.azurejavaexamples.servicebus.queues;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class PushToAzureBusQueue {
	private static final String CONNECTION_STRING = "Endpoint=sb://salesteamappkps23aug2020.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=RtVdIzSv+hejCYKpT8BE331roUAPnjIggpfmtGQ/48I=";
	private static final String QUEUE_NAME = "salesmessages";

	public static void main(String[] args) throws InterruptedException, ServiceBusException {
		// Create a queue client
		QueueClient queueClient = new QueueClient(new ConnectionStringBuilder(CONNECTION_STRING, QUEUE_NAME),
				ReceiveMode.PEEKLOCK);
		for (int i = 0; i < 3; i++) {
			Message message = new Message("Test".getBytes());
			message.setLabel("Test");
			message.setMessageId(Integer.toString(i));
			queueClient.sendAsync(message).thenRunAsync(() -> {
				System.out.printf("\n\tMessage acknowledged: Id = %s", message.getMessageId());
			});
		}

	}
}
