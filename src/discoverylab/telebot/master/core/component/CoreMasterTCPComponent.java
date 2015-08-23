package discoverylab.telebot.master.core.component;

import static discoverylab.util.logging.LogUtils.LOGE;

import java.lang.reflect.InvocationTargetException;

import static discoverylab.util.logging.LogUtils.*;
import TelebotDDSCore.DDSCommunicator;

import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriterImpl;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

import discoverylab.telebot.master.core.socket.CoreServerSocket;
import discoverylab.telebot.master.core.socket.CoreServerSocket.SocketEventListener;

/**
 * CoreMasterTCPComponent
 * Core class that handles Device Client to Server socket connection
 * This is used when dealing with a device such as the YEI IMU, whose library is implemented in Python,
 * but we can use a socket connection to connect locally and then use this core class to extend and implement
 * the DDS writing logic
 * @author Irvin Steve Cardenas
 *
 */
public abstract class CoreMasterTCPComponent implements SocketEventListener {
	
	public static String TAG = makeLogTag("CoreMasterTCPComponent");

	private CoreServerSocket serverSocket;
	
//  DDS 
	private DDSCommunicator communicator;
	private static Topic topic 						= null;
	private static DataWriterImpl writer			= null;
	InstanceHandle_t instance_handle 				= InstanceHandle_t.HANDLE_NIL;
	
	public CoreMasterTCPComponent(int portNumber){
		LOGI(TAG, "Creating Core Server Socket");
		serverSocket = new CoreServerSocket(portNumber, this);
	}
	
	public void initiate(){
		serverSocket.startServer();
	}
	
	
	/**
	 * Initiate DDS Protocol
	 * @return
	 */
	public boolean initiateTransmissionProtocol(String topicName, Class type){
		
		communicator = new DDSCommunicator();
		try {
			communicator.createParticipant();
			
		} catch (Exception e) {
			LOGE(TAG, "Error Creating Participant");
			e.printStackTrace();
		}
		try {
			communicator.createPublisher();
		} catch (Exception e) {
			LOGE(TAG, "Error Creating Publisher");
			e.printStackTrace();
		}
		
		try {
			this.topic = communicator.createTopic(topicName, type);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			LOGE(TAG, "Error Creating Topic");
			e.printStackTrace();
		}
		
		try {
			communicator.createPublisher();
		} catch (Exception e) {
			LOGE(TAG, "Error Creating Publisher");
			e.printStackTrace();
		}
		
		try {
			writer = (DataWriterImpl) communicator.getPublisher().create_datawriter(
					topic, Publisher.DATAWRITER_QOS_DEFAULT, null /* listener */,
					StatusKind.STATUS_MASK_NONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TODO Get assertion of Participant
		return false;
	}
	
	public DataWriterImpl getDataWriter() {
		return writer;
	}
	
	public boolean shutdown(){
		return serverSocket.stopServer();
	}
}
