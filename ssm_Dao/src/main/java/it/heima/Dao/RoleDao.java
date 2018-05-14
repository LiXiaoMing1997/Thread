package it.heima.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import it.heima.Domain.SysRole;

public interface RoleDao {
	
	@Insert("insert into sys_role (roleName, roleDesc) values (#{roleName}, #{roleDesc})")
	public void save(SysRole sysRole);
	
	@Select("select * from sys_role")
	public List<SysRole> findAll();
	
	@Select("select sr.* from sys_role sr, user_role ur where sr.id=ur.roleid and ur.userid=#{id}")
	@Results({
		@Result(property="permissions", column="id", javaType=List.class,
				many = @Many(select="it.heima.Dao.PermissionDao.findByRid"))
	})
	public List<SysRole> findByUid(String id);
	
}
