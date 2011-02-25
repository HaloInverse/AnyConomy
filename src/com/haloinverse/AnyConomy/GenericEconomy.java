package com.haloinverse.AnyConomy;

import com.haloinverse.AnyConomy.Exceptions.*;

public interface GenericEconomy {

	//////// Account methods.
	
	public boolean getAccountExists(String accountName)
		throws 	InternalEconException;
		// Returns true if the account name exists, and false if it doesn't.
	
	public void createAccount(String accountName)
		throws	InternalEconException,
				AccountExistsException;	// if account already exists.
	
	public void deleteAccount(String accountName)
		throws	InternalEconException,
				NoAccountException;		// if account already did not exist.
	
	public String[] accountList()
		throws	InternalEconException;
		// Returns a list of account names.
	
	///////// Account balance methods.
	// Note that an economy can choose to throw NoAccountExceptions (safer), *or* auto-create the account (easier).
	
	public long getBalance(String accountName)
		throws	InternalEconException,
				NoAccountException;
		// Returns the current balance, rounded to the nearest integer.
	
	public long getBalance(String accountName, int decimals)
		throws	InternalEconException,
				NoAccountException;
		// Returns the balance * 10^(<decimals>), rounded to the nearest integer.
		// For example, if the actual balance is 123.456, getBalanceScaled(name, 2) returns 12347.
	
	public long addBalance(String accountName, long fundsToAdd)
		throws	InternalEconException,
				NoAccountException,
				InvalidTransactionException;	// Thrown if the economy rejects that transaction
												// (i.e. balance required to be >=0, below some cap, whatever.)
		// Adds the given amount to the account (subtracts if negative).
	
	public long addBalance(String accountName, long fundsToAdd, int decimals)
		throws	InternalEconException,
				NoAccountException,
				InvalidTransactionException;
		// Adds the given amount to the account (subtracts if negative), scaled by decimals.
		// i.e. addBalance(<name>, 12345, 2) adds 123.45 to the account.
	
	// There is no setBalance method. It's sort of like GOTO: a simple, easy-to-understand, very bad idea.
	
	//////// Meta-economic methods.
	
	public String getEconomyName();
	// Returns the base name of the economy plugin, i.e. "iConomy", "Cookies", etc. 
	
	public float getEconomyVersion();
	// Returns the version number of the economy plugin, i.e. 2.4, 1.0
	
	public String getEconomyCurrencyName();
	// Returns the (singular) name of the economy's currency, i.e. "Coin", "Buck"
	// Return null if no name is currently set.
	
	public String getEconomyCurrencyNamePlural();
	// Returns the (pluralized) name of the economy's currency, i.e. "Coins", "Buckz"
	// Usually, this will be getEconomyCurrencyName()+"s", but not always.
	
	public int getEconomyMaxDecimals();
	// Returns the number of decimal places stored by the economy, i.e. the maximum sensible value to pass into getBalanceScaled.
	// i.e. If the economy supports two decimal places, such as 100.23, then getEconomyMaxDecimals = 2.
	// If the economy only supports integer values, getEconomyMaxDecimals = 0.
	// Higher decimal are expected to be rounded off by the economy plugin without throwing errors.
	// Economy-using plugins should not expect economy plugins to keep decimals supplied past getEconomyMaxDecimals.
	
	public boolean getEconomyCaseSensitive();
	// Returns whether the economy plugin is case-sensitive regarding account names or not.
}
