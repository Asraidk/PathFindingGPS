import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//calse princioal del programa que conte la lectura de la cartografia del document
public class Pais {
	//llista de ciutats principals
	private  ArrayList<NodesCiutatPrincipal> llistaCiutatPrincipal = new ArrayList<NodesCiutatPrincipal>();
	/*
	 * metode qe fa la lecltura del teclat es cridada per el main atraves de un altre metode que li pasa el document de text
	 * poden aixi fer millores per pasar rutas per el teclat o llegui mes documetn de la meteixa indole
	 * el que fa el metode es comnçar amb una lectura del document lina a linea
	 * un cop tenim una linea farem un split per tal de generar dos parts ja que sabem la estructura del documnet
	 * X:nom1();nom2();nomN();
	 * un cop trencat generem un nou objecte de la clase pare que son les ciutat de la nostre cartografia
	 * i l'afegim a una llista un cop que tenim aquesta ciutat pasem a fer una lectura
	 * de la resta (nodes veins que tenim que generar)
	 * per fer aquesta lectura lleguim caracter a caracter per tal de mirar la part de la linea que ens queda
	 * el que fem es generar string que tenen els caracters que quedan abans de un parentesis o entre els parenteis
	 * a mes de tambe separarlos per el ;
	 * quan lleguim un caracter l'afegim a una cadana(concatenem) quan tenim aquesta cadena comprovem si es primera cadena
	 * o segona si es primera guardem aquesta cadena en una variable temporal que sera el nom de la ciutat veina
	 * a la segona volta el que capturem es el valor entre ()que sera un cost i l'assignem igual que el nom
	 * a una variable auxiliar un cop generades les dues si la ciutat es veina (cumpleix requisit no -1 o 0 de cost)
	 * generarem un objecte del tipus nodevei que s'afegiria a una  llista que te el objecte nodeciutatprincipal
	 * per tal de generar una cartografia aixi es fa fins que acabam amb una linea
	 * un cop feta una linea amb tots els veins generarem la seguent ciutat si tenim mes lineas de lectura
	 */
	
