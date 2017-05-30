import java.util.ArrayList;
//clase que no es cartografica per generar nodes per posar en una llista que es al que tractarem per tal de fer el algortime recursiu
//a mes de gets set te un el metode adicional recursiu que esta explicat mes endevan com intento fer-lo funcionar
public class NodesL {

	private String nomNode	=null;
	private int cost=0;
	private boolean esNodePare=false;
	private ArrayList<NodesL> nodesAdjunts=null;
	
	public ArrayList<NodesL> getNextNodes() {
		return nodesAdjunts;
	}
	public NodesL(String name){
		this.nomNode=name;
		this.nodesAdjunts=new ArrayList<NodesL>();
	}
	public NodesL(String name, int cost){
		this.nomNode=name;
		this.setCost(cost);
		this.nodesAdjunts=new ArrayList<NodesL>();
	}	

	public String getName() {
		return nomNode;
	}

	public void setName(String name) {
		this.nomNode = name;
	}

	public boolean isParent() {
		return esNodePare;
	}

	public void setParent(boolean parent) {
		this.esNodePare = parent;
	}

	public void addNextNode(NodesL ciudad) {
		this.nodesAdjunts.add(ciudad);
		
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	//algoritme recursiu del calcul de la ruta mes curta.
	/*
	 * Aquest metode retorna una llista de objectes del tipus NodeL que tenen un nom cost varibale pare i una llista
	 * nomes utilitzarem nom i cost(encara que el cost no ha pogut ser implementat corrrectament en lectura
	 * El que fa es pasat un string que es el desti (no varia) i un node que considerem origen
	 * mirem el cas de si el node desti es igual al node origen i si es el cs afegim el node (this el que estem tractant)
	 * i l'afegim a la ruta i retornem la ruta en cas de aver tribat el resultat (aquesta ruta s'anira cargan de nodesL
	 * per els que anem pasant)
	 * sino es el node que tractem el desti pasem a fer un bucle per tal 
	 * comprovan si el node es pare, node que ja em tractat per tal de no reutilitzar y nodar voltes(consells trobat per internet
	 * a un github) pasarem a dir que es node pare per si entra en recursibitat i tornem a crida el metode miran tots els nodes fills
	 * d'aquell node pare aixi comproven les possibles rutes disponibles(aqui tindria que entrar factor cost per millorar)
	 * si un com fet la crida a si mateix tornem i la nova ruta esta buida i es mes mes petita que la que tenim fins ara
	 * o esta buida generem suplim la ruta (llista per la nova que em trobat)
	 * un cop acabat amb el bucle pasarem a posar el node que estem tractant com pare a ja no o es i si esta buida la ruta que utilitzem
	 * pasem a afegir el node que em tractat i retornarem la ruta en questio per tornar a fer recursivitat sobre la ruta
	 * fins trobar el cas base
	 * en cas de aver pugut fer els costos en la part del size tindriem mes condicion per el cost de les rutas ja que de moment
	 * el que faig es mira rla ruta que te menys nodes de origen a desti
	 */
	public ArrayList<NodesL> rutaMesCurta(String destination,NodesL parentNode){
		
		ArrayList<NodesL>route = new ArrayList<NodesL>();
		//cas que si tinguem desti igual al node tractat retornem el node afegit a la ruta sino
		if(this.nomNode.equalsIgnoreCase(destination)){
			route.add(this);
			return route;
		}else{
			//recorem la llista de nodesL veins 
			for(int i =0; i<nodesAdjunts.size();i++){
				//part consell torbat a github per no quedarnos donand voles infinites en camins infinits 
				//alhora de fer un tractament de un node direm que no podem toranr aquest
				if(!nodesAdjunts.get(i).isParent()){
				//par de dir que no tornme
					parentNode.setParent(true);
					//crida recursiva de un node fill que tenim que sera el nou node pare per trobar el cami
					ArrayList<NodesL>newRoute=nodesAdjunts.get(i).rutaMesCurta(destination,this);
					//tria quina es la ruta optima si la que tenim fins ara o la nova trobada
					if(!newRoute.isEmpty()&&(newRoute.size()<route.size() || route.isEmpty())){
						route=newRoute;
					}
				}
			
			}
			//diem que no es pare per si despres el trobem en una iteracio de un altre cami
			this.esNodePare=false;
			//si no tenim encara ruta amb node afegim la ruta generada a la routa que volem mostrar
			if(!route.isEmpty()){
				route.add(this);
			}
			return route;
		}
	}
}
