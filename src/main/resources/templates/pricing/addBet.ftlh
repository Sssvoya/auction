<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<#include "../parts/security.ftlh">
<@c.scriptPage "connect()">
    <div class="container border">
        <div class="row" style="background-color: #f8f9fa">
            <div class="col-sm-12 text-center mb-2 py-3">
                <div class="h4">
                    <@spring.message "field.LotInAu.name"/>: ${LotInAu.getName()!''}
                </div>
                <div class="h4">
                    <@spring.message "field.LotInAu.author"/>: ${LotInAu.getCreator().getUsername()!''}
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4 text-center ">
                <img src="/img/resized/300px_${LotInAu.getFilename()!''}" alt="" style="max-width: 100%">
            </div>
            <div class="col-sm-8">
                <div class="row my-3 " style="font-weight: bold">
                    <div class="col"><@spring.message "date"/></div>
                    <div class="col"><@spring.message "name"/></div>
                    <div class="col"><@spring.message "rate"/></div>
                </div>

                <div class="row my-2">
                    <div class="col" id="rateDate">${Price.getDate()?string('yyyy.MM.dd HH:mm:ss')!''}</div>
                    <div class="col" id="Username">${Price.getUser().getUsername()!''}</div>
                    <div class="col" id="rate" style="font-size: 14pt">
                        #{Price.getRate()!''}
                    </div>
                </div>

                <div class="mb-3" style="font-size: 14pt">
                    <@spring.message "form.label.timeStep"/>: <span id="interval">${LotInAu.getTimeStep()!''}</span>
                </div>
                <div class="mt-5 mb-2" style="font-size: 14pt">
                    <span id="timerText">${timerText!''}</span>
                    <span id="timer">${timerValue!''}</span>
                </div>

                <form method="post" action="/LotInAu/${LotInAu.getId()}/rate">
                    <div class="form-group row mt-5 mb-3">
                        <label for="inputrate" class="col-sm-2 col-form-label mr-2 mb-2">
                            <@spring.message "form.label.rate"/>
                        </label>
                        <div class="col col-sm-4">
                            <input type="number" class="form-control ${(rateError??)?string('is-invalid', '')}"
                                   id="inputrate"
                                   placeholder="<@spring.message "form.placeholder.rate"/>"
                                   name="rate">
                            <#if rateError??>
                                <#list rateError as rateErr>
                                    <div class="invalid-feedback">
                                        ${rateErr!''}
                                    </div>
                                </#list>
                            </#if>
                        </div>
                        <input type="hidden" name="LotInAuId" value="${LotInAu.getId()!''}">
                        <input type="hidden"  name="UserId" value="${User.id}">
                        <input type="hidden" name="date" id="hiddenDate">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-outline-secondary px-2 mx-2"
                                style="height: 2.4rem; width: 8rem"
                                onclick="hiddenDateIsNow()">
                            <@spring.message "button.addrate"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row" style="height:25px;    background-color: #f8f9fa"></div>
    </div>

</@c.scriptPage>

