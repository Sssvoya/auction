<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<#import "../parts/pager.ftlh" as p>

<@c.page>
    <h3 class="m-5 fw-bold"><@spring.message "UserList"/></h3>
    <@p.pager url 5 10 15 20 page />
    <div class="container m-3">
        <div class="row my-3">
            <div class="col-sm-3">
                <p class="fw-bold"><@spring.message "field.User.Username"/><p>
            </div>
            <div class="col-sm-2">
                <p class="fw-bold"><@spring.message "field.User.roles"/></p>
            </div>
            <div class="col-sm-2">
                <p class="fw-bold"><@spring.message "field.User.balance"/></p>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <#list page.content as User>
            <div class="row my-3">
                <div class="col-sm-3">
                    ${User.getUsername()}
                </div>
                <div class="col-sm-2">
                    <#list User.getRoles() as role>
                        ${role} <#sep>,
                    </#list>
                </div>
                <div class="col-sm-2">
                    ${User.getBalance()}
                </div>
                <div class="col-sm-2">
                    <a href="/User/${User.getId()}" class="btn btn-secondary btn-sm">
                        <@spring.message "link.edit"/>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</@c.page>