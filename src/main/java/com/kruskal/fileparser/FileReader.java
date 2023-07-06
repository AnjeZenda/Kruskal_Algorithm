import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

public class FileReader {

    public GraphBuilder read(String inputFileName, GraphBuilder graph){

        Path path = Paths.get(inputFileName);
        Scanner scanner;
        int nodeNumber;

        try{
            scanner = new Scanner(path);
        } catch (java.io.IOException e){
            System.out.println("File does not exits");
            return null;
        }

        if(scanner.hasNextInt()){
            nodeNumber = scanner.nextInt();
        } else{
            System.out.println("Wrong file format");
            return null;
        }

        for(int i = 1; i <= nodeNumber; i++){
            graph.addNode(i);
        }

        int edgeId = 1;
        for(int i = 1; i <= nodeNumber; i++){
            for(int j = 1; j <= nodeNumber; j++) {
                if (scanner.hasNextInt()) {
                    int weight = scanner.nextInt();
                    if(weight == 0){
                        continue;
                    }
                    if(graph.addEdge(i, j, weight, edgeId)){
                        edgeId++;
                    }
                } else {
                    System.out.println("Wrong file format");
                    return null;
                }
            }
        }
        return graph;
    }
}
