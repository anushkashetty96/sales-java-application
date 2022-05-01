public class DuplicateRecordException extends Exception{
    public DuplicateRecordException(Sales sales)
    {
        super("********Duplicate record found (Record will not be inserted)******** : "+sales.toString());
    }

    public DuplicateRecordException(String message)
    {
        super(message);
    }

    public DuplicateRecordException(Throwable cause)
    {
        super(cause);
    }

}
