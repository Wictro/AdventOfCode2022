import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static Tree tree = new Tree();
    private static TreeNode currentNode = null;

    private static long currentSum = 0;

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //construct a tree with the directories and files
        //Recursively calculate the directories sizes and get their sums
        //skip if the size becomes 100000 or more
        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            if(currentLine.startsWith("$")){
                processCommand(currentLine);
            }
            else{
                processOutput(currentLine);
            }
        }

        setFileSizeSum(tree.root);

        System.out.println(currentSum);
    }

    private static void setFileSizeSum(TreeNode node) {
        for (TreeNode childNode : node.children){
            if (childNode instanceof DirectoryNode)
                setFileSizeSum(childNode);
            node.size += childNode.size;
        }
        if (node.size <= 100000){
            currentSum += node.size;
        }
    }

    private static void processOutput(String currentLine) {
        String[] tokens = currentLine.split(" ");

        if(tokens[0].equals("dir")){
            DirectoryNode dir = new DirectoryNode(tokens[1]);
            currentNode.addChild(dir);
        }
        else{
            FileNode file = new FileNode(tokens[1], Long.parseLong(tokens[0]));
            currentNode.addChild(file);
        }
    }

    private static void processCommand(String command) {
        String[] tokens = command.split(" ");

        if(tokens[1].equals("cd")){
            if(tokens[2].equals("/")){
                currentNode = tree.root;
            }
            else if (tokens[2].equals("..")){
                currentNode = currentNode.parent;
            }
            else{
                currentNode = currentNode.getChild(tokens[2]);
            }
        }
    }
}
