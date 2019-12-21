package mybatis;

import com.alibaba.fastjson.JSON;
import mybatis.dao.RoleMapper;
import mybatis.model.TransRole;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-11-24 18:22
 */
public class Main {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
//            TransRole role = new TransRole();
//            role.setName("name");
//            role.setBrithDate("20191214");

//            System.out.println(ToStringBuilder.reflectionToString(role, ToStringStyle.SHORT_PREFIX_STYLE));
//            roleMapper.insertOne(role);
            Map role = roleMapper.selectOne(27L);
            System.out.println(ToStringBuilder.reflectionToString(role, ToStringStyle.SHORT_PREFIX_STYLE));
            System.out.println(JSON.toJSONString(role));

//            List<TransRole> transRoleList = roleMapper.selectAll();
//            for (TransRole transRole : transRoleList) {
//                System.out.println(transRole.toString());
//            }

            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}
