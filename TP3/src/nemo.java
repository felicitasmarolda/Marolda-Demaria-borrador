package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {
	
	private int height;
	private int xCoord;
	private int YCoord;
	private int direction;
	
	public Nemo ( int height, int distX, int distY, int direction ) {
		this.height = height;
		this.xCoord = distX;
		this.xCoord = distX;
		this.direction = direction;
	}
	
	public Nemo () {
	}
	
    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return YCoord;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public List<Integer> getCoordinates() {
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(getXCoord());
        coordinates.add(getYCoord());
        return coordinates;
    }
	

	// ver si comando perpetuado es string[] o string 
	public Nemo commandsForNemo(String string) {
		for (int i = 0; i < string.length(); i++) {
            Character command = string.charAt(i);
            if (command == 'd') {
            	this.height = height - 1;
            }
            if (command == 'u') {
            	this.height = height + 1;
            }
            if (command == 'l') {
                	if (direction <= 180) {
                		this.direction = direction + 90;}
                	else if(direction == 270) {
                		this.direction= 0;}
             }
            if (command == 'r') {
            	if (direction >= 90) {
            		this.direction = direction - 90;}
            	else if(direction == 0) {
            		this.direction = 270;}
            	}
              
            if (command == 'f') {
            	if (direction == 0) {
            		this.xCoord = xCoord + 1;
            	} 
            	if (direction == 90) {
            		this.YCoord = YCoord + 1;
            	}
            	if (direction == 180) {
            		this.xCoord = xCoord - 1;
            	}
            	if (direction == 270) {
            		this.YCoord = YCoord - 1;
            	} 
        }
            if (command == 'm') {
            	//entender que significa "librar la capsula"
            	}
            }
		return this;
	}
	
	// falta lo de sumergirse y salir o algo asi

}
