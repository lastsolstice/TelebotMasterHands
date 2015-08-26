package discoverylab.telebot.master.hands;

import java.util.Random;

import javax.swing.JOptionPane;

import static discoverylab.util.logging.LogUtils.*;
import discoverylab.telebot.master.hands.configurations.FlexSensorConfig;
import discoverylab.telebot.master.hands.configurations.MasterHandsConfig;
import discoverylab.telebot.master.hands.mapper.ServoDataMapper;
import discoverylab.telebot.master.hands.packets.FlexSensorPacket;

import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.publication.DataWriterImpl;

import TelebotDDSCore.DDSCommunicator;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHands;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsDataWriter;
import discoverylab.telebot.master.hands.parser.FlexSensorDataParser;
import discoverylab.telebot.master.core.component.CoreMasterTCPComponent;
import discoverylab.telebot.master.core.socket.CoreServerSocket; 

public class TelebotMasterHands extends CoreMasterTCPComponent implements CoreServerSocket.SocketEventListener{
	
	public static String TAG = makeLogTag(TelebotMasterHands.class);
	private FlexSensorDataParser parser;
	private ServoDataMapper mapper;
	private TMasterToHandsDataWriter writer;
	TMasterToHands instance = new TMasterToHands();
	InstanceHandle_t instance_handle = InstanceHandle_t.HANDLE_NIL;
	
	private Random ran;
	
	private int defaultSpeed = 100;
	
	public TelebotMasterHands(int portNumber) 
	{
		super(portNumber);
		parser = new FlexSensorDataParser();
		mapper = new ServoDataMapper();
		ran = new Random();
	}
	
	/**
	 * Cast the Writer to our Arms DataWriter
	 * This allows us to publish the appropriate Topic data
	 */
	public void initiateDataWriter()
	{
		writer = (TMasterToHandsDataWriter) getDataWriter();
	}
	
