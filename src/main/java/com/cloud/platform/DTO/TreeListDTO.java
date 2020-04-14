package com.cloud.platform.DTO;

import com.cloud.platform.model.Leaf;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeListDTO<T> {
    private String label;
    private List<T> children;

    TreeListDTO(String label,List<T> children){
        this.label = label;
        this.children = children;
    }

    public static List<Object> getAffiliationTree(){
        List<Object> treeListDTOS = new ArrayList<>();
        List<Object> children = new ArrayList<>();
        List<Object> children1 = new ArrayList<>();
        children1.add(new Leaf("苏州河闸加油站"));
        children1.add(new Leaf("苏州南桥加油站"));
        children1.add(new Leaf("苏州百花加油站"));
        children1.add(new Leaf("苏州南麻加油站"));
        children1.add(new Leaf("苏州常熟天马加油站"));
        children1.add(new Leaf("苏州张家港西张加油站"));
        children.add(new TreeListDTO("苏州", children1));
        children.add(new Leaf("仓储分公司"));
        TreeListDTO root = new TreeListDTO("分公司",children);
        treeListDTOS.add(root);
        return treeListDTOS;
    }

    public static List<Object> getDeviceClassificationTree() {
        List<Object> treeListDTOS = new ArrayList<>();
        List<Object> children = new ArrayList<>();
        List<Object> children1 = new ArrayList<>();
        List<Object> children1_1 = new ArrayList<>();
        children1_1.add(new Leaf("加油机"));
        children1_1.add(new Leaf("油气回收设备"));
        children1_1.add(new Leaf("多用途箱柜"));
        children1_1.add(new Leaf("防撞柱杆"));
        children1.add(new TreeListDTO("加油区设施", children1_1));
        List<Object> children1_2 = new ArrayList<>();
        children1_2.add(new Leaf("便利店电气设备"));
        children1_2.add(new Leaf("便利店其他设备"));
        children1.add(new TreeListDTO("便利店设施", children1_2));
        children.add(new TreeListDTO("配套服务设施",children1));
        treeListDTOS.add(new TreeListDTO("设备分类",children));
        return treeListDTOS;
    }
}
