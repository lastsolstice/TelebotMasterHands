package discoverylab.telebot.slave.hands;

import java.util.ArrayDeque;
import java.util.Deque;

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

public class TMasterToHandsListener extends DataReaderAdapter{
	TMasterToHandsSeq dataSeq = new TMasterToHandsSeq();
	SampleInfoSeq infoSeq = new SampleInfoSeq();
	
	Deque<TMasterToHands> masterToHandsQueue = new ArrayDeque<>();
	
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
					System.out.println(command.lPinky + " " + 
										command.lRing + " " + 
										command.lMiddle + " " +
										command.lIndex + " " +
										command.lThumbFlexion + " " +
										command.lThumbOpposition);
				}
			}
		} catch (RETCODE_NO_DATA noData) {
            // No data to process
        } finally {
        	tMasterToHandsDataReader.return_loan(dataSeq, infoSeq);
        }
	}
	
}
