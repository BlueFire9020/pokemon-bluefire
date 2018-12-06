package bluefire.game;

import java.util.HashMap;

public class Pokemon 
{
	private String name;
	private String description;
	private String nickname;
	
	private String originalTrainer;
	private int ID;
	private int pokedexID;
	private boolean gender;
	private boolean shiny;
	private boolean fainted;
	
	private int maxHP;
	private int currentHP;
	private int level;
	
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;
	
	private Item heldItem;
	private PokemonMove[] moves;
	private StatusEffect[] debuffs;
	//Object for the trigger, PokemonID for the Integer
	private Evolution[] evolutions;
	
	
	/**
	 * Returns the Item that the Pokemon is currently holding,
	 * <i>null</i> if the Pokemon isn't holding an item. 
	 * @return heldItem
	 */
	public Item getItem()
	{
		return heldItem;
	}
	
	/**
	 * Sets the held item of the Pokemon. 
	 * @param newItem
	 */
	public void setItem(Item newItem)
	{
		heldItem = newItem;
	}
	
	/**
	 * Get the nickname of this Pokemon.
	 * @return nickname
	 */
	public String getNickname()
	{
		return nickname;
	}
	
	/**
	 * Set the Pokemon's nickname. 
	 * @param newNickname
	 */
	public void setNickname(String newNickname)
	{
		nickname = newNickname;
	}
	
	
	/**
	 * Heal the Pokemon to the specified amount of HP.
	 * @param amount
	 */
	public void Heal(int amount)
	{
		Heal(amount, null);
	}
	/**
	 * Heal the Pokemon to the specified amount of HP, as well 
	 * as removing the provided StatusEffects if the Pokemon 
	 * has them.
	 * @param amount
	 * @param effectsToRemove
	 */
	public void Heal(int amount, StatusEffect[] effectsToRemove)
	{
		if (effectsToRemove.length > 0)
		{
			for (int x = 0; x < debuffs.length; x++)
			{
				for (StatusEffect effectToRemove : effectsToRemove)
				{
					if (effectToRemove == debuffs[x])
					{
						debuffs[x] = null;
					}
				}
			}	
		}
		if (amount > 0)
		{
			if (fainted)
			{
				fainted = false;
			}
			currentHP += amount;
		}
	}
	
	
	
	
}
