import java.util.Arrays;

public class Main
{
    static int iLoc=1;
    static int jLoc=1;
    
    public static void main(String[] args)
    {
        System.out.println("The initial array with the binary tree node values.");
        int[] nodeArray = new int[]{4,2,7,1,3,6,9,8};
        System.out.println(Arrays.toString(nodeArray)+"\n");
        
        System.out.println("The binary tree node values as a two dimensional array.");
        int[][] d2nodeArray = generateD2NodeArray(nodeArray);
        System.out.println(Arrays.deepToString(d2nodeArray)+"\n");
        
        System.out.println("The binary tree node objects as a two dimensional array.");
        TreeNode[][] binaryTree = generateBinaryTree(d2nodeArray);
        System.out.println(Arrays.deepToString(binaryTree)+"\n"); 
        
        System.out.println("The positions of the TreeNode objects of the binary tree.");
        TreeNode rootTreeNode = binaryTree[0][0];
        binaryTree = null;
        displayNodes(rootTreeNode);
        System.out.println();
        
        System.out.println("The positions of the TreeNode objects of the inverted binary tree.");
        new Solution().invertTree(rootTreeNode);
        displayNodes(rootTreeNode);
    }
    
    static TreeNode[][] generateBinaryTree(int[][] d2nodeArray)
    {
        int numLevels = d2nodeArray.length;
        TreeNode[][] d2treeNodeArray = new TreeNode[numLevels][];
        for(int i=numLevels-1; i==numLevels-1 && i>=0; i--)
        {
            TreeNode[] levelTreeNodeArray = new TreeNode[d2nodeArray[i].length];
            for(int j=0; j<d2nodeArray[i].length; j++)
            {
                levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j]);
            }
            d2treeNodeArray[i]=levelTreeNodeArray;
        }
        for(int i=numLevels-2; i>=0; i--)
        {
            TreeNode[] levelTreeNodeArray = new TreeNode[d2nodeArray[i].length];
            for(int j=0; j<d2nodeArray[i].length; j++)
            {
                if(2*j+1<d2nodeArray[i+1].length)
                {
                   levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],d2treeNodeArray[i+1][2*j],d2treeNodeArray[i+1][2*j+1]);
                }
                else if(2*j+1==d2nodeArray[i+1].length)
                {
                    levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],d2treeNodeArray[i+1][2*j],null);
                }
                else
                {
                    levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],null,null);
                }
            }
            d2treeNodeArray[i]=levelTreeNodeArray;
        }
        return d2treeNodeArray;
    }
    
    static int[][] generateD2NodeArray(int[] nodeArray)
    {
        int nodeArrayLength = nodeArray.length;
        int level=0;
        int levelNodeQty=0;
        int numNodes=0;
        if(numNodes<nodeArrayLength)
        {   
            level=1; levelNodeQty=1; numNodes=1; 
        }
        while(numNodes<nodeArrayLength)
        {
            level++;
            levelNodeQty*=2;
            numNodes+=levelNodeQty;
        }  
        int[][] D2NodeArray = new int[level][];
        level=0;
        levelNodeQty=0;
        numNodes=0;
        if(numNodes<nodeArrayLength)
        {   
            level=1; levelNodeQty=1; numNodes=1; 
            D2NodeArray[level-1] = subArray(nodeArray,numNodes-levelNodeQty,levelNodeQty);
        }
        while(numNodes<nodeArrayLength)
        {
            level++;
            levelNodeQty*=2;
            numNodes+=levelNodeQty;
            D2NodeArray[level-1] = subArray(nodeArray,numNodes-levelNodeQty,numNodes);
        }
        return D2NodeArray;
    }
    
    static int[] subArray(int[] array, int a, int b)
    {
        if(array!=null)
        {
            int lenArray=array.length;
            if(a>b){   throw new RuntimeException();   }
            if(a>lenArray) a=lenArray;
            if(b>lenArray) b=lenArray;
            int lenNewArray = b-a;
            int[] newArray= new int[lenNewArray];
            for(int i=a; i<b; i++)
            {
                newArray[i-a]=array[i];    
            }
            return newArray;
        }
        else 
        {
            return null;
        }
    }
    
    static void displayNodes(TreeNode node)
    {
        System.out.println("i: "+iLoc+" j: "+jLoc+" node: "+node);
        if(node.left!=null)
        {
            iLoc++; jLoc=(jLoc*2)-1;
            displayNodes(node.left);
            iLoc--; jLoc=((jLoc+1)/2);
        }
        if(node.right!=null)
        {
            iLoc++; jLoc=(jLoc*2);
            displayNodes(node.right);
            iLoc--; jLoc=(jLoc/2);
        }
    }
}


