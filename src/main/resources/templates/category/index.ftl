<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--边栏-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="categoryName" value="${(productCategory.categoryName)!''}" type="text" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" value="${(productCategory.categoryType)!''}" type="text" class="form-control"/>
                        </div>

                        <input hidden name="categoryId" type="text" value="${(productCategory.categoryId)!''}" class="">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>