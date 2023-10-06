package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {
	
	public int zCoord;
	public int xCoord;
	public int yCoord;
	public int direction;
	
	boolean capsule = false;
	
	public Nemo () {
	}        
	
	// ver si comando perpetuado es string[] o string 
	public void commandsForNemo(String string) {
		for (int i = 0; i < string.length(); i++) {
            Character command = string.charAt(i);
            this.commandsForNemo(command);           
	}
		}
	public void commandsForNemo( Character command ) {
		 if (command == 'd') {
            	this.zCoord = zCoord - 1;
            }
        if (command == 'u') {
        	if (this.zCoord <= -1) {
        		this.zCoord = zCoord + 1;
        	}
        	else {
        		this.zCoord = 0;
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
        		this.xCoord = xCoord + 1;
        	} 
        	if (direction == 90) {
        		this.yCoord = yCoord + 1;
        	}
        	if (direction == 180) {
        		this.xCoord = xCoord - 1;
        	}
        	if (direction == 270) {
        		this.yCoord = yCoord - 1;
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
}}
