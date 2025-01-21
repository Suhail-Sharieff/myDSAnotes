package _7_Graph._02_Dir_Unweigh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//application of KahnAlgo as described in _12_topoSort
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */

public class _03_course_schedule {

    public static void main(String[] args) {
        int numCourses=2;
        int course_preReq[][]={// each pair has course and its prerequeiste
            {1,0}
        };

        var course_we_can_complete_as_per_given_order_to_follow=courses_we_can_complete_topologically(numCourses, course_preReq);

        System.out.println(course_we_can_complete_as_per_given_order_to_follow);

        boolean canComplete_all_courses=course_we_can_complete_as_per_given_order_to_follow.size()==numCourses;

        System.out.println("Can we complete all course: "+canComplete_all_courses);
        
    }
    //the algorith is as same as that of kahn's algo, with almost no modifications
    public static List<Integer> courses_we_can_complete_topologically(int nCourses,int course_preReq[][]){
        int in_degree[]=new int[nCourses];

        for(int pair[]:course_preReq){
            int course_we_want_to_do=pair[0];
            // int preReq_for_that_course=pair[1];
            in_degree[course_we_want_to_do]++;
        }

        List<Integer>completed_courses=new ArrayList<>();

        Queue<Integer>q=new LinkedList<>();
        //add all courses (complte them) which doent have any prerequisite, ie has in_degree as 0
        for(int course_number=0;course_number<nCourses;course_number++) if(in_degree[course_number]==0) q.offer(course_number);

        //start complteting them from no preqreq to next
        while (!q.isEmpty()) {
            int just_completed_course=q.poll();
            completed_courses.add(just_completed_course);

            //now go through all course that has the course (that we just now complted) as prerequisite
            for(int pair[]:course_preReq){
                int course_we_want_to_do=pair[0];
                int preReq_for_that_course=pair[1];
                if(preReq_for_that_course==just_completed_course){
                    in_degree[course_we_want_to_do]--;//we have complted the 1 preq of that couse(just complted)

                    // the nuber of preeq for the couse we wanted to do , decrememnt its indgree, check if has no more indgree,if yes then put that also in queue, so that we can check if that also is prereq for someone else
                    if(in_degree[course_we_want_to_do]==0) q.offer(course_we_want_to_do);
                }
            }
        }

        return completed_courses;
    }
    
}
