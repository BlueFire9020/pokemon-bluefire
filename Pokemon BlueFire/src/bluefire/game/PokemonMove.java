package bluefire.game;

public class PokemonMove 
{
	public final int maxStatChange = 6;
	
	private Type[] types;
	private String moveName;
	
	private int maxPP;
	private int currentPP;
	//negative effects given to the enemy pokemon
	private int[] debuffs = new int[6];
	//positive effects given to the parent pokemon
	private int[] buffs = new int[6];
	
	private int power;
	private int accuracy;
	
	
	
}
