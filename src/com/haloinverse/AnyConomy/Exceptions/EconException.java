package com.haloinverse.AnyConomy.Exceptions;

public class EconException extends Exception {
	// Generic economy exception.

	private static final long serialVersionUID = 1454944642248810818L;
	 
	   public EconException()
	   {
	   }
	 
	   public EconException(String message)
	   {
	     super(message);
	   }
	 
	   public EconException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public EconException(Throwable cause)
	   {
	     super(cause);
	   }
}