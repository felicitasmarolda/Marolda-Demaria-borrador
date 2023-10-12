package nemo;

public class West extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.xCoord -= 1;
	}

	public void left(Nemo nemo) {
		nemo.direction = new South();
	}

	public void right(Nemo nemo) {
		nemo.direction = new North();
	}	
	
	public String direccion() {
		return "Oeste";
	}	
}