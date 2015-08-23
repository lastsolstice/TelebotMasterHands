package discoverylab.telebot.master.hands.test;

import static discoverylab.util.logging.LogUtils.*;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHands;
import TelebotDDSCore.Source.Java.Generated.master.hands.TOPIC_MASTER_TO_SLAVE_HANDS;
import discoverylab.telebot.master.hands.TelebotMasterHands;

public class TelebotMasterHandsTest {
	
	public static String TAG = makeLogTag("TelebotMasterHandsTest");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//INITIATE Slave Component DEVICE
		TelebotMasterHands telebotMasterHands = new TelebotMasterHands(5555);
		telebotMasterHands.initiate();
		
		//INITIATE Transmission PROTOCOL
		telebotMasterHands.initiateTransmissionProtocol(TOPIC_MASTER_TO_SLAVE_HANDS.VALUE, TMasterToHands.class);
		
		//INITIATE DataWriter
		telebotMasterHands.initiateDataWriter();
		

	}

}
