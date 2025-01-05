import 'dart:collection';

void main() {
  print("Bismillah");
  List<List<int>>image=[[1,1,1],[1,1,0],[1,0,1]];
  int sr=1,sc=1,color=2;
  print(floodFill(image, sr, sc, color));
}


List<List<int>> floodFill(List<List<int>> image, int sr, int sc, int color) {
  if(image[sr][sc]==color) return image;
    Queue<List<int>>q=Queue();
    q.add([sr,sc]);
    int origColor=image[sr][sc];
    image[sr][sc]=color;
    List dirs=[[-1,0],[1,0],[0,1],[0,-1]];
    while(!q.isEmpty){
      List top=q.removeFirst();
      int top_x=top[0];
      int top_y=top[1];
      for(List<int> e in dirs){
        int x=top_x+e[0];
        int y=top_y+e[1];
        if(x>=0 && x<image.length && y>=0 && y<image[0].length){
          if(image[x][y]==origColor){
            image[x][y]=color;
            q.add([x,y]);
          }
        }
      }
    }
    return image;

  }