package nemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nemo {

	public int zCoord;
	public int xCoord;
	public int yCoord;
	public int direction;
	public boolean capsule = true;
	
	private AnswererCommand finder(Character comm) {
		Map<Character, AnswererCommand> comandos = new HashMap<>();
		comandos.put('d', new DownSlot());
		comandos.put('u', new UpSlot());
		comandos.put('l', new LeftSlot());
		comandos.put('r', new RightSlot());
		comandos.put('f', new FrontSlot());
		comandos.put('m', new MSlot());
		
		return comandos.get(comm);
	}
	
	public void commandsForNemo(String string) {
		for (int i = 0; i < string.length(); i++) {
			Character command = string.charAt(i);
			commandsForNemo(command);
		}
	}
	
	public void commandsForNemo(Character command) {
		finder(command).commandsForNemoPolymorfic(this);
	}
}
