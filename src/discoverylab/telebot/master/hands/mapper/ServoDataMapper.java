package discoverylab.telebot.master.hands.mapper;


public class ServoDataMapper extends Mapper {
	
	@Override
	public int constrain(int val, int servo_max, int servo_min)
	{
		int ret = val;
		
		if(val <= servo_min)
		{
			ret = servo_min;
		}
		else if(val >= servo_max)
		{
			ret = servo_max;
		}
		
		return ret;
	}
	
	@Override
	public int map(int val, int sensor_max, int sensor_min, int servo_max, int servo_min)
	{
        int mapped = (val - sensor_min) * (servo_max - servo_min) / (sensor_max - sensor_min) + servo_min;
        
        int constrained =  constrain(mapped, servo_max, servo_min);
        
        return constrained;
	}

}
