package com.kruskal.fileparser;

import com.kruskal.graph.EdgeData;
import com.kruskal.graph.GraphBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {

    public void write(String outFileName, GraphBuilder graphBuilder){
        List<EdgeData> edgesData = graphBuilder.getEdgesData();
        int nodesNumber = graphBuilder.getGraph().getNodesId().size();
        File file;
        try {
            file = new File(outFileName);
            file.createNewFile();
        }
        catch (Exception e) {
            System.err.println("File not found");
            return;
        }
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        printWriter.println(nodesNumber);
        int secondNodeIndex = 1;
        for(int firstNodeIndex = 1; firstNodeIndex <= nodesNumber; firstNodeIndex++){
            for(EdgeData edgeData: edgesData){
                if((firstNodeIndex != edgeData.getFirstNodeId() && firstNodeIndex != edgeData.getSecondNodeId())){
                    continue;
                }
                while((firstNodeIndex != edgeData.getFirstNodeId() || secondNodeIndex != edgeData.getSecondNodeId())
                && (secondNodeIndex != edgeData.getFirstNodeId() || firstNodeIndex != edgeData.getSecondNodeId())){
                    printWriter.print(0 + " ");
                    secondNodeIndex++;
                    if(secondNodeIndex > nodesNumber){
                        break;
                    }
                }
                printWriter.print(edgeData.getWeight());
                if(secondNodeIndex != nodesNumber) {
                    printWriter.print(" ");
                }
                secondNodeIndex++;
            }
            while(secondNodeIndex <= nodesNumber){
                printWriter.print(0 + "");
                secondNodeIndex++;
            }
            printWriter.println();
            secondNodeIndex = 1;
        }
        printWriter.close();
    }
}
