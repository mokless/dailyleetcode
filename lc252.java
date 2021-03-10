class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        
        for (int[] interval : intervals) {
            pq.add(interval);
        }
        
        while (!pq.isEmpty()) {
            int[] interval = pq.poll();
            if (pq.peek() == null) {
                break;
            }
            if (interval[1] > pq.peek()[0]) {
                return false;
            }
        }
        
        return true;
    }
}
