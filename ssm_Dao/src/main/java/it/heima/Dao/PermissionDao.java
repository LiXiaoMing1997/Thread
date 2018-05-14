package it.heima.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import it.heima.Domain.SysPermission;

public interface PermissionDao {

	@Insert("insert into sys_permission values (seq_permission.nextval, #{permissionName}, #{url}, #{pid, jdbcType=INTEGER})")
	public void save(SysPermission sysPeimission);
	
	@Select("select * from sys_permission")
	public List<SysPermission> findAll();
	
	@Select("select sp.* from sys_permission sp, role_permission rp where sp.id = rp.permissionid and rp.roleid=#{id}")
	public List<SysPermission> findByRid(String id);
	
}
