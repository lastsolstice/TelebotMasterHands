package discoverylab.telebot.slave.hands;

import java.util.ArrayDeque;
import java.util.Deque;

import jssc.SerialPort;
import jssc.SerialPortException;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHands;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsDataReader;
import TelebotDDSCore.Source.Java.Generated.master.hands.TMasterToHandsSeq;

import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.ViewStateKind;

import discoverylab.telebot.master.hands.gui.Main.SerialReader;

public class TMasterToHandsListener extends DataReaderAdapter{
	TMasterToHandsSeq dataSeq = new TMasterToHandsSeq();
	SampleInfoSeq infoSeq = new SampleInfoSeq();
	
	Deque<TMasterToHands> masterToHandsQueue = new ArrayDeque<>();
	
	// Serial Port
	private SerialPort serialPort;
	protected Boolean serialConnected = false;
	private Boolean serialPortsAvailable = false;
	
	public TMasterToHandsListener(){
		serialPort = new SerialPort("/dev/ttyACM0");
		try {
			serialPort.openPort();
			serialPort.setParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// add an event listener, which is the subclass at the
		// bottom of this file

		// change the button to be disconnect once we are
	}
	
	public void on_data_available(DataReader reader) {
		TMasterToHandsDataReader tMasterToHandsDataReader = (TMasterToHandsDataReader) reader;
		
		try {
			tMasterToHandsDataReader.read(
					dataSeq, infoSeq,
					ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
                    SampleStateKind.ANY_SAMPLE_STATE,
                    ViewStateKind.ANY_VIEW_STATE,
                    InstanceStateKind.ANY_INSTANCE_STATE);
			
			for(int i = 0; i < dataSeq.size(); i++) {
				SampleInfo info = (SampleInfo) infoSeq.get(i);
				
				if(info.valid_data) {
					masterToHandsQueue.push((TMasterToHands)dataSeq.get(i));
					
					TMasterToHands command = (TMasterToHands)dataSeq.get(i);
					
					String commandStr = command.lPinky + " " + 
							command.lRing + " " + 
							command.lMiddle + " " +
							command.lIndex + " " +
							command.lThumbFlexion + " " +
							command.lThumbOpposition + "\r";
					
					System.out.println(commandStr);
					serialPort.writeString(commandStr);
				}
			}
		} catch (RETCODE_NO_DATA noData) {
            // No data to process
        } catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	tMasterToHandsDataReader.return_loan(dataSeq, infoSeq);
        }
	}
	
}
