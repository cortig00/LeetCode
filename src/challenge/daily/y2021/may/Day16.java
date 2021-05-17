package challenge.daily.y2021.may;

import java.util.HashSet;
import java.util.Set;

/**
 * 16/05/2021 - UNSOLVED - https://leetcode.com/problems/binary-tree-cameras/
 * 
 * Given a binary tree, we install cameras on the nodes of the tree.
 * 
 * Each camera at a node can monitor its parent, itself, and its immediate
 * children.
 * 
 * Calculate the minimum number of cameras needed to monitor all nodes of the
 * tree.
 * 
 * 
 * Input: [0,0,null,0,0] Output: 1 Explanation: One camera is enough to monitor
 * all nodes if placed as shown.
 * 
 * Input: [0,0,null,0,null,0,null,null,0] Output: 2 Explanation: At least two
 * cameras are needed to monitor all nodes of the tree. The above image shows
 * one of the valid configurations of camera placement.
 * 
 *
 */
public class Day16 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/*
	 * 
	 * Approach 1: Dynamic Programming
	 * 
	 * Intuition
	 * 
	 * Let's try to cover every node, starting from the top of the tree and working
	 * down. Every node considered must be covered by a camera at that node or some
	 * neighbor.
	 * 
	 * Because cameras only care about local state, we can hope to leverage this
	 * fact for an efficient solution. Specifically, when deciding to place a camera
	 * at a node, we might have placed cameras to cover some subset of this node,
	 * its left child, and its right child already.
	 * 
	 * Algorithm
	 * 
	 * Let solve(node) be some information about how many cameras it takes to cover
	 * the subtree at this node in various states. There are essentially 3 states:
	 * 
	 * [State 0] Strict subtree: All the nodes below this node are covered, but not
	 * this node. [State 1] Normal subtree: All the nodes below and including this
	 * node are covered, but there is no camera here. [State 2] Placed camera: All
	 * the nodes below and including this node are covered, and there is a camera
	 * here (which may cover nodes above this node).
	 * 
	 * Once we frame the problem in this way, the answer falls out:
	 * 
	 * To cover a strict subtree, the children of this node must be in state 1. To
	 * cover a normal subtree without placing a camera here, the children of this
	 * node must be in states 1 or 2, and at least one of those children must be in
	 * state 2. To cover the subtree when placing a camera here, the children can be
	 * in any state.
	 * 
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(N)O(N)O(N), where NNN is the number of nodes in the given
	 * tree.
	 * 
	 * Space Complexity: O(H)O(H)O(H), where HHH is the height of the given tree.
	 * 
	 */
	class SolutionDP {
	    public int minCameraCover(TreeNode root) {
	        int[] ans = solve(root);
	        return Math.min(ans[1], ans[2]);
	    }

	    // 0: Strict ST; All nodes below this are covered, but not this one
	    // 1: Normal ST; All nodes below and incl this are covered - no camera
	    // 2: Placed camera; All nodes below this are covered, plus camera here
	    public int[] solve(TreeNode node) {
	        if (node == null)
	            return new int[]{0, 0, 99999};

	        int[] L = solve(node.left);
	        int[] R = solve(node.right);
	        int mL12 = Math.min(L[1], L[2]);
	        int mR12 = Math.min(R[1], R[2]);

	        int d0 = L[1] + R[1];
	        int d1 = Math.min(L[2] + mR12, R[2] + mL12);
	        int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);
	        return new int[]{d0, d1, d2};
	    }
	}

	/*
	 * 
	 * Approach 2: Greedy
	 * 
	 * Intuition
	 * 
	 * Instead of trying to cover every node from the top down, let's try to cover
	 * it from the bottom up - considering placing a camera with the deepest nodes
	 * first, and working our way up the tree.
	 * 
	 * If a node has its children covered and has a parent, then it is strictly
	 * better to place the camera at this node's parent.
	 * 
	 * Algorithm
	 * 
	 * If a node has children that are not covered by a camera, then we must place a
	 * camera here. Additionally, if a node has no parent and it is not covered, we
	 * must place a camera here.
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(N)O(N)O(N), where NNN is the number of nodes in the given
	 * tree.
	 * 
	 * Space Complexity: O(H)O(H)O(H), where HHH is the height of the given tree.
	 */
	

	class SolutionGreedy {
	    int ans;
	    Set<TreeNode> covered;
	    public int minCameraCover(TreeNode root) {
	        ans = 0;
	        covered = new HashSet();
	        covered.add(null);

	        dfs(root, null);
	        return ans;
	    }

	    public void dfs(TreeNode node, TreeNode par) {
	        if (node != null) {
	            dfs(node.left, node);
	            dfs(node.right, node);

	            if (par == null && !covered.contains(node) ||
	                    !covered.contains(node.left) ||
	                    !covered.contains(node.right)) {
	                ans++;
	                covered.add(node);
	                covered.add(par);
	                covered.add(node.left);
	                covered.add(node.right);
	            }
	        }
	    }
	}
	
	
	
	
	
	
	
	
}
