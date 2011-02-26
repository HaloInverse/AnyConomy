package com.haloinverse.AnyConomy.Exceptions;

public class AccountExistsException extends EconException {

	private static final long serialVersionUID = 7174617556324168496L;
	// Thrown if an account is being created, and already exists.
	   public AccountExistsException()
	   {
	   }
	 
	   public AccountExistsException(String message)
	   {
	     super(message);
	   }
	 
	   public AccountExistsException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public AccountExistsException(Throwable cause)
	   {
	     super(cause);
	   }
}
