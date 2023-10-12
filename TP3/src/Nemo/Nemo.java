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
	List<Comando> comandos = new ArrayList<>();
	
	public Nemo(int x, int y, Cardinal direc) {
		xCoord = x;
		yCoord = y;
		direction = direc;
		comandos.add(new Up());
		comandos.add(new Down());
		comandos.add(new Forward());
		comandos.add(new Left());
		comandos.add(new Right());
		comandos.add(new Open());
	}
	
	public String direction() {
		return direction.direccion();
	}
	
	public boolean capsule() {
		return capsule.capsula();
	}

	public void execute(String string) {
		for (int i = 0; i < string.length(); i++) {
			Character command = string.charAt(i);
			this.execute(command);           
		}
	}
	
	public void execute( Character command ) {
		Instruccion passedCommand = new Instruccion(command);
		Stream<Comando> commands = comandos.stream().filter(comm -> comm.value() == passedCommand.value);
		List<Comando> filteredCommand = commands.collect(Collectors.toList());
		Comando comando = filteredCommand.get(0);
		comando.executeSpecific(this);
	}
}
