<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    User = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = User.getUsername()
    isAdmin = User.isAdmin()
    isUser = User.isUser()
    >
<#else>
    <#assign
    name = "guest"
    isAdmin = false
    isUser = false
    >
</#if>
