<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<#include "../parts/security.ftlh">

<@c.page>
    <#if isUser>
        <div class="m-5">
            <a href="/User/profile/subscriptions"><@spring.message "link.toSubscriptions"/></a>
        </div>
    </#if>

    <#if message??>
        <div class="m-5">
            ${message}
        </div>
    </#if>

    <div class="m-5">
        <h5>${User.getUsername()!''}</h5>
    </div>

    <div class="m-5">
        <h5><@spring.message "field.User.balance"/>: #{User.getBalance()!''}</h5>
    </div>
    <form method="post" action="/User/profile">

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">
                <@spring.message "form.label.password"/>
            </label>
            <div class="col-sm-5">
                <input type="password" class="form-control ${(newPasswordError??)?string('is-invalid', '')}"
                       placeholder="<@spring.message 'form.placeholder.newPassword'/>"
                       name="newPassword"/>
                <#if newPasswordError??>
                    <#list newPasswordError as newPasswordErr >
                        <div class="invalid-feedback">
                            ${newPasswordErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">
                <@spring.message "form.label.email"/>
            </label>
            <div class="col-sm-5">
                <input type="text" class="form-control ${(newEmailError??)?string('is-invalid', '')}"
                       value="${User.getEmail()!''}"
                       placeholder="<@spring.message 'form.placeholder.newEmail'/>"
                       name="newEmail"/>
                <#if newEmailError??>
                    <#list newEmailError as newEmailErr >
                        <div class="invalid-feedback">
                            ${newEmailErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button type="submit" class="btn btn-secondary"><@spring.message "button.saveUpdates"/></button>
    </form>
</@c.page>