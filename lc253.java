class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals.length;
        }
        
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));
        
        pq.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            
            pq.add(intervals[i][1]);
        }
        
        return pq.size();
    }
}
