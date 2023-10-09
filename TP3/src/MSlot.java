package nemo;

public class MSlot extends AnswererCommand {
	
	public void commandsForNemoPolymorfic(Nemo submarino) {
		if (submarino.zCoord >= -1 && submarino.capsule == true) {
			submarino.capsule = false;
		}
		else {
            throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
    	}  
	}
}
