package nemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Nemo {

	public int xCoord;
	public int yCoord;
	public int zCoord;
	public Cardinal direction;
	public Capsule capsule = new ClosedCapsule();
	List<Command> commands = new ArrayList<>();
	
	public Nemo(int x, int y, Cardinal direc) {
		xCoord = x;
		yCoord = y;
		direction = direc;
		commands.add(new Up());
		commands.add(new Down());
		commands.add(new Forward());
		commands.add(new Left());
		commands.add(new Right());
		commands.add(new Open());
	}
//	public Nemo() {
//	}
	
	public String direction() {
		return direction.direccion();
	}
	
	public boolean capsule() {
		return capsule.capsula();
	}

	public void executeNemoCommand( String string ) {
		for (int i = 0; i < string.length(); i++) {
			Character command = string.charAt(i);
			this.executeNemoCommand(command);           
		}
	}
	
	public void executeNemoCommand( Character command ) {
		Instruccion passedCommand = new Instruccion(command);
		Stream<Command> commandsInStream = commands.stream()
				.filter(comm -> 
						comm.value() == passedCommand.value);
		List<Command> filteredCommand = commandsInStream.collect(Collectors.toList());
		Command comando = filteredCommand.get(0);
		comando.executeSpecific(this);
	}
}