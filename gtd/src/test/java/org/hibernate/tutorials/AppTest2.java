package org.hibernate.tutorials;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import junit.framework.TestCase;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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

public class AppTest2 extends TestCase {

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
    public void testGen1() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "String");
        map.put("name", "testName");

        List<EntityField> fieldList = new ArrayList<>();
        fieldList.add(new EntityPrimary(ColumnType.NUMBER, "idNum", "id_num",   GenerationType.IDENTITY));
        fieldList.add(new EntityField(ColumnType.VARCHAR, "fname", "fname", true));
        fieldList.add(new EntityField(ColumnType.CHAR, "minit", "minit", true));
        fieldList.add(new EntityField(ColumnType.CHAR, "nnn", "nnn", true));
        EntityField field2 = new EntityField(ColumnType.VARCHAR, "lname", "lname", false);
        field2.setLength(100);
        fieldList.add(field2);
        map.put("fieldList", fieldList);

        String className = "NewEmployeeEntity";

        map.put("TableName","new_employees");
        map.put("ClassName", className);
        map.put("IDColumnName","id_num");

        //Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        // 模板名称
        String templateFile = "Entity.ftl";
        // 模板生成后存放目录
        String targetPath = "D:\\Workspaces\\GetTableData\\gtd\\src\\main\\java\\org\\hibernate\\tutorials\\entity";
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
