package com.kruskal.fileparser;

import com.kruskal.controlstructures.Mediator;
import com.kruskal.gui.ActionMessage;
import com.kruskal.gui.State;

import java.net.URLConnection;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private enum ReadSymbol{
        SPACE,
        NUMBER
    }

    public void read(String inputFileName, double screenWidth, double screenHeight, Mediator mediator){

        Path path = Paths.get(inputFileName);
        Scanner scanner;
        int nodeNumber;
        List<List<Integer>> nodeMatrix = new ArrayList<>();
        String type = URLConnection.guessContentTypeFromName(path.toString());
        if(!type.equals("text/plain")){
            throw new FileFormatException("Неверный тип файла", 0);
        }
        try{
            scanner = new Scanner(path);
        } catch (java.io.IOException e){
            throw new FileFormatException("Не удалось открыть файл для чтения", 0);
        }

        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);
        if(lineScanner.hasNextInt()){
            nodeNumber = lineScanner.nextInt();
            if (lineScanner.hasNext()){
                throw new FileFormatException("Неверно задана строка с размером графа", 1);
            }
        } else{
            throw new FileFormatException("Неверно задана строка с размером графа", 1);
        }
        if(nodeNumber > 100){
            throw new FileFormatException("Размер графа превышает 80 вершин", 1);
        }
        nodeReader(nodeNumber, screenWidth, screenHeight, mediator, nodeMatrix);
        scanMatrix(nodeMatrix, scanner, nodeNumber);
        nodeMatrixToGraph(nodeMatrix, mediator);
    }

    private void scanMatrix(List<List<Integer>> nodeMatrix, Scanner scanner, int nodeNumber){
        for(int i = 0; i < nodeNumber; i++){
            if(!scanner.hasNextLine()) {
                throw new FileFormatException("Задана матрица меньшего размера, чем указано в строке с размером", i+2);
            }
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line).useDelimiter("");
            ReadSymbol readSymbol = ReadSymbol.NUMBER;
            int numberCount = 0;
            for(int j = 1; j <= line.length(); j++) {
                if((lineScanner.hasNextInt() && (readSymbol == ReadSymbol.SPACE)) ||
                        (!lineScanner.hasNextInt() && (readSymbol == ReadSymbol.NUMBER))){
                    throw new FileFormatException("Символ вместо ожидаемого числа", i+2);
                }
                if(readSymbol == ReadSymbol.NUMBER) {
                    int weight = 0;
                    while (lineScanner.hasNextInt()) {
                        weight = weight * 10 + lineScanner.nextInt();
                        if(weight >= 10){
                            j++;
                        }
                    }

                    if(i == numberCount && weight != 0){
                        throw new FileFormatException("Значение на диалогнали матрицы отличное от 0", i+2);
                    }

                    nodeMatrix.get(i).add(weight);
                    if(numberCount < i && !(nodeMatrix.get(i).get(numberCount).equals(nodeMatrix.get(numberCount).get(i)))){
                        throw new FileFormatException("Матрица не симметричная", i+2);
                    }
                    numberCount++;
                } else{
                    String next = lineScanner.next();
                    if(!next.equals(" ")) {
                        throw new FileFormatException("Присутствуют символы кроме разделительного пробела", i+2);
                    }
                }
                if(readSymbol == ReadSymbol.NUMBER){
                    readSymbol = ReadSymbol.SPACE;
                } else {
                    readSymbol = ReadSymbol.NUMBER;
                }
            }
            if(numberCount != nodeNumber){
                throw new FileFormatException("В строке не то количество чисел, которое ожидалось", i+2);
            }
        }
        if(scanner.hasNextLine()){
            throw new FileFormatException("В файле больше строк, чем ожидалось", nodeNumber+2);
        }
        scanner.close();
    }

    private void nodeMatrixToGraph(List<List<Integer>> nodeMatrix, Mediator mediator){
        for(int raw = 1; raw <= nodeMatrix.size(); raw++){
            for(int column = 1; column <= nodeMatrix.size(); column++){
                if(nodeMatrix.get(raw-1).get(column-1) != 0){
                    mediator.sendRequest(new ActionMessage(State.ADDEDGE, raw, column, nodeMatrix.get(raw-1).get(column-1)));
                }
            }
        }
    }

    private void nodeReader(int nodeNumber, double screenWidth, double screenHeight, Mediator mediator, List<List<Integer>> nodeMatrix){
        double radius = Double.min((screenHeight - 80)/2, (screenWidth - 80)/2);
        double centreY = screenHeight/2;
        double centreX = screenWidth/2;
        int layerNodeNumber = 40;
        if (nodeNumber < layerNodeNumber){
            layerNodeNumber = nodeNumber;
        }
        double degreeStep = Math.toRadians((double) 360/(layerNodeNumber));
        int layerNodeIterator = 0;
        for(int i = 1; i <= nodeNumber; i++){
            mediator.sendRequest(new ActionMessage(State.ADDNODE, centreX - Math.sin(degreeStep*layerNodeIterator)*radius,
                    centreY - Math.cos(degreeStep*layerNodeIterator)*radius));
            nodeMatrix.add(new ArrayList<>());
            layerNodeIterator++;
            if(layerNodeIterator == layerNodeNumber){
                layerNodeNumber -= 10;
                layerNodeIterator = 0;
                if (nodeNumber - i < layerNodeNumber){
                    layerNodeNumber = nodeNumber - i;
                }
                degreeStep =  Math.toRadians((double) 360/(layerNodeNumber));
                radius = radius - 60;

            }
        }
    }
}
