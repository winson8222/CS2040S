import com.sun.source.tree.Tree;

/**
 * ScapeGoat Tree class
 *
 * This class contains some of the basic code for implementing a ScapeGoat tree.
 * This version does not include any of the functionality for choosing which node
 * to scapegoat.  It includes only code for inserting a node, and the code for rebuilding
 * a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this
//        class Counting{
//
//            TreeNode node;
//
//            Counting(TreeNode node){
//                this.node = node;
//            }
//            int counting(TreeNode node){
//                if(node.left != null & node.right != null) {
//                    return 2 + counting(node.left) + counting(node.right);
//                }
//                if (node.left != null){
//                    return 1 + counting(node.left);
//                }
//
//                if(node.right != null){
//                    return 1 + counting(node.right);
//                }
//                return 0;
//            }
//
//            int getCount(){
//                return counting(this.node);
//            }
//        }
//
//
//        Counting counting;
//        if(child == Child.LEFT & node.left != null){
//            counting = new Counting(node.left);
//            return 1 + counting.getCount();
//        }
//
//        if(child == Child.RIGHT & node.right != null){
//            counting = new Counting(node.right);
//            return 1 + counting.getCount();
//        }
//
//        return 0;
        if(child == Child.LEFT & node.left != null){
            return 1 + countNodes(node.left, Child.RIGHT) + countNodes(node.left, Child.LEFT);
        }
        if(child == Child.RIGHT & node.right != null){
            return 1 + countNodes(node.right, Child.RIGHT) + countNodes(node.right, Child.LEFT);
        }

        return 0;

    }

    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        int length = countNodes(node, child);

        class GetNodes{
            TreeNode[] nodearray;
            int count;

            public GetNodes(int len){
                this.nodearray = new TreeNode[len];
                this.count = 0;
            }

            public void traverseTree(TreeNode node){
                if(node.left != null){
                    traverseTree(node.left);
                }

                    this.nodearray[this.count] = node;
                    this.count++;

                if(node.right != null){
                    traverseTree(node.right);
                }
            }
        }

        if(child == Child.LEFT & node.left != null){
            GetNodes leftnode = new GetNodes(length);
            leftnode.traverseTree(node.left);
            return leftnode.nodearray;
        }
        if(child == Child.RIGHT & node.right != null){
            GetNodes rightnode = new GetNodes(length);
            rightnode.traverseTree(node.right);
            return rightnode.nodearray;
        }
        return new TreeNode[0];


    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this
        class Building{
            TreeNode[] nodeList;

            public Building(TreeNode[] nodeList){
                this.nodeList = nodeList;
            }
            public TreeNode build(int start, int end){
                System.out.println(start);
                System.out.println(end);
                if(start == end){
                    return this.nodeList[start];
                }
                if(end - start <= 1){
                    this.nodeList[end].left = this.nodeList[start];
                    return this.nodeList[end];
                }

                if((start+end)%2 == 0){
                    this.nodeList[(end + start)/2].left = build(start, (end + start)/2 - 1);
                    this.nodeList[(end + start)/2].right = build((end + start)/2 + 1, end);
                    return this.nodeList[(end + start)/2];
                } else {
                    this.nodeList[(end+start)/2].left = build(start, (end + start)/2 - 1);
                    this.nodeList[(end + start)/2].right = build((end + start)/2 + 1, end);
                }
                return this.nodeList[(end + start)/2];


            }
        }


        for(int i = 0; i < nodeList.length; i ++) {
            nodeList[i].left = null;
            nodeList[i].right = null;
        }

        Building buildlist = new Building(nodeList);
        TreeNode newroot = buildlist.build(0, nodeList.length - 1);
        root = newroot;
        return newroot;
    }

    /**
     * Rebuilds the specified subtree of a node
     *
     * @param node the part of the subtree to rebuild
     * @param child specifies which child is the root of the subtree to rebuild
     */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
    }

    /**
     * Inserts a key into the tree
     *
     * @param key the key to insert
     */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node = node.left;
            } else {
                if (node.right == null) break;
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }


    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root, Child.RIGHT);
        System.out.println(tree.root.right.left.right.key);
//        TreeNode a = new TreeNode(1);
//        TreeNode b = new TreeNode(2);
//        TreeNode c = new TreeNode(3);
//        TreeNode d = new TreeNode(4);
//        TreeNode e = new TreeNode(5);
//        TreeNode f = new TreeNode(6);
//        TreeNode g = new TreeNode(7);
//        TreeNode h = new TreeNode(8);
//        TreeNode[] nodelist = new TreeNode[8];
//        for(int i = 0; i < 8; i ++){
//            nodelist[i] = new TreeNode(i);
//        }

//        System.out.println(tree.buildTree(nodelist).right.key);
    }
}
