package com.ruoyi.common.utils.tree;



import com.ruoyi.common.utils.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树状生成工厂
 *
 * @author dengn
 */
public class TreeFactory {

    /**
     * @param list          过滤集合
     * @param rootValue     根目录值
     * @param rootFieldName 根目录属性名
     * @return
     */
    public static <T> List<T> getRootList(List<T> list, final Object rootValue, String rootFieldName) {
        return list.stream().filter(t -> rootValue.equals(ReflectUtils.invokeGetter(t, rootFieldName) == null ? "" : ReflectUtils.invokeGetter(t, rootFieldName))).collect(Collectors.toList());
    }

    ;

    /**
     * 构建树
     *
     * @param list               树数据
     * @param root               根节点（树形实例）
     * @param keyFieldName       关联属性
     * @param parentKeyFieldName 关联父属性
     * @param subFieldName       子节点数据
     * @param <T>                根节点
     */
    public static <T> void createTree(List<T> list, T root, String keyFieldName, String parentKeyFieldName, String subFieldName) {
        //获取关联属性名
        Field keyField = ReflectUtils.getAccessibleField(root, keyFieldName);
        //获取关联父属性名
        Field parentKeyField = ReflectUtils.getAccessibleField(root, parentKeyFieldName);
        //子节点属性名
        Field subField = ReflectUtils.getAccessibleField(root, subFieldName);
        find(list, root, keyField, parentKeyField, subField);
    }


    /**
     * 递归子节点
     *
     * @param list           树数据
     * @param parent         根节点（树形实例）
     * @param keyField       关联属性
     * @param parentKeyField 关联父属性
     * @param subField       子节点数据
     * @param <T>
     */
    private static <T> void find(List<T> list, T parent, Field keyField, Field parentKeyField, Field subField) {
        //构建子节点
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);

        if (subs != null) {
//            ReflectUtils.setValueByField(subField, parent, subs);
            //获取子节点数据赋值
            if(subs.size() > 0){
                ReflectUtils.invokeSetter(parent, subField.getName(), subs);
            }else{
                ReflectUtils.invokeSetter(parent, subField.getName(), null);
            }

            subs.forEach(sub -> {
                find(list, sub, keyField, parentKeyField, subField);
            });
        }
    }


    /**
     * 构建子项集合
     *
     * @param list           数据源
     * @param parent         实例对象
     * @param keyField       关联属性
     * @param parentKeyField 父类关联属性
     * @param <T>
     * @return
     */
    private static <T> List<T> getSubs(List<T> list, T parent, Field keyField, Field parentKeyField) {
//        List<T> subs = null;
//        for (T t : list) {
//            Object keyFieldValue = ReflectionUtils.invokeGetter(parent, keyField.getName());
//            Object parentFieldValue = ReflectionUtils.invokeGetter(t, parentKeyField.getName());
//            if (keyFieldValue.equals(parentFieldValue)) {
//                if (subs == null) {
//                    subs = new ArrayList<>();
//                }
//                subs.add(t);
//            }
//        }
//        return subs;
        return list.stream().filter(t ->
                ReflectUtils.invokeGetter(parent, keyField.getName()).equals(ReflectUtils.invokeGetter(t, parentKeyField.getName()))
        ).collect(Collectors.toList());
    }

/****************/
    /**
     * @param list           树数据
     * @param keys           存储集合
     * @param parent         关联属性
     * @param keyField       关联父属性
     * @param parentKeyField 子节点数据
     * @param <T>
     * @param <E>
     * @return
     */
    private static <T, E> List<E> findKeys(List<T> list, List<E> keys, T parent, Field keyField, Field parentKeyField) {
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);
        List<E> subKeys = getSubKeys(list, parent, keyField, parentKeyField);
        if (subs != null) {
            keys.addAll(subKeys);
            for (T sub : subs) {
                //递归找子节点
                findKeys(list, keys, sub, keyField, parentKeyField);
            }
        }
        return keys;
    }


    /**
     * 构建子项Key集合
     *
     * @param list
     * @param parent
     * @param keyField
     * @param parentKeyField
     * @param <T>
     * @param <E>
     * @return
     */
    private static <T, E> List<E> getSubKeys(List<T> list, T parent, Field keyField, Field parentKeyField) {
        List<E> subs = null;
        for (T t : list) {
            Object keyFieldValue = ReflectUtils.invokeGetter(parent, keyField.getName());//父节点key
            Object parentFieldValue = ReflectUtils.invokeGetter(t, parentKeyField.getName());//根结点关联的key
            if (keyFieldValue.equals(parentFieldValue)) { //关联字段相等
                if (subs == null) {
                    subs = new ArrayList<>();
                }
                //取子节点key
                Object key = ReflectUtils.invokeGetter(t, keyField.getName());
                subs.add((E) key);
            }
        }
        return subs;
    }


    /**
     * 根据父节点的关联值 查找
     *
     * @param list               树数据
     * @param root               根节点（树形实例）
     * @param keyFieldName       关联属性
     * @param parentKeyFieldName 关联父属性
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> List<E> getKeys(List<T> list, T root, String keyFieldName, String parentKeyFieldName) {
        Field keyField = ReflectUtils.getAccessibleField(root, keyFieldName);
        Field parentKeyField = ReflectUtils.getAccessibleField(root, parentKeyFieldName);
        List<E> keys = new ArrayList<>();
        E value = ReflectUtils.invokeGetter(root, keyField.getName());
        keys.add(value);
        findKeys(list, keys, root, keyField, parentKeyField);
        return keys;
    }

    public static void main(String[] args) {
        Tree testTree = new Tree();
        testTree.setId(1001L);
        testTree.setLabel("潍坊市");
        testTree.setPid(1000L);
        Tree testTree1 = new Tree();
        testTree1.setId(1002L);
        testTree1.setLabel("青岛市");
        testTree1.setPid(1000L);
        Tree testTree2 = new Tree();
        testTree2.setId(1001001L);
        testTree2.setLabel("高新区");
        testTree2.setPid(1001L);
        Tree testTree3 = new Tree();
        testTree3.setId(1002001L);
        testTree3.setLabel("四方区");
        testTree3.setPid(1002L);
        Tree testTree4 = new Tree();
        testTree4.setId(1000L);
        testTree4.setLabel("山东省");
        testTree4.setPid(null);
        Tree testTree5 = new Tree();
        testTree5.setId(1001001001L);
        testTree5.setLabel("清池街办");
        testTree5.setPid(1001001L);
        List<Tree> testTreeList = new ArrayList<>();
        testTreeList.add(testTree);
        testTreeList.add(testTree1);
        testTreeList.add(testTree2);
        testTreeList.add(testTree3);
        testTreeList.add(testTree4);
        testTreeList.add(testTree5);
        TreeFactory.createTree(testTreeList, testTree4, "id", "pid", "children");
        System.out.println(testTree4); //通过上边的createTree方法，根节点，即testTree4就是最全的最后的树结构。

    }
}
