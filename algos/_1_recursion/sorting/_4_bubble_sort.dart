void main(List<String> args) {
  var li = List.of([1, -23, 34, 12, 1, 0, 9, 34, 1]);
  for (var i = 0; i < li.length; i++) {
    for (var j = i + 1; j < li.length; j++) {
      if (li[j] < li[i]) {
        int temp = li[i];
        li[i] = li[j];
        li[j] = temp;
      }
    }
  }
  print(li);
}
