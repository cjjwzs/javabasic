package collection;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 16:02
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RetainAllDemo {
    public static void main(String[] args) {
        List<String> listA = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        List<String> listB = Arrays.asList("B", "D", "E");

        // listA 调用 retainAll，只保留 listB 中也存在的元素
        boolean isChanged = listA.retainAll(listB);

        System.out.println("listA 是否被修改: " + isChanged); // 输出: true
        System.out.println("交集结果 (listA): " + listA);     // 输出: [B, D]
        // 注意：listA 被原地修改了，原本的 "A" 和 "C" 已经丢失
    }
}