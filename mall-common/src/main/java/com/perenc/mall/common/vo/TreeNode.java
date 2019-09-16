package com.perenc.mall.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeNode
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/16 16:15 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/16     GR     		
 */
@Data
public class TreeNode {

    /**
     * @description: 当前节点ID
     * @author: GR
     * @date: 2019/9/16 16:15
     */
    protected int id;

    /**
     * @description: 父节点ID
     * @author: GR
     * @date: 2019/9/16 16:15
     */
    protected int parentId;

    /**
     * @description: 子节点列表
     * @author: GR
     * @date: 2019/9/16 16:16
     */
    List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * @description: 添加子节点
     * @author: GR
     * @date: 2019/9/16 16:18
     */
    public void add(TreeNode node) {
        children.add(node);
    }

}
