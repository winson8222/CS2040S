import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 * ScapeGoat Tree class
 * <p>
 * This class contains some basic code for implementing a ScapeGoat tree. This version does not include any of the
 * functionality for choosing which node to scapegoat. It includes only code for inserting a node, and the code for
 * rebuilding a subtree.
 */
import java.util.Random;
public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     * <p>
     * This class holds the data for a node in a binary tree.
     * <p>
     * Note: we have made things public here to facilitate problem set grading/testing. In general, making everything
     * public like this is a bad idea!
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        public int weight;

        public int leftWeight = 0;

        public int rightWeight = 0;

        TreeNode(int k) {
            this.key = k;
            this.weight = 1;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree.
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int helperCount(TreeNode node, Child child, int counter) {

        if (node.left == null && node.right != null) {

            return helperCount(node.right, child, counter + 1);
        } else if (node.right == null && node.left != null) {

            return helperCount(node.left, child, counter + 1);
        } else if (node.left == null) {

            return counter;
        } else {

            return counter + helperCount(node.left, child, 1)
                    + helperCount(node.right, child, 1);
        }
    }
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
        int end = nodeList.length - 1;
        int start = 0;
        TreeNode newtree;

        class Build {
            public TreeNode building(TreeNode[] tree, int start, int end) {
                if (start > end) {
                    return null;
                }

                int mid = (start + end) / 2;
                TreeNode root = nodeList[mid];

                root.left = building(nodeList, start, mid - 1);
                root.right = building(nodeList, mid + 1, end);
                return root;
            }
        }

        Build newbuild = new Build();
        newtree = newbuild.building(nodeList, start, end);

        return newtree;
    }

    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode node) {
        // TODO: Implement this
        if(node == null) {
            return true;
        }


        if(node.left != null & node.right != null){
            return node.weight * 2.0/3 >= node.left.weight & node.weight * 2.0/3 >= node.right.weight;
        }

        if(node.left != null) {
            return node.weight * 2.0/3 >= node.left.weight;
        }

        if (node.right != null) {
            return node.weight * 2.0/3 >= node.right.weight;
        }

        return true;
    }

    /**
     * Rebuilds the specified subtree of a node.
     *
     * @param node  the part of the subtree to rebuild
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

        class GetWeight {

            public void setWeight(TreeNode tree) {
                if(tree.left != null & tree.right != null){
                    setWeight(tree.left);
                    setWeight(tree.right);
                    tree.weight = 1 + tree.left.weight + tree.right.weight;
                } else if (tree.left != null) {
                    setWeight(tree.left);
                    tree.weight = 1 + tree.left.weight;
                } else if (tree.right != null) {
                    setWeight(tree.right);
                    tree.weight = 1 + tree.right.weight;
                } else {
                    tree.weight = 1;
                }
            }
        }

        GetWeight setweight = new GetWeight();
        setweight.setWeight(newChild);
    }
    /**
     * Inserts a key into the tree.
     *
     * @param key the key to insert
     */

    public void insert(int key) {
        if(root == null) {
            root = new TreeNode(key);
            return;
        }

        int rootweight = root.weight;
        int maxheight = rootweight + 1;
        TreeNode[] path = new TreeNode[maxheight];

        TreeNode node = root;
        int sequence = 0;

        while (true) {
            path[sequence] = node;
            if (key <= node.key) {
                if (node.left == null) break;
                node.weight++;
                node = node.left;
                sequence++;
            } else {
                if (node.right == null) break;
                node.weight++;
                node = node.right;
                sequence++;
            }
        }


        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
        node.weight++;

        int tobalanceparent = -1;
        int tobalancechild = -1;

        for(int i = sequence; i >= 1; i-- ) {
            if(!checkBalance(path[i])) {
                tobalanceparent = i - 1;
                tobalancechild = i;
            }
        }


        if(tobalanceparent != -1) {
            if(path[tobalanceparent].left == path[tobalancechild]) {
                rebuild(path[tobalanceparent], Child.LEFT);
            } else {
                rebuild(path[tobalanceparent], Child.RIGHT);
            }
        }
    }




    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            tree.insert(random.nextInt(100));
        }
        tree.rebuild(tree.root, Child.RIGHT);
        //tree.rebuild(tree.root, Child.LEFT);
        System.out.println(tree.checkBalance(tree.root));
    }
}