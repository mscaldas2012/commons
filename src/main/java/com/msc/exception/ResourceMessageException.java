package com.msc.exception;

/**
 * <p>A base class for all exceptions that configures messages and reads them from resource bundles. </p>
 * <p>Want two constructors: one w/ (String msg), and one w/ (String msg, Exception cause)</p>
 * <p/>
 * <P>The ResourceMessageException class acts as Contract to identify exceptions raised on our code,
 * not on third party Java code.</P>
 * <p/>
 * <P>This exception does not support internationalized error messages. An Error key mapped to a
 * resource bundle will added, so clients dealing with these exceptions can look for the error
 * messages on the appropriate locale.</P>
 * <p/>
 * <P>Other intents to achieve here are:
 * <UL>
 * <LI>Make the Chaining of exceptions mandatory via code - Not viable, since constructors are not inherited.</LI>
 * <LI>Make the Logging of exceptions mandatory here - Done</LI>
 * <UL>
 * Analysis are still under revision to see the possibility of those two requirements to be implemented here.</P>
 * <p/>
 * <p/>
 * This code was written by Marcelo Caldas.
 * e-Mail: mscaldas@gmail.com
 * <p/>
 * Project: commons
 * <p/>
 * Date: 2/24/14
 * <p/>
 * Enjoy the details of life.
 */
public abstract class ResourceMessageException extends Exception {
    private String[] args = new String[5];
//	private static final String ERROR_PROPERTIES_FILES = "errorBundle";

    /**
     * This property exists, so Web Services can serialize the message and foward it to clients.
     */
    private String message;
    private String finalMessage;

    /**
     * <p>Constructor for ResourceMessageException.</p>
     */
    protected ResourceMessageException() {
        super();
    }

    /**
     * <p>Constructor for ResourceMessageException.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    public ResourceMessageException(String msg) {
        this(msg, null, null);
    }

    /**
     * <p>Constructor for ResourceMessageException.</p>
     *
     * @param msg  a {@link java.lang.String} object.
     * @param args a {@link java.lang.String} object.
     */
    public ResourceMessageException(String msg, String... args) {
        this(msg, null, args);
    }

    /**
     * <p>Constructor for ResourceMessageException.</p>
     *
     * @param msg   a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Exception} object.
     */
    public ResourceMessageException(String msg, Exception cause) {
        this(msg, cause, null);
    }

    /**
     * <p>Constructor for ResourceMessageException.</p>
     *
     * @param msg   a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Exception} object.
     * @param args  a {@link java.lang.String} object.
     */
    public ResourceMessageException(String msg, Exception cause, String... args) {
        super(msg, cause);
        this.message = msg;
        this.args = args;
        this.finalMessage = this.translateMessage();
        //If there's no cause, this is cause:
        if (cause == null) {
            cause = this;
        }
//        String loggerBelongsTo = cause.getStackTrace()[0].getClassName();
    }

    /**
     * This method should be implemented by children Exceptions to determine at what level that
     * exception should be logger.
     *
     * @return a String identifyng the log level for the specific Exception. Possible values are
     * the constants defined on LanierLogger: SEVERE, WARNING, INFO, FINE, FINER, FINEST
     */
    public abstract String getLogLevel();


    /**
     * <p>translateMessage.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String translateMessage() {
        boolean hasArguments = this.args[0] != null;
        if (hasArguments) {
            StringBuilder result = new StringBuilder(this.getMessage());
            //Get ResourceBundle...
            //Translate Message and arguments...
            int index = 0;
            while (hasArguments) {
                String argumentValue = this.args[index];
                String placeHolder = "{" + index++ + "}";
                int startIndex = result.indexOf(placeHolder);
                if (startIndex > 0) {//Make sure we have a placeholder
                    int endIndex = startIndex + placeHolder.length();
                    if (endIndex > startIndex) {
                        result.replace(startIndex, endIndex, argumentValue);
                    }
                }
                hasArguments = this.args[index] != null;
            }
            this.message = result.toString();
        }
        return this.message;
    }

    public String getFinalMessage() {



        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    //	public String getInternationalizedMessage() {
//		StringBuffer result = new StringBuffer();
//        //Get ResourceBundle...
//        ResourceBundle bundle = ResourceBundle.getBundle(ERROR_PROPERTIES_FILES);
//		String errorKey = bundle.getString(this.getErrorKey());
//		result.append(errorKey);
//		//Translate Message and arguments...
//		boolean hasArguments = this.args[0] != null;
//		int index = 0;
//		while (hasArguments) {
//            String argumentValue = bundle.getString(this.getArg(index));
//			String placeHolder = "{" + index++ + "}";
//			int startIndex = result.indexOf(placeHolder);
//			int endIndex = startIndex + placeHolder.length();
//			result.replace(startIndex, endIndex, argumentValue);
//		}
//		logger.finest("generated message: " + result.toString());
//		System.out.println("generated message: " + result.toString());
//		return result.toString();

//	}

}
