package IC09B;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
   This program demonstrates stacks, queues, and priority queues.
*/
public class StackQueueDemo
{
   public static void main(String[] args)
   {
      System.out.println("Stack: ");
      Stack<String> s = new Stack<String>();
      s.push("Tom");
      s.push("Diana");
      s.push("Harry");
      while (s.size() > 0) { System.out.println(s.pop()); }

      System.out.println("Queue: ");
      Queue<String> q = new LinkedList<String>();
      q.add("Tom");
      q.add("Diana");
      q.add("Harry");
      while (q.size() > 0) { System.out.println(q.remove()); }

      System.out.println("Priority Queue: ");
      PriorityQueue<String> pq = new PriorityQueue<String>();
      pq.add("Tom");
      pq.add("Diana");
      pq.add("Harry");
      while (pq.size() > 0) { System.out.println(pq.remove()); }
   }
}
