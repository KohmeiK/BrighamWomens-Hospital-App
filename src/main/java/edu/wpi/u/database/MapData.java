package edu.wpi.u.database;

import edu.wpi.u.algorithms.Node;
import edu.wpi.u.models.GraphManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class MapData {
    private static Connection conn = null;
    private static ResultSet rset;
    private final static String url = "jdbc:derby:BWdb";

    public static void connect() {
        try {
            conn = DriverManager.getConnection(url);
            //conn.setAutoCommit(false);
            //conn.commit();
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public static int addNode(
            String node_id,
            int x,
            int y,
            int floor,
            String building,
            String node_type,
            String longname,
            String shortname) {
        try {
            String str =
                    "insert into Nodes (nodeID, xcoord, ycoord, floor, building, nodeType, longname, shortname, teamAssigned) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, node_id);
            ps.setInt(2, x);
            ps.setInt(3, y);
            ps.setInt(4, floor);
            ps.setString(5, building);
            ps.setString(6, node_type);
            ps.setString(7, longname);
            ps.setString(8, shortname);
            ps.setString(9, "u");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add node");
            return 0;
        }
        return 1;
    }
    public static int updCoords(String node_id, int new_x, int new_y) {
        try {
            String str = "update Nodes set xcoord=?, ycoord=? where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1, new_x);
            ps.setInt(2, new_y);
            ps.setString(3,node_id);
            //    ps.setString(2, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update coordinates");
            return 0;
        }
        return 1;
    }

    public static int updFloor(String node_id, int new_floor_number) {
        try {
            String str = "update Nodes set floor=? where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1, new_floor_number);
            ps.setString(2, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update floor #");
            return 0;
        }
        return 1;
    }

    public static int updBuilding(String node_id, String new_building) {
        try {
            String str = "update Nodes set building=? where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, new_building);
            ps.setString(2, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update building");
            return 0;
        }
        return 1;
    }

    public static int updLongname(String node_id, String new_longname) {
        try {
            String str = "update Nodes set longname=? where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, new_longname);
            ps.setString(2, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update longname");
            return 0;
        }
        return 1;
    }

    public static int updShortname(String node_id, String new_shortname) {
        try {
            String str = "update Nodes set shortname=? where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, new_shortname);
            ps.setString(2, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update coordinates");
            return 0;
        }
        return 1;
    }

    public static int delNode(String node_id) {
        try {
            String str = "delete from Nodes where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete node");
            return 0;
        }
        return 1;
    }

    public static int delNodeCoord(int x, int y) {
        try {
            String str = "delete from Nodes where xcoord=? and ycoord=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete node");
            return 0;
        }
        System.out.println("Successful delete");
        return 1;
    }

    public static int addEdge(String edge_id, String start_node_id, String end_node_id) {
        try {
            String str = "insert into Edges (edgeId, startID, endID) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, edge_id);
            ps.setString(2, start_node_id);
            ps.setString(3, end_node_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add edge");
            return 0;
        }
        return 1;
    }

    public static int updEdgeStart(String edge_id, String new_start_node_id) {
        try {
            String str = "update Edges set startID=? where edgeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, new_start_node_id);
            ps.setString(2, edge_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update start ID");
            return 0;
        }
        return 1;
    }

    public static int updEdgeEnd(String edge_id, String new_end_node_id) {
        try {
            String str = "update Edges set endID=? where edgeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, new_end_node_id);
            ps.setString(2, edge_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update end ID");
            return 0;
        }
        return 1;
    }

    public static int delEdge(String edge_id) {
        try {
            String str = "delete from Edges where edgeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, edge_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update start ID");
            return 0;
        }
        return 1;
    }

    public static int delEdgeByNodes(String start_node_id, String end_node_id){
        try {
            String str = "delete from Edges where startID=?, endID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, start_node_id);
            ps.setString(2, end_node_id);
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static void loadGraph(GraphManager gm){
        try{
            Statement ps = conn.createStatement();
            String str = "select * from Nodes";
            rset = ps.executeQuery(str);
            while (rset.next()) {
                String id = rset.getString("nodeID");
                int x = rset.getInt("xcoord");
                int y = rset.getInt("ycoord");
                int floor = rset.getInt("floor");
                String building = rset.getString("building");
                String nodeType = rset.getString("nodeType");
                String longName = rset.getString("longName");
                String shortName = rset.getString("shortName");
                gm.makeNode(id,x,y,floor,building,nodeType,longName,shortName,"u");
            }
            String str2 = "select * from Edges";
            PreparedStatement ps2 = conn.prepareStatement(str2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                String id = rs2.getString("edgeID");
                String start = rs2.getString("startID");
                String end = rs2.getString("endID");
                gm.makeEdge(id,start,end);
            }
            rs2.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load graph");
        }
    }

    public static boolean isNode(String node_id) {
        try {
            String str = "select nodeID from Nodes where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, node_id);
            rset = ps.executeQuery();
            return rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEdge(String edge_id) {
        try {
            String str = "select edgeID from Edges where edgeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, edge_id);
            rset = ps.executeQuery();
            return rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Node getNode(String node_id){
        try {
            String str = "select * from Nodes where nodeID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            Node n = new Node(rs.getString("nodeId"), rs.getInt("xcoord"), rs.getInt("ycoord"), rs.getInt("floor"), rs.getString("building"), rs.getString("node_type"), rs.getString("longname"), rs.getString("shortname"), "u");
            rs.close();
            return n;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveNodesCSV() {
        try {
            String str = "SELECT * FROM Nodes";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(str);
            BufferedWriter fileWriter =
                    new BufferedWriter(new FileWriter("src/main/resources/edu/wpi/u/OutsideMapNodes.csv"));

            fileWriter.write(
                    "nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned");

            while (result.next()) {
                String nodeID = result.getString("nodeID");
                int xcoord = result.getInt("xcoord");
                int ycoord = result.getInt("ycoord");
                int floor = result.getInt("floor");
                String building = result.getString("building");
                String nodeType = result.getString("nodeType");
                String longName = result.getString("longName");
                String shortName = result.getString("shortName");
                String teamAssigned = result.getString("teamAssigned");

                String line =
                        String.format(
                                "%s,%d,%d,%d,%s,%s,%s,%s,%s",
                                nodeID,
                                xcoord,
                                ycoord,
                                floor,
                                building,
                                nodeType,
                                longName,
                                shortName,
                                teamAssigned);

                fileWriter.newLine();
                fileWriter.write(line);
            }
            rset.close();
            statement.close();
            fileWriter.close();
        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    public static void saveEdgesCSV() {
        try {
            String str = "SELECT * FROM Edges";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(str);
            BufferedWriter fileWriter =
                    new BufferedWriter(new FileWriter("src/main/resources/edu/wpi/u/OutsideMapEdges.csv"));

            fileWriter.write("edgeID, startID, endID");

            while (result.next()) {
                String edgeID = result.getString("edgeID");
                String startID = result.getString("startID");
                String endID = result.getString("endID");
                String line = String.format("%s,%s,%s", edgeID, startID, endID);
                fileWriter.newLine();
                fileWriter.write(line);
            }
            statement.close();
            fileWriter.close();
        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
}
