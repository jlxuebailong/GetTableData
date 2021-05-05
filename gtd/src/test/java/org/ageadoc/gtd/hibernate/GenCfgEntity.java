package org.ageadoc.gtd.hibernate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import junit.framework.TestCase;
import org.junit.Test;

import javax.persistence.GenerationType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenCfgEntity extends TestCase {

    private Configuration configuration = null;


    @Override
    protected void setUp() throws Exception{
        this.configuration = new Configuration(Configuration.VERSION_2_3_31);
        try {
            String templateDirectory = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\resources\\template";
            this.configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            this.configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void tearDown() throws Exception {

    }

    @Test
    public void testGenTable() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PackageName", "org.ageadoc.gtd.cfg.entity");
        String className = "CfgTable";

        map.put("ClassName", className);
        map.put("TableName","CFG_TABLE");

        List<EntityField> fieldList = new ArrayList<>();
        fieldList.add(new EntityPrimary(ColumnType.BIGINT, "id", "TBL_ID",   GenerationType.IDENTITY, null));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "tableName", "TBL_NAME", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "tableDesc", "TBL_DESC", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "packageName", "PKG_NAME", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "className", "CLS_NAME", true));

        map.put("fieldList", fieldList);

        //Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        // 模板名称
        String templateFile = "Entity.ftl";
        // 模板生成后存放目录
        String targetPath = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\java\\org\\ageadoc\\gtd\\cfg\\entity";
        // 模板生成后新文件名
        String fileName = className + ".java";
        // 创建文件夹
        if(!new File(targetPath).exists()) {
            new File(targetPath).mkdirs();
        }
        File nFile = new File(targetPath +"/"+ fileName);

        /*if (nFile.exists()) {
            throw new RuntimeException("File \'"+fileName+"\' already exists");
        }*/

        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);

            Template template = this.configuration.getTemplate(templateFile, "UTF-8");
            template.process(map, writer);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }

    @Test
    public void testGenTableColumn() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PackageName", "org.ageadoc.gtd.cfg.entity");
        String className = "CfgTableColumn";

        map.put("ClassName", className);
        map.put("TableName","CFG_TABLECOL");

        List<EntityField> fieldList = new ArrayList<>();
        fieldList.add(new EntityPrimary(ColumnType.BIGINT, "id", "COL_ID",   GenerationType.IDENTITY, null));
        fieldList.add(new EntityField(ColumnType.BIGINT, "tableId", "TBL_ID", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "columnName", "COL_NAME", true));
        fieldList.add(new EntityField(ColumnType.CHAR, 1, "isPrimary", "COL_PRIMARY", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "columnType", "COL_TYPE", true));
        fieldList.add(new EntityField(ColumnType.INT, "columnLength", "COL_LEN", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "defaultValue", "COL_DEFVAL", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "columnDesc", "COL_DESC", true));

        map.put("fieldList", fieldList);

        //Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        // 模板名称
        String templateFile = "Entity.ftl";
        // 模板生成后存放目录
        String targetPath = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\java\\org\\ageadoc\\gtd\\cfg\\entity";
        // 模板生成后新文件名
        String fileName = className + ".java";
        // 创建文件夹
        if(!new File(targetPath).exists()) {
            new File(targetPath).mkdirs();
        }
        File nFile = new File(targetPath +"/"+ fileName);

        /*if (nFile.exists()) {
            throw new RuntimeException("File \'"+fileName+"\' already exists");
        }*/

        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);

            Template template = this.configuration.getTemplate(templateFile, "UTF-8");
            template.process(map, writer);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }

    @Test
    public void testGenTableRelation() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PackageName", "org.ageadoc.gtd.cfg.entity");
        String className = "CfgTableRelation";

        map.put("ClassName", className);
        map.put("TableName","CFG_RELATION");

        List<EntityField> fieldList = new ArrayList<>();
        fieldList.add(new EntityPrimary(ColumnType.BIGINT, "id", "REL_ID",   GenerationType.IDENTITY, null));
        fieldList.add(new EntityField(ColumnType.BIGINT, "tableLeft", "REL_LEFT", true));
        fieldList.add(new EntityField(ColumnType.BIGINT, "tableRight", "REL_RIGHT", true));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "relationDesc", "REL_DESC", true));
        fieldList.add(new EntityField(ColumnType.CHAR, 1, "typeLeft", "TYPE_LEFT", true));
        fieldList.add(new EntityField(ColumnType.CHAR, 1, "typeRight", "TYPE_RIGHT", true));

        map.put("fieldList", fieldList);

        //Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        // 模板名称
        String templateFile = "Entity.ftl";
        // 模板生成后存放目录
        String targetPath = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\java\\org\\ageadoc\\gtd\\cfg\\entity";
        // 模板生成后新文件名
        String fileName = className + ".java";
        // 创建文件夹
        if(!new File(targetPath).exists()) {
            new File(targetPath).mkdirs();
        }
        File nFile = new File(targetPath +"/"+ fileName);

        /*if (nFile.exists()) {
            throw new RuntimeException("File \'"+fileName+"\' already exists");
        }*/

        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);

            Template template = this.configuration.getTemplate(templateFile, "UTF-8");
            template.process(map, writer);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }

    @Test
    public void testGenTableRelationJoin() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PackageName", "org.ageadoc.gtd.cfg.entity");
        String className = "CfgTableRelJoin";

        map.put("ClassName", className);
        map.put("TableName","CFG_RELJOIN");

        List<EntityField> fieldList = new ArrayList<>();
        fieldList.add(new EntityPrimary(ColumnType.BIGINT, "id", "JOIN_ID",   GenerationType.IDENTITY, null));
        fieldList.add(new EntityField(ColumnType.BIGINT, "relId", "REL_ID", false));
        fieldList.add(new EntityField(ColumnType.BIGINT, "columnLeft", "COL_LEFT", true));
        fieldList.add(new EntityField(ColumnType.BIGINT, "columnRight", "COL_RIGHT", true));

        map.put("fieldList", fieldList);

        //Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        // 模板名称
        String templateFile = "Entity.ftl";
        // 模板生成后存放目录
        String targetPath = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\java\\org\\ageadoc\\gtd\\cfg\\entity";
        // 模板生成后新文件名
        String fileName = className + ".java";
        // 创建文件夹
        if(!new File(targetPath).exists()) {
            new File(targetPath).mkdirs();
        }
        File nFile = new File(targetPath +"/"+ fileName);

        /*if (nFile.exists()) {
            throw new RuntimeException("File \'"+fileName+"\' already exists");
        }*/

        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);

            Template template = this.configuration.getTemplate(templateFile, "UTF-8");
            template.process(map, writer);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }


}
