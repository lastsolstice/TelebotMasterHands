package discoverylab.telebot.master.hands.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHands;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsDataWriter;
import TelebotDDSCore.Source.Java.Generated.master.hands.TOPIC_MASTER_TO_SLAVE_HANDS;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import com.rti.dds.topic.TypeSupport;
import com.rti.dds.type.builtin.StringTypeSupport;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

//https://github.com/rticommunity/rticonnextdds-usecases-medical/blob/master/MedicalDevices/ExampleCode/src/HMI/src/com/rti/medical/DDSCommunicator.java
public class Main {

	private JFrame frmMasterHands;
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
	private static DomainParticipant participant = null;
	private static Publisher publisher = null;
	private static TMasterToHandsDataWriter writer = null;
	TMasterToHands instance = new TMasterToHands();
	InstanceHandle_t instance_handle = InstanceHandle_t.HANDLE_NIL;
	
	/**
	 * 
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) 
	{

		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main window = new Main();
					window.frmMasterHands.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public Main() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		initializeDDS();
		initializeGUI();
	}
	
	private void initializeDDS() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		participant = DomainParticipantFactory.get_instance().create_participant(
                0, // Domain ID = 0
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT, 
                null, // listener
                StatusKind.STATUS_MASK_NONE);
		
		Class<TMasterToHands> typeClass = TMasterToHands.class;
        String typeClassName = typeClass.getName();
        
        Class<? extends TypeSupport> typeSupportClass = 
        		(Class<? extends TypeSupport>) Class.forName(
                typeClassName + "TypeSupport");
        Method getInstanceMethod = typeSupportClass.getMethod("get_instance");
        TypeSupport typeSupport = (TypeSupport) getInstanceMethod.invoke(null);
        
        Method getTypeName = typeSupportClass.getMethod("get_type_name");
        String typeName = (String) getTypeName.invoke(null);
        
        // register the type
        participant.register_type(typeName, typeSupport, null);

		Topic topic = participant.create_topic(
                TOPIC_MASTER_TO_SLAVE_HANDS.VALUE, 
                typeName, 
                DomainParticipant.TOPIC_QOS_DEFAULT, 
                null, // listener
                StatusKind.STATUS_MASK_NONE);
		
		publisher = participant.create_publisher(
				DomainParticipant.PUBLISHER_QOS_DEFAULT, null /* listener */,
				StatusKind.STATUS_MASK_NONE);
		
		writer = (TMasterToHandsDataWriter) publisher.create_datawriter(
				topic, Publisher.DATAWRITER_QOS_DEFAULT, null /* listener */,
				StatusKind.STATUS_MASK_NONE);
		
		if (writer == null) {
			System.err.println("create_datawriter error\n");
			return;
		}
	}
	
	/**
	 * Initializes the GUI - JFrame
	 */
	private void initializeGUI(){
		// this sets swing to use the native look and feel, much more
		// aesthetically pleasing
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		// all of the swing components and layout management is best edited
		// using the windowbuilder GUI
		frmMasterHands = new JFrame();
		frmMasterHands.setTitle("ANPP Example");
		frmMasterHands.setBounds(100, 100, 819, 631);
		frmMasterHands.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initializing GridBagLa
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 0, 0, 90, 5, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 0, 5, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		frmMasterHands.getContentPane().setLayout(gridBagLayout);

		comboBoxPort = new JComboBox<String>();
		comboBoxPort.setEnabled(false);
		comboBoxPort.setModel(new DefaultComboBoxModel<String>(new String[] { "No Serial Ports" }));
		GridBagConstraints gbc_comboBoxPort = new GridBagConstraints();
		gbc_comboBoxPort.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPort.gridx = 1;
		gbc_comboBoxPort.gridy = 1;
		frmMasterHands.getContentPane().add(comboBoxPort, gbc_comboBoxPort);

		comboBoxBaud = new JComboBox<String>();
		comboBoxBaud.setModel(new DefaultComboBoxModel<String>(new String[] { "1200", "2400", "4800", "9600", "19200", "38400", "57600", "115200", "230400", "460800", "500000", "921600", "1000000" }));
		comboBoxBaud.setSelectedIndex(7);
		GridBagConstraints gbc_comboBoxBaud = new GridBagConstraints();
		gbc_comboBoxBaud.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBaud.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBaud.gridx = 2;
		gbc_comboBoxBaud.gridy = 1;
		frmMasterHands.getContentPane().add(comboBoxBaud, gbc_comboBoxBaud);

		btnConnect = new JButton("Connect");
		btnConnect.setEnabled(false);
		btnConnect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// if the serial port is not connected, this is a connect action
				// if the serial port is connected, this is a disconnect action
				if (!serialConnected)
				{
					try
					{
						serialPort = new SerialPort((String) comboBoxPort.getSelectedItem());
						serialPort.openPort();
						serialPort.setParams(Integer.parseInt((String) comboBoxBaud.getSelectedItem()), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
						serialPort.setEventsMask(SerialPort.MASK_RXCHAR);

						// add an event listener, which is the subclass at the
						// bottom of this file
						serialPort.addEventListener(new SerialReader());

						// change the button to be disconnect once we are
						// connected
						btnConnect.setText("Disconnect");
						serialConnected = true;
						comboBoxPort.setEnabled(false);
						comboBoxBaud.setEnabled(false);
					}
					catch (Exception exception)
					{
						System.err.println(exception.toString());
						serialPortClose();
					}
				}
				else
				{
					serialPortClose();
				}
			}
		});
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect.gridx = 3;
		gbc_btnConnect.gridy = 1;
		frmMasterHands.getContentPane().add(btnConnect, gbc_btnConnect);
		
		// Creating a Panel to add Labels and Text
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		frmMasterHands.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 10, 0, 0, 5, 0, 5, 0, 0 };
		gbl_panel.rowHeights = new int[] { 5, 0, 0, 0, 5, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		// Adding a Label
		lblFullHand = new JLabel("Hand Values:");
		GridBagConstraints gbc_lblFullHand = new GridBagConstraints();
		gbc_lblFullHand.anchor = GridBagConstraints.EAST;
		gbc_lblFullHand.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullHand.gridx = 1;
		gbc_lblFullHand.gridy = 1;
		panel.add(lblFullHand, gbc_lblFullHand);

		// Adding TextField
		textFieldFullHand = new JTextField();
		GridBagConstraints gbc_textFieldFullHand = new GridBagConstraints();
		gbc_textFieldFullHand.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFullHand.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFullHand.gridx = 2;
		gbc_textFieldFullHand.gridy = 1;
		panel.add(textFieldFullHand, gbc_textFieldFullHand);
		textFieldFullHand.setColumns(15);

		// Slider Label
		lblSlider = new JLabel("Slider:");
		GridBagConstraints gbc_lblSlider = new GridBagConstraints();
		gbc_lblSlider.anchor = GridBagConstraints.EAST;
		gbc_lblSlider.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlider.gridx = 4;
		gbc_lblSlider.gridy = 1;
		panel.add(lblSlider, gbc_lblSlider);
		
		// Slider Widget
//		slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
//		Hashtable labelTable = new Hashtable();
//		labelTable.put( new Integer( 0 ), new JLabel("0.0") );
//		labelTable.put( new Integer( 5 ), new JLabel("0.5") );
//		labelTable.put( new Integer( 10 ), new JLabel("1.0") );
		
//		slider = new JSlider();
		slider = new JSlider(JSlider.HORIZONTAL, 1000, 2000, 2000);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridx = 5;
		//slider.setLabelTable( labelTable );
		slider.setMajorTickSpacing(250);
//        slider.setMaximum(10);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
	    panel.add(slider, gbc_slider);
	    
	    slider.addChangeListener(new ChangeListener(){
	    	public void stateChanged(ChangeEvent e) {
	    	    JSlider source = (JSlider)e.getSource();
	    	    if (!source.getValueIsAdjusting()) {
	    	        int val = (int)source.getValue();
	    	        System.out.println("VAL: " + val);
//	    	        instance.lPinky = val/100.0;
//	    	        instance.lRing = val/100.0;
//	    	        instance.lMiddle  = val/100.0;
//	    	        instance.lIndex = val/100.0;
//	    	        instance.lThumbFlexion = val/100.0;
//	    	        instance.lThumbOpposition = val/100.0;
	    	        instance.lPinky = val;
	    	        instance.lRing = val;
	    	        instance.lMiddle  = val;
	    	        instance.lIndex = val;
	    	        instance.lThumbFlexion = val;
	    	        instance.lThumbOpposition = val;
	    	        
	    	        /* Write data */
					writer.write(instance, instance_handle);
	    	    }
	    	}
	    });
	}
	

	
	public class PortScanner implements Runnable
	{
		public void run()
		{
			while (true)
			{
				if (!serialConnected)
				{
					SwingUtilities.invokeLater(new Runnable()
					{
						public void run()
						{
							if (!serialConnected)
							{
								String[] portNames = SerialPortList.getPortNames();
								int comboLength = comboBoxPort.getItemCount();
								if (!serialPortsAvailable) comboLength = 0;
								Boolean portsChanged = portNames.length != comboLength;
								if(!portsChanged)
								{
									for(int i = 0; i < portNames.length; i++)
									{
										if(portNames[i].compareTo((String)comboBoxPort.getItemAt(i)) != 0)
										{
											portsChanged = true;
											break;
										}
									}
								}
								if (portsChanged)
								{
									comboBoxPort.removeAllItems();
									for (int i = 0; i < portNames.length; i++)
									{
										comboBoxPort.addItem(portNames[i]);
									}
									if (portNames.length == 0)
									{
										comboBoxPort.addItem("No Serial Ports");
										comboBoxPort.setSelectedIndex(0);
										comboBoxPort.setEnabled(false);
										btnConnect.setEnabled(false);
										serialPortsAvailable = false;
									}
									else
									{
										comboBoxPort.setSelectedIndex(0);
										comboBoxPort.setEnabled(true);
										btnConnect.setEnabled(true);
										serialPortsAvailable = true;
									}
								}
							}
						}
					});
				}
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

	private void serialPortClose()
	{
		try
		{
			serialPort.removeEventListener();
			serialPort.closePort();
		}
		catch (SerialPortException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnConnect.setText("Connect");
		serialConnected = false;
		comboBoxPort.setEnabled(true);
		comboBoxBaud.setEnabled(true);
	}


	/*
	 * This class is the event listener for received serial data
	 * Whenever serial data is received the serialEvent is called
	 * which adds the received data to the decoders buffer and then
	 * checks whether any packets can be decoded.
	 */
	public class SerialReader implements SerialPortEventListener
	{
		//ANPacketDecoder packetDecoder;
		StringBuilder message = new StringBuilder();
		Boolean receivingMessage = false;
		
		TMasterToHands instance = new TMasterToHands();

		public SerialReader()
		{
			//packetDecoder = new ANPacketDecoder();
		}

		public void serialEvent(SerialPortEvent event)
		{
			//TODO TEST: might need to add this for Windows machine
			// if(event.isRXCHAR() && event.getEventValue() > 0)
			if (event.isRXCHAR())
			{
				try
				{
					byte[] buffer = serialPort.readBytes();
					if (buffer != null)
					{
						for (byte b: buffer) {
			                if (b == '<') {
			                    receivingMessage = true;
			                    message.setLength(0);
			                }
			                else if (receivingMessage == true) {
			                    //if (b == '\r') {
			                	if (b == '>') {
			                        receivingMessage = false;
			                        String toProcess = message.toString();
			                        //Platform.runLater(new Runnable() {
			                        SwingUtilities.invokeLater(new Runnable()
			                        {
			                            @Override public void run() {
			                            	// TODO make separate method to handle GUI updates
			                                //processMessage(toProcess);
			                            	textFieldFullHand.setText(toProcess);
			                            	// TODO
			                            	// instance.lPinky = ___
			                           }
			                        });
			                    }
			                    else {
			                        message.append((char)b);
			                    }
			                }
			            } 
					}
				}
				catch (SerialPortException exception)
				{
					System.err.println(exception.toString());
				}
			}
		}
	}
}
