package digui;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname MiGong
 * @Description TODO
 * @Date 2019/12/23 11:03
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = -1;
            map[7][i] = -1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = -1;
            map[i][6] = -1;
        }
        map[3][1] = -1;
        map[3][2] = -1;
        setWay(map, 1, 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 功能描述:走迷宫
     *
     * @param i   起始横坐标
     * @param j   起始纵坐标
     * @param map 地图
     * @param k   代表第几步
     * @return: boolean 如果找到了通路，就返回true
     * @auther: mada
     * @date: 2019/12/23 11:08
     */
    public static boolean setWay(int[][] map, int i, int j, int k) {
        if (map[6][5] != 0) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                map[i][j] = k++;
                if (setWay(map, i + 1, j, k)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1, k)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j, k)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1, ++k)) {//向左走
                    return true;
                } else {
                    map[i][j] = -2;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
