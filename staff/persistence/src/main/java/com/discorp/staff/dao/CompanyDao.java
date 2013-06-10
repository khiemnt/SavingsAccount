package com.discorp.staff.dao;
import com.discorp.staff.model.Company;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDao {
    public List<Company>findAllCompany();
    public void saveCompany(Company company);
    public List<String> getCompanyNameByDealerId(Integer dealerId);
}
