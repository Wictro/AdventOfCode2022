

public class FileNode extends TreeNode{

    public FileNode(String name) {
        super(name);
    }

    public FileNode(String name, long size){
        super(name);
        this.size = size;
        this.children = null;
    }
}
