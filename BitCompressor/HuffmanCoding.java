package Project;
import java.util.*;

import static javax.swing.UIManager.put;

public class HuffmanEncoder {
    HashMap<Character,String> encoder;
    HashMap<String,Character> decoder;

    private class Node implements Comparable<Node>{
        Character data;
        int cost;
        Node left;
        Node right;

        public Node(Character data,int cost){
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }
        @Override
        public int compareTo(Node other){
            return this.cost-other.cost;
        }
    }
    public HuffmanEncoder(String feeder) throws Exception{
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < feeder.length(); i++) {
                char cc = feeder.charAt(i);
                if(fmap.containsKey(cc)){
                    int ov = fmap.get(cc);
                    ov+=1;
                    fmap.put(cc,ov);
                }else fmap.put(cc, 1);
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        Set<Map.Entry<Character, Integer>> entrySet = fmap.entrySet();

        for (Map.Entry<Character, Integer> entry : entrySet){
            Node node = new Node(entry.getKey(),entry.getValue());
            minHeap.add(node);
        }
        while (minHeap.size()!=1){
            Node first = minHeap.remove();
            Node second = minHeap.remove();
            Node newNode = new Node('\0', first.cost+second.cost);
            newNode.left = first;
            newNode.right = second;

            minHeap.add(newNode);
        }
        Node ft = minHeap.remove();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();

        this.initEncoderDecoder(ft, "");
    }
    private void initEncoderDecoder(Node node,String osf){
        if(node==null) return;
        if(node.left==null && node.right==null){
            this.decoder.put(osf,node.data);
            this.encoder.put(node.data,osf);
        }
        initEncoderDecoder(node.left,osf+"0");
        initEncoderDecoder(node.right,osf+"1");
    }

    public String decoder(String source) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            sb.append(source.charAt(i));
            String key = sb.toString();

            if (decoder.containsKey(key)) {
                ans.append(decoder.get(key));
                sb.setLength(0);
            }
        }
        return ans.toString();
    }

    public String encoder(String source) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            sb.append(encoder.get(source.charAt(i)));
        }
        return sb.toString();
    }

        public static void main(String[] args) throws Exception {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your Message: ");
            String input = in.next();

            HuffmanEncoder hf = new HuffmanEncoder(input);

            String encoded = hf.encoder(input);
            System.out.println("Encoded: " + encoded);

            String decoded = hf.decoder(encoded);
            System.out.println("Decoded: " + decoded);
        }
}
