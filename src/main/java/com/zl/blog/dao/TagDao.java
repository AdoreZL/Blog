package com.zl.blog.dao;

import com.zl.blog.model.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zl on 17-8-14.
 * @author zl
 */
@Mapper
public interface TagDao {
    String TABLE_NAEM = " tag ";
    String INSERT_FIELDS = " name, count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS,") values (#{name},#{count})"})
    int insertTag(Tag tag);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where name=#{name}"})
    Tag selectByName(String name);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM})
    List<Tag> selectAll();

    @Select({"select count(id) from",TABLE_NAEM})
    int getTagCount();

    @Update({"update",TABLE_NAEM,"set count = #{count} where id = #{tagId}"})
    void updateCount(@Param("tagId") int tagId,@Param("count") int count);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    void deleteById(int id);
}
