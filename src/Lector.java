import java.util.Scanner;
//Clase auxiliar utilitzada per fer les lectures del usuari, ames de combrobar tipus
public class Lector {
	//nuestro teclado lector univresa
	Scanner teclat = new Scanner(System.in);
	//metode per lectura de teclat amb el qual comprovem que es un enter el nombre que ha posat l'usuari
	public int comprovarInt(String missatge){
		//
		int valor;
		System.out.print(missatge);
		while(!teclat.hasNextInt()){
			teclat.nextLine();
			System.out.print("No has introduit un valor enter."+missatge);
		}
		valor = teclat.nextInt();
		teclat.nextLine();
		return valor;
	}
	//metode igual que l'anterior pero que no controla el misatge
	public int comprovarInt(){
		//
		int valor;
		while(!teclat.hasNextInt()){
			teclat.nextLine();
			System.out.print("No has introduit un valor enter.Torna a provar: ");
		}
		valor = teclat.nextInt();
		teclat.nextLine();
		return valor;
	}
	//metode per lectura de teclat amb el qual comprovem que es un real el nombre que ha posat l'usuari
	public double comprovarDouble(String missatge){
		//
		double valor;		
		System.out.print(missatge);
		while(!teclat.hasNextDouble()){
			teclat.nextLine();
			System.out.print("No has introduit una valor real."+missatge);
		}
		valor = teclat.nextDouble();
		teclat.nextLine();
		return valor;
	}
	//metode tonto comprova string, utilitzat mes que res per lleguir
	public String comprovarString(String missatge){
		
		String valor;
		System.out.print(missatge);
			while(!teclat.hasNext()){
				teclat.nextLine();
				System.out.print("No has introduit una cadena de caracters."+missatge);
			}
		valor= teclat.next();
		teclat.nextLine();		
		return valor;
	}
	public boolean comprovarBool(String missatge){
		//
		boolean valor;
		System.out.print(missatge);
		while(!teclat.hasNextBoolean()){
			teclat.nextLine();
			System.out.print("No has introduit un boolea(true/false)."+missatge);
		}
		valor = teclat.nextBoolean();
		teclat.nextLine();
		return valor;
	}
	
}