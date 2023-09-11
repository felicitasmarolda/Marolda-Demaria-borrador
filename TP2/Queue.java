package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class Queue {

  private ArrayList<Object> objetos = new ArrayList();

  public boolean isEmpty() {
		// TODO Auto-generated method stub
		return objetos.isEmpty();
	}

	public Queue add( Object  cargo ) {
		// TODO Auto-generated method stub
		objetos.add(cargo);
		return this;
	}

	public Object take() {
		// TODO Auto-generated method stub
		if (objetos.isEmpty()) {
			throw new Error("Queue is empty");
		}
		else
			return objetos.remove(0);
	}

	public Object head() {
		// TODO Auto-generated method stub
		if (objetos.isEmpty()) {
			throw new Error("Queue is empty");
		}
		else
			return objetos.get(0);
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}