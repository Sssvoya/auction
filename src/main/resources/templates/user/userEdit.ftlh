<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>

    <form method="post" action="/User">

        <div class="form-group row my-3">
            <div class="col-form-label col-sm-2">
                <@spring.message "form.label.Username"/>
            </div>
            <div class="col-sm-5 fs-4">
                ${User.getUsername()}
            </div>
        </div>
        <div class="form-group row my-3">
            <label class="col-form-label col-sm-2">
                <@spring.message "form.label.balance"/>
            </label>
            <div class="col-sm-5">
                <input type="number" class="form-control col-sm-4" value="#{User.getBalance()}"
                       min="-9999999" max="9999999" name="balance">
            </div>
        </div>
        <div class="form-group row my-3">
            <div class="btn-group-vertical col-sm-2 my-3" role="group"
                 aria-label="Basic checkbox toggle button group">
                <div class="col-sm-3 my-3">
                    <@spring.message "field.User.roles"/>
                </div>

                <#list roles as role>
                    <input type="checkbox" class="btn-check" id="target${role}" autocomplete="off"
                           name="${role}" ${User.roles?seq_contains(role)?string("checked", "")}>
                    <label class="btn btn-outline-secondary" for="target${role}">${role}</label>
                </#list>
            </div>
        </div>
        <input type="hidden" value="${User.id}" name="UserId">

        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-secondary m-3"><@spring.message "button.saveUpdates"/></button>
    </form>

</@c.page>