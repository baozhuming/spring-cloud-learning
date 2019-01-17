package com.thymeleaf.thymeleaf.jpa;

import com.google.common.collect.Iterables;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecs {
    public static <T>Specification<T> byAuto(final EntityManager entityManager,final T example){//定义一个返回值为Specification的方法byAuto，这里使用的方向T，所以这个Specification是可以用于任意的实体类的，它接受的参数是entityManager和当前的包含值作为查询条件的实体类对象
        final Class<T> type = (Class<T>)example.getClass();//获取当前实体类对象类的类型
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();//新建Predicate列表存储构造的查询条件
                EntityType<T> entity = entityManager.getMetamodel().entity(type);//获得实体类的EntityType，可以从EntityType获得实体类的属性
                for(Attribute<T,?> attr : entity.getDeclaredAttributes()){//对实体类的所有属性做循环
                    Object attrValue = getValue(example,attr);//获得实体类对象的某一个属性的值
                    if(attrValue != null){
                        if(attr.getJavaType() == String.class){//当前属性值为字符类型的时候
                            if(!StringUtils.isEmpty(attrValue)){//若当前字符不为空的清空下
                                predicates.add(criteriaBuilder.like(root.get(attribute(entity,attr.getName(),String.class)),pattern((String)attrValue)));//构造当前属性like（前后%）属性值查询条件，并添加到条件列表中
                            }
                        }else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())),attrValue));//其余情况下，构造属性和属性值equal查询条件，并添加到条件列表中
                        }
                    }
                }
                return predicates.isEmpty()?criteriaBuilder.conjunction():criteriaBuilder.and(Iterables.toArray(predicates,Predicate.class));//将条件列表转换成Predicate
            }

            /**
             * 通过反射获得实体类对象对应属性的属性值
             * @param example
             * @param attr
             * @param <T>
             * @return
             */
            private <T> Object getValue(T example,Attribute<T,?> attr){
                return ReflectionUtils.getField((Field) attr.getJavaMember(),example);
            }

            /**
             * 获得实体类的当前属性的SingularAttrbute，SingularAttribute包含的是实体类的某个单独属性
             * @param entity
             * @param fieldName
             * @param fieldClass
             * @param <E>
             * @param <T>
             * @return
             */
            private <E,T>SingularAttribute<T,E> attribute(EntityType<T> entity,String fieldName,Class<E> fieldClass){
                return entity.getDeclaredSingularAttribute(fieldName,fieldClass);
            }
        };
    }

    /**
     * 构造like的查询模式，即前后加%
     * @param str
     * @return
     */
    static private String pattern(String str){
        return "%"+str+"%";
    }
}
