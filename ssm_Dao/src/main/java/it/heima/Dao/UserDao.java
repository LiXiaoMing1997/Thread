package it.heima.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import it.heima.Domain.SysRole;
import it.heima.Domain.UserInfo;

public interface UserDao {

	@Select("select * from sys_user where username = #{username}")
	@Results({
		@Result(column="id",property="sysRole",javaType=List.class,
				many=@Many(select="it.heima.Dao.RoleDao.findByUid", fetchType=FetchType.LAZY))
	})
	public  UserInfo  findByUserName (String userName);
	
	@Select("select * from sys_user")
	public List<UserInfo> findAll();
	
	@Insert("insert into sys_user (username, email, password, phoneNum, status) "
			+ "values (#{username}, #{email}, #{password}, #{phoneNum}, #{status})")
	public void Save(UserInfo UserInfo);
						
	@Select("select * from sys_user where id = #{id}")
	@Results({
		@Result(property="sysRole", column="id", javaType=List.class,
				many = @Many(select="it.heima.Dao.RoleDao.findByUid", fetchType=FetchType.LAZY))
	})
	public UserInfo findById(String id);

	
	@Select("select * FROM SYS_ROLE sr "
			+ "where not exists "
			+ "(SELECT *from USER_ROLE ur "
			+ "WHERE  ur.USERID = #{id} "
			+ "and UR.ROLEID=SR.ID)")
	public List<SysRole> addRole(String id);

	@Insert("insert into user_role values(#{userid},#{rid})")
	public void AddUserRole(@Param("userid")String userid, @Param("rid")String rid);
	
}