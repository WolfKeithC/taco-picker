package main.java.com.wolftrain.app;

import java.util.LinkedList;

public class TacoTotals
{
	private LinkedList<TacoEntry> Orders;
	private float SubTotal;
	private float TaxTotal;
	private float TipTotal;
	private float Total;
	private String Notes;
	
    public TacoTotals(float subTotal, float tax, float tip, float total, String Note) 
    { 
    	this.Orders = new LinkedList<TacoEntry>(); 
    	this.SubTotal = subTotal;
    	this.TaxTotal = tax;
    	this.TipTotal = tip;
    	this.Total = total;
    	this.Notes = Note;    	
    }
    
	public LinkedList<TacoEntry> getOrders() { return this.Orders; } 
	public float getSubtotal() { return this.SubTotal; }
	public float getTax() { return this.TaxTotal; }
	public float getTip() { return this.TipTotal; }
	public float getTotal() { return this.Total; }
	public String getNotes() { return this.Notes; }
}
