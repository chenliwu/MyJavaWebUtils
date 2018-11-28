package com.charlie.ssm.demo.utils;

import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-11-28 8:47
 */
public class JackSonUtils {


    public static void main(String[] args) {

        //对象转换Josn
        testObjectToJson();
        testObjectToJsonPretty();

        //Json转换成对象
        jsonToObjectTypeReference();
        jsonToObjectByClass();

        //Json与List对象互转
        testJsonToListTypeReference();
        testJsonToListClass();

        System.out.println();
    }

    /*********************************************  对象转化成json start ************************************************/
    /**
     * 对象转化成json
     */
    public static void testObjectToJson() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenlw");
        userEntity.setPassword("chenlw");
        String userJson = objectToJson(userEntity);
        System.out.println("\n对象转化成json：" + userJson);
    }

    /**
     * 将对象转换成格式化的字符串
     */
    public static void testObjectToJsonPretty() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenlw");
        userEntity.setPassword("chenlw");
        String userJson = objectToJsonPretty(userEntity);
        System.out.println("\n将对象转换成格式化的字符串：" + userJson);
    }
    /*********************************************  对象转化成json end ************************************************/


    /*********************************************  Json转换成对象 start ************************************************/

    /**
     * 将json转换成对象User（TypeReference）
     */
    public static void jsonToObjectTypeReference() {
        String json = "{\"username\":\"chenlw\",\"password\":\"chenlw\"}";
        //将json字符串转换成User对象
        UserEntity user = jsonToObject(json, new TypeReference<UserEntity>() {
        });
        //如果转换不为空
        if (user != null) {
            System.out.println("\n将json转换成对象User（TypeReference）：" + user.toString());
        } else {
            System.out.println("\n将json转换成对象User（TypeReference）失败！");
        }
    }

    /**
     * 将json转换成对象User（Class）
     */
    public static void jsonToObjectByClass() {
        String json = "{\"username\":\"chenlw\",\"password\":\"chenlw\"}";
        //将json字符串转换成User对象
        UserEntity user = jsonToObject(json, UserEntity.class);
        //如果转换不为空
        if (user != null) {
            System.out.println("\n将json转换成对象User（Class）：" + user.toString());
        } else {
            System.out.println("\n将json转换成对象User（Class）失败！");
        }
    }

    /*********************************************  Json转换成对象 end ************************************************/


    /*********************************************  Json与List对象互转 start ************************************************/
    /**
     * 将json转换成list对象（TypeReference）
     */
    public static void testJsonToListTypeReference() {
        //准备数据   将list对象转换成json
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("user1");
        userEntity1.setPassword("user1");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("user2");
        userEntity2.setPassword("user2");
        List<UserEntity> dataList = new ArrayList<>();
        dataList.add(userEntity1);
        dataList.add(userEntity2);

        String json = objectToJson(dataList);

        List<UserEntity> list = jsonToObject(json, new TypeReference<List<UserEntity>>() {

        });
        if (list != null) {
            System.out.println("\n将json转换成list对象（TypeReference）:");
            for (UserEntity user : list) {
                System.out.println(user.toString());
            }
        }
    }


    /**
     * 将json转换成list对象
     */
    public static void testJsonToListClass() {

        //准备数据   将list对象转换成json
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("user1");
        userEntity1.setPassword("user1");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("user2");
        userEntity2.setPassword("user2");
        List<UserEntity> dataList = new ArrayList<>();
        dataList.add(userEntity1);
        dataList.add(userEntity2);

        String json = objectToJson(dataList);

        List<UserEntity> list = jsonToObject(json, List.class, UserEntity.class);
        if (list != null) {
            System.out.println("\n将json转换成list对象:");
            for (UserEntity user : list) {
                System.out.println(user.toString());
            }
        }
    }


    /*********************************************  Json与List对象互转 end ************************************************/


    //////////////////////////////////////////////////////////////////////////


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //FAIL_ON_UNKNOWN_PROPERTIES在序列化的时候，如果遇到不认识的字段的处理方式
        //默认启用特性，这意味着在遇到未知属性时抛出JsonMappingException。在引入该特性之前，这是默认的默认设置。
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        //FAIL_ON_EMPTY_BEANS决定了在没有找到类型的存取器时发生了什么（并且没有注释表明它是被序列化的）。如果启用（默认），
        // 将抛出一个异常来指明这些是非序列化类型;如果禁用了，它们将被序列化为空对象，即没有任何属性。
        //请注意，这个特性只对那些没有任何识别注释的“空”bean产生影响（如@json序列化）：那些有注释的bean不会导致抛出异常。
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //过滤类的属性id
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        //在序列化时，只有那些值为null或被认为为空的值的属性才不会被包含在内。
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 对象转换成json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转换成格式化的json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象Class
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, Class<T> clazz) {
        if (StringUtils.isEmpty(src) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) src : objectMapper.readValue(src, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象TypeReference
     *
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(src) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象
     *
     * @param src
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(src, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
