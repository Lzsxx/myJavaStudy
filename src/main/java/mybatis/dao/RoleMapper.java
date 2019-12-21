package mybatis.dao;

import mybatis.model.TransRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-11-24 18:18
 */
public interface RoleMapper {
    Map selectOne(Long id);

    List<TransRole> selectAll();

    int deleteById(Long id);

    int insertOne(TransRole role);

}
