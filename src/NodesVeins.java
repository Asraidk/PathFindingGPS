
public class NodesVeins {

	private String nomVei;
	private int cost;
	
	public NodesVeins(String nomVei,int cost){
		this.setNomVei(nomVei);
		this.setCost(cost);
	}

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
