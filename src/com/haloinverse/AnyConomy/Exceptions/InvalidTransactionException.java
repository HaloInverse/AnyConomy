package com.haloinverse.AnyConomy.Exceptions;

public class InvalidTransactionException extends EconException {
	// Thrown when an economy rejects a given transaction.
	private static final long serialVersionUID = 4265113110756376236L;
	   public InvalidTransactionException()
	   {
	   }
	 
	   public InvalidTransactionException(String message)
	   {
	     super(message);
	   }
	 
	   public InvalidTransactionException(String message, Throwable cause)
	   {
	     super(message, cause);
	   }
	 
	   public InvalidTransactionException(Throwable cause)
	   {
	     super(cause);
	   }
}
