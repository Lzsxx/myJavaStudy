package sword_offer;

//请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
// 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
// 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e 矩阵中
// 包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行
// 第二个格子之后，路径不能再次进入该格子。

// 思路：回溯法

public class s65_route_in_rectangle {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //创建一个布尔数组
        boolean visited[]= new boolean[matrix.length];
        // 开始不断判断，如果里面为false，会继续尝试其他
        for(int i = 0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(hasPathCore(matrix,i,j,0,rows,cols,str,visited)){    // k表示当前要判断的字符
                    return true;
                }
            }
        }
        return false;
    }
    // rows和cols表示矩阵的行数和列数
    // i,j表示当前在矩阵中要判断的位置
    public boolean hasPathCore(char[] matrix,int i,int j,int k,int rows,int cols,char[] str,boolean[] visited){
        // 对于给定行数和列数得到其在矩阵中的下标
        int index = i*cols+j;
        //判断合法性
        if(i<0||i>=rows||j<0||j>=cols||matrix[index]!=str[k]||visited[index]){
            return false;
        }
        visited[index] = true;
        // 如果递归到最后一个位置的字符，则表明前面位置的字符都在矩阵中找到了对应的位置
        if (k == str.length - 1)
            return true;
        if(hasPathCore(matrix,i-1,j,k+1,rows,cols,str,visited)  //向左走
                ||hasPathCore(matrix,i,j-1,k+1,rows,cols,str,visited)   // 向上走
                ||hasPathCore(matrix,i+1,j,k+1,rows,cols,str,visited)   // 向右走
                ||hasPathCore(matrix,i,j+1,k+1,rows,cols,str,visited)){ //向下走
            return true;
        }else{
            //如果相邻格子的字符都没有匹配到下一个字符，则需要回到前一个格子，从而需要把位置的状态重新设定为未访问
            k--;
            visited[index] = false;
        }
        return false;
    }

    // 临场发挥版

    public int index = 0;   // 待匹配的字符
    public boolean findFlag = false;
    public boolean hasPath2(char[] matrix, int rows, int cols, char[] str)
    {
        char[][] myMat = new char[rows][cols];
        boolean[][] visit = new boolean[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                myMat[i][j] = matrix[k++];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                findPath(myMat, rows, cols, str, i, j, visit);
                if (index >= str.length) {
                    return true;
                }
            }
        }
        return false;
    }

    public void findPath(char[][] matrix, int rows, int cols, char[] str, int currRow, int currCol, boolean[][] visit) {
        if (currRow >= rows || currCol >= cols || findFlag) {
            return;
        }
        if (matrix[currRow][currCol] == str[index]) {
            visit[currRow][currCol] = true;
            index ++;
            if (index >= str.length) {
                // 匹配完了，返回
                findFlag = true;
                return;
            }
            if ( currRow + 1 < rows && !visit[currRow + 1][currCol]){
                findPath(matrix, rows, cols, str, currRow + 1, currCol, visit);
            }
            if (currRow - 1 >= 0 && !visit[currRow - 1][currCol]){
                findPath(matrix, rows, cols, str, currRow - 1, currCol, visit);
            }
            if (currCol + 1 < cols && !visit[currRow][currCol + 1]){
                findPath(matrix, rows, cols, str, currRow, currCol + 1, visit);
            }
            if (currCol - 1 >= 0 && !visit[currRow][currCol - 1]){
                findPath(matrix, rows, cols, str, currRow, currCol - 1, visit);
            }
            // 如果走到了这里还没有退出，那么这一格就是要被放弃的，后面会进行回退
            if (!findFlag) {   // 没有匹配完才需要进行这些回溯处理
                visit[currRow][currCol] = false;
                index--;
            }
        }
    }
}
