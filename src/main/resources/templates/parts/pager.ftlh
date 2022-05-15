<#macro pager url size1 size2 size3 size4 page>
    <#import "/spring.ftl" as spring>
    <#if page.getTotalPages() gt 5>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 3)?then([1, -1], [1, 2])
        tail = (pageNumber < totalPages - 2)?then([-1, totalPages], [totalPages -1, totalPages])
        bodyBefore = (pageNumber > 3 && pageNumber < totalPages)?then([pageNumber - 1], [])
        bodyAfter = (pageNumber < totalPages - 2 && pageNumber > 1)?then([pageNumber +1], [])

        body = head + bodyBefore + (pageNumber > 2 && pageNumber < totalPages - 1)?then([pageNumber], []) + bodyAfter + tail
        >
    <#elseif page.getTotalPages() == 0>
        <#assign body = [1]>
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>

    <div class="my-3 container">
        <div class="row">
            <div class="col-sm-6">
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">
                            <@spring.message "pager.pageNumber"/>
                        </a>
                    </li>
                    <#list body as p>
                        <#if (p - 1) == page.getNumber()>
                            <li class="page-item active" aria-current="page">
                                <a class="page-link" href="#">${p}</a>
                            </li>
                        <#elseif p == -1>
                            <li class="page-item disabled" aria-current="page">
                                <a class="page-link" href="#">...</a>
                            </li>
                        <#else>
                            <li class="page-item" aria-current="page">
                                <a class="page-link" href="${url}?page=${p - 1}&amp;size=${page.getSize()}">${p}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
            <div class="col-sm-6">
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">
                            <@spring.message "pager.amountOfItems"/>
                        </a>
                    </li>
                    <#list [size1, size2, size3, size4] as s>
                        <#if s == page.getSize()>
                            <li class="page-item active" aria-current="page">
                                <a class="page-link" href="#">${s}</a>
                            </li>
                        <#else>
                            <li class="page-item" aria-current="page">
                                <a class="page-link" href="${url}?page=${page.getNumber()}&amp;size=${s}">${s}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</#macro>