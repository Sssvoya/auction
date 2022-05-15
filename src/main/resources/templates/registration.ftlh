<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring/>
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

        <div class="form-group row my-3">
            <label for="inputUsername" class="col-sm-2 col-form-label">
                <@spring.message "form.label.Username"/>
            </label>
            <div class="col-sm-5">
                <input type="text" class="form-control ${(UsernameError??)?string('is-invalid', '')}" id="inputUsername"
                       placeholder="<@spring.message 'form.placeholder.Username'/>"
                       value="<#if User??>${User.getUsername()!''}</#if>"
                       name="Username"/>
                <#if UsernameError??>
                    <#list UsernameError as UsernameEr >
                        <div class="invalid-feedback">
                            ${UsernameEr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
        <div class="form-group row my-3">
            <label for="inputPassword" class="col-sm-2 col-form-label">
                <@spring.message "form.label.password"/>
            </label>
            <div class="col-sm-5">
                <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" id="inputPassword"
                       placeholder="<@spring.message 'form.placeholder.password'/>"
                       name="password"/>
                <#if passwordError??>
                    <#list passwordError as pErr >
                        <div class="invalid-feedback">
                            ${pErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="mb-3 mt-5">
            <div class="g-recaptcha my-5" data-sitekey="6LeCm6EaAAAAAFCCEHbfJ04cG9L4AbbYSlhvnuAv"></div>
            <div class="text-center">
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert" style="max-width: 80rem;">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-secondary my-5"><@spring.message "button.registration"/></button>
    </form>
</@c.page>