package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {

	public int zCoord;
	public int xCoord;
	public int yCoord;
	public int direction;

	boolean capsule = false;

	public void commandsForNemo(String string) {
		for (int i = 0; i < string.length(); i++) {
			Character command = string.charAt(i);
			this.commandsForNemo(command);           
		}
	}
	
	public void commandsForNemo( Character command ) {
		if (command == 'd') {
			zCoord -= 1;
		}
		if (command == 'u') {
			if (zCoord <= -1) {
				zCoord += 1;
			}
		}
		if (command == 'l') {
			direction = (direction + 90 + 360) % 360;
		}
		if (command == 'r') {
			direction = (direction - 90 + 360) % 360;

		}

		if (command == 'f') {
			if (direction == 0) {
				xCoord += 1;
			} 
			if (direction == 90) {
				yCoord += 1;
			}
			if (direction == 180) {
				xCoord -= 1;
			}
			if (direction == 270) {
				yCoord -= 1;
			} 
		}
		if (command == 'm') {
			if ( zCoord >= -1 && capsule == false) {
				capsule = true;
			}
			else {
				throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
			}  	
		}
	}
}
