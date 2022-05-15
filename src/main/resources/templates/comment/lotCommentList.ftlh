<#import '../parts/common.ftlh' as c>
<#include "../parts/security.ftlh">
<#import "../parts/pager.ftlh" as p>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="card mb-3">
    <div class="row g-0">
    <div class="col-md-4">
        <img src="/img/resized/300px_${LotInAu.getFilename()!''}" class="mx-auto d-block img-fluid" alt="">
    </div>
    <div class="col-md-8">
        <div class="card-body">
            <h5 class="card-title">
                <div class="row">
                    <div class="col-sm-6">
                        <@spring.message "field.LotInAu.name"/>: ${LotInAu.getName()}
                    </div>
                    <div class="col-sm-6">
                        <@spring.message "field.LotInAu.author"/>: ${LotInAu.getCreator().getUsername()}
                    </div>
                </div>
            </h5>
            <@p.pager url 5 10 15 20 page/>
            <p class="card-text">
                <#list page.content as commentary>
            <div class="row">
                <div class="col-sm-3">
                    <p>${commentary.getDate()?string('dd.MM.yyyy HH:mm:ss')!''}</p>
                </div>
                <div class="col-sm-2">
                    <p>${commentary.getAuthor().getUsername()!''}</p>
                </div>
                <div class="col-sm-6">
                    <p class="text-break">${commentary.getText()!''}</p>
                </div>
                <div class="col-sm-1">
                    <#if isAdmin>
                        <form method="post" action="/LotInAu/${LotInAu.getId()!''}/commentary/${commentary.getId()!''}/delete">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-outline-danger btn-sm">Х</button>
                        </form>
                    <#else>
                    </#if>
                </div>
            </div>
            </#list>
            <#if isUser>
                <form method="post">
                    <div class="form-group row my-4">
                        <div class="col-sm-6">
                            <input type="text" class="form-control ${(textError??)?string('is-invalid', '')} "
                                   placeholder="<@spring.message 'form.placeholder.text'/>"
                                   id="inputText"
                                   value="<#if commentary??>${commentary.getText()!''}</#if>" aria-label="With textarea"
                                   name="text">
                            <#if textError??>
                                <#list textError as textEr>
                                    <div class="invalid-feedback">
                                        ${textEr}
                                    </div>
                                </#list>
                            </#if>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                        <div class="col-sm-3">
                            <button type="submit" class="btn btn-outline-secondary px-2 mx-2">
                                <@spring.message "button.addCommentary"/>
                            </button>
                        </div>
                    </div>
                </form>
            </#if>
            </p>
        </div>
    </div>
</@c.page>