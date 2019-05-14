package main.java.com.wolftrain.app;

public class TacoTypes
{
	private String Name;
    private float Cost;
	
	public TacoTypes(String Person, float Amount) {
		this.Name = Person;
		this.Cost = Amount;
	}
	
    public String getName() { return this.Name; }
    public float getCost() { return this.Cost; }
}