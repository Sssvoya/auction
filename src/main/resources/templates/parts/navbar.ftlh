<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/LotInAu">
            <@spring.message "navbar.link.title"/>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <@spring.message "navbar.dropdown.LotInAu"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <a class="dropdown-item" href="/LotInAu"><@spring.message 'link.viewLotInAus'/></a>
                        </li>
                        <#if isUser>
                            <li>
                                <a class="dropdown-item" href="/LotInAu/add"><@spring.message "link.addLotInAu"/></a>
                            </li>
                        </#if>
                    </ul>
                </li>
            </ul>
            <div class="container-fluid" style="width: 30rem">
                <form class="d-flex" action="/LotInAu" method="get">
                    <input class="form-control me-2" type="search"
                           placeholder="<@spring.message 'navbar.placeholder.nameFilter'/>"
                           aria-label="Search"
                           name="name">
                    <input class="form-control me-2" type="search"
                           placeholder="<@spring.message 'navbar.placeholder.descriptionFilter'/>"
                           aria-label="Search"
                           name="description">

                    <button class="btn btn-outline-secondary" type="submit">
                        <@spring.message 'navbar.button.search'/>
                    </button>
                </form>
            </div>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <#if known>${name!''}
                        <#else>
                            <@spring.message "navbar.profile.unknownName"/>
                        </#if>
                    </a>
                    <ul class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
                        <#if known>
                            <li><a class="dropdown-item" href="/User/profile">
                                    <@spring.message "link.myProfile"/>
                                </a></li>
                        </#if>
                        <#if isAdmin>
                            <li><a class="dropdown-item" href="/User/list">
                                    <@spring.message 'link.UserList'/>
                                </a></li>
                        </#if>
                        <#if !known>
                            <li><a class="dropdown-item" href="/login">
                                    <@spring.message "button.login"/>
                                </a></li>
                        </#if>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <#if !known>
                            <li><a class="dropdown-item" href="/registration">
                                    <@spring.message "link.registration"/>
                                </a></li>
                        </#if>
                        <#if known>
                            <li class="my-2"><#include "logout.ftlh"></li>
                        </#if>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>