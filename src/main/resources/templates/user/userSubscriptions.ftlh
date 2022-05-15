<#import "../parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<#import "../parts/pager.ftlh" as p>
<@c.page>
    <div class="my-3">
        <a href="/User/profile"><@spring.message "link.myProfile"/></a>
    </div>
    <div class="row my-3">
        <h3><@spring.message "subscriptions"/></h3>
    </div>
    <@p.pager url 3 6 9 12 page/>
    <div class="my-3">
        <#list page.content as subscription>
            <div class="row mt-3">
                <div class="col-sm-3"><@spring.message "field.LotInAu.name"/>: ${subscription.getName()}</div>
                <div class="col-sm-3">
                    <@spring.message "field.LotInAu.timeStart"/>
                    : ${subscription.gettimeStart()?string('dd.MM.yyyy HH:mm')!''}
                </div>
            </div>

            <div class="row my-1">
                <div class="col-sm-3">
                    <a href="/LotInAu/${subscription.getId()}/rate"
                       class="btn btn-sm btn-secondary">
                        <@spring.message "link.torates"/>
                    </a>
                </div>
                <div class="col-sm-3">
                    <a href="/LotInAu/${subscription.getId()}/commentary"
                       class="btn btn-sm btn-secondary">
                        <@spring.message "link.tocommentarys"/>
                    </a>
                </div>
                <div class="col-sm-3">
                    <a href="/User/profile/subscriptions/${subscription.getId()}/unsubscribe"
                       class="btn btn-sm btn-outline-danger">
                        <@spring.message "unsubscribe"/>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</@c.page>