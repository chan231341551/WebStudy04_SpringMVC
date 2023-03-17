<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message }">
   <script>
      alert("${message}");
   </script>
</c:if>
</head>
<body>
   <h4>상품 입력</h4>
   <form:form modelAttribute="prod" enctype="multipart/form-data">
      <table class="table table-bordered">
         <tr>
            <th><spring:message code="prod.prodId" /></th>
            <td>
               <form:input path="prodId" class="form-control" readOnly="readOnly"/>
               <form:errors path="prodId" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodName" /></th>
            <td>
               <form:input path="prodName" class="form-control" />
               <form:errors path="prodName" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodLgu" /></th>
            <td>
	            <form:select path="prodLgu">
	            	<option value>분류</option>
                      <c:forEach items="${lprodList }" var="lprod">
            				<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }"></form:option>
                      </c:forEach>
	            </form:select>
	            <form:errors path="prodLgu" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodBuyer" /></th>
            <td>
<%--             <form:select path="prodBuyer" items="${buyerList }"  itemValue="buyerId" itemLabel="buyerName"></form:select> --%>
				<form:select path="prodBuyer">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
                          <form:option value="${buyer.buyerId }" 
                          		label="${buyer.buyerName }"
                          		cssClass="${buyer.buyerLgu }"
                          />   
                      </c:forEach>
				</form:select>
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodCost" /></th>
            <td>
               <form:input type="number" path="prodCost" class="form-control" />
               <form:errors path="prodCost" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodPrice" /></th>
            <td>
               <form:input type="number" path="prodPrice" class="form-control" />
               <form:errors path="prodPrice" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodSale" /></th>
            <td>
               <form:input type="number" path="prodSale" class="form-control" />
               <form:errors path="prodSale" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodOutline" /></th>
            <td>
               <form:input path="prodOutline" class="form-control" />
               <form:errors path="prodOutline" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodDetail" /></th>
            <td>
               <form:input path="prodDetail" class="form-control" />
               <form:errors path="prodDetail" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodImage" /></th>
            <td>
               <input type="file" name="prodImage" accept="image/*"/>
               <form:errors path="prodImage" element="span" cssClass="text-danger"/>
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodTotalstock" /></th>
            <td>
               <form:input type="number" path="prodTotalstock" class="form-control" />
               <form:errors path="prodTotalstock" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodInsdate" /></th>
            <td>
               <form:input type="date" path="prodInsdate" class="form-control" />
               <form:errors path="prodInsdate" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodProperstock" /></th>
            <td>
               <form:input type="number" path="prodProperstock" class="form-control" />
               <form:errors path="prodProperstock" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodSize" /></th>
            <td>
               <form:input path="prodSize" class="form-control" />
               <form:errors path="prodSize" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodColor" /></th>
            <td>
               <form:input path="prodColor" class="form-control" />
               <form:errors path="prodColor" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodDelivery" /></th>
            <td>
               <form:input path="prodDelivery" class="form-control" />
               <form:errors path="prodDelivery" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodUnit" /></th>
            <td>
               <form:input path="prodUnit" class="form-control" />
               <form:errors path="prodUnit" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodQtyin" /></th>
            <td>
               <form:input type="number" path="prodQtyin" class="form-control" />
               <form:errors path="prodQtyin" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodQtysale" /></th>
            <td>
               <form:input type="number" path="prodQtysale" class="form-control" />
               <form:errors path="prodQtysale" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <th><spring:message code="prod.prodMileage" /></th>
            <td>
               <form:input type="number" path="prodMileage" class="form-control" />
               <form:errors path="prodMileage" element="span" cssClass="text-danger" />
            </td>
         </tr>
         <tr>
            <td colspan="2">
            <input type="submit" value="저장" />
            <input type="submit" value="취소" />
            </td>
         </tr>
      </table>
   </form:form>
<script>
let prodBuyerTag = $("[name=prodBuyer]");
$("[name=prodLgu]").on("change", function(){
   let prodLgu = $(this).val();
   if(prodLgu){ //prodLgu가 적정한 값을 가지고 있으면~
      prodBuyerTag.find("option:gt(0)").hide();
      prodBuyerTag.find("option."+prodLgu).show();
   }
}).trigger("change");
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>