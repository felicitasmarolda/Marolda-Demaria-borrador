package nemo;
public class Nemo {
	
	private static Cardinal North = new North();
	private static Cardinal South = new South();
	private static Cardinal East = new East();
	private static Cardinal West = new West();

	public int zCoord;
	public int xCoord;
	public int yCoord;
	public Cardinal direction = North;
	public boolean capsule = false;

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
		
		if (command == 'f') {
			direction.forward(this);
		}
		
		if (command == 'l') {
			direction.left(this);
		}
		
		if (command == 'r') {
			direction.right(this);
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
	
	public String direction() {
		return direction.direccion();
	}
}
