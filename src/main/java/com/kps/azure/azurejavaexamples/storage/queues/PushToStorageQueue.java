package com.kps.azure.azurejavaexamples.storage.queues;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import com.microsoft.azure.storage.queue.CloudQueueMessage;

public class PushToStorageQueue {
	private static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;EndpointSuffix=core.windows.net;AccountName=kpsstorage260820;AccountKey=e8tgjF0Lo4gSNnXj+rjcg2pG1qy2rH9Q/XbEzqmkWVthaWMjG+E5P+DqylWMLDCtFiP3dkOLCFxEoDTrOeQbwA==";
	private static final String QUEUE_NAME = "newsqueue";

	public static void main(String[] args) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudStorageAccount storageAccount = CloudStorageAccount.parse(CONNECTION_STRING);
		CloudQueueClient queueClient = storageAccount.createCloudQueueClient();
		CloudQueue queue1 = queueClient.getQueueReference(QUEUE_NAME);
		boolean queuCreated = queue1.createIfNotExists();
		if (queuCreated) {
			System.out.println("Queue got created");
		}
		queue1.addMessage(new CloudQueueMessage("Hello!"));
	}
}
