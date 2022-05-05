<#import "/spring.ftl" as spring>
<@c.page>
    <div class="m-5">
        ${message!''}
    </div>
    <form method="post" action="/registration">
    <div class="form-group row my-3">
        <label for="inputEmail" class="col-sm-2 col-form-label">
            <@spring.message "form.label.email"/>
        </label>
        <div class="col-sm-5">
            <input type="text" class="form-control  ${(emailError??)?string('is-invalid', '')}" id="inputEmail"
                   placeholder="<@spring.message 'form.placeholder.email'/>"
                   value="<#if User??>${User.getEmail()!''}</#if>"
                   name="email"/>
            <#if emailError??>
                <#list emailError as emailEr >
                    <div class="invalid-feedback">
                        ${emailEr}
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</@c.page>