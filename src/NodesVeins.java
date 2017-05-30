//Clase utilitzada per genere elementr de la cartografia fills de les ciutat princial el cami marcat amb el seu cost
//tote els metodes son simples get set i el construtor unic metode diferent es el que mostrara les dades que tenim
//en nom i cost de un vei concret
public class NodesVeins {

	private String nomVei;
	private int cost;
	
	public NodesVeins(String nomVei,int cost){
		this.setNomVei(nomVei);
		this.setCost(cost);
	}
	//metode qu serveix per veure les dades capturades desde el document de text
	public String MostrarDadesVei(){
		return("	+["+nomVei+"]distancia->["+cost+"km]"+"\n");
	}
	public String getNomVei() {
		return nomVei;
	}

	public void setNomVei(String nomVei) {
		this.nomVei = nomVei;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
