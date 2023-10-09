package nemo;

import java.util.HashMap;
import java.util.Map;

public class UpSlot extends AnswererCommand {
	
	private HeightsSlots finder(boolean altura) {
		Map<Boolean, HeightsSlots> alturas = new HashMap<>();
		alturas.put(true, new EnoughHigh());
		alturas.put(false, new EnoughDeep());
		
		return alturas.get(altura);
	}
	
	public void commandsForNemoPolymorfic(Nemo submarino) {
		finder(Math.abs(submarino.zCoord) == submarino.zCoord).goUp(submarino);
	}	
}
