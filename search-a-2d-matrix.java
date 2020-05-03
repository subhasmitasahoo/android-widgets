//Problem link: https://leetcode.com/problems/search-a-2d-matrix/
//Time complexity: O(m+n)
//Space complexity: O(1)

//Note: Can be done in O(logm) + O(logn), by first finding the appropriate row by using BS and then doing the same for column, or viceversa.
//Too lazy to implement it, will do someday!

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rlen = matrix.length;
        if(rlen == 0) {
            return false;
        }
        int clen = matrix[0].length;
        if(clen == 0) {
            return false;
        }
        
        int i = 0, j = clen-1;
        
        while(true) {
            if(i>=rlen || j<0) {
                break;
            }
            if(matrix[i][j] == target) {
                return true;
            }
            if(matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        
        return false;
    }
}
