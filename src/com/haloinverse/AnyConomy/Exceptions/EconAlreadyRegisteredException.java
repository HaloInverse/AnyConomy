package com.haloinverse.AnyConomy.Exceptions;

public class EconAlreadyRegisteredException extends EconException {
	// Thrown when an economy tries to register, and another economy is already registered.
	private static final long serialVersionUID = -2838227332190644542L;
	   public EconAlreadyRegisteredException()
	   {
	   }
	 
	   public EconAlreadyRegisteredException(String message)
	   {
	     super(message);
	   }
	 
	   public EconAlreadyRegisteredException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public EconAlreadyRegisteredException(Throwable cause)
	   {
	     super(cause);
	   }
}
