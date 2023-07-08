package com.kruskal.fileparser;

import com.kruskal.graph.EdgeData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileWriter {

    public void write(String outFileName, List<EdgeData> edgesData, Set<Integer> nodesId){
        int nodesNumber = nodesId.size();
        File file;
        try {
            file = new File(outFileName);
            file.createNewFile();
        }
        catch (Exception e) {
            throw new FileFormatException("Неудалось создать файл для записи", 0);
        }
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new FileFormatException("Неудалось найти файл для записи", 0);
        }

        printWriter.println(nodesNumber);
        IdConverter idConverter = new IdConverter();
        Iterator<Integer> iterator = nodesId.iterator();
        for(int i = 1; i <= nodesNumber; i++){
            idConverter.addId(iterator.next(), i);
        }
        for(int firstNodeIndex = 1; firstNodeIndex <= nodesNumber; firstNodeIndex++){
            for(int secondNodeIndex = 1; secondNodeIndex <= nodesNumber; secondNodeIndex++){
                boolean edgeExists = false;
                for (EdgeData edgeData : edgesData) {
                    if (!((idConverter.getId(firstNodeIndex) == edgeData.getFirstNodeId() && idConverter.getId(secondNodeIndex) == edgeData.getSecondNodeId())
                            || (idConverter.getId(secondNodeIndex) == edgeData.getFirstNodeId() && idConverter.getId(firstNodeIndex) == edgeData.getSecondNodeId()))) {
                        continue;
                    }
                    printWriter.print(edgeData.getWeight());
                    edgeExists = true;
                    if (secondNodeIndex != nodesNumber) {
                        printWriter.print(" ");
                    }
                }
                if(!edgeExists) {
                    printWriter.print(0);
                    if (secondNodeIndex != nodesNumber) {
                        printWriter.print(" ");
                    }
                }
            }
            printWriter.println();
        }
        printWriter.close();
    }
}

class IdConverter{
    private Map<Integer, Integer> idMap;

    public IdConverter(){
        idMap = new HashMap<>();
    }

    public void addId(int id, int value){
        idMap.put(value, id);
    }

    public int getId(int value){
        return idMap.get(value);
    }
}