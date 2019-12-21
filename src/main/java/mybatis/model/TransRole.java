package mybatis.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-11-24 16:11
 */
public class TransRole {
    private Integer id;
    private String name;
    private String brithDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(String brithDate) {
        this.brithDate = brithDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);

//        return "TransRole{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
    }

}
