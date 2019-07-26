/**
 * SinthomaBffApplication.java
 * sinthoma-bff
 * Copyright 2019 Shishir Kumar
 * 
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete 
 * source code of licensed works and modifications under the same license 
 * or the GNU GPLv3. Copyright and license notices must be preserved. 
 * 
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code for 
 * the larger work.
 */
package cok.sk.sinthoma.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cok.sk.sinthoma.bff", "cok.sk.sinthoma.bff.user"})
public class SinthomaBffApplication {

    public static void main(String[] args) {
	SpringApplication.run(SinthomaBffApplication.class, args);
    }

}
