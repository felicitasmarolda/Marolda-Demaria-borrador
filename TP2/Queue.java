package queue;

public class Queue {

  private ArrayList<Object> objetos = new ArrayList();

  public boolean isEmpty() {
		// TODO Auto-generated method stub
		return objetos.isEmpty();
	}

	public Queue add( Object  cargo ) {
		objetos.add(cargo);
		// TODO Auto-generated method stub
		return this;
	}

	public Object take() {
    // TODO Auto-generated method stub
		return null;
	}

	public Object head() {
		// TODO Auto-generated method stub
    return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}