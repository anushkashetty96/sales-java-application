This application is designed to demonstrate your ability to handle Exceptions and File I/O, and to utilize object-oriented concepts. The application includes a Sales class, an Exception class, and a SalesDatabase class.

# Sales Class
The Sales class includes the following attributes:

1. country (String)
2. item_type (String)
3. order_priority (Char)
4. order_date (Date)
5. order_ID (long)
6. ship_date (Date)
7. units_sold (Int)
8. unit_price (Float)
9. unit_cost (float)
10. revenue (Double)
11. total_cost (Double)
12. total_profit (Double)

The order_date and ship_date attributes are in dd/mm/yyyy format.

# DuplicateRecordException Class
The DuplicateRecordException class extends the Exception class and is thrown when a duplicate record is detected.

# SalesDatabase Class
The SalesDatabase class includes a static array of Sales objects called salesArr[], as well as the following methods:

* addRecords(): Accepts a Sales object and adds it to the salesArr[] object.
* displayFileContents(): Accepts an input file stream name and displays the contents of the file to the standard output. This method uses the BufferedReader class to read the file.
* binarySaleSearch(): Accepts an order_ID parameter and searches for a record using the binary search approach. The method also keeps track and displays how many iterations it needed to perform the search.
* sequentialSaleSearch(): Accepts an order_ID parameter and searches for a record using the sequential search approach. The method also keeps track and displays how many iterations it needed to perform the search.
You may add any other methods to this class if you wish to.
# Exception Classes
The application includes two custom exception classes:

* InvalidFileException: Ensures that the log file is valid and the file paths in it are valid. The constructor allows for a default error message "Error: Input file named XXX cannot be found," as well as a custom error message.
* EmptyFolderException: Checks for empty directories. The constructor allows for a default error message "Error: Input file named XXX cannot be found," as well as a custom error message. All exceptions are logged and the valid files are processed.

# Usage
1. The application assumes that there is a folder named Data in the same directory as the .java file, and that it contains all the files that must be processed. To list all the directories, subdirectories, and files present at a path, you can run the following command: java SalesDatabase list
2. To process the files, you can run the following command: java SalesDatabase process
3. The application provides three options to users: List files, Process files, and Exit.
* List files (1): Creates the "log.txt" file and lists all the files and directories present in the Data folder. If the log file already exists, its contents are overwritten.
* Process files (2): Opens the "log.txt" file and processes all the files listed in it.
* Exit (3): Exits the program and closes all opened files.

# Output
The output of the application includes a file named "log.txt" that contains the detailed file structure present in the Data folder. If any duplicate records are detected, the user is notified. The application also includes a sample output file for reference.
