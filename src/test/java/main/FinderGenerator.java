package main;

import io.ebean.typequery.generator.Generator;
import io.ebean.typequery.generator.GeneratorConfig;

import java.io.IOException;

/**
 * Created by adam on 11/17/2017.
 */
public class FinderGenerator {
    public static void main(String[] args) throws IOException {

        GeneratorConfig config = new GeneratorConfig();
        config.setClassesDirectory("./target/classes");
        config.setDestDirectory("./src/main/java");
        config.setDestResourceDirectory("./src/main/resources");

        config.setEntityBeanPackage("org.cba.model.entities");
        //config.setDestPackage("org.example.domain.query");
        //config.setMaxPathTraversalDepth(3);
//        config.setAopStyle(false);
        config.setAddFinderWherePublic(true);
        config.setOverwriteExistingFinders(true);
        //config.setLang("kt");

        Generator generator = new Generator(config);
        generator.generateFinders();
        generator.modifyEntityBeansAddFinderField();

    }
}
