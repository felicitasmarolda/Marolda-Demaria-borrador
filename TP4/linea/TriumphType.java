package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TriumphType {

	static List<TriumphType> possibleModes = new ArrayList<TriumphType>( Arrays.asList(new TypeA(),
			  																		   new TypeB(),
			  																		   new TypeC()) );
	
	public static TriumphType typeFor(Character typeChar) {
		return possibleModes.stream()
			    			.filter(typeInstance -> typeInstance.canHandle(typeChar))
			    			.toList()
			    			.get(0);
	}
	
	public abstract Line checkMeAsTriumphTypeFor(Line linea);
	public abstract boolean canHandle(char typeChar);
//	public abstract boolean wonInCurrentType(Line line, char color, int column);
	public abstract boolean verifyTriumphInGameAsTypeWithColorAndColumn(Line line, char color, int column);
	
}
