package discoverylab.telebot.master.hands.packets;

/**
 * 
 * @author 	irvin
 * @github	kPatch
 * @twitter ElectroFunks
 * @version 0.1
 * Description
 * 		This represents a data packet received from 12 flex sensors connected to a microcontroller
 * 			6 Right Hand Flex Sensor Data Values
 * 			6 Left	Hand Flex Sensor Data Values
 * 
 * For reference on thumb flexion and opposition visit: 
 * 		http://morphopedics.wikidot.com/dequervain-s-disease
 * 
 * Data Format - The values represent a view of the back part of an operator's hands
 * 		< lPinky, lRing, lMiddle, lIndex, lThumbFlexion, lThumbOpposition, rThumbFlexion, rThumbOpposition, rIndex, rMiddle, rRing, rPinky>
 * 
 * 
 */
public class FlexSensorPacket {
	//public int lPinky, lRing, lMiddle, lIndex, lThumbFlexion, lThumbOpposition, rThumbFlexion, rThumbOpposition, rIndex, rMiddle, rRing, rPinky;
	
	// Left Hand
	public int lPinky;
	public int lRing; 
	public int lMiddle; 
	public int lIndex;
	public int lThumbFlexion; 
	public int lThumbOpposition;
	
	// Right Hand
	public int rThumbFlexion; 
	public int rThumbOpposition;
	public int rIndex;
	public int rMiddle;
	public int rRing;
	public int rPinky;
	
	public FlexSensorPacket(){
		
	}
}
