package nemo;

public class FrontSlot extends AnswererCommand {
	
	public void commandsForNemoPolymorfic(Nemo submarino) {
		if (submarino.direction == 0) {
			submarino.xCoord += 1;
		} 
		if (submarino.direction == 90) {
			submarino.yCoord += 1;
		}
		if (submarino.direction == 180) {
			submarino.xCoord -= 1;
		}
		if (submarino.direction == 270) {
			submarino.yCoord -= 1;
		} 
	}
}
