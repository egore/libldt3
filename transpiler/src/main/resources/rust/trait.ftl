<#import "./comments.ftl" as comments/>

<@comments.comments comments=trait.comments />
pub trait ${trait.simpleName} {
}
