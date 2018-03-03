
<%--
  Created by IntelliJ IDEA.
  User: shankunassv
  Date: 28.02.2018
  Time: 22:01
  Шаблон для добавления/изменеия Items

--%>

<form name="Modify" action="/pushitemmodify" method="POST">
    <div>
        <div>
        <label for="uname">Item name: </label>
        <input type="text" id="uname" name="name"
               size="30" value="${ item.name }">
        </div>
        <div>
        <label for="uprice">Price: </label>
        <input type="text" id="uprice" name="price"
               size="8" value="${ item.price }">
        </div>
        <div>
            <textarea name="description" id="description">${ item.description }</textarea>
        </div>
        <div>
            <label for="stat[]">Status: </label>
            <select class name="itemstatus">
                   <c:forEach var="itatus" items="${statuses}">
                       <option value="${itatus.itemStatusId}" ${itatus.itemStatusId == item.itemStatusId ? 'selected="selected"' : ''}>${itatus.itemStatus}</option>
                   </c:forEach>
             </select>
        </div>
    </div>
