package org.hibernate.tutorials.entity;

import javax.persistence.*;

@Entity
@Table(name = "${TableName}"<#if IDColumnName??>, uniqueConstraints = { @UniqueConstraint(columnNames = { "${IDColumnName}" }) } </#if>)
public class ${ClassName} {

<#list fieldList as field>
    private ${field.type} ${field.name};
</#list>

<#list fieldList as field>
    <#if field.primary?? && field.primary>
    @Id
    <#if field.strategyType?? && field.strategyType.ordinal() == 2>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    </#if>
    @Column(name = "${field.columnName}", nullable = ${field.nullable?string('true', 'false')}<#if field.length??>, length = ${field.length}</#if><#if field.columnType?? && field.columnType.ordinal() == 0 >, columnDefinition = "char"</#if>)
    public ${field.type} get${field.name?cap_first}() {
        return this.${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>


}