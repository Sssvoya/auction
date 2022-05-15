<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>

    <div class="container border">
        <div class="row" style="background-color: #f8f9fa">
            <div class="col-sm-12 text-center mb-2 py-3">
                <div class="h4">
                    <@spring.message "field.LotInAu.name"/>: ${LotInAu.getName()!''}
                </div>
                <div class="h4">
                    <@spring.message "field.LotInAu.author"/>: ${LotInAu.getCreator().getUsername()!''}
                </div>
                <div class="h4">
                    <@spring.message "field.LotInAu.timeStart"/>: ${LotInAu.gettimeStart()?string('dd.MM.yyyy HH:mm')!''}
                </div>
            </div>
        </div>
        <form method="post">
            <div class="form-group row my-4 m-4">
                <label for="reason" class="col-sm-2 col-form-label mx-3">
                    <@spring.message "cancelReason"/>
                </label>
                <div class="col-sm-8">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" id="reason"
                           placeholder="<@spring.message 'cancelReason.placeholder'/>"
                           value="<#if reason??>${reason.getText()}</#if>"
                           name="text">
                    <#if textError??>
                        <#list textError as textErr>
                            <div class="invalid-feedback">
                                ${textErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-secondary btn-sm m-5"><@spring.message "button.cancelAuction"/></button>
        </form>
    </div>
</@c.page>
