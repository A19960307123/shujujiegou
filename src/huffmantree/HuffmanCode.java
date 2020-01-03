package huffmantree;



import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HuffmanCode
 * @Description 实现赫夫曼编码
 * 这种压缩方式有一定的问题，如果最后的字节上是0开头，则会有数据丢失，最后导致解压失败
 * 下面的例子就是解压出的二进制字符串和压缩的二进制字符串不相同
 * 解决办法：1.可以把最后一个字节占用的位数保存，然后输出，还原的时候就不会出现数据丢失
 * @Date 2019/12/31 15:49
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String srcFile = "D://常用//头像.jpg";
//        String destFile = "D://常用//头像.zip";
//        zipFile(srcFile, destFile);
        String srcFile = "D://科研//谐振腔//yuanzhu//14s//AA.txt";
        String destFile = "D://科研//谐振腔//yuanzhu//14s//AA.zip";
        zipFile(srcFile, destFile);
/*        String begin = "i like like like java do you like a java";
        byte[] beginBytes = begin.getBytes();
        HuffmanZipResource zipResource = new HuffmanZipResource();
        HuffmanNode huffmanTree = createHuffmanTree(beginBytes);//创建赫夫曼树
        zipResource.setHuffmanCodeMap(huffmanCode(huffmanTree));
        zip(beginBytes, zipResource);
        System.out.println(new String(decode(zipResource)));*/
    }


    /**
     * 生成赫夫曼树对应的赫夫曼编码表
     * @param root 放入赫夫曼树的根结点
     * @return 对应的赫夫曼编码表
     */
    private static Map<Byte,String> huffmanCode(HuffmanNode root){
        Map<Byte,String> huffmanCodeMap =new HashMap<>();
        StringBuilder stringBuilder =new StringBuilder();
        if (root !=null){
            huffmanCode(root, stringBuilder, "",huffmanCodeMap);
        }
        return huffmanCodeMap;
    }

    /**
     * 生成赫夫曼编码表，放入huffmanCodeMap中
     * @param node 要处理的结点
     * @param stringBuilder1 用于拼接路径
     * @param mark 上一步的路径，"0"代表往左子结点，"1"代表往右子结点，
     */
     private static void huffmanCode(HuffmanNode node,StringBuilder stringBuilder1,String mark,Map<Byte,String> huffmanMap){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1);
        stringBuilder2.append(mark);
        if (node.data!=null){//如果node是一个叶子结点
            huffmanMap.put(node.data, stringBuilder2.toString());
        }else {
            huffmanCode(node.left, stringBuilder2, "0",huffmanMap);
            huffmanCode(node.right, stringBuilder2, "1",huffmanMap);
        }
    }

    /**
     * 创建对应的Huffman树
     * @param arr 接收字节数组
     * @return Huffman树
     */
    private static HuffmanNode createHuffmanTree(byte[] arr){
        Map<Byte, Integer> integerMap = new HashMap<>();
        for (byte key : arr){
            integerMap.merge(key, 1, Integer::sum);
        }
        PriorityQueue<HuffmanNode> huffmanNodes = new PriorityQueue<>();
        integerMap.forEach((k,v)->huffmanNodes.add(new HuffmanNode(k,v)));
        while (huffmanNodes.size()>1){
            HuffmanNode left =huffmanNodes.poll();
            HuffmanNode right = huffmanNodes.poll();
            HuffmanNode parent = new HuffmanNode(left.weight + right.weight);
            parent.left =left;
            parent.right=right;
            huffmanNodes.add(parent);
        }
        return huffmanNodes.poll();
    }

    /**
     * 将字符串对应的byte[]数组通过赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     * byte[] ->(通过赫夫曼编码表) 一个二进制的字符串 ->（8位一组，将二进制字符串转化为一个byte[]数组） byte[]
     * 因为使用了赫夫曼编码压缩，最后byte[] 数组的长度降低了
     *
     * @param bytes 要压缩的字符串对应的byte[]数组
     * @return 赫夫曼编码压缩后的byte[]数组
     */
    private static void zip(byte[] bytes, HuffmanZipResource hz){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes){
            stringBuilder.append(hz.getHuffmanCodeMap().get(b));
        }
        //将"101110110010010110...."转化为byte[]数组
        int len ;//最后返回的byte[]的长度
        if (stringBuilder.length()%8==0){
            len =stringBuilder.length()/8;
        }else {
            len =stringBuilder.length()/8+1;
        }
        byte[] huffumanCodeBytes = new byte[len];
        int index =0;//用于记录huffmanCodeBytes
        String strByte;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            if (i+8 > stringBuilder.length()){
                strByte =stringBuilder.substring(i);
                hz.setLastNum(stringBuilder.length()-i);
            }else {
                strByte =stringBuilder.substring(i, i+8);
            }
            huffumanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        hz.setHuffmanZip(huffumanCodeBytes);
    }

    /**
     * 将一个byte转换为一个二进制的字符串
     * @param flag 是否需要补高位，true代表要补
     * @param b 传入的byte
     * @return 该b对应的二进制的字符串（按补码形式返回）
     */
    private static String byteToBitString(boolean flag,byte b, int k){
        int temp =b;//将b转化为int
        temp |= 256;//如果是正数，需要补高位，用100000000来补
        String str = Integer.toBinaryString(temp);//返回temp对应的二进制的补码
        if (flag){
            return str.substring(str.length()-8);
        }else {
            return str.substring(str.length()-k);
        }
    }

    /**
     * 使用赫夫曼编码压缩字节数组
     * @param begin 要压缩的byte[]数组
     * @return
     */
    private static HuffmanZipResource huffmanZip(byte[] begin){
        HuffmanZipResource zipResource = new HuffmanZipResource();
        HuffmanNode huffmanTree = createHuffmanTree(begin);//创建赫夫曼树
        zipResource.setHuffmanCodeMap(huffmanCode(huffmanTree));
        zip(begin, zipResource);//使用赫夫曼编码压缩文件，写入到zipResource内
        return zipResource;
    }

    /**
     * 完成赫夫曼解码
     * @return 解码得到的字节数组
     */
    private static byte[] decode(HuffmanZipResource hz){
        StringBuilder stringBuilder = new StringBuilder();
        //将字节数组转化成二进制字符串
        byte[] huffmanBytes =hz.getHuffmanZip();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag, b,hz.getLastNum()));
        }
        //将字符串在指定的赫夫曼编码进行解码
        Map<String,Byte> map = new HashMap<>();
        ArrayList<Byte> bytes = new ArrayList<>();
        hz.getHuffmanCodeMap().forEach((k,v)->map.put(v,k));
        for (int i = 0; i < stringBuilder.length(); ) {
            int count=1;
            while (i < stringBuilder.length()){
                String key = stringBuilder.substring(i, i+count);
                if (map.containsKey(key)){
                    i+=count;
                    bytes.add(map.get(key));
                    break;
                }
                count++;
            }
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }


    /**
     * 将文件进行压缩
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param destFile 希望压缩后将压缩文件放在哪里的全路径
     */
    public static void zipFile(String srcFile , String destFile){
        FileInputStream fs = null;
        OutputStream os = null;
        ObjectOutputStream oos =null;
        try {
            fs = new FileInputStream(srcFile);
            byte[] b = new byte[fs.available()];
            fs.read(b);//读取文件
            HuffmanZipResource huffmanZipResource = huffmanZip(b);
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanZipResource);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fs!=null)
                    fs.close();
                if (os!=null)
                    os.close();
                if (oos!=null)
                    oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 将文件进行解压
     * @param srcFile 准备解压的文件
     * @param destFile 要解压的路径
     */
    public static void unZipFile(String srcFile , String destFile){
        InputStream is =null;
        ObjectInputStream ois =null;
        OutputStream  os= null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            HuffmanZipResource huffmanZipResource = (HuffmanZipResource) ois.readObject();
            byte[] decode = decode(huffmanZipResource);
            os = new FileOutputStream(destFile);
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        try {
            if (os != null)
                os.close();
            if (is != null)
                is.close();
            if (ois != null)
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

/**
 * 赫夫曼树
 */
class HuffmanNode implements Comparable<HuffmanNode>{
    Byte data;
    int weight;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public HuffmanNode(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    public void preOrder(){
        System.out.println(this);
        if (left != null)
            left.preOrder();
        if (right!=null)
            right.preOrder();
    }
}

/**
 * 赫夫曼编码后的资源文件，存储了赫夫曼编码表，赫夫曼编码后的byte[]数组，以及最后一个字节的长度
 */
class HuffmanZipResource implements Serializable{
    private static final long serialVersionUID = 6538153385604229624L;
    private Map<Byte,String> huffmanCodeMap;//对应的赫夫曼编码表
    private int lastNum;//最后一个字节的长度（防止数据丢失）
    private byte[] huffmanZip;//赫夫曼编码后的byte数组（已压缩）

    public Map<Byte, String> getHuffmanCodeMap() {
        return huffmanCodeMap;
    }

    public void setHuffmanCodeMap(Map<Byte, String> huffmanCodeMap) {
        this.huffmanCodeMap = huffmanCodeMap;
    }

    public int getLastNum() {
        return lastNum;
    }

    public void setLastNum(int lastNum) {
        this.lastNum = lastNum;
    }

    public byte[] getHuffmanZip() {
        return huffmanZip;
    }

    public void setHuffmanZip(byte[] huffmanZip) {
        this.huffmanZip = huffmanZip;
    }
}