package main.java.com.wolftrain.app;

import java.util.LinkedList;

public class TacoEntry
{
	private LinkedList<TacoOrder> Tacos;
	private String Name;
	private float TotalCost;
	private float UnitCost;
	private boolean Paid;
	private boolean Split;
	private String Notes;
	
	private TacoEntry(String Person, float Total, float Unit, boolean p, boolean s, String Note) 
	{ 
		this.Tacos = new LinkedList<TacoOrder>();
		
		this.Name = Person;
		this.TotalCost = Total;
		this.UnitCost = Unit;
		this.Paid = p;
		this.Split = s;
		this.Notes = Note;
	}

    public String getName() { return this.Name; }
    public LinkedList<TacoOrder> getTacos() { return this.Tacos; }
    public float getTotal() { return this.TotalCost; }
    public float getUnit() { return this.UnitCost; }
    public boolean isPaid() { return this.Paid; }
	public boolean isSplit() { return this.Split; }
	public String getNotes() { return this.Notes; }
}
