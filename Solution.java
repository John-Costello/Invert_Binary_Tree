class Solution
{
    public TreeNode invertTree(TreeNode root)
    {
        TreeNode node = root;
        if(node.left!=null)
        {
            //iLoc++; jLoc=(jLoc*2)-1;
            invertTree(node.left);
            //iLoc--; jLoc=((jLoc+1)/2);
        }
        if(node.right!=null)
        {
            //iLoc++; jLoc=(jLoc*2);
            invertTree(node.right);
            //iLoc--; jLoc=(jLoc/2);
        }
        TreeNode tempNode=node.left;
        node.left = node.right;
        node.right = tempNode;
        return node;
    }
}