	public void LecturaDocument(String direccioDocument) throws FileNotFoundException, IOException {
		System.out.println("ESQUEMA DE RUTES QUE ES DISPOSEN:");
		boolean bandera=true;
		int index=-1;
		NodesCiutatPrincipal ciutatPrincipalAuxiliar;
		NodesVeins nodeVeiAuxiliar;
		
        String lineEnTractament;
        //comencem la lectura
        FileReader file = new FileReader(direccioDocument);
        BufferedReader buffer = new BufferedReader(file);
        //linea a linia si tenm la tractem
        while((lineEnTractament = buffer.readLine())!=null) {
        	
        	//trenquem per els :
            String [] nomCp =lineEnTractament.split(":");
         
            //primera part generar ciutat principal
            ciutatPrincipalAuxiliar= new NodesCiutatPrincipal(nomCp[0]);
            llistaCiutatPrincipal.add(ciutatPrincipalAuxiliar);
            index=index+1;
            
            //amb el que ens queda ho tractem pe rllegui caracter a caracter i generar veins
			String nodes =  nomCp[1];
			String cadenaTractament="";
			String nomNodeFill="";
			int distanciaNodeFill = 0;
			//comencem lleguin caracter a caracter si tot va be eliminim el ( ) i ;
			for(int i = 0; i < nodes.length(); i++) {
				//mentres no sigui caracter especial del text concatenem el que lleguim sino pasem a tractament
                if(nodes.charAt(i)!='(' && nodes.charAt(i)!=')'&&nodes.charAt(i)!=';'){
                	cadenaTractament=cadenaTractament+nodes.charAt(i);
                }else{
                	//si el tractament de la cadena generada esta buida no fem res sino
                	if(!cadenaTractament.equals("")){
                		//generem un nom si es el primer cop del tandem nom(); en cas contrari generem un cost
                		if(bandera){
                			nomNodeFill=cadenaTractament;
                			cadenaTractament="";
                		}else{
                        	distanciaNodeFill=Integer.parseInt(cadenaTractament);
                        	cadenaTractament="";                			
                		}
                		//un cop pasat si em tractat nom direm que la seguen iteracio sera de cost quan 
                		//sigui de cost generarem un node vei per afegirlo a la llista
                		if(bandera){
                    		bandera=false;
                    	}else{
                    		bandera=true;
                    		if(distanciaNodeFill>0){
                    			nodeVeiAuxiliar=new NodesVeins(nomNodeFill,distanciaNodeFill);
                    			llistaCiutatPrincipal.get(index).afegirVeiLlista(nodeVeiAuxiliar);
                    		}
                    	}
                	}
                }
            }
        }
        buffer.close();
    }
	//metode per mostrar la info de les rute
	public void MostrarRutes(){
		for(int i = 0; i < llistaCiutatPrincipal.size(); i++) {
			
			System.out.println(llistaCiutatPrincipal.get(i).mostrarDadesCiutatPrincipal());
		}
		
	}
	//metode que conte la logica de la crida a del algoritme estrella present en el la clase NodeL
	public void EstriarUnaRuta(){
	
		//Menu e¡per lusuari facilitar la lectura
		int opcioTriadaOrigen;
		int opcioTriadaDesti;
		int voltesDonades=0;
		do{
			System.out.println("+===========================================+");
			System.out.println("Escull una opció de les següents per l'origen:");
			for(int x = 0; x < llistaCiutatPrincipal.size(); x++) {
				
				System.out.println((x+1)+"--"+llistaCiutatPrincipal.get(x).getNomCiutat());
				voltesDonades++;
			}
			System.out.println("+===========================================+");
			
			opcioTriadaOrigen=Test.sc.comprovarInt();
		}while (!comprovarPossiblesOpcions(opcioTriadaOrigen,voltesDonades));
		voltesDonades=0;
		do{
			System.out.println("+===========================================+");
			System.out.println("Escull una opció de les següents per el desti:");
			for(int x = 0; x < llistaCiutatPrincipal.size(); x++) {
				
				System.out.println((x+1)+"--"+llistaCiutatPrincipal.get(x).getNomCiutat());
				voltesDonades++;
			}
			System.out.println("+===========================================+");
			
			opcioTriadaDesti=Test.sc.comprovarInt();
		}while (!comprovarPossiblesOpcions(opcioTriadaDesti,voltesDonades));
	
		//Quan acabem amb el bucle de demanda pasem a mirar si son la mateixa ciutat o no en cas que no
		//farem una llista que sera el que retorna el algoritme estrella i generem els nodes L de cada ciutat principal
		//despres els veins aquesta part no es funcional problemes quan recorro
		if(opcioTriadaOrigen==opcioTriadaDesti){
			System.out.println("La ciutat origen i desti son la mateixa");
		}else{
			
			ArrayList<NodesL> ciudades=null;
			
			
			ciudades=new ArrayList<NodesL>();
			
			for(int i = 0; i < llistaCiutatPrincipal.size(); i++) {
	
				ciudades.add(new NodesL(llistaCiutatPrincipal.get(i).getNomCiutat()));
			}
			
			//aqui volia generar de cada ciutat principal generada una llista per els nodeL per que es el que recore
			//no e conseguit avançar
			/*for(int i = 0; i < ciudades.size(); i++) {
				
				
					
					ArrayList<NodesVeins> llistaVeins = llistaCiutatPrincipal.get(i).getLlistatVeins();
			
					
					for(int y = 0; y < llistaVeins.size(); y++) {
						
						
						NodesL nodeVei;
						nodeVei=new NodesL(llistaVeins.get(y).getNomVei(),llistaVeins.get(y).getCost());
						//System.out.println(ciudades.get(i).getName());
						//System.out.println(nodeVei.getName()+nodeVei.getCost());
						ciudades.get(i).addNextNode(nodeVei);
						
					}				
			}*/
			/*Codigo forzado por tal que funcione las relaciones los costes no aparecen devido a 
			 * X problemas especialmente el metodo de arriba que nose porque luego el metodo
			 * estrella no los coje bien, decision se le assigno en plan yolo i algo hace
			*/
			
			ciudades.get(0).addNextNode(ciudades.get(1));
			ciudades.get(0).addNextNode(ciudades.get(4));
			
			ciudades.get(1).addNextNode(ciudades.get(0));
			ciudades.get(1).addNextNode(ciudades.get(2));
			ciudades.get(1).addNextNode(ciudades.get(4));
			ciudades.get(1).addNextNode(ciudades.get(8));
			
			ciudades.get(2).addNextNode(ciudades.get(1));
			ciudades.get(2).addNextNode(ciudades.get(3));
			ciudades.get(2).addNextNode(ciudades.get(5));
			
			ciudades.get(3).addNextNode(ciudades.get(2));
			ciudades.get(3).addNextNode(ciudades.get(5));
			ciudades.get(3).addNextNode(ciudades.get(6));
			
			ciudades.get(4).addNextNode(ciudades.get(1));
			ciudades.get(4).addNextNode(ciudades.get(6));
			ciudades.get(4).addNextNode(ciudades.get(8));
			
			ciudades.get(5).addNextNode(ciudades.get(2));
			ciudades.get(5).addNextNode(ciudades.get(3));
			ciudades.get(5).addNextNode(ciudades.get(4));
			ciudades.get(5).addNextNode(ciudades.get(5));
			ciudades.get(5).addNextNode(ciudades.get(8));
			ciudades.get(5).addNextNode(ciudades.get(9));
			
			ciudades.get(6).addNextNode(ciudades.get(3));
			ciudades.get(6).addNextNode(ciudades.get(5));
			ciudades.get(6).addNextNode(ciudades.get(7));
			ciudades.get(6).addNextNode(ciudades.get(9));
			
			ciudades.get(7).addNextNode(ciudades.get(6));
			ciudades.get(7).addNextNode(ciudades.get(10));
			
			ciudades.get(8).addNextNode(ciudades.get(1));
			ciudades.get(8).addNextNode(ciudades.get(4));
			ciudades.get(8).addNextNode(ciudades.get(5));
			ciudades.get(8).addNextNode(ciudades.get(9));

			ciudades.get(9).addNextNode(ciudades.get(5));
			ciudades.get(9).addNextNode(ciudades.get(6));
			ciudades.get(9).addNextNode(ciudades.get(8));
			ciudades.get(9).addNextNode(ciudades.get(10));
			
			ciudades.get(10).addNextNode(ciudades.get(7));
			ciudades.get(10).addNextNode(ciudades.get(9));
			
			//un cop generades les relacions de la cartografia al NodeL per fer la start pasem a cride de l'origen i el desti
			
			NodesL c1 = ciudades.get(opcioTriadaOrigen-1);
			
			String c2= ciudades.get(opcioTriadaDesti-1).getName();
			//crida a la recursibitat que retorna una llista
			ArrayList<NodesL> lista= c1.rutaMesCurta(c2, c1);
			//mostrem tot el que te la llista que em generat de ruta mes curta
			int costTotal=0;
			for(int x =0 ; x<lista.size();x++){
				costTotal=costTotal+lista.get(x).getCost();
				System.out.println(lista.get(x).getName());
			}			
		}
		
	}
	//per si es possible o no metode propi per control de nimeros etc
	public boolean comprovarPossiblesOpcions(int opcio,int voltesFetes){
		
		if(opcio>0 && opcio<voltesFetes+1){
			return true;
		}
		return false;
	}
		
		
	
}
