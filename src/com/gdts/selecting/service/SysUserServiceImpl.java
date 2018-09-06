package com.gdts.selecting.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gdts.selecting.dao.SysUserDao;
import com.gdts.selecting.entity.SysUser;
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {
	@Autowired
	private SysUserDao sysUserDAO;
	private List<SysUser> sysUserList;
	/**
	 * 添加学生信息逻辑接口实现
	 * @author 秦松
	 * @param sysUser
	 */
	@Cacheable(value="userCache", key="#sysUser.userId + 'addStudent'")
	public void addStudent(SysUser sysUser) {
	   sysUserDAO.save(sysUser);
	}
	
	/**
	  * administratorLimit 管理员分页
	  * totel 总数
	  * @param start 分页开始位置
	  * @param end 分页结束位置
	  * @return
	  */
	@Override
	public List<SysUser> administratorLimit(Long totel, Long start, Long end, int userType) {
		List<SysUser> list = sysUserDAO.administratorLimit(userType);
		if(end >= totel)end = totel-1;
		System.out.println("totel:"+totel+"---start:"+start+"---end:"+end);
		if(null != list){
			int idx = start.intValue();
			sysUserList = new ArrayList<SysUser>();
			for(;idx<=end;idx++){
				sysUserList.add(list.get(idx));
			}
		}
		return sysUserList;
	}

	/**
	  * administrator count 总数
	  * @return
	  */
	@Override
	public Long getTotel(String tableName, int userType) {
		return sysUserDAO.findTotel(tableName,userType);
	}

	@Override
	public void updateStudent(SysUser sysUser) {
		sysUserDAO.update(sysUser);
		
	}

	/**
	 * 
	 * @Description: 管理员删除用户
	 * @param @param sysUser
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月27日
	 */
	@CacheEvict(value="userCache", key="#sysUser.userId + 'addStudent'")
	public void delStudent(SysUser sysUser) {
		sysUserDAO.remove(sysUser);
	}

	/**
	  * 
	  * @Description: 根据用户id查询用户实体
	  * @param @param id
	  * @param @return   
	  * @return SysUser  
	  * @throws
	  * @author liuchunfu
	  * @date 2018年5月27日
	  */
	@Override
	public SysUser getSysUserById(int id) {
		List<SysUser> sysUserList = sysUserDAO.getSysUserById(id);
		return (null != sysUserList && 0<sysUserList.size())?sysUserList.get(0):null;
	}
	/**
	 * 查询用户id
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	@Override
	public SysUser findByUserId(String userId) {
		// TODO Auto-generated method stub
		return sysUserDAO.findByUserId(userId);
	}
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * @param property
	 * @param value
	 * @return
	 */
	@Override
	public SysUser findPropertyByValue(String property, String value) {
		return sysUserDAO.findPropertyByValue(property,value);
	}

}
