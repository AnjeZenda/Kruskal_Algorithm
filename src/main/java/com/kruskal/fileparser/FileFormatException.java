package com.kruskal.fileparser;
public class FileFormatException extends RuntimeException{

    private final int fileStringNumber;
    public FileFormatException(String message, int fileStringNumber){
        super(message);
        this.fileStringNumber = fileStringNumber;
    }

    @Override
    public String toString() {
        return "FileFormatException{" + getMessage() + " (Строка файла: " + fileStringNumber + ")}";
    }
}
