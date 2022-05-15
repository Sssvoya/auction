<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<#import "../parts/pager.ftlh" as p>
<#import "/spring.ftl" as spring>
<@c.page>

    <@p.pager url 3 6 9 12 page />
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list page.content as LotInAu>
            <div class="col" id="LotInAuCol">
                <div class="card text-wight mb-3">
                    <div class="card-header text-center">
                        <p class="h5 mt-2">
                            <@spring.message "field.LotInAu.name"/>: ${LotInAu.getName()!''}
                        </p>
                    </div>
                    <p class="my-2" id="imgContainer">
                        <img src="img/300PX_${LotInAu.getFilename()!''}" class="card-img-top mx-auto d-block img-fluid"
                             id="LotInAuImg" alt="">
                    </p>
                    <div class="card-body p-0">
                        <div class="fw-bold mt-2 text-center">
                            <p>
                                <@spring.message "field.LotInAu.timeStart"/>
                                : ${LotInAu.gettimeStart()?string('dd.MM.yyyy HH:mm')!''}
                            </p>
                            <#if isAdmin>
                                <div class="mb-2">
                                    <a href="/LotInAu/${LotInAu.getId()}/cancel">
                                        <@spring.message "link.cancelAuction"/>
                                    </a>
                                </div>
                            </#if>
                            <#if isUser && LotInAu.isSubscriber(User)>
                                <a href="LotInAu/${LotInAu.getId()}/unsubscribe"><@spring.message "unsubscribe"/></a>
                            <#elseif isUser && !LotInAu.isSubscriber(User)>
                                <a href="LotInAu/${LotInAu.getId()}/subscribe"><@spring.message "subscribe"/></a>
                            </#if>
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn btn-outline-secondary btn-sm " type="button" data-bs-toggle="collapse"
                                    data-bs-target="#description${LotInAu.getId()}" aria-expanded="false"
                                    aria-controls="collapseExample">
                                <@spring.message "button.openDescription"/>
                            </button>
                        </div>
                        <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                            <#if isUser>
                                <a href="LotInAu/${LotInAu.getId()}/rate" id="btnGroup" role="button"
                                   class="btn btn-outline-secondary">
                                    <@spring.message "link.torates"/>
                                </a>
                            </#if>
                            <a href="LotInAu/${LotInAu.getId()}/commentary" id="btnGroup" role="button"
                               class="btn btn-outline-secondary">
                                <@spring.message "link.tocommentarys"/>
                            </a>
                        </div>
                        <div class="collapse my-2   " id="description${LotInAu.getId()}">
                            <p class="m-1">${LotInAu.getDescription()!''}</p>
                        </div>
                        <div class="card-footer text-center">
                            <p class="mt-2">
                                <@spring.message "field.LotInAu.author"/>: ${LotInAu.getCreator().getUsername()!''}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@c.page>