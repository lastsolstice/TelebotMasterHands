package discoverylab.telebot.master.hands.parser;

import java.util.StringTokenizer;

import discoverylab.telebot.master.core.parser.CoreDataParser;
import discoverylab.telebot.master.hands.packets.FlexSensorPacket;


public class FlexSensorDataParser extends CoreDataParser{
	
	@Override
	public Object parse(String str)
	{
		StringTokenizer tokenizer = parseUsingTokenizer(str);
		FlexSensorPacket instance = new FlexSensorPacket();
	
		//Left Hand
		instance.lPinky = Integer.parseInt(tokenizer.nextToken());
		instance.lRing = Integer.parseInt(tokenizer.nextToken()); 
		instance.lMiddle = Integer.parseInt(tokenizer.nextToken()); 
		instance.lIndex = Integer.parseInt(tokenizer.nextToken());
		instance.lThumbFlexion = Integer.parseInt(tokenizer.nextToken()); 
		instance.lThumbOpposition = Integer.parseInt(tokenizer.nextToken());
		
		// Right Hand
		instance.rThumbFlexion = Integer.parseInt(tokenizer.nextToken()); 
		instance.rThumbOpposition = Integer.parseInt(tokenizer.nextToken());
		instance.rIndex = Integer.parseInt(tokenizer.nextToken());
		instance.rMiddle = Integer.parseInt(tokenizer.nextToken());
		instance.rRing = Integer.parseInt(tokenizer.nextToken());
		instance.rPinky = Integer.parseInt(tokenizer.nextToken());
		
		return instance;
	}

}
