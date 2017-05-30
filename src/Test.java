import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	//Variables que farem servir de manera statica
	static Lector sc;
	static Pais  PS= new Pais();//per si volem crear nous documents i volem fer llistas de paisos mapes en si
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//metode que fa la lectura del arxiu podriem fer un menu per escollir el menu que volem lleguir si es cal
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
			//crides al metodess una fara la ruta optima i l'altre en canvi mistrara la informacio que em capturar
		    i = sc.comprovarInt();
		    switch (i){
		    case 1: PS.MostrarRutes(); break;
		    	
		    case 2: PS.EstriarUnaRuta(); i=0; break;		    
		
		    }
		}while (i!=0);			
	}
	/*
	 * metode que crida metode per fer la lectura del document i generar objectes de clases determinades
	 * generar aixi una cartografia guardada en memoria del programa, si sindtorudis un altre document
	 * amb la mateixa etructura funcionaria igual
	 * */	
	public static void lecturaArxiu() throws FileNotFoundException, IOException {		
		PS.LecturaDocument("España.txt");		
	}
}
