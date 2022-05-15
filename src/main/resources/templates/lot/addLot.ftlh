<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <div>
        <form method="post" action="/LotInAu/add" enctype="multipart/form-data">
            <div class="form-group row my-4">
                <label for="inputName" class="col-sm-2 col-form-label mr-3">
                    <@spring.message "form.label.name"/>
                </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')} " id="inputName"
                           value="<#if LotInAu??>${LotInAu.getName()}</#if>"
                           name="name"
                           placeholder="<@spring.message 'form.placeholder.name'/>">
                    <#if nameError??>
                        <#list nameError as nErr >
                            <div class="invalid-feedback">
                                ${nErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="form-group row my-4">
                <label for="inputDescription" class="col-sm-2 col-form-label mr-3 ">
                    <@spring.message "form.label.description"/>
                </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')} "
                           id="inputDescription"
                           value="<#if LotInAu??>${LotInAu.getDescription()!''}</#if>" aria-label="With textarea"
                           name="description"
                           placeholder="<@spring.message 'form.placeholder.description'/>">
                    <#if descriptionError??>
                        <#list descriptionError as dErr>
                            <div class="invalid-feedback">
                                ${dErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="form-group row my-4">
                <label for="inputinitialRate" class="col-sm-2 col-form-label mr-3">
                    <@spring.message "form.label.initialRate"/>
                </label>
                <div class="col-sm-6">
                    <input type="number" class="form-control ${(initialRateError??)?string('is-invalid', '')} "
                           value="<#if LotInAu??>${LotInAu.getinitialRate()!''}</#if>"
                           id="inputinitialRate"
                           name="initialRate"
                           placeholder="<@spring.message 'form.placeholder.initialRate'/>">
                    <#if initialRateError??>
                        <#list initialRateError as ibErr >
                            <div class="invalid-feedback">
                                ${ibErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="form-group row my-4">
                <label for="date" class="col-sm-2 col-form-label mr-3">
                    <@spring.message "form.label.timeStart"/>
                </label>
                <div class="col-sm-6">
                    <input type="datetime-local" id="date"
                           class="form-control ${(timeStartError??)?string('is-invalid', '')}">
                    <input type="hidden" id="hiddenDate" name="timeStart">
                    <#if timeStartError??>
                        <#list timeStartError as stErr >
                            <div class="invalid-feedback">
                                ${stErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="form-group row my-4">
                <label for="inputTimeStep" class="col-sm-4 col-form-label mr-3">
                    <@spring.message "form.label.timeStep"/>
                </label>
                <div class="col-sm-4">
                    <input type="number" class="form-control  ${(timeStepError??)?string('is-invalid', '')} "
                           value="<#if LotInAu??>${LotInAu.getTimeStep()!''}</#if>"
                           name="timeStep" id="inputTimeStep"
                           placeholder="<@spring.message 'form.placeholder.timeStep'/>">
                    <#if timeStepError??>
                        <#list timeStepError as tsErr >
                            <div class="invalid-feedback">
                                ${tsErr}
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="form-group row my-4">
                <label class="col-sm-2 col-form-label ml-0">
                    <@spring.message "form.label.image"/>
                </label>
                <div class="custom-file col-sm-6">
                    <label class="custom-file-label" for="inputFile"></label>
                    <input class="form-control form-control-sm" id="formFileSm" type="file" accept=".png, .jpg, .jpeg" name="file"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" onclick="dateToUTC()" class="btn btn-primary">
                <@spring.message "button.createLotInAu"/>
            </button>
        </form>
    </div>
</@c.page>
