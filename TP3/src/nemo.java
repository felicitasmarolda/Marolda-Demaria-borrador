package nemo;

import java.util.Map;
import java.util.stream.Stream;

import nemo.CommandTypesSubClasses.Down;
import nemo.CommandTypesSubClasses.Forward;
import nemo.CommandTypesSubClasses.Left;
import nemo.CommandTypesSubClasses.MCapsule;
import nemo.CommandTypesSubClasses.Right;
import nemo.CommandTypesSubClasses.Up;

import java.util.HashMap;

public class Nemo {
//    public static Character Up = 'u';
//    public static Character Down = 'd';
//    public static Character Forward = 'f';
//    public static Character Left = 'l';
//    public static Character Right = 'r';
//    public static Character MCapsule = 'm';

	public int zCoord;
	public int xCoord;
	public int yCoord;
	public int direction;
	
	public boolean capsule = false;
		
	private Map<Character, CommandType> commandMap;

    public Nemo() {
        commandMap = new HashMap<>();
        commandMap.put('u', new Up());
        commandMap.put('d', new Down());
        commandMap.put('f', new Forward());
        commandMap.put('l', new Left());
        commandMap.put('r', new Right());
        commandMap.put('m', new MCapsule());

    }
        
//	public Nemo () {
//		CommandProcessor();
//	}  
	
	public void commandsForNemo(String commandString) {
		for (int i = 0; i < commandString.length(); i++) {
            Character command = commandString.charAt(i);
//            CommandType commandWithType = commandMap.get(command);
//            commandWithType.commandsForNemoPolymorphic( this );

//            Runnable runnable = commandMap.get(command);
//            runnable.run();

            this.commandsForNemo(command);           
	}
		}
	
        
    public void commandsForNemo( Character command) {
        CommandType commandWithType = commandMap.get(command);
    	commandWithType.commandsForNemoPolymorphic( this );
    }
//        commandMap = new HashMap<>();
//        commandMap.put(Up, () -> {
//        	if (this.zCoord <= -1) {
//        		this.zCoord += 1;
//        	}
//        	else {
//        		this.zCoord = 0;
//        	}        });
//        commandMap.put(Down, () -> {
//            this.zCoord = zCoord - 1;
//        });
//        commandMap.put(Forward, () -> {
//        	if (direction == 0) {
//        		this.xCoord = xCoord + 1;
//        	} 
//        	if (direction == 90) {
//        		this.yCoord = yCoord + 1;
//        	}
//        	if (direction == 180) {
//        		this.xCoord = xCoord - 1;
//        	}
//        	if (direction == 270) {
//        		this.yCoord = yCoord - 1;
//        	}         });
//        commandMap.put(Left, () -> {
//        	direction = (direction + 90 + 360) % 360;
//        });
//        commandMap.put(Right, () -> {
//    		direction = (direction - 90 + 360) % 360;
//        });
//        commandMap.put(MCapsule, () -> {
//        	if ( zCoord >= -1 && capsule == false) {
//        		capsule = true;
//        	}
//        	else {
//                throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
//        	}          });
//    }

//	public void commandsForNemo( Character command ) {
//		if (command == 'd') {
//            this.zCoord = zCoord - 1;
//            }
//        if (command == 'u') {
//        	if (this.zCoord <= -1) {
//        		this.zCoord += 1;
//        	}
//        	else {
//        		this.zCoord = 0;
//        	}
//        }
//        if (command == 'l') {
//            	direction = (direction + 90 + 360) % 360;
//         }
//        if (command == 'r') {
//        		direction = (direction - 90 + 360) % 360;
//
//        	}
//          
//        if (command == 'f') {
//        	if (direction == 0) {
//        		this.xCoord = xCoord + 1;
//        	} 
//        	if (direction == 90) {
//        		this.yCoord = yCoord + 1;
//        	}
//        	if (direction == 180) {
//        		this.xCoord = xCoord - 1;
//        	}
//        	if (direction == 270) {
//        		this.yCoord = yCoord - 1;
//        	} 
//        }
//        if (command == 'm') {
//        	if ( zCoord >= -1 && capsule == false) {
//        		capsule = true;
//        	}
//        	else {
//                throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
//        	}  	
//    }
//}
}
