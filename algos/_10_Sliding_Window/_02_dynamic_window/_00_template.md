## Templte for most of sliding window pattern problems:

```java
public int fucn(int[] nums, int some_conditional_integer) {
        int left_ptr=0,right_ptr=0;
        int some_window_attribute=0;
        while(right_ptr<nums.length){
            while(some_condition){
                //may be some updation of window_attributes(like sum,product etc)
                left_ptr++;
            }
            //updation of answer like length/count, for most problems its like right_ptr-left_ptr+1
            right_ptr++;
        }
        return cnt;
    }
```