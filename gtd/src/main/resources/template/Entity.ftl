package org.hibernate.tutorials.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "${TableName}"<#if IDColumnName??>, uniqueConstraints = { @UniqueConstraint(columnNames = { "${IDColumnName}" }) } </#if>)
public class ${ClassName} {

<#list fieldList as field>
    private ${field.type} ${field.name};
</#list>

<#list fieldList as field>
    @Column(name = "${field.columnName}"<#if field.length??>, length = ${field.length}</#if>, nullable = ${field.nullable?string('true', 'false')})
    public ${field.type} get${field.name?cap_first}() {
        return this.${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>


}