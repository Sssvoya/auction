<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

    <div class="m-5">
        ${message!''}
    </div>

    <div class="text-center">
        <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <div class="alert alert-danger" role="alert" style="max-width: 80rem;">
                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </#if>
    </div>

    <form method="post" action="/login">
        <div class="form-group row my-3">
            <label for="inputUsername" class="col-sm-2 col-form-label">
                <@spring.message "form.label.Username"/>
            </label>
            <div class="col-sm-5">
                <input type="text" class="form-control " id="inputUsername"
                       name="Username"/>
            </div>
        </div>
        <div class="form-group row my-3">
            <label for="inputPassword" class="col-sm-2 col-form-label">
                <@spring.message "form.label.password"/>
            </label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="inputPassword"
                       name="password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <a class="mx-3  my-5" href="/registration"><@spring.message "link.registration"/></a>
        <button type="submit" class="btn btn-secondary mx-4"><@spring.message "button.login"/></button>
    </form>
</@c.page>
