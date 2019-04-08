/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendy.orders;

/**
 *
 * @author PRISTON
 */

import java.util.ArrayList;
import java.util.List;



//main class that ties in everything and demonstrates the full functionality of the order combining algorithm.

public class Order implements Comparable<Order>{
    public boolean isPICKUP;
    public boolean isDROPOFF;
    private String orderNo;
    public int index;
    private double pickUpLat;
    private double pickUpLon;
    private double dropOffLat;
    private double dropOffLon;
    private double orderDistance;
    
     


    public Order(double pickUpLat, double pickUpLon, double dropOffLat, double dropOffLon){
        this.pickUpLat = pickUpLat;
        this.pickUpLon = pickUpLon;
        this.dropOffLat = dropOffLat;
        this.dropOffLon = dropOffLon;
    }

    public Order(Order ord){
        this.pickUpLat = ord.pickUpLat;
        this.pickUpLon = ord.pickUpLon;
        this.dropOffLat = ord.dropOffLat;
        this.dropOffLon = ord.dropOffLon;
        this.orderNo = ord.orderNo;
    }

    public double getOrderDistance() {
        return orderDistance;
    }

    public double calculateOrderDistance(){
        DistanceCalculator dst = new DistanceCalculator();
        return dst.distance(this.pickUpLat, this.pickUpLon, this.dropOffLat,this.dropOffLon,"K");
    }

    public double getDropOffLat() {
        return dropOffLat;
    }

    public void setDropOffLat(double dropOffLat) {
        this.dropOffLat = dropOffLat;
    }

    public double getDropOffLon() {
        return dropOffLon;
    }