	public void test()
	{
		int val = Integer.parseInt(JOptionPane.showInputDialog("Enter some shit."));
		
	
		// Left Hand
		int pos_lThumbFlexion = val; 
		int pos_lThumbOpposition = val;
		int pos_lIndex = val;
		int pos_lMiddle = val;
		int pos_lRing = val;
		int pos_lPinky = val;		
		
		// Right Hand
		int pos_rPinky = val;
		int pos_rRing = val; 
		int pos_rMiddle = val; 
		int pos_rIndex = val;
		int pos_rThumbFlexion = val; 
		int pos_rThumbOpposition = val;
	
	
//		pos_lPinky = mapper.map(
//				lPinky, 
//				FlexSensorConfig.LPINKY_MAX, 
//				FlexSensorConfig.LPINKY_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lPinky = pos_lPinky;
	
		
//		pos_lRing = mapper.map(
//				lRing, 
//				FlexSensorConfig.LRING_MAX, 
//				FlexSensorConfig.LRING_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lRing = pos_lRing;
	
		
//		pos_lMiddle = mapper.map(
//				lMiddle, 
//				FlexSensorConfig.LMIDDLE_MAX, 
//				FlexSensorConfig.LMIDDLE_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lMiddle = pos_lMiddle;
		
	
//		pos_lIndex = mapper.map(
//				lIndex, 
//				FlexSensorConfig.LINDEX_MAX, 
//				FlexSensorConfig.LINDEX_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lIndex = pos_lIndex;
	
//		
//		pos_lThumbFlexion = mapper.map(
//				lThumbFlexion, 
//				FlexSensorConfig.LTHUMBFLEXION_MAX, 
//				FlexSensorConfig.LTHUMBFLEXION_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lThumbFlexion = pos_lThumbFlexion;
	
		
//		pos_lThumbOpposition = mapper.map(
//				lThumbOpposition, 
//				FlexSensorConfig.LTHUMBOPPOSITION_MAX, 
//				FlexSensorConfig.LTHUMBOPPOSITION_MIN, 
//				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
//				MasterHandsConfig.LEFTHAND_SERVO_MIN
//				);
		
		instance.lThumbOpposition = pos_lThumbOpposition;
	
		
//		pos_rThumbFlexion = mapper.map(
//				rThumbFlexion, 
//				FlexSensorConfig.RTHUMBFLEXION_MAX, 
//				FlexSensorConfig.RTHUMBFLEXION_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rThumbFlexion = pos_rThumbFlexion;
	
		
//		pos_rThumbOpposition = mapper.map(
//				rThumbOpposition, 
//				FlexSensorConfig.RTHUMBOPPOSITION_MAX, 
//				FlexSensorConfig.RTHUMBOPPOSITION_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rThumbOpposition = pos_rThumbOpposition;
	
		
//		pos_rIndex = mapper.map(
//				rIndex, 
//				FlexSensorConfig.RINDEX_MAX, 
//				FlexSensorConfig.RINDEX_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rIndex = pos_rIndex;
	
		
//		pos_rMiddle = mapper.map(
//				rMiddle, 
//				FlexSensorConfig.RMIDDLE_MAX, 
//				FlexSensorConfig.RMIDDLE_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rMiddle = pos_rMiddle;
	
//		
//		pos_rRing = mapper.map(
//				rRing, 
//				FlexSensorConfig.RRING_MAX, 
//				FlexSensorConfig.RRING_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rRing = pos_rRing;
	
		
//		pos_rPinky = mapper.map(
//				rPinky, 
//				FlexSensorConfig.RPINKY_MAX, 
//				FlexSensorConfig.RPINKY_MIN, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
//				MasterHandsConfig.RIGHTHAND_SERVO_MIN
//				);
		
		instance.rPinky = pos_rPinky;
	
		LOGI(TAG, "DATA: " 
				+ instance.lPinky + " "
				+ instance.lRing + " "
				+ instance.lMiddle + " "
				+ instance.lIndex + " "
				+ instance.lThumbFlexion + " "
				+ instance.lThumbOpposition + " "
				+ instance.rPinky + " "
				+ instance.rRing + " "
				+ instance.rMiddle + " "
				+ instance.rIndex + " "
				+ instance.rThumbFlexion + " "
				+ instance.rThumbOpposition);
		
		writer.write(instance, instance_handle);	
	}
	
	
	@Override
	public void callback(String data)
	{
		LOGI(TAG, "DATA: " + data);
		
		FlexSensorPacket flexSensorDataInstance = (FlexSensorPacket) parser.parse(data);
		
		//Left Hand
		int lPinky = flexSensorDataInstance.lPinky;
		int lRing = flexSensorDataInstance.lRing; 
		int lMiddle = flexSensorDataInstance.lMiddle; 
		int lIndex = flexSensorDataInstance.lIndex;
		int lThumbFlexion = flexSensorDataInstance.lThumbFlexion; 
		int lThumbOpposition = flexSensorDataInstance.lThumbOpposition;
		
		// Right Hand
		int rThumbFlexion = flexSensorDataInstance.rThumbFlexion; 
		int rThumbOpposition = flexSensorDataInstance.rThumbOpposition;
		int rIndex = flexSensorDataInstance.rIndex;
		int rMiddle = flexSensorDataInstance.rMiddle;
		int rRing = flexSensorDataInstance.rRing;
		int rPinky = flexSensorDataInstance.rPinky;
		
		int pos_lPinky, pos_lRing, pos_lMiddle, pos_lIndex, pos_lThumbFlexion, pos_lThumbOpposition, 
		pos_rThumbFlexion, pos_rThumbOpposition, pos_rIndex, pos_rMiddle, pos_rRing, pos_rPinky;
	
	
		pos_lPinky = mapper.map(
				lPinky, 
				FlexSensorConfig.LPINKY_MAX, 
				FlexSensorConfig.LPINKY_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lPinky = pos_lPinky;
	
		
		pos_lRing = mapper.map(
				lRing, 
				FlexSensorConfig.LRING_MAX, 
				FlexSensorConfig.LRING_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lRing = pos_lRing;
	
		
		pos_lMiddle = mapper.map(
				lMiddle, 
				FlexSensorConfig.LMIDDLE_MAX, 
				FlexSensorConfig.LMIDDLE_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lMiddle = pos_lMiddle;
		
	
		pos_lIndex = mapper.map(
				lIndex, 
				FlexSensorConfig.LINDEX_MAX, 
				FlexSensorConfig.LINDEX_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lIndex = pos_lIndex;
	
		
		pos_lThumbFlexion = mapper.map(
				lThumbFlexion, 
				FlexSensorConfig.LTHUMBFLEXION_MAX, 
				FlexSensorConfig.LTHUMBFLEXION_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lThumbFlexion = pos_lThumbFlexion;
	
		
		pos_lThumbOpposition = mapper.map(
				lThumbOpposition, 
				FlexSensorConfig.LTHUMBOPPOSITION_MAX, 
				FlexSensorConfig.LTHUMBOPPOSITION_MIN, 
				MasterHandsConfig.LEFTHAND_SERVO_MAX, 
				MasterHandsConfig.LEFTHAND_SERVO_MIN
				);
		
		instance.lThumbOpposition = pos_lThumbOpposition;
	
		
		pos_rThumbFlexion = mapper.map(
				rThumbFlexion, 
				FlexSensorConfig.RTHUMBFLEXION_MAX, 
				FlexSensorConfig.RTHUMBFLEXION_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rThumbFlexion = pos_rThumbFlexion;
	
		
		pos_rThumbOpposition = mapper.map(
				rThumbOpposition, 
				FlexSensorConfig.RTHUMBOPPOSITION_MAX, 
				FlexSensorConfig.RTHUMBOPPOSITION_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rThumbOpposition = pos_rThumbOpposition;
	
		
		pos_rIndex = mapper.map(
				rIndex, 
				FlexSensorConfig.RINDEX_MAX, 
				FlexSensorConfig.RINDEX_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rIndex = pos_rIndex;
	
		
		pos_rMiddle = mapper.map(
				rMiddle, 
				FlexSensorConfig.RMIDDLE_MAX, 
				FlexSensorConfig.RMIDDLE_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rMiddle = pos_rMiddle;
	
		
		pos_rRing = mapper.map(
				rRing, 
				FlexSensorConfig.RRING_MAX, 
				FlexSensorConfig.RRING_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rRing = pos_rRing;
	
		
		pos_rPinky = mapper.map(
				rPinky, 
				FlexSensorConfig.RPINKY_MAX, 
				FlexSensorConfig.RPINKY_MIN, 
				MasterHandsConfig.RIGHTHAND_SERVO_MAX, 
				MasterHandsConfig.RIGHTHAND_SERVO_MIN
				);
		
		instance.rPinky = pos_rPinky;
	
		LOGI(TAG, "DATA: " 
				+ instance.lPinky + " "
				+ instance.lRing + " "
				+ instance.lMiddle + " "
				+ instance.lIndex + " "
				+ instance.lThumbFlexion + " "
				+ instance.lThumbOpposition + " "
				+ instance.rPinky + " "
				+ instance.rRing + " "
				+ instance.rMiddle + " "
				+ instance.rIndex + " "
				+ instance.rThumbFlexion + " "
				+ instance.rThumbOpposition);
		
		writer.write(instance, instance_handle);	
	}

	/**
	 * CallbackInterface
	 * @author Irvin Steve Cardenas
	 * 
	 * Callback interface to retrieve parsed Data Object
	 * Purpose: GUI or other components can implement this to receive a  YEIDataModel object object
	 *
	 */
	public interface CallbackInterface{
		public void callback(FlexSensorPacket data);
	}
}
