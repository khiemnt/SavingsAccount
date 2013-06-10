package routing;

import com.discorp.staff.dto.CustomerInfoDTO;
import com.discorp.staff.service.AdminService;
import com.discorp.staff.service.CommonUserService;
import com.discorp.staff.service.FirstLightService;
import com.discorp.staff.service.StaffService;
import com.discorp.staff.xml.*;
import org.springframework.beans.factory.InitializingBean;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/10/12
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountTarget implements InitializingBean {
    XPath xpath;
    private CommonUserService commonUserService;
    private StaffService staffService;
    private AdminService adminService;
    private FirstLightService firstLightService;

    /**
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        xpath = XPathFactory.newInstance().newXPath();

    }

    public CommonUserService getCommonUserService() {
        return commonUserService;
    }

    public void setCommonUserService(CommonUserService commonUserService) {
        this.commonUserService = commonUserService;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public FirstLightService getFirstLightService() {
        return firstLightService;
    }

    public void setFirstLightService(FirstLightService firstLightService) {
        this.firstLightService = firstLightService;
    }

    public StaffDTOResponse getStaffDetail(StaffDetailRequest staffDetailRequest) {
        return staffService.getStaffDetail(staffDetailRequest.getMemberId(), staffDetailRequest.getUserId(), staffDetailRequest.getUserLogin(), staffDetailRequest.getRole());
    }

    public StaffDTOResponse getListBlockField(StaffBlockRequest staffBlockRequest) {
        return staffService.getListBlockField(staffBlockRequest);
    }

    public StaffDTOResponse saveListField(StaffDetailAddRequest staffDetailAddRequest) {
        return staffService.saveListField(staffDetailAddRequest);
    }

    public StaffDTOResponse addStaff(AddStaffRequest staffRequest) {
        return staffService.addStaff();
    }

    public StaffDTOResponse getRole(RoleRequest roleRequest) {
        return staffService.getRole(roleRequest.getMemberId());
    }

    public StaffDTOResponse getInformationAccount(StaffInformationRequest staffInformationRequest) {
        return staffService.getInformationAccount(staffInformationRequest.getUserId());
    }


    public BlockResponse isAddAnother(BlockRequest blockRequest) {
        return staffService.isAddAnother(blockRequest.getBlockId());
    }

    public StaffDTOResponse getListFieldMutileRow(AddMutileRequest addMutileRequest) {
        return staffService.getListFieldMutileRow(addMutileRequest.getBlockId());
    }

    public StaffDTOResponse deleteMultiField(DeleteMutilFieldRequest deleteMutilFieldRequest) {
        return staffService.deleteMultiField(deleteMutilFieldRequest.getUserId(), deleteMutilFieldRequest.getFieldId(), deleteMutilFieldRequest.getGroup());
    }

    public AccountResponse deleteRight(DeleteRightRequest deleteRightRequest) {
        return staffService.getListCustomer(deleteRightRequest.getUserId(), deleteRightRequest.getDealerId());
    }


    public AccountResponse findAllUser(StaffRequest staffRequest) {
        if (null != staffRequest.getName()) {
            return commonUserService.searchUser(staffRequest.getName().toUpperCase(), staffRequest.getDealerId(), staffRequest.getAchive());
        }

        return commonUserService.findAll(staffRequest.getDealerId());
    }

    public AccountResponse getUserCommon(CommonStaffRequest accountRequest) {
        AccountResponse accountResponse = new AccountResponse();
        if ("isValidate".equals(accountRequest.getMethod())) {
            String userName = accountRequest.getUserName();
            String firstName = accountRequest.getFirstName();
            String lastName = accountRequest.getLastName();
            String email = accountRequest.getEmail();
            int userId = accountRequest.getUserId();
            accountResponse.setValidate(commonUserService.isValidate(userId, userName, firstName, lastName, email));
        } else if ("updateUser".equals(accountRequest.getMethod())) {
            int userId = accountRequest.getUserId();
            String memberId = accountRequest.getMemberId();
            accountResponse.setValidate(commonUserService.updateUser(userId, memberId));
        }
        return accountResponse;
    }

    public CompanyResponse getAdmins(AdminRequest adminRequest) {
        return adminService.getAdminList();
    }

    public CompanyResponse getBillingHistory(BillingHistoryRequest billingHistoryRequest) {
        return adminService.getBillingHistoryListByDealerId(billingHistoryRequest.getDealerId());
    }

    public CompanyResponse getListPayment(AdminPaymentRequest adminPaymentRequest) {
        return adminService.getPaymentList();
    }

    public CompanyResponse getListEntryText(TextEntryRequest textEntryRequest) {
        return adminService.getEntryTextList();
    }

    public CompanyResponse editEntryText(EditEntryTextRequest editEntryTextRequest) {
        adminService.updateEntryText(editEntryTextRequest);
        return new CompanyResponse();
    }
    public AddCustomerResponse registerCustomer(AddCustomerRequest request)
    {
        return firstLightService.registerCustomer(request);

    }
    public Account getCustomerById(CustomerRequest customerRequest)
    {
        System.out.println("getCustomerById");
        return firstLightService.getAccount(customerRequest);
    }
	public AddCustomerResponse validateVerificationCode(ValidateVerificationCodeRequest request)
	{

		System.out.println("validate");
		return firstLightService.validateVerificationCode(request);

	}

    public AccountResponse getListStaffReceiveSuggestion(StaffReceiveSuggestionRequest request)
    {
        return commonUserService.getListStaffReceiveSuggestion(request.getDealerId());

    }

	public DynamicSearchResponse executeDynamicSearchQuery(DynamicSearchRequest request) {
		System.out.println("dynamic search");
		List<CustomerInfoDTO> list = firstLightService.executeDynamicSearchQuery(request);
		DynamicSearchResponse response = new DynamicSearchResponse();
		if(list.size() > 0) {
			List<DynamicSearchResponse.CustomerInfo> customerInfoList = new ArrayList<DynamicSearchResponse.CustomerInfo>();
			DynamicSearchResponse.CustomerInfo customerInfo;
			for(CustomerInfoDTO customerInfoDTO: list) {
				customerInfo = new DynamicSearchResponse.CustomerInfo();
				customerInfo.setFirstName(customerInfoDTO.getFirstName());
				customerInfo.setLastName(customerInfoDTO.getLastName());
				customerInfo.setCity(customerInfoDTO.getCity());
				customerInfo.setEmail(customerInfoDTO.getEmail());
				customerInfo.setPhoneNumber(customerInfoDTO.getPhoneNumber());
				customerInfoList.add(customerInfo);
			}
			response.setCustomerInfoList(customerInfoList);
		}
		return response;
	}

    public SearchConditionResponse getConditionSearch(SearchConditionRequest request)
    {
        return firstLightService.getConditionSearch(request);
    }


}
