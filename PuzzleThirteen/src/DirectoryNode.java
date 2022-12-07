import java.util.ArrayList;

public class DirectoryNode extends TreeNode{
    public DirectoryNode(String name) {
        super(name);
        this.children = new ArrayList<>();
    }
}
