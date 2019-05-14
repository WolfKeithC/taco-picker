package main.java.com.wolftrain.app;

public class TacoOrder
{
    private String Taco;
    private String Tortilla;
    private float Cost;
    private int Qty;
    
    public TacoOrder(String TacoName, String TortillaType, float Amount, int Count)
    {
    	this.Taco = TacoName;
    	this.Tortilla = TortillaType;
    	this.Cost = Amount;
    	this.Qty = Count;
    }
    
    public String getTaco() { return this.Taco; }
    public String getTortilla() { return this.Tortilla; }
    public float getCost() { return this.Cost; }
    public int getQty() { return this.Qty; }
}
