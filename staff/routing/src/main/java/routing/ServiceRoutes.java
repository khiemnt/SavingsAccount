package routing;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 3/20/12
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceRoutes extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {


        from("cxf:bean:staffDetailService?dataFormat=PAYLOAD")
                .to("bean:jaxbProcessor?method=unmarshall")
                .choice()
                .when(header("operationName").isEqualTo("getStaffDetail"))
                .to("bean:accountTarget?method=getStaffDetail")
                .when(header("operationName").isEqualTo("getListBlockField"))
                .to("bean:accountTarget?method=getListBlockField")
                .when(header("operationName").isEqualTo("saveListField"))
                .to("bean:accountTarget?method=saveListField")
                .when(header("operationName").isEqualTo("addStaff"))
                .to("bean:accountTarget?method=addStaff")
                .when(header("operationName").isEqualTo("getRole"))
                .to("bean:accountTarget?method=getRole")
                .when(header("operationName").isEqualTo("getInformationAccount"))
                .to("bean:accountTarget?method=getInformationAccount")
                .when(header("operationName").isEqualTo("isAddAnother"))
                .to("bean:accountTarget?method=isAddAnother")
                .when(header("operationName").isEqualTo("getListFieldMutileRow"))
                .to("bean:accountTarget?method=getListFieldMutileRow")
                .when(header("operationName").isEqualTo("deleteMultiField"))
                .to("bean:accountTarget?method=deleteMultiField")
                .when(header("operationName").isEqualTo("deleteRight"))
                .to("bean:accountTarget?method=deleteRight")
                .when(header("operationName").isEqualTo("findAllUser"))
                .to("bean:accountTarget?method=findAllUser")
                .when(header("operationName").isEqualTo("getUserCommon"))
                .to("bean:accountTarget?method=getUserCommon")
                .when(header("operationName").isEqualTo("getAdmins"))
                .to("bean:accountTarget?method=getAdmins")
                .when(header("operationName").isEqualTo("getBillingHistory"))
                .to("bean:accountTarget?method=getBillingHistory")
                .when(header("operationName").isEqualTo("getListPayment"))
                .to("bean:accountTarget?method=getListPayment")
                .when(header("operationName").isEqualTo("getListEntryText"))
                .to("bean:accountTarget?method=getListEntryText")
                .when(header("operationName").isEqualTo("editEntryText"))
                .to("bean:accountTarget?method=editEntryText")
                .when(header("operationName").isEqualTo("registerCustomer"))
                .to("bean:accountTarget?method=registerCustomer")
                .when(header("operationName").isEqualTo("getCustomerById"))
                .to("bean:accountTarget?method=getCustomerById")
				.when(header("operationName").isEqualTo("validateVerificationCode"))
				.to("bean:accountTarget?method=validateVerificationCode")

                .when(header("operationName").isEqualTo("getListStaffReceiveSuggestion"))
                .to("bean:accountTarget?method=getListStaffReceiveSuggestion")

				.when(header("operationName").isEqualTo("executeDynamicSearchQuery"))
				.to("bean:accountTarget?method=executeDynamicSearchQuery")

                .when(header("operationName").isEqualTo("getConditionSearch"))
                .to("bean:accountTarget?method=getConditionSearch")
                .end()
                .to("bean:jaxbProcessor?method=marshall");


        from("cxf:bean:setupService?dataFormat=PAYLOAD")
                .to("bean:jaxbProcessor?method=unmarshall")
                .choice()
                .when(header("operationName").isEqualTo("checkInvationCode"))
                .to("bean:setupTarget?method=checkInvationCode")
                .when(header("operationName").isEqualTo("updateCreditCardInfo"))
                .to("bean:setupTarget?method=updateCreditCardInfo")

                .when(header("operationName").isEqualTo("changePassword"))
                .to("bean:setupTarget?method=changePassword")

                .when(header("operationName").isEqualTo("cancelService"))
                .to("bean:setupTarget?method=cancelService")

                .when(header("operationName").isEqualTo("updateNotification"))
                .to("bean:setupTarget?method=updateNotification")

                .when(header("operationName").isEqualTo("getNotificationInfor"))
                .to("bean:setupTarget?method=getNotificationInfor")

                .when(header("operationName").isEqualTo("getInformation"))
                .to("bean:setupTarget?method=getInformation")

                .when(header("operationName").isEqualTo("setupStaflink"))
                .to("bean:setupTarget?method=setupStaflink")

                .when(header("operationName").isEqualTo("getMailServerInfo"))
                .to("bean:setupTarget?method=getMailServerInfo")

                .when(header("operationName").isEqualTo("updateMailServerInfo"))
                .to("bean:setupTarget?method=updateMailServerInfo")

                .end()
                .to("bean:jaxbProcessor?method=marshall");
    }
}
