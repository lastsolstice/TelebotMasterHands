package discoverylab.telebot.slave.hands.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import TelebotDDSCore.DDSCommunicator;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHands;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsDataReader;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsDataWriter;
import TelebotDDSCore.Source.Java.Generated.master.hands.TOPIC_MASTER_TO_SLAVE_HANDS;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.topic.Topic;

import discoverylab.telebot.slave.hands.TMasterToHandsListener;
import jssc.SerialPort;

public class Main {


	private JFrame frmSlaveHands;
	private JComboBox<String> comboBoxPort;
	private JComboBox<String> comboBoxBaud;
	private JButton btnConnect;
	
	private JPanel panel;
	private JLabel lblFullHand;
	private JLabel lblSlider;
	protected JTextField textFieldFullHand;
	
	protected JSlider slider;
	static final int SLIDER_MIN = 0;
	static final int SLIDER_MAX = 1;
	static final int SLIDER_INIT = 0;    //initial slider value
	
	// Serial Port
	private SerialPort serialPort;
	protected Boolean serialConnected = false;
	private Boolean serialPortsAvailable = false;
	
	// DDS
	private DDSCommunicator communicator;
	private static DomainParticipant participant = null;
	private static Topic topic = null;
	private static TMasterToHandsListener listener = null;
	private static Subscriber subscriber = null;
	private static TMasterToHandsDataReader reader = null;
	TMasterToHands instance = new TMasterToHands();
	InstanceHandle_t instance_handle = InstanceHandle_t.HANDLE_NIL;
	
	public static void main(String [] args) {
		try {
			Main window = new Main();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Main() throws Exception{
		initializeDDS();
		initializeGUI();
	}
	
	private void initializeDDS() throws Exception{
		communicator = new DDSCommunicator();
		
		communicator.createParticipant();
		communicator.createPublisher();
		communicator.createSubscriber();
		
		topic = communicator.createTopic(
				TOPIC_MASTER_TO_SLAVE_HANDS.VALUE, 
				TMasterToHands.class);
		
		listener = new TMasterToHandsListener();
		
		reader = (TMasterToHandsDataReader)communicator
				.getSubscriber()
				.create_datareader(
						topic,
						Subscriber.DATAREADER_QOS_USE_TOPIC_QOS, 
						listener,
						StatusKind.STATUS_MASK_ALL);
		
		if (reader == null) {
			System.err.println("Error creating data reader.\n");
			throw new Exception();
		}

	}
	private void initializeGUI(){
		
	}
}
