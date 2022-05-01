public class EmptyFolderException extends Exception{
    String message;

    public EmptyFolderException(String foldername)
    {
        super(foldername + " is Empty");
    }

    public EmptyFolderException(String foldername,String message) {
        super(message);
        this.message = message;
    }

    public EmptyFolderException(Throwable cause)
    {
        super(cause);
    }
}
