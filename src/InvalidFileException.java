public class InvalidFileException extends Exception{
    String message;

    public InvalidFileException(String filename)
    {
        super("********Error: Invalid input file : "+filename+"********");
    }

    public InvalidFileException(String filename,String message) {
        super(message);
        this.message = message;
    }

    public InvalidFileException(Throwable cause)
    {
        super(cause);
    }
}
