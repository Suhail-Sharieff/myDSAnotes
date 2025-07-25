package _12_Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _05_max_number_of_events_that_can_be_attended {
    /*
     * N meetings in one room
     * Difficulty: EasyAccuracy: 45.3%Submissions: 318K+Points: 2Average Time: 20m
     * You are given timings of n meetings in the form of (start[i], end[i]) where
     * start[i] is the start time of meeting i and end[i] is the finish time of
     * meeting i. Return the maximum number of meetings that can be accommodated in
     * a single meeting room, when only one meeting can be held in the meeting room
     * at a particular time.
     * 
     * Note: The start time of one chosen meeting can't be equal to the end time of
     * the other chosen meeting.
     * 
     * Examples :
     * 
     * Input: start[] = [1, 3, 0, 5, 8, 5], end[] = [2, 4, 6, 7, 9, 9]
     * Output: 4
     * Explanation: Maximum four meetings can be held with given start and end
     * timings. The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
     * Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
     * Output: 1
     * Explanation: Only one meetings can be held with given start and end timings.
     * Input: start[] = [1, 2], end[] = [100, 99]
     * Output: 1
     * Constraints:
     * 1 ≤ n ≤ 105
     * 0 ≤ start[i] < end[i] ≤ 106
     */

    // inutuion: we want to maximize the number of meetings, so obviously we will
    // try to complete those meetings that we can end as early as possible, so sort
    // array as per ending time, iterate though each by also keepng track of how
    // much time is elapsed
    public int maxMeetings(int start[], int end[]) {
        Event[] arr = new Event[start.length];
        for (int i = 0; i < start.length; i++)
            arr[i] = new Event(start[i], end[i], i);
        Arrays.sort(arr, (x, y) -> {
            if (x.end != y.end)
                return x.end - y.end;
            return x.start - y.start;
        });
        int time_elapsed = -1;// Mistake: took this as 0, but contraint start from 0
        int cnt = 0;
        // for printting order of meetings as well
        List<Integer> meetings_order = new ArrayList<>();
        for (Event ev : arr) {
            int starts_on = ev.start, ends_on = ev.end;
            if (time_elapsed < starts_on) {
                meetings_order.add(ev.pos);
                time_elapsed = ends_on;
                cnt++;
            }
        }
        System.out.println("For max number of meetings follow order:" + meetings_order);
        return cnt;

    }

    static class Event {
        int start;
        int end;
        int pos;

        public Event(int s, int e, int idx) {
            start = s;
            end = e;
            pos = idx;// for printing purpose
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    // -----------------------------FOLLOW UP:
    /*
     * /*
     * 1353. Maximum Number of Events That Can Be Attended
     * Attempted
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given an array of events where events[i] = [startDayi, endDayi].
     * Every event i starts at startDayi and ends at endDayi.
     * 
     * You can attend an event i at any day d where startTimei <= d <= endTimei. You
     * can only attend one event at any time d.
     * 
     * Return the maximum number of events you can attend.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: events = [[1,2],[2,3],[3,4]]
     * Output: 3
     * Explanation: You can attend all the three events.
     * By attending an event doesn't necessarily means attending for the whole given
     * interval.
     * Instead it means that one can attend the event on one of the given days in
     * interval
     * in TC: events= [[1,2],[2,3],[3,4],[1,2]], i can attend
     * [1,2] on Day 1,
     * [1,2] on Day 2, since the event has not ended and would only end on
     * completion of Day 2
     * [2,3] on Day 3
     * [3,4] on Day 4
     * Hence Output = 4
     * One way to attend them all is as shown.
     * Attend the first event on day 1.
     * Attend the second event on day 2.
     * Attend the third event on day 3.
     * Example 2:
     * 
     * Input: events= [[1,2],[2,3],[3,4],[1,2]]
     * Output: 4
     * 
     * 
     * Constraints:
     * 
     * 1 <= events.length <= 105
     * events[i].length == 2
     * 1 <= startDayi <= endDayi <= 105
     * 
     */

    // key differnece between prev and curr poblem:
    /*
     * Difference Between the Two Problems
     * Both problems involve scheduling events/meetings with a greedy approach, but
     * the key difference is how they handle conflicts and availability.
     * 
     * 1. Maximum Number of Events That Can Be Attended (Leetcode 1353)
     * You can attend only one event per day.
     * You can attend an event on any day within its given range
     * [
     * 𝑠
     * 𝑡
     * 𝑎
     * 𝑟
     * 𝑡
     * ,
     * 𝑒
     * 𝑛
     * 𝑑
     * ]
     * [start,end].
     * Key constraint: You can attend at most one event per day.
     * Approach:
     * Sort events by start time.
     * Use a min-heap to track event end times.
     * Iterate day by day, always choosing the event that ends the earliest.
     * Example
     * python
     * Copy
     * Edit
     * events = [[1, 2], [2, 3], [3, 4]]
     * 📌 You can attend:
     * 
     * Event 1 on day 1
     * Event 2 on day 2
     * Event 3 on day 3
     * ✅ Output: 3
     * 
     * 2. Maximum Number of Meetings in One Room
     * Each meeting must be scheduled completely (from start to end) in a single
     * slot.
     * No two meetings can overlap, and meeting start time ≠ previous meeting's end
     * time.
     * Key constraint: If a meeting ends at time
     * 𝑡
     * t, the next meeting must start strictly after
     * 𝑡
     * t.
     * Approach:
     * Sort meetings by end time.
     * Select the meeting that ends the earliest to maximize room availability.
     * Example
     * python
     * Copy
     * Edit
     * start = [1, 3, 0, 5, 8, 5]
     * end = [2, 4, 6, 7, 9, 9]
     * 📌 You can schedule:
     * 
     * Meeting (1,2)
     * Meeting (3,4)
     * Meeting (5,7)
     * Meeting (8,9)
     * ✅ Output: 4
     */
    public int maxEvents(int[][] events) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, d = 0, n = events.length;
        while (!pq.isEmpty() || i < n) {
            if (pq.isEmpty())
                d = events[i][0];
            while (i < n && events[i][0] <= d)
                pq.offer(events[i++][1]);
            pq.poll();
            ++res;
            ++d;
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
        }
        return res;
    }

    // -----------------------follow up:------Asked by Gooogle
    /*
     * 2402. Meeting Rooms III
     * Solved
     * Hard
     * Topics
     * premium lock icon
     * Companies
     * Hint
     * You are given an integer n. There are n rooms numbered from 0 to n - 1.
     * 
     * You are given a 2D integer array meetings where meetings[i] = [starti, endi]
     * means that a meeting will be held during the half-closed time interval
     * [starti, endi). All the values of starti are unique.
     * 
     * Meetings are allocated to rooms in the following manner:
     * 
     * Each meeting will take place in the unused room with the lowest number.
     * If there are no available rooms, the meeting will be delayed until a room
     * becomes free. The delayed meeting should have the same duration as the
     * original meeting.
     * When a room becomes unused, meetings that have an earlier original start time
     * should be given the room.
     * Return the number of the room that held the most meetings. If there are
     * multiple rooms, return the room with the lowest number.
     * 
     * A half-closed interval [a, b) is the interval between a and b including a and
     * not including b.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
     * Output: 0
     * Explanation:
     * - At time 0, both rooms are not being used. The first meeting starts in room
     * 0.
     * - At time 1, only room 1 is not being used. The second meeting starts in room
     * 1.
     * - At time 2, both rooms are being used. The third meeting is delayed.
     * - At time 3, both rooms are being used. The fourth meeting is delayed.
     * - At time 5, the meeting in room 1 finishes. The third meeting starts in room
     * 1 for the time period [5,10).
     * - At time 10, the meetings in both rooms finish. The fourth meeting starts in
     * room 0 for the time period [10,11).
     * Both rooms 0 and 1 held 2 meetings, so we return 0.
     * Example 2:
     * 
     * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
     * Output: 1
     * Explanation:
     * - At time 1, all three rooms are not being used. The first meeting starts in
     * room 0.
     * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in
     * room 1.
     * - At time 3, only room 2 is not being used. The third meeting starts in room
     * 2.
     * - At time 4, all three rooms are being used. The fourth meeting is delayed.
     * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in
     * room 2 for the time period [5,10).
     * - At time 6, all three rooms are being used. The fifth meeting is delayed.
     * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts
     * in room 1 for the time period [10,12).
     * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return
     * 1.
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 100
     * 1 <= meetings.length <= 105
     * meetings[i].length == 2
     * 0 <= starti < endi <= 5 * 105
     * All the values of starti are unique.
     */
    // https://www.youtube.com/watch?v=8JTr7Hk0p2o&ab_channel=codestorywithMIK
    static class Solution {
        public int mostBooked(int n, int[][] meetings) {
            Arrays.sort(meetings, (x, y) -> x[0] - y[0]);
            PriorityQueue<Integer> free = new PriorityQueue<>();// roomNumber
            for (int i = 0; i < n; i++)
                free.offer(i);//put all free rooms
            PriorityQueue<long[]> busy = new PriorityQueue<>((x, y) -> {// endTime,rommNumber
                return (x[0] == y[0]) ? Long.compare(x[1], y[1]) : Long.compare(x[0], y[0]);
            });
            int cnt[] = new int[n];
            for (var m : meetings) {
                int s = m[0], e = m[1];
                // i will check if its possible to assign this meeting any free room
                // i will pop all rooms whose end times r less than the start of this meeting
                while (!busy.isEmpty() && busy.peek()[0] <= s)
                    free.offer((int) busy.poll()[1]);
                if (free.isEmpty()) {// if no rroms free
                    // then, i will pop the fastest ending meeting, add delayed meeting to busy
                    long top[] = busy.poll();
                    busy.offer(new long[] { top[0] + e - s, top[1] });//the next end time must be added with delay ie e-s,------HEART OF PROBLEM
                    cnt[(int) top[1]]++;
                } else {// at least 1 room is free
                    busy.offer(new long[] { e, free.peek() });
                    cnt[free.poll()]++;
                }
            }
            int ans = 0;
            for (int i = 1; i < n; i++)
                if (cnt[i] > cnt[ans])
                    ans = i;
            return ans;
        }
    }
}
