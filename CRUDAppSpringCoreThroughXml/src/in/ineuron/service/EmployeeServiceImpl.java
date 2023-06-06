package in.ineuron.service;

import org.springframework.beans.BeanUtils;

import in.ineuron.bo.EmployeeBO;
import in.ineuron.dao.IEmployeeDao;
import in.ineuron.dto.EmployeeDTO;
import in.ineuron.vo.EmployeeVO;

public class EmployeeServiceImpl implements IEmployeeService {

	IEmployeeDao dao;

	private EmployeeServiceImpl(IEmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public String save(EmployeeDTO dto) throws Exception {
		EmployeeBO bo = new EmployeeBO();
		bo.setEname(dto.getEname());
		bo.setEage(dto.getEage());
		bo.setEaddr(dto.getEaddr());

		int count = dao.save(bo);
		return count == 0 ? "Record insertion failed" : "Record inserted successfully...";
	}

	@Override
	public EmployeeDTO findById(EmployeeDTO dto) throws Exception {
		EmployeeBO bo = new EmployeeBO();
		bo.setEid(dto.getEid());
		
		EmployeeBO employeeBO = dao.findById(bo);
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeBO, employeeDTO);
		
		return employeeDTO;
	}

	@Override
	public String updateById(EmployeeDTO dto) throws Exception {
		EmployeeBO empBO = new EmployeeBO();
		BeanUtils.copyProperties(dto, empBO);
		
		int count = dao.updateById(empBO);
		
		return count == 0 ? "Record updation failed" : "Record updated successfully...";
	}

	@Override
	public String deleteById(EmployeeDTO dto) throws Exception {
		EmployeeBO bo = new EmployeeBO();
		bo.setEid(dto.getEid());
		
		int count = dao.deleteById(bo);
		return count == 0 ? "Record not available for employeeId :: "+bo.getEid() : "Record deleted successfully...";
	}

}
