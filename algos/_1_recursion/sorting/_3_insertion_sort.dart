//imagine arranging cards lowest at bottom and largest at top, when u encounter a smaller than bottom, u bring it front{arr[j+1]=arr[j]}


void main(List<String> args) {
  List<int>li=[-1,23,1,0,90,23,12,1];

  for (int i = 1; i < li.length; i++) {
    int key=li[i];
    int j=i-1;
    while(j>=0&&li[j]>key){
      li[j+1]=li[j];
      j--;
    }
    li[j+1]=key;
  }
  print(li);
}