    public double getPickUpLat() {
        return pickUpLat;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getPickUpLon() {
        return pickUpLon;
    }

    public void setDropOffLon(double dropOffLon) {
        this.dropOffLon = dropOffLon;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setPickUpLat(double pickUpLat) {
        this.pickUpLat = pickUpLat;
    }

    public void setPickUpLon(double pickUpLon) {
        this.pickUpLon = pickUpLon;
    }

    public void setOrderDistance(double orderDistance) {
        this.orderDistance = orderDistance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Order No = ").append(orderNo).append("\n");

        builder.append("is PICKUP = ").append(isPICKUP).append(" is DROPOFF = ").append(isDROPOFF).append(" \n");
//        builder.append("Distance =").append(orderDistance).append("\n");
        return builder.toString();
    }

    public int compareTo(Order o) {
        if (this.getOrderNo()==o.getOrderNo())
            return 0;
        return 1;
    }




    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Order> orders = new ArrayList<Order>();
        List <Order> orOrder = new ArrayList<Order>();

        // Umoja to KNH
        Order order1 = new Order(0.6333308, 37.6333308,-1.3010129,36.8071939);
        order1.setOrderNo("1");
        order1.setOrderDistance(order1.calculateOrderDistance());

        //green house to Westgate Mall
        Order order2 = new Order(-1.300433,36.7816695,-1.25694 ,36.80333);
        order2.setOrderNo("2");
        order2.setOrderDistance(order2.calculateOrderDistance());

        //Thika to Hilton
        Order order3 = new Order(-1.0499998 ,37.083333,-1.2853884,36.824507);
        order3.setOrderNo("3");
        order3.setOrderDistance(order3.calculateOrderDistance());

        // Umoja to Machakos

        Order order4 = new Order(0.6333308, 37.6333308,-1.5166646 ,37.2666656);
        order4.setOrderNo("4");
        order4.setOrderDistance(order4.calculateOrderDistance());

        //Mathare to prestige
        
        Order order5 = new Order(-1.255998976 ,36.857163238,-1.2942147,36.7817814);
        order5.setOrderNo("5");
        order5.setOrderDistance(order5.calculateOrderDistance());
        
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        double orderDistanceScaled = 0;
        for (Order od : orders){
            orderDistanceScaled += od.getOrderDistance();
        }
        double traversalWeight = traverseGRaph(orders,orOrder);

        System.out.printf("Raw distance: %s, Combined distance %s %n", orderDistanceScaled,traversalWeight);
        System.out.printf("Can be combined: %s%n", orderDistanceScaled>traversalWeight ? true : false);
        System.out.println(orOrder);




    }




    public static double traverseGRaph(List<Order> orders,List<Order> result){
        //create undirected pick up graph
        Order[] values = new Order[orders.size()*2];
        for (int i=0; i < orders.size(); i++) {
            Order drp = new Order(orders.get(i));
            drp.index = i+1;
            drp.isPICKUP = true;
            values[i] = drp;
        }

        for (int i = orders.size();i < orders.size()*2; i++) {
            Order drp = new Order(orders.get(i%orders.size()));
            drp.index = i+1;
            drp.isDROPOFF = true;
            values[i] = drp;

        }


        Graph<Order> G = new Graph<Order>(values.length, values);


        for (int i=0; i < orders.size(); i++) {
            Order drp = new Order(orders.get(i));
            drp.index = i+1;
            drp.isPICKUP = true;
            values[i] = drp;
        }

        for (int i = 0; i<values.length; i++){
            for (int j =0; j<values.length; j++){
                if (i!=j){
                    if (values[i].isPICKUP && values[j].isPICKUP){
                        Order temp = new Order(values[i].getPickUpLat(),values[i].getPickUpLon(),
                                values[j].getPickUpLat(),values[j].getPickUpLon());
                        temp.setOrderDistance(temp.calculateOrderDistance());
                        G.addEdge(i+1,j+1,temp.getOrderDistance());
                    }else if (values[i].isPICKUP && values[j].isDROPOFF){
                        Order temp = new Order(values[i].getPickUpLat(),values[i].getPickUpLon(),
                                values[j].getDropOffLat(),values[j].getDropOffLon());
                        temp.setOrderDistance(temp.calculateOrderDistance());
                        G.addEdge(i+1,j+1,temp.getOrderDistance());
                    }else if (values[i].isDROPOFF && values[j].isDROPOFF){
                        Order temp = new Order(values[i].getDropOffLat(),values[i].getDropOffLon(),
                                values[j].getDropOffLat(),values[j].getDropOffLon());
                        temp.setOrderDistance(temp.calculateOrderDistance());
                        G.addEdge(i+1,j+1,temp.getOrderDistance());
                    }
                    else if (values[i].isDROPOFF && values[j].isPICKUP){
                        Order temp = new Order(values[i].getDropOffLat(),values[i].getDropOffLon(),
                                values[j].getPickUpLat(),values[j].getPickUpLon());
                        temp.setOrderDistance(temp.calculateOrderDistance());
                        G.addEdge(i+1,j+1,temp.getOrderDistance());
                    }

                }
            }
        }

        System.out.println(G.vertices().size());
        System.out.println(G.noOfEdges());
        Vertex<Order> start = G.vertices().get(2);

        start.setVisited(true);
        result.add(start.getValue());
        while (true) {
            try {
                start = findNextUnvisted(G, start,result);
                result.add(start.getValue());
            } catch (IllegalArgumentException e) {
                break;
            }
        }
        return G.deltaWeight;

    }


    public  static Vertex<Order> findNextUnvisted (Graph<Order> g,Vertex<Order> start,List<Order> result){
        Vertex<Order> next;
        while (!g.vertices().get(start.getValue().index).sortedAdjacencyList().isEmpty()){
            next = g.vertices().get(start.getValue().index).sortedAdjacencyList().peek().to();
            if (!next.isVisited() && next.getValue().isPICKUP){
//                System.out.printf("%nFound a pick up, go ahead, order no:%s %d%n", next.getValue().orderNo,next.getValue().index);
                next.setVisited(true);
                g.deltaWeight += g.vertices().get(start.getValue().index).sortedAdjacencyList().peek().weight();
                return next;
            }else if (!next.isVisited() && next.getValue().isDROPOFF){
//                System.out.printf("%nFound drop off close by. Order no: %s index %d Has the orders been picked up? ",next.getValue().orderNo,next.getValue().index);
                for (Order res : result){
                    if (res.orderNo == next.getValue().orderNo){
//                        System.out.printf("%s%n",res.isPICKUP ? "yes":"no");
                        next.setVisited(true);
                        g.deltaWeight += g.vertices().get(start.getValue().index).sortedAdjacencyList().peek().weight();
                        return next;
                    }
                }

            }
            g.vertices().get(start.getValue().index).sortedAdjacencyList().poll();
        }
        throw new IllegalArgumentException("All nodes have been visited");

    }
}

