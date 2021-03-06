package cn.smbms.service.role;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Role> roleList = null;
		try {
			connection = BaseDao.getConnection();
			roleList = roleDao.getRoleList(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return roleList;
	}
	
}
