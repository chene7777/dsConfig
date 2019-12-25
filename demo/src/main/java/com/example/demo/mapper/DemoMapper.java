package com.example.demo.mapper;

import com.example.demo.model.dto.Demo;
import com.example.demo.model.dto.DemoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DemoMapper {
    @SelectProvider(type=DemoSqlProvider.class, method="countByExample")
    int countByExample(DemoCriteria example);

    @DeleteProvider(type=DemoSqlProvider.class, method="deleteByExample")
    int deleteByExample(DemoCriteria example);

    @Delete({
        "delete from demo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into demo (id, name, ",
        "code)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR})"
    })
    int insert(Demo record);

    @InsertProvider(type=DemoSqlProvider.class, method="insertSelective")
    int insertSelective(Demo record);

    @SelectProvider(type=DemoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    List<Demo> selectByExample(DemoCriteria example);

    @Select({
        "select",
        "id, name, code",
        "from demo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    Demo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DemoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoCriteria example);

    @UpdateProvider(type=DemoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Demo record, @Param("example") DemoCriteria example);

    @UpdateProvider(type=DemoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Demo record);

    @Update({
        "update demo",
        "set name = #{name,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Demo record);
}