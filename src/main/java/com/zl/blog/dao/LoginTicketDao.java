package com.zl.blog.dao;

import com.zl.blog.model.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * Created by zl on 17-7-20.
 * @author zl
 */
@Mapper
public interface LoginTicketDao {
    String TABLE_NAEM = " login_ticket ";
    String INSERT_FIELDS = " user_id, ticket, expired, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS,") values (#{userId},#{ticket},#{expired},#{status})"})
    void insertLoginTicket(LoginTicket loginTicket);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    LoginTicket seletById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where ticket=#{ticket}"})
    LoginTicket seletByTicket(String ticket);

    @Update({"update",TABLE_NAEM,"set status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    void deleteById(int id);
}
