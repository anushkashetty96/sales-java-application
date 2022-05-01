import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.String.format;

//-----------------------------------------------
// Assignment 1
// © Anushka R Shetty
// Written by: Anushka R Shetty 40192371
//-----------------------------------------------

public class SalesDatabase{

    //Declaration of static variables
    private static Scanner scanner;
    private static final String directory_name = "Data";
    private static final String file_name = "log.txt";
    private static final String output_filename = "output.txt";
    private static FileWriter myWriter = null;
    private static Set<Sales> fileRecords = new LinkedHashSet<>();
    static Sales[] salesArr = {};
    private static BufferedWriter bufferedWriter = null;


    /**
     *
     * @param directoryName
     * @return List of files within all the directories and subdirectories
     * @throws IOException
     */
    public static List<File> listFiles(BufferedWriter bufferedWriter,String directoryName) throws IOException {
        File directory = new File(directoryName);
        myWriter = new FileWriter("log.txt",true);
        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath()+"\n");
                bufferedWriter.write("\tfile:"+file.getAbsolutePath()+"\n");
            } else if (file.isDirectory()) {
                System.out.println( "directory:" + file.getAbsolutePath() + "\n");
                bufferedWriter.write("directory:" + file.getAbsolutePath() + "\n");
                resultList.addAll(listFiles(bufferedWriter,file.getAbsolutePath()));
            }
        }
        myWriter.close();
        return resultList;
    }

    /**
     * writes file names to log.txt
     * @throws IOException
     */
    public static void writeToLogFile() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(file_name));
        myWriter = new FileWriter(file_name);
        myWriter.write("");
        myWriter.close();
        System.out.println("Listing all the directories, subdirectories and files. Writing the following contents to the log file");
        List<File> files =  listFiles(bufferedWriter,directory_name);
        bufferedWriter.close();
    }

    /**
     * processes log.txt
     * @throws IOException
     */
    private static void processFiles() throws IOException {
        System.out.println("******** Processing started ********");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(file_name));
            myWriter=new FileWriter(output_filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String logFilePath = null;
        while (true) {
            try {
                if (((logFilePath = fileReader.readLine())==null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(logFilePath.contains("file:"))
            {

                String filePath = logFilePath.substring(logFilePath.indexOf(':')+1);
                String fileName = logFilePath.substring(logFilePath.lastIndexOf("/")+1);

                BufferedReader bufferedReaderForFile = null;
                try {

                    if(!fileName.endsWith(".txt"))
                        throw new InvalidFileException(fileName);

                    bufferedReaderForFile = new BufferedReader(new FileReader(filePath));

                    String record = null;
                    while((record=bufferedReaderForFile.readLine())!=null)
                    {
                        Sales salesRecord = null;
                        try {
                            String[] splitRecord = record.split("[\t\\s]+");

                            String country = splitRecord[0];
                            String item_type = splitRecord[1];
                            Character order_priority = splitRecord[2].charAt(0);
                            Date order_date = simpleDateFormat.parse(splitRecord[3]);
                            Long order_id = Long.parseLong(splitRecord[4]);
                            Date ship_date = simpleDateFormat.parse(splitRecord[5]);
                            Integer units_sold = Integer.parseInt(splitRecord[6]);
                            Float unit_price = Float.parseFloat(splitRecord[7]);
                            Float unit_cost = Float.parseFloat(splitRecord[8]);
                            Double revenue = Double.parseDouble(splitRecord[9]);
                            Double total_cost = Double.parseDouble(splitRecord[10]);
                            Double total_profit = Double.parseDouble(splitRecord[11]);

                            salesRecord = new Sales(country, item_type, order_priority, order_date,
                                    order_id, ship_date, units_sold, unit_price, unit_cost, revenue, total_cost, total_profit);
                        }
                        catch (Exception e)
                        {
                            throw new InvalidFileException(fileName);
                        }
                        if(!fileRecords.contains(salesRecord))
                        {
                            System.out.println(salesRecord);
                            addRecords(salesRecord);
                        }
                        else
                        {
                            try {
                                throw new DuplicateRecordException(salesRecord);
                            }
                            catch (DuplicateRecordException e) {
                                System.out.println(e.getLocalizedMessage());
                            }
                        }

                    }
                    bufferedReaderForFile.close();
                }
                catch (FileNotFoundException e) {
                    System.out.println();
                }
                catch (InvalidFileException e)
                {
                    System.out.println(e.getLocalizedMessage());
                }
            }

        }
        salesArr = fileRecords.stream().toArray(Sales[]::new);

        System.out.println("******** Processing completed ********");
        fileReader.close();
        myWriter.close();
    }

    /**
     * Inserts sales record into the output.txt
     * @param salesRecord SalesRecord
     * @throws IOException while writing to the file
     */
    private static void addRecords(Sales salesRecord) throws IOException {
        fileRecords.add(salesRecord);

        String datePattern = "dd/MM/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        String orderDate = dateFormat.format(salesRecord.getOrder_date());
        String shipDate = dateFormat.format(salesRecord.getShip_date());

        String record = format(salesRecord.getCountry())+"\t"+format(salesRecord.getItem_type())+"\t"+format(salesRecord.getOrder_priority().toString())
                +"\t"+format(orderDate)+"\t"+format(String.valueOf(salesRecord.getOrder_id()))+"\t"+format(shipDate)+"\t"+format(salesRecord.getUnits_sold().toString())
                +"\t"+format(salesRecord.getUnit_price().toString())+"\t"+format(salesRecord.getUnit_cost().toString())+"\t"+format(salesRecord.getRevenue().toString())
                +"\t"+format(salesRecord.getTotal_cost().toString())+"\t"+format(salesRecord.getTotal_profit().toString())+"\n";

        myWriter.write(record);
    }

    /**
     * Displays Menu for the user on the console
     */
    public static void displayMenu(){
        System.out.println("Choose one option\n(1) List files\n(2) Process files\n(3) Exit\n(4) Binary search\n(5) Sequential search");
    }

    /**
     * performs action from the menu based on the userInput
     * @param userInput takes user option
     * @throws IOException throws file not found
     */
    public static void performAction(int userInput) throws IOException {

        switch(userInput){
            case 1:

                writeToLogFile();
                displayMenu();
                break;
            case 2:
                processFiles();
                displayMenu();
                break;
            case 3:
                System.out.println("Closing files & exiting the Application!!");
                myWriter.close();
                System.exit(0);
                break;
            case 4:
                if(salesArr==null || salesArr.length==0)
                {
                    System.out.println("Please process files (option 2) first");
                }
                else {
                    System.out.println("Please enter order id to search");
                    Long order_id = Long.parseLong(scanner.nextLine());
                    binarySaleSearch(order_id);
                }
                displayMenu();
                break;
            case 5:
                if(salesArr==null || salesArr.length==0)
                {
                    System.out.println("Please process files (option 2) first");
                }
                else
                {
                    System.out.println("Please enter order id to search");
                    Long order_id = Long.parseLong(scanner.nextLine());
                    sequentialSaleSearch(order_id);
                }
                displayMenu();
                break;
        }
    }

    /**
     * accepts an input file stream name, then displays contents of this file to the standard output.
     * @param fileInputStream
     */
    private static void displayFileContents(FileInputStream fileInputStream)
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                fileInputStream, StandardCharsets.UTF_8));) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * searches a record using the binary search approach keeping track of iterations it needed to perform the search.
     * @param order_id
     */
    private static void binarySaleSearch(Long order_id)
    {
        Arrays.sort(salesArr, Comparator.comparing(Sales::getOrder_id));
        Long noOfIterations = 1L;
        int low = 0 , high = salesArr.length-1;
        int mid;

        while(low<=high)
        {
            noOfIterations++;
            mid = low+(high-low)/2;

            if(salesArr[mid].getOrder_id().equals(order_id))
            {
                System.out.println("Record found in "+ noOfIterations+" iterations");
                System.out.println(salesArr[mid]);
                System.out.println();
                return;
            }
            else if(order_id<salesArr[mid].getOrder_id())
                high=mid-1;
            else
                low=mid+1;
        }

        System.out.println("Record not found!");
    }

    /**
     * searches a record using the sequential search approach keeping track of iterations it needed to perform the search.
     * @param order_id
     */
    private static void sequentialSaleSearch(Long order_id)
    {
        Long iteration = 1L;
        for(Sales sales : salesArr)
        {
            if(sales.getOrder_id().equals(order_id))
            {
                System.out.println("Record found in "+ iteration+" iterations");
                return;
            }
            else
                iteration++;

        }
    }
    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        displayMenu();
        while(true){
            int userInput = Integer.parseInt(scanner.nextLine());
            if(userInput > 0 && userInput < 6)
                performAction(userInput);
            else
                break;
        }
        scanner.close();
    }
}
