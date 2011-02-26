package com.haloinverse.AnyConomy.Exceptions;

public class InternalEconException extends EconException {

	// Thrown when an internal error occurs with the economy plugin (i.e. database read error)
	private static final long serialVersionUID = -9110940848568597849L;
	   public InternalEconException()
	   {
	   }
	 
	   public InternalEconException(String message)
	   {
	     super(message);
	   }
	 
	   public InternalEconException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public InternalEconException(Throwable cause)
	   {
	     super(cause);
	   }
}
