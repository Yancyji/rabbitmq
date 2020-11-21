package com.example.producer.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TreeBean implements Serializable {

    private static final long serialVersionUID = 1L;


    /*id和pid必须赋值，otherParameter装一些其他参数，比如name，key-value键值对的形式*/
    /*将你从数据库查出来的集合循环，然后对每一行数据进行赋值转换成TreeBean结构，调用TreeUtils工具即可得到树形结构列表*/
    private Long id;//id

    private String pid;//pid

    //private Map<String, Object> otherParameter;//其他参数，需要展示的参数,比如name之类的

    private Integer strichenMedical ; //重疾医疗风险

    private String classification; //职业名称

    private String classificationnumber; //职业编码

    private Integer accident; //意外风险

    private Integer classtype;

    /*---------------------------------下面这些不需要你赋值-------------------------------------------*/
    private Boolean hasChildren;//是否含有下一级

    private List<TreeBean> children;//包含的子节点

    private Long childrenSize;//子节点的集合大小

    private Integer level;//级别

}
