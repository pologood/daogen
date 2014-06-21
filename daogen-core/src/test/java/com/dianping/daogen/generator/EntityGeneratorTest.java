package com.dianping.daogen.generator;

import com.dianping.daogen.generator.entity.Entity;
import com.dianping.daogen.generator.entity.EntityGenerator;
import com.dianping.daogen.parser.MysqlCreateTableParser;
import com.dianping.daogen.model.db.Table;
import com.dianping.daogen.model.java.Model;
import com.dianping.daogen.transfer.DefaultFieldTransfer;
import com.dianping.daogen.transfer.DefaultModelTransfer;
import org.junit.Before;
import org.junit.Test;

/**
 * @author code4crafer@gmail.com
 */
public class EntityGeneratorTest {

    private EntityGenerator entityGenerator = new EntityGenerator();

    private DefaultModelTransfer modelTransfer = new DefaultModelTransfer();

    private String sql = "CREATE TABLE `ME_Web` (\n" +
            "  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '',\n" +
            "  `Name` varchar(200) NOT NULL DEFAULT '' COMMENT '',\n" +
            "  `EnName` varchar(200) NOT NULL DEFAULT '' COMMENT '',\n" +
            "  `Status` int(11) NOT NULL DEFAULT '0' COMMENT '状态，为0表示不可用',\n" +
            "  `AddTime` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '添加时间',\n" +
            "  `UpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  PRIMARY KEY (`Id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


    @Before
    public void setUp() throws Exception {
        modelTransfer.setFieldTransfer(new DefaultFieldTransfer());
        entityGenerator.setSuffix("Entity");
        entityGenerator.setPkg("com.dianping.entity");
    }

    @Test
    public void testGenerate() throws Exception {
        Table table = new MysqlCreateTableParser().parse(sql);
        Model model = modelTransfer.transfer(table);
        GeneratorContext generatorContext = new GeneratorContext();
        generatorContext.setModel(model);
        generatorContext.setTable(table);
        Entity entity = entityGenerator.generate(generatorContext);
        System.out.println(entity);
    }
}
