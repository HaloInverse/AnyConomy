package com.haloinverse.AnyConomy;

import java.util.logging.Logger;

import com.haloinverse.AnyConomy.Exceptions.*;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AnyConomy extends JavaPlugin {

	private GenericEconomy econPlugin = null;
	protected final Logger log = Logger.getLogger("Minecraft");
	private PluginDescriptionFile desc = getDescription();
	
	public void registerEconomy(GenericEconomy thisEcon) throws EconAlreadyRegisteredException
	{
		// Economy plugins should call this method on initialization, with a listener object which implements GenericEconomy.
		if (thisEcon == null)
		{
			log.warning("[" + desc.getName() + "] Null economy listener registration attempted!");
			return;
		}
		if (econPlugin == thisEcon)
			return;	// it's OK to re-register the same object
		if (econPlugin != null)
		{	
			log.warning("[" + desc.getName() + "] Multiple economy plugin registration attempted!");
			throw new EconAlreadyRegisteredException();
		}
		econPlugin = thisEcon;
		log.info("[AnyConomy] Economy plugin registered: " + econPlugin.getEconomyName());
	}
	
	@Override
	public void onDisable() {
		econPlugin = null;
		log.info("[" + desc.getName() + "] version " + desc.getVersion() +" disabled");
	}

	@Override
	public void onEnable() {
		desc = getDescription();
		log.info("[" + desc.getName() + "] version " + desc.getVersion() +" enabled");
	}
	

	private void checkEcon() throws NoEconRegisteredException
	{
		if (econPlugin == null)
			throw new NoEconRegisteredException();
	}
	
	// Methods to pass to registered economy plugin.

	public boolean getAccountExists(String accountName)
		throws 	NoEconRegisteredException,
				InternalEconException
		// Returns true if the account name exists, and false if it doesn't.
	{
		checkEcon();
		return econPlugin.getAccountExists(accountName);
	}
		
	public void createAccount(String accountName)
		throws	NoEconRegisteredException,
				InternalEconException,
				AccountExistsException			// May be thrown if account already exists.
												// The economy plugin MAY ignore this call and leave the account intact instead of throwing an error.
	{
		checkEcon();
		econPlugin.createAccount(accountName);
	}
	
	public void deleteAccount(String accountName)
		throws	NoEconRegisteredException,
				InternalEconException,
				NoAccountException		// if account already did not exist.
	{
		checkEcon();
		econPlugin.deleteAccount(accountName);
	}
	
	public String[] accountList()
		throws	NoEconRegisteredException,
				InternalEconException
		// Returns a list of account names.
	{
		checkEcon();
		return econPlugin.accountList();
	}
	
	///////// Account balance methods.
	// Note that an economy can choose to throw NoAccountExceptions (safer), *or* auto-create the account (easier).
	
	public long getBalance(String accountName)
		throws	InternalEconException,
				NoAccountException,
				NoEconRegisteredException
		// Returns the current balance, rounded to the nearest integer.
	{
		checkEcon();
		return econPlugin.getBalance(accountName);
	}
	
	public long getBalance(String accountName, int decimals)
		throws	InternalEconException,
				NoAccountException,
				NoEconRegisteredException
		// Returns the balance * 10^(<decimals>), rounded to the nearest integer.
		// For example, if the actual balance is 123.456, getBalanceScaled(name, 2) returns 12347.
	{
		checkEcon();
		return econPlugin.getBalance(accountName, decimals);
	}
	
	public void addBalance(String accountName, long fundsToAdd)
		throws	InternalEconException,
				NoAccountException,
				NoEconRegisteredException,
				InvalidTransactionException		// Thrown if the economy rejects that transaction
												// (i.e. balance required to be >=0, below some cap, whatever.)
		// Adds the given amount to the account (subtracts if negative).
	{
		checkEcon();
		econPlugin.addBalance(accountName, fundsToAdd);
	}
	
	public void addBalance(String accountName, long fundsToAdd, int decimals)
		throws	InternalEconException,
				NoAccountException,
				InvalidTransactionException,
				NoEconRegisteredException
		// Adds the given amount to the account (subtracts if negative), scaled by decimals.
		// i.e. addBalance(<name>, 12345, 2) adds 123.45 to the account.
	{
		checkEcon();
		econPlugin.addBalance(accountName, fundsToAdd, decimals);
	}
	
	// There is NO setBalance method. It's sort of like GOTO: a simple, easy-to-understand, bad idea.
	
	//////// Meta-economic methods.
	
	public String getEconomyName()
		throws NoEconRegisteredException
	// Returns the base name of the economy plugin, i.e. "iConomy", "Cookies", etc. 
	{
		checkEcon();
		return econPlugin.getEconomyName();
	}
	
	public float getEconomyVersion()
		throws NoEconRegisteredException
	// Returns the version number of the economy plugin, i.e. 2.4, 1.0
	{
		checkEcon();
		return econPlugin.getEconomyVersion();
	}
	
	public String getEconomyCurrencyName()
		throws NoEconRegisteredException
	// Returns the (singular) name of the economy's currency, i.e. "Coin", "Buck"
	// Return null if no name is currently set.
	{
		checkEcon();
		return econPlugin.getEconomyCurrencyName();
	}
	
	public String getEconomyCurrencyNamePlural()
		throws NoEconRegisteredException
	// Returns the (pluralized) name of the economy's currency, i.e. "Coins", "Buckz"
	// Usually, this will be getEconomyCurrencyName()+"s", but not always.
	{
		checkEcon();
		return econPlugin.getEconomyCurrencyNamePlural();
	}
	
	public int getEconomyMaxDecimals()
		throws NoEconRegisteredException
	// Returns the number of decimal places stored by the economy, i.e. the maximum sensible value to pass into getBalanceScaled.
	// i.e. If the economy supports two decimal places, such as 100.23, then getEconomyMaxDecimals = 2.
	// If the economy only supports integer values, getEconomyMaxDecimals = 0.
	// Higher decimal are expected to be rounded off by the economy plugin without throwing errors.
	// Economy-using plugins should not expect economy plugins to keep decimals supplied past getEconomyMaxDecimals.
	{
		checkEcon();
		return econPlugin.getEconomyMaxDecimals();
	}
	
	public boolean getEconomyCaseSensitive()
		throws NoEconRegisteredException
	// Returns whether the economy plugin is case-sensitive regarding account names or not.
	{
		checkEcon();
		return econPlugin.getEconomyCaseSensitive();
	}
	
}
