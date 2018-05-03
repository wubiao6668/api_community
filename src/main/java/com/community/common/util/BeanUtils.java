package com.community.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象转换工具
 */
public class BeanUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * list转换为map，分组 eg: [[id:1,name:1],[id:2,name:2],[id:1,name:3]] -> [[id:1,[[id:1,name:1],[id:1,name:3]]],[id:2,[[id:2,name:2]]]]
     *
     * @param list    待转换的集合
     * @param keyProp 列表中类的属性
     * @param <K>     列表中类的属性值
     * @param <V>     对应的符合条件的集合
     * @return
     */
    public static <K, V> Map<K, Collection<V>> list2Multimap(Collection<V> list, String keyProp) {

        Map<K, Collection<V>> map = Maps.newLinkedHashMap();

        if (CollectionUtils.isEmpty(list)) {
            return map;
        }

        try {
            Field keyField = list.iterator().next().getClass().getDeclaredField(keyProp);
            keyField.setAccessible(true);

            for (V v : list) {
                K key = (K) keyField.get(v);

                Collection<V> vList = map.get(key);

                if (vList == null) {
                    vList = Lists.newArrayList();
                    map.put(key, vList);
                }
                vList.add(v);
            }
        } catch (Exception e) {
            LOGGER.error("list2Multimap occurred error list:[{}], keyProp:[{}]", list, keyProp, e);
        }


        return map;
    }


    /**
     * 获取集合内部属性值组装成列表
     *
     * @param collection
     * @param keyProp
     * @param <V>
     * @param <E>
     * @return
     */
    public static <V, E> List<V> getFieldList(Collection<E> collection, String keyProp) {
        List<V> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(collection)) {
            return list;
        }

        try {
            Field keyField = collection.iterator().next().getClass().getDeclaredField(keyProp);
            keyField.setAccessible(true);

            for (E e : collection) {
                list.add((V) keyField.get(e));
            }

        } catch (Exception e) {
            LOGGER.error("getFieldList occurred error collection:[{}], keyProp:[{}]", collection, keyProp, e);
        }

        return list;
    }


    /**
     * list转map，返回的map保持跟传入的collection一致
     *
     * @param collection
     * @param keyProp
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> list2Map(Collection<V> collection, String keyProp) {

        //保证有序
        Map<K, V> map = Maps.newLinkedHashMap();
        if (CollectionUtils.isEmpty(collection)) {
            return map;
        }
        try {
            Class<?> clazz = collection.iterator().next().getClass();

            Field keyField = clazz.getDeclaredField(keyProp);
            keyField.setAccessible(true);

            for (V v : collection) {
                K key = (K) keyField.get(v);
                map.put(key, v);
            }

        } catch (Exception e) {
            LOGGER.error("list2Map occurred error collection:[{}], keyProps:[{}]", collection, keyProp, e);
        }


        return map;

    }

    /**
     * list转map，支持Collection元素中多个字段拼接作为key
     *
     * @param collection
     * @param keyProps   分组的属性值
     * @param <V>
     * @return
     */
    public static <V> Map<String, V> list2Map(Collection<V> collection, String... keyProps) {

        Map<String, V> map = Maps.newLinkedHashMap();
        if (CollectionUtils.isEmpty(collection)) {
            return map;
        }
        try {
            Class<?> clazz = collection.iterator().next().getClass();

            List<Field> fieldList = Lists.newArrayList();

            for (String key : keyProps) {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                fieldList.add(field);
            }


            for (V v : collection) {
                StringBuilder key = new StringBuilder();
                for (Field field : fieldList) {
                    key.append(field.get(v)).append("_");
                }

                map.put(key.substring(0, key.length() - 1), v);
            }

        } catch (Exception e) {
            LOGGER.error("list2Map occurred error collection:[{}], keyProps:[{}]", collection, keyProps, e);
        }


        return map;

    }


    /**
     * list转map
     *
     * @param collection
     * @param keyProp    作为key的字段
     * @param valueProp  作为value的字段
     * @param <K>        map key的类型
     * @param <V>        map value的类型
     * @param <D>        集合数据的类型
     * @return
     */
    public static <K, V, D> Map<K, V> list2Map(List<D> collection, String keyProp, String valueProp) {

        Map<K, V> map = Maps.newLinkedHashMap();

        if (CollectionUtils.isEmpty(collection))
            return map;

        try {
            Class clazz = collection.iterator().next().getClass();
            Field keyField = clazz.getDeclaredField(keyProp);
            Field valueField = clazz.getDeclaredField(valueProp);
            keyField.setAccessible(true);
            valueField.setAccessible(true);

            for (D data : collection) {
                K key = (K) keyField.get(data);
                V value = (V) valueField.get(data);
                map.put(key, value);
            }

        } catch (Exception e) {
            LOGGER.error("list2Map error collection:[{}], props:[{},{}], {}", collection, keyProp, valueProp, e.getMessage());
        }
        return map;
    }

    /**
     * list转map
     *
     * @param map        目标map，将相关数据放入该map中，也可以为null
     * @param collection
     * @param keyProp    作为key的字段名
     * @return
     */
    public static <K, V> Map<K, V> list2Map(Map<K, V> map, List<V> collection, String keyProp) {

        if (map == null)
            map = Maps.newLinkedHashMap();

        if (CollectionUtils.isEmpty(collection))
            return map;

        try {
            Field keyField = collection.iterator().next().getClass().getDeclaredField(keyProp);
            keyField.setAccessible(true);
            for (V v : collection) {
                K key = (K) keyField.get(v);
                map.put(key, v);
            }
        } catch (Exception e) {
            LOGGER.error("list2Map error collection:[{}], keyProp:[{}], {}", collection, keyProp, e.getMessage());
        }
        return map;
    }

    /**
     * list转换成list
     *
     * @param sourceList 源数据列表
     * @param tClazz     目标对象
     * @param <T>        目标对象类型
     * @param <S>        源对象类型
     * @return
     */
    public static <T, S> List<T> list2list(List<S> sourceList, Class<T> tClazz) {
        if (CollectionUtils.isEmpty(sourceList))
            return Lists.newArrayList();
        List<T> targetList = Lists.newArrayList();

        try {
            for (S s : sourceList) {
                T t = tClazz.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(s, t);
                targetList.add(t);
            }
        } catch (Exception e) {
            LOGGER.error("list2list e:{}", e);
        }
        return targetList;
    }


    /**
     * 类转换
     *
     * @param source 源对象
     * @param tClazz 目标对象
     * @param <S>    源对象类型
     * @param <T>    目标对象类型
     * @return
     */
    public static <S, T> T copyProperties(S source, Class<T> tClazz) {
        if (source == null)
            return null;

        try {
            T t = tClazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            LOGGER.error("copyProperties e:{}", e);
            return null;
        }
    }


    /**
     * map转换成map
     *
     * @param sourceMap 源数据map
     * @param tClazz    目标对象
     * @param <K>       map key的类型
     * @param <T>       目标map value的类型
     * @param <S>       源集合数据的类型
     * @return
     */
    public static <K, T, S> Map<K, T> map2Map(Map<K, S> sourceMap, Class<T> tClazz) {

        Map<K, T> map = Maps.newHashMap();
        if (MapUtils.isEmpty(sourceMap))
            return map;

        try {
            for (K k : sourceMap.keySet()) {
                S s = sourceMap.get(k);
                if (s == null)
                    continue;
                T t = tClazz.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(s, t);
                map.put(k, t);
            }
        } catch (Exception e) {
            LOGGER.error("ConvertUtils.Map2Map e:{}", e);
        }
        return map;
    }

    /**
     * 按小时维度更新随机列表。若数据量不够则不做随机处理
     *
     * @param list      初始列表
     * @param hour      小时维度更新
     * @param maxLength 返回列表最大长度
     * @param <T>
     * @return
     */
    public static <T> List<T> randomList(List<T> list, int hour, int maxLength) throws Exception {
        List<T> result = Lists.newArrayList();
        if (hour < 0) {
            throw new Exception("最小更新单位(1小时)");
        }
        if (maxLength < 1) {
            throw new Exception(("返回列表长度不得小于1"));
        }
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        if (list.size() == maxLength) {
            return list;
        }
        Calendar calendar = Calendar.getInstance();
        Random random = new Random(calendar.get(Calendar.HOUR_OF_DAY) / hour);
        int size = 0;
        Set<Integer> used = Sets.newHashSet();

        while (size < maxLength && size < list.size()) {
            int randomIndex = random.nextInt(list.size());
            while (used.contains(randomIndex)) {
                randomIndex = (randomIndex + 1) % (list.size());
            }
            result.add(list.get(randomIndex));
            used.add(randomIndex);
            size++;
        }
        return result;
    }
}
