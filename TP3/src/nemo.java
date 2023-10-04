package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {
	
	private int height;
	private int distX;
	private int distY;
	private int direction;
	
	public Nemo ( int height, int distX, int distY, int direction ) {
		this.height = height;
		this.distX = distX;
		this.distX = distX;
		this.direction = direction;
	}
	
	public Nemo () {
	}
	
    public int getDistX() {
        return distX;
    }

    public int getDistY() {
        return distY;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public List<Integer> getCoordinates() {
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(getDistX());
        coordinates.add(getDistY());
        return coordinates;
    }
	

	// ver si comando perpetuado es string[] o string 
	public Nemo commandsForNemo(String[] commandChain) {
		for (int i = 0; i < commandChain.length; i++) {
            String command = commandChain[i];
            if (command == "d") {
            	int newHeight = height - 1;
            }
            if (command == "u") {
            	int newHeight = height + 1;
            }
            if (command == "l") {
                if (command == "r") {
                	if (direction <= 180) {
                		int newDirection = direction + 90;}
                	else if(direction == 270) {
                		int newDirection = 0;}
                	}
            	int newDirection = direction + 90;
            }
            if (command == "r") {
            	if (direction >= 90) {
            		int newDirection = direction - 90;}
            	else if(direction == 0) {
            		int newDirection = 270;}
            	}
              
            if (command == "f") {
            	if (direction == 0) {
            		int newDistX = distX + 1;
            	} 
            	if (direction == 90) {
            		int newDistY = distY + 1;
            	}
            	if (direction == 180) {
            		int newDistX = distX - 1;
            	}
            	if (direction == 270) {
            		int newDistY = distY - 1;
            	} 
        }
            if (command == "m") {
            	//entender que significa "librar la capsula"
            	}
            }
		return this;
	}

}
