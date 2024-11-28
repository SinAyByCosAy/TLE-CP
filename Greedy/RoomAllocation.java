//https://cses.fi/problemset/task/1164
package DPBootcamp.Greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class RoomAllocation {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        Person[] person = new Person[n];
        for(int i = 0; i < n; i++){
            person[i] = new Person(fr.nextLong(), fr.nextLong(), i); //new person details
        }
        Arrays.sort(person, (a, b) -> Long.compare(a.arrival, b.arrival)); //sorting based on arrival time
        long roomCount = 0;
        TreeSet<CurrentBookings> bookings = new TreeSet<>(); //set maintaining order on departure time
        long[] result = new long[n];
        for(int i = 0; i < n; i++){
            if(bookings.isEmpty() || bookings.first().departure >= person[i].arrival){ //we don't have any room available
                roomCount++; //need new room
                bookings.add(new CurrentBookings(person[i].departure, roomCount)); //add booking details
                result[person[i].index] = roomCount; //store the room no
            }else{//we found a vacant room
                long roomNo = bookings.pollFirst().roomNo;
                bookings.add(new CurrentBookings(person[i].departure, roomNo));
                result[person[i].index] = roomNo;
            }
        }
        out.println(roomCount);
        StringBuilder rooms = new StringBuilder();
        for(long ele : result)
            rooms.append(ele + " ");
        out.println(rooms);
        out.flush();
    }
}
class CurrentBookings implements Comparable<CurrentBookings>{
    long departure, roomNo;
    CurrentBookings(long departure, long roomNo){
        this.departure = departure;
        this.roomNo = roomNo;
    }
    @Override
    public int compareTo(CurrentBookings booking){//custom comparator for class objects
        int comparison = Long.compare(this.departure, booking.departure);
        return comparison != 0 ? comparison : Long.compare(this.roomNo, booking.roomNo);
    }
}
class Person{
    long arrival, departure;
    int index;
    Person(long arrival, long departure, int index){
        this.arrival = arrival;
        this.departure = departure;
        this.index = index;
    }
}
//TC: O(NlogN)