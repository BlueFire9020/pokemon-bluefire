package bluefire.game;

public class PokemonAttack 
{
	private String name;
	private String description;
	
	private Type type;
	
	private int maxPP;
	private int currentPP;
	private int power;
	private int accuracy;
	
	private int[] buffs = new int[6];
	private int[] debuffs = new int[6];
	
}
