package com.haloinverse.AnyConomy.Exceptions;

public class NoEconRegisteredException extends EconException {
	// Thrown when an economy-plugin method is called, but no economy is loaded. 
	private static final long serialVersionUID = 8478950190219938072L;
	   public NoEconRegisteredException()
	   {
	   }
	 
	   public NoEconRegisteredException(String message)
	   {
	     super(message);
	   }
	 
	   public NoEconRegisteredException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public NoEconRegisteredException(Throwable cause)
	   {
	     super(cause);
	   }
}
