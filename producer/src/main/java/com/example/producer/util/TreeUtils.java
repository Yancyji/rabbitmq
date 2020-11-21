package com.example.producer.util;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TreeUtils {
    /**
     * 使用递归方法建树
     * @param treeBeans 按规则转换的树形实体集合列表，没有层次结构
     * @param topId 指定顶级节点id，必须指定
     * @param topTreeBean 指定顶级节点构造的树形实体，可以传入空值，但是传入空值会将顶级节点的数据去除，返回指定顶级节点的子节点树形结构集合
     * @return 树形结构实体集合
     */
    public static List<TreeBean> buildByRecursive(List<TreeBean> treeBeans, Long topId ,TreeBean topTreeBean) {
        List<TreeBean> trees = new ArrayList<>();
        TreeBean treeBeanTop = null;
        for (TreeBean treeBean : treeBeans) {
            if (topId.equals(treeBean.getPid())) {
                Integer level = 1;
                if(!ObjectUtils.isEmpty(topTreeBean) && topId.equals(topTreeBean.getId())){
                    level = 2;
                }
                TreeBean treeBean2 = findChildren(treeBean,treeBeans,level);
                if(CollectionUtils.isEmpty(treeBean2.getChildren())){
                    treeBean2.setHasChildren(false);
                    treeBean2.setLevel(level);
                }else {
                    treeBean2.setHasChildren(true);
                    treeBean2.setLevel(level);
                    treeBean2.setChildrenSize((long) treeBean2.getChildren().size());
                }
                trees.add(treeBean2);
            }else if(!ObjectUtils.isEmpty(topTreeBean) && topId.equals(treeBean.getId())){
                treeBeanTop = treeBean;
            }
        }
        if(!ObjectUtils.isEmpty(treeBeanTop)){
            treeBeanTop.setChildren(trees)
                    .setChildrenSize((long) trees.size())
                    .setHasChildren(true)
                    .setLevel(1);
            trees = new ArrayList<>();
            trees.add(treeBeanTop);
        }
        return trees;
    }


    /**
     * 递归查找子节点
     * @param
     * @return
     */
    private static TreeBean findChildren(TreeBean treeBean, List<TreeBean> treeBeans, Integer level) {
        for (TreeBean it : treeBeans) {
            if(treeBean.getId().equals(it.getPid())) {
                if (treeBean.getChildren() == null) {
                    treeBean.setChildren(new ArrayList<>());
                }
                TreeBean treeBean2 = findChildren(it,treeBeans,level+1);
                if(!CollectionUtils.isEmpty(treeBean2.getChildren())){
                    treeBean2.setHasChildren(true);
                    treeBean2.setChildrenSize((long) treeBean.getChildren().size());
                    treeBean2.setLevel(level+1);
                }else if(treeBean2.getChildren() == null || treeBean2.getChildren().size() == 0){
                    treeBean2.setHasChildren(false);
                    treeBean2.setLevel(level+1);
                }
                treeBean.getChildren().add(treeBean2);
            }
        }
        return treeBean;
    }


}
