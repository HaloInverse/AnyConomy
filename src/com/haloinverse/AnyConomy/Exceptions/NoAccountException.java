package com.haloinverse.AnyConomy.Exceptions;

public class NoAccountException extends EconException {
	// Thrown when the requested account does not exist.
	private static final long serialVersionUID = 2825221634843601173L;
	   public NoAccountException()
	   {
	   }
	 
	   public NoAccountException(String message)
	   {
	     super(message);
	   }
	 
	   public NoAccountException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public NoAccountException(Throwable cause)
	   {
	     super(cause);
	   }
}
