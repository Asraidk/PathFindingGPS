import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	//Static que usarem com clases per cridar els seus metodes
	static Lector sc;
	static Pais  PS= new Pais();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		lecturaArxiu();
		
		//Variables i instancias pera crear nuestro menu de trabajo
		int i;
		sc = new Lector();
		/*Menu recurente en el que comprovamos el valor que introduce un usuario y 
		*/
		do{
			System.out.println("+===========================================+");
			System.out.println("Escull una opció de les següents:");
			System.out.println("1.- Mostrar llistat de les rutes.");
			System.out.println("2.- Tria la ruta ciutat origen<->desti.");
			System.out.println("0.- Sortir");
			System.out.println("+===========================================+");
			
		    i = sc.comprovarInt();
		    switch (i){
		    case 1: PS.MostrarRutes(); break;
		    	
		    case 2: PS.EstriarUnaRuta(); i=0; break;		    
		
		    }
		}while (i!=0);			
	}
	public static void lecturaArxiu() throws FileNotFoundException, IOException {		
		PS.LecturaDocument("España.txt");		
	}
}
