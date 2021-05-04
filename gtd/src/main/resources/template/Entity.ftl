package ${PackageName};

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "${TableName}"<#if uniqueColumnName??>, uniqueConstraints = { @UniqueConstraint(columnNames = { "${uniqueColumnName}" }) } </#if>)
public class ${ClassName} {

<#list fieldList as field>
    private ${field.type} ${field.name};
</#list>

<#list fieldList as field>
    <#if field.primary?? && field.primary>
    @Id
    <#if field.strategyType?? && field.strategyType.name() == "SEQUENCE">
    @SequenceGenerator(name = "sequence-generator",  sequenceName = "${field.sequenceName}", allocationSize = 1)
    @GeneratedValue(generator = "sequence-generator", strategy=GenerationType.SEQUENCE)
    </#if>
    <#if field.strategyType?? && field.strategyType.name() == "IDENTITY">
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    </#if>
    @Column(name = "${field.columnName}", nullable = ${field.nullable?string('true', 'false')}<#if field.length??>, length = ${field.length}</#if><#if field.columnType?? && field.columnType.name() == "CHAR" >, columnDefinition = "char"</#if><#if field.columnType?? && field.columnType.name() == "NVARCHAR" >, columnDefinition = "nvarchar"</#if><#if field.columnType?? && field.columnType.name() == "IMAGE" >, columnDefinition = "image"</#if>)
    public ${field.type} get${field.name?cap_first}() {
        return this.${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>


}