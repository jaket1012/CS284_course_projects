package pa3;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class StevensMaps {
    public static List<Location> parseFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int n = Integer.parseInt(scanner.nextLine().trim());
            ArrayList<Location> locations = new ArrayList<>(n);
            if (n <= 0) return null;
            for (int i = 0; i < n; i++) {
                if (!scanner.hasNext()) return null;
                String name = scanner.nextLine().trim();
                Location loc = new Location(name);
                locations.add(loc);
            }
            int m = Integer.parseInt(scanner.nextLine().trim());
            if (m < 0) return null;
            for (int j = 0; j < m; j++) {
                String[] parts = scanner.nextLine().trim().split(" ");
                if (parts.length < 3) return null;
                int start_num = Integer.parseInt(parts[0]);
                int end_num = Integer.parseInt(parts[1]);
                double length = Double.parseDouble(parts[2]);
                if (start_num < 0 || end_num < 0 || start_num >= n || end_num >= n){
                    return null;
                }
                Connection new_con = new Connection(locations.get(start_num), locations.get(end_num), length);
                locations.get(start_num).addConnection(new_con);
                locations.get(end_num).addConnection(new_con.reverse());
            }
            return locations;
        }
        catch (Exception e){
            return null;
        }
    }

    public static Path shortestPath(Location start, Location end) {
        PriorityQueue<Path> frontier = new PriorityQueue<>(new Path.PathComparator());
        Set<Location> visited = new HashSet<>();
        Path start_path = new Path(start);
        frontier.add(start_path);
        while (!frontier.isEmpty()){
            Path current = frontier.poll();
            if (current.getEnd() == end){
                return current;
            }
            if (visited.contains(current.getEnd())){
                continue;
            }
            visited.add(current.getEnd());
            for (Connection connection : current.getEnd().getOutgoingConnections()){
                if (!visited.contains(connection.getEnd())){
                    frontier.add(current.extend(connection));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
       try{
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            String file_name = scanner.nextLine().trim();
            File file_to_read = new File(file_name);
            List<Location> locs = parseFile(file_to_read);
            if (locs == null){
                System.out.println("Error: Could not parse file.");
                return;
            }
            int t = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < t; i++){
                Location start = null;
                Location end = null;

                String start_name = scanner.nextLine().trim();
                String end_name = scanner.nextLine().trim();
                for (Location loc : locs) {
                    if (loc.getName().equals(start_name)) {
                        start = loc;
                    }
                    if (loc.getName().equals(end_name)){
                        end = loc;
                    }
                }
                if (start == null || end == null){
                    System.out.println("Error: no path from " + start_name + " to " + end_name + "!");
                    continue;
                }
                Path path = shortestPath(start, end);

                if (path == null){
                    System.out.println("There is no path from " + start_name + " to " + end_name + "!");
                    continue;
                }

                System.out.printf("Distance from %s to %s: %f%n", start_name, end_name, path.getLength());
                System.out.println("Locations Along Path:");
                for (Location loc : path) {
                    System.out.println("    " + loc.getName());
                }
            }
       }
       catch (FileNotFoundException e){
            System.out.println("Error: Task file not found.");
       }
    }
}
