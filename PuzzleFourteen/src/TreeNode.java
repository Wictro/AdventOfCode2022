import java.util.List;

public abstract class TreeNode {
    public long size;
    public TreeNode parent;
    public List<TreeNode> children;
    public String name;

    public TreeNode(String name){
        this.name = name;
    }

    public void addChild(TreeNode node){
        node.parent = this;
        this.children.add(node);
    }

    public List<TreeNode> getChildren(){
        return this.children;
    }

    public TreeNode getChild(String name){
        return children.stream().filter(child -> child.name.equals(name)).findFirst().get();
    }
}
