package com.surepay.controller.startupbox;

import com.surepay.businesslayer.exceptions.TransactionValidationException;
import com.surepay.controller.startupbox.interfaces.FileProcessFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@ComponentScan("com.surepay")
@EntityScan("com.surepay.datalayer")
@EnableJpaRepositories(basePackages = "com.surepay.datalayer.repository")
public class TransactionValidatorApplication implements CommandLineRunner {

    private FileProcessFlow fileProcessFlow;

    @Autowired
    public TransactionValidatorApplication(FileProcessFlow fileProcessFlow){
        this.fileProcessFlow = fileProcessFlow;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TransactionValidatorApplication.class, args);
    }

    @Override
    public void run(String ... args) throws TransactionValidationException {
        String inputTransactionRecordFilePath = "";
        if (1 == args.length) {
            inputTransactionRecordFilePath = args[0];
        }
        fileProcessFlow.processTransactionRecordFile(inputTransactionRecordFilePath);
    }

}